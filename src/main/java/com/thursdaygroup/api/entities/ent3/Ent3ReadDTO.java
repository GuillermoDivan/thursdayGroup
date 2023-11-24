package com.thursdaygroup.api.entities.ent3;
import java.time.LocalDateTime;

public record Ent3ReadDTO(Long id,
                                 String publicInfo,
                                 String noUpdateableInfo,
                                 String email,
                                 LocalDateTime date) {

    public Ent3ReadDTO(Ent3 ent3){
        this(ent3.getId(), ent3.getPublicInfo(), ent3.getNoUpdateableInfo(), ent3.getEmail(), ent3.getDate());
    }
}