package com.thursdaygroup.api.entities.ent3;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

//En este DTO se pide el id de Ent1 para luego settear la relación entre varios Ent3 y un solo Ent1.
public record Ent3CreateDTO (@NotNull String publicInfo, @NotNull String secretInfo,
                            @NotNull String noUpdatableInfo, @NotNull @Email String email,
                             @NotNull Long Ent1Id){

}
