package users.models.entities;

import javax.persistence.*;

@Entity
@Table(name = "users")
@NamedQueries(value =
{
@NamedQuery(name = "UserEntity.getAll",
        query = "SELECT u FROM UserEntity u"),

@NamedQuery(name = "UserEntity.login",
        query = "SELECT u FROM UserEntity u WHERE u.cred.username = :user AND u.cred.password = :pass")
})
public class UserEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "\"credId\"", nullable = false)
    private CredentialsEntity cred;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "\"detailsId\"", nullable = false)
    private DetailsEntity details;

    public DetailsEntity getDetails() {
        return details;
    }

    public void setDetails(DetailsEntity details) {
        this.details = details;
    }

    public CredentialsEntity getCred() {
        return cred;
    }

    public void setCred(CredentialsEntity cred) {
        this.cred = cred;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
