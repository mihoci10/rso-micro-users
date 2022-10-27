package users.models.entities;

import users.lib.UserDetails;

import javax.persistence.*;

@Entity
@Table(name = "credentials")
public class CredentialsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    public String getUsername() { return username; }

    public void setUsername(String username) { this.username = username; }

    public Integer getId() { return id; }

    public void setId(Integer id) { this.id = id; }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
