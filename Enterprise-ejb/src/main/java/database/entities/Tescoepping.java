/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author James
 */
@Entity
@Table(name = "tescoepping")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tescoepping.findAll", query = "SELECT t FROM Tescoepping t")
    , @NamedQuery(name = "Tescoepping.findByTescoId", query = "SELECT t FROM Tescoepping t WHERE t.tescoId = :tescoId")
    , @NamedQuery(name = "Tescoepping.findByTescoName", query = "SELECT t FROM Tescoepping t WHERE t.tescoName = :tescoName")
    , @NamedQuery(name = "Tescoepping.findByTescoBranch", query = "SELECT t FROM Tescoepping t WHERE t.tescoBranch = :tescoBranch")
    , @NamedQuery(name = "Tescoepping.findByTescoLoc", query = "SELECT t FROM Tescoepping t WHERE t.tescoLoc = :tescoLoc")})
public class Tescoepping implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "tescoId")
    private String tescoId;
    @Size(max = 45)
    @Column(name = "tescoName")
    private String tescoName;
    @Size(max = 45)
    @Column(name = "tescoBranch")
    private String tescoBranch;
    @Size(max = 45)
    @Column(name = "tescoLoc")
    private String tescoLoc;

    public Tescoepping() {
    }

    public Tescoepping(String tescoId) {
        this.tescoId = tescoId;
    }

    public String getTescoId() {
        return tescoId;
    }

    public void setTescoId(String tescoId) {
        this.tescoId = tescoId;
    }

    public String getTescoName() {
        return tescoName;
    }

    public void setTescoName(String tescoName) {
        this.tescoName = tescoName;
    }

    public String getTescoBranch() {
        return tescoBranch;
    }

    public void setTescoBranch(String tescoBranch) {
        this.tescoBranch = tescoBranch;
    }

    public String getTescoLoc() {
        return tescoLoc;
    }

    public void setTescoLoc(String tescoLoc) {
        this.tescoLoc = tescoLoc;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tescoId != null ? tescoId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tescoepping)) {
            return false;
        }
        Tescoepping other = (Tescoepping) object;
        if ((this.tescoId == null && other.tescoId != null) || (this.tescoId != null && !this.tescoId.equals(other.tescoId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.Tescoepping[ tescoId=" + tescoId + " ]";
    }
    
}
