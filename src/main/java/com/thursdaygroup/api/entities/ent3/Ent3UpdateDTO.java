package com.thursdaygroup.api.entities.ent3;
import jakarta.validation.constraints.NotNull;

//En este DTO se pide el id de Ent1 para luego settear la relación entre varios Ent3 y un solo Ent1.
public record Ent3UpdateDTO(@NotNull Long id, String publicInfo,
                            String secretInfo, String email, Long Ent1Id) {
}

