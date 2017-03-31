package ch.hesge.capitao.techmarket.domaine;

import java.util.Objects;

/**
 *
 * @author jonathan.capitao
 */
public class TM_Manager {
    
    private String username;
    private String password;
    
    public TM_Manager(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    // On vérifie le manager (pour la connexion entre autre)
    // Nom d'utilisateur et mot de passe uniquement sont vérifier
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final TM_Manager other = (TM_Manager) obj;
        if (!Objects.equals(this.username, other.username)) {
            return false;
        }
        if (!Objects.equals(this.password, other.password)) {
            return false;
        }
        return true;
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
}
