package com.thursdaygroup.api.entities.ent1;
import java.time.LocalDate;

public record Ent1ReadDTO(Long id,
                          String publicInfo,
                          String noUpdatableInfo,
                          String email,
                          LocalDate date) {

public Ent1ReadDTO(Ent1 ent1){
    this(ent1.getId(), ent1.getPublicInfo(), ent1.getNoUpdatableInfo(),
            ent1.getEmail(), ent1.getDate());
}
}
