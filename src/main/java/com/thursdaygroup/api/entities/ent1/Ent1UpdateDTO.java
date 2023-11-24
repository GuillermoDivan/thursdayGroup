package com.thursdaygroup.api.entities.ent1;
import jakarta.validation.constraints.NotNull;

public record Ent1UpdateDTO(@NotNull Long id, String publicInfo,
                            String secretInfo, String email) {
}
