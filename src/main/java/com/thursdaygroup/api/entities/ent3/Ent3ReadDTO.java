package com.thursdaygroup.api.entities.ent3;
import java.time.LocalDate;

public record Ent3ReadDTO(Long id,
                                 String publicInfo,
                                 String noUpdatableInfo,
                                 String email,
                                 LocalDate date) {

    public Ent3ReadDTO(Ent3 ent3){
        this(ent3.getId(), ent3.getPublicInfo(), ent3.getNoUpdatableInfo(), ent3.getEmail(), ent3.getDate());
    }
}