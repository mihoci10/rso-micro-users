package users.models.converters;

import users.lib.UserDetails;
import users.models.entities.CredentialsEntity;
import users.models.entities.DetailsEntity;
import users.models.entities.UserEntity;

public class UserConverter {

    public static UserDetails toDto(UserEntity entity){
        UserDetails dto = new UserDetails();

        dto.setUserId(entity.getId());
        dto = CredentialsConverter.toDto(dto, entity.getCred());
        dto = DetailsConverter.toDto(dto, entity.getDetails());

        return dto;
    }

    public static UserEntity toEntity(UserDetails dto){
        UserEntity entity = new UserEntity();

        entity.setId(dto.getUserId());
        entity.setCred(CredentialsConverter.toEntity(dto));
        entity.setDetails(DetailsConverter.toEntity(dto));

        return entity;
    }

}
