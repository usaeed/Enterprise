/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database.controllers;

import database.controllers.exceptions.NonexistentEntityException;
import database.controllers.exceptions.PreexistingEntityException;
import database.controllers.exceptions.RollbackFailureException;
import database.entities.Tescoepping;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.UserTransaction;

/**
 *
 * @author James
 */
public class TescoeppingJpaController implements Serializable {

    public TescoeppingJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Tescoepping tescoepping) throws PreexistingEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            em.persist(tescoepping);
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findTescoepping(tescoepping.getTescoId()) != null) {
                throw new PreexistingEntityException("Tescoepping " + tescoepping + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Tescoepping tescoepping) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            tescoepping = em.merge(tescoepping);
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = tescoepping.getTescoId();
                if (findTescoepping(id) == null) {
                    throw new NonexistentEntityException("The tescoepping with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(String id) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Tescoepping tescoepping;
            try {
                tescoepping = em.getReference(Tescoepping.class, id);
                tescoepping.getTescoId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tescoepping with id " + id + " no longer exists.", enfe);
            }
            em.remove(tescoepping);
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Tescoepping> findTescoeppingEntities() {
        return findTescoeppingEntities(true, -1, -1);
    }

    public List<Tescoepping> findTescoeppingEntities(int maxResults, int firstResult) {
        return findTescoeppingEntities(false, maxResults, firstResult);
    }

    private List<Tescoepping> findTescoeppingEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Tescoepping.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Tescoepping findTescoepping(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Tescoepping.class, id);
        } finally {
            em.close();
        }
    }

    public int getTescoeppingCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Tescoepping> rt = cq.from(Tescoepping.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
