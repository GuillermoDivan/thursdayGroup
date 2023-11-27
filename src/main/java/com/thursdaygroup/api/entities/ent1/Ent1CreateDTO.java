package com.thursdaygroup.api.entities.ent1;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;


public record Ent1CreateDTO(@NotNull String publicInfo, @NotNull String secretInfo,
                            @NotNull String noUpdatableInfo, @NotNull @Email String email) {
}
