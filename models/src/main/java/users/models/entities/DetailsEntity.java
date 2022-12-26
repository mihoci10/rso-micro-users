package users.models.entities;

import users.lib.UserDetails;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "details")
public class DetailsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Lob
    @Column(name = "name")
    private String name;

    @Lob
    @Column(name = "surname")
    private String surname;

    @Lob
    @Column(name = "email")
    private String email;

    @Column(name = "\"createdAt\"", columnDefinition = "timestamp without time zone default NOW()")
    private Instant createdAt;

    @Column(name = "gender")
    @Enumerated(EnumType.STRING)
    private UserDetails.Gender gender;

    public UserDetails.Gender getGender() {
        return gender;
    }

    public void setGender(UserDetails.Gender gender) {
        this.gender = gender;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}