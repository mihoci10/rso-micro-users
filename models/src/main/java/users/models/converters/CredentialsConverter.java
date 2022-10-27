package users.models.converters;

import users.lib.UserDetails;
import users.models.entities.CredentialsEntity;
import users.models.entities.UserEntity;

public class CredentialsConverter {

    public static UserDetails toDto(UserDetails dto, CredentialsEntity entity){
        dto.setUsername(entity.getUsername());
        return dto;
    }

    public static CredentialsEntity toEntity(UserDetails dto) {
        CredentialsEntity entity = new CredentialsEntity();

        entity.setUsername(dto.getUsername());
        entity.setPassword(dto.getPassword());

        return entity;
    }
}
