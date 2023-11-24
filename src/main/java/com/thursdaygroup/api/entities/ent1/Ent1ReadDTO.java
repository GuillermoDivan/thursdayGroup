package com.thursdaygroup.api.entities.ent1;
import com.thursdaygroup.api.entities.ent2.Ent2ReadDTO;
import com.thursdaygroup.api.entities.ent3.Ent3ReadDTO;
import java.time.LocalDateTime;
import java.util.List;

public record Ent1ReadDTO(Long id,
                          String publicInfo,
                          String noUpdateableInfo,
                          String email,
                          LocalDateTime date) {

public Ent1ReadDTO(Ent1 ent1){
    this(ent1.getId(), ent1.getPublicInfo(), ent1.getNoUpdateableInfo(),
            ent1.getEmail(), ent1.getDate());
}
}
