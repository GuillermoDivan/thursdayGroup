package com.thursdaygroup.api.entities.ent2;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ent2DTO {
    private Long id;
    private String publicInfo;
    private String noUpdateableInfo;
    private String email;
    private LocalDateTime date;
}

// Si se recurre a mapper, el DTO simplemente es una copia de la entidad con los atributos
// que se quiere pedir/mostrar.
// Para poner en práctica el modelo de UN SOLO DTO, debe definirse si se prefiere
// (opción 1): pedir tooodos los datos para Create (y pasarlos todos en Read, cosa
// seguramente no recomendable con info sensible...) u
// (opción 2): aplicar el DTO para Mostrar la info que se desea mostrar (Read),
// entonces pedir los atributos para Create/Update sin que sea a través del DTO.
// Se optó por la segunda forma priorizando seguridad.

