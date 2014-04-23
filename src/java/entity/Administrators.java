/**
 * Brian Greene - 11042141 Eoghan Griffin - 10091157 Bartosz Kaminiecki -
 * 11060204
 */

package entity;

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
 * @author Admin
 */
@Entity
@Table(name = "ADMINISTRATORS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Administrators.findAll", query = "SELECT a FROM Administrators a"),
    @NamedQuery(name = "Administrators.findByUsername", query = "SELECT a FROM Administrators a WHERE a.username = :username"),
    @NamedQuery(name = "Administrators.findByPassword", query = "SELECT a FROM Administrators a WHERE a.password = :password")})
public class Administrators implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "USERNAME")
    private String username;
    @Size(max = 50)
    @Column(name = "PASSWORD")
    private String password;

    public Administrators() {
    }

    public Administrators(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (username != null ? username.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Administrators)) {
            return false;
        }
        Administrators other = (Administrators) object;
        if ((this.username == null && other.username != null) || (this.username != null && !this.username.equals(other.username))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Administrators[ username=" + username + " ]";
    }
    
}
