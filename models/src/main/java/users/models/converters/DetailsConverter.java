package users.models.converters;

import users.lib.UserDetails;
import users.models.entities.CredentialsEntity;
import users.models.entities.DetailsEntity;

public class DetailsConverter {

    public static UserDetails toDto(UserDetails dto, DetailsEntity entity) {
        dto.setName(entity.getName());
        dto.setSurname(entity.getSurname());
        dto.setEmail(entity.getEmail());
        dto.setCreatedAt(entity.getCreatedAt());
        dto.setGender(entity.getGender());

        return dto;
    }

    public static DetailsEntity toEntity(UserDetails dto) {
        DetailsEntity entity = new DetailsEntity();

        entity.setName(dto.getName());
        entity.setSurname(dto.getSurname());
        entity.setEmail(dto.getEmail());
        entity.setCreatedAt(dto.getCreatedAt());
        entity.setGender(dto.getGender());

        return entity;
    }
}
