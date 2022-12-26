package users.services.beans;

import users.lib.UserDetails;
import users.models.converters.UserConverter;
import users.models.entities.CredentialsEntity;
import users.models.entities.DetailsEntity;
import users.models.entities.UserEntity;
import users.payload.LoginPayload;
import users.payload.RegisterPayload;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.time.Instant;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Model
@RequestScoped
public class UsersBean {

    private Logger log = Logger.getLogger(UsersBean.class.getName());

    @Inject
    private EntityManager em;

    public List<UserDetails> getAllUsers(){
        List<UserEntity> resultList = null;
        try{
            TypedQuery<UserEntity> query = em.createNamedQuery("UserEntity.getAll", UserEntity.class);

            resultList = query.getResultList();

        }catch (Exception e){
            System.out.println(e);
        }

        return resultList.stream().map(UserConverter::toDto).collect(Collectors.toList());
    }

    public void registerUser(RegisterPayload registerPayload){

        try {
            beginTx();

            UserEntity userEntity = new UserEntity();
            DetailsEntity detailsEntity = new DetailsEntity();
            CredentialsEntity credEntity = new CredentialsEntity();

            detailsEntity.setName(registerPayload.getName());
            detailsEntity.setSurname(registerPayload.getSurname());
            detailsEntity.setEmail(registerPayload.getEmail());
            detailsEntity.setGender(UserDetails.Gender.valueOf(registerPayload.getGender()));
            detailsEntity.setCreatedAt(Instant.now());

            credEntity.setUsername(registerPayload.getUsername());
            credEntity.setPassword(registerPayload.getPassword());

            userEntity.setDetails(detailsEntity);
            userEntity.setCred(credEntity);

            em.persist(userEntity);
            em.persist(detailsEntity);
            em.persist(credEntity);

            commitTx();

        }catch (Exception e) {
            rollbackTx();

            throw e;
        }

    }

    public UserDetails loginUser(LoginPayload loginPayload){

        List<UserEntity> resultList = null;
        try{
            TypedQuery<UserEntity> query = em.createNamedQuery("UserEntity.login", UserEntity.class)
                    .setParameter("user", loginPayload.getUsername())
                    .setParameter("pass", loginPayload.getPassword());

            resultList = query.getResultList();

        }catch (Exception e){
            System.out.println(e);
        }

        if (resultList.size() == 1)
            return UserConverter.toDto(resultList.get(0));

        return null;
    }

    private void beginTx() {
        if (!em.getTransaction().isActive()) {
            em.getTransaction().begin();
        }
    }

    private void commitTx() {
        if (em.getTransaction().isActive()) {
            em.getTransaction().commit();
        }
    }

    private void rollbackTx() {
        if (em.getTransaction().isActive()) {
            em.getTransaction().rollback();
        }
    }
}
