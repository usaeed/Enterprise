/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import database.entities.Tescoepping;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author James
 */
@Stateless
public class TescoeppingFacade extends AbstractFacade<Tescoepping> {

    @PersistenceContext(unitName = "com.mycompany_Enterprise-web_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TescoeppingFacade() {
        super(Tescoepping.class);
    }
    
}
