package com.thursdaygroup.api.entities.ent1;

import com.thursdaygroup.api.entities.ent3.Ent3CreateDTO;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record Ent1CreateDTO(@NotNull String publicInfo, @NotNull String secretInfo,
                            @NotNull String noUpdatableInfo, @NotNull @Email String email) {
}
