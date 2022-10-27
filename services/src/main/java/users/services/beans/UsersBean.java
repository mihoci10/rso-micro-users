package users.services.beans;

import users.lib.UserDetails;
import users.models.converters.UserConverter;
import users.models.entities.UserEntity;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.io.Console;
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
}
