package com.metaphorce.rrhh.converters;

public abstract class AbstractConverters<Entity, DTO> {
    
    public abstract Entity fromDTO(DTO dto);
    public abstract DTO fromEntity(Entity entity);
}
