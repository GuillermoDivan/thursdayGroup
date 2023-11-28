package com.thursdaygroup.api.entities.ent2;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.thursdaygroup.api.entities.ent1.Ent1;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ent2DTO {
    private Long id;
    private String publicInfo;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    //Anotación necesaria para que el DTO no la muestre a la hora de devolver info.
    private String secretInfo;
    private String noUpdatableInfo;
    private String email;
    private LocalDate date;
    private List<Ent1> ent1List;

}

// Si se recurre a mapper, el DTO simplemente es una copia de la entidad con los atributos
// que se quiere pedir/mostrar.
// Para poner en práctica el modelo de UN SOLO DTO, debe definirse si se prefiere
// (opción 1): pedir tooodos los datos para Create (y pasarlos todos en Read, cosa
// seguramente no recomendable con info sensible...) u
// (opción 2): aplicar el DTO para Mostrar la info que se desea mostrar (Read),
// entonces pedir los atributos para Create/Update sin que sea a través del DTO.
// Se optó por la segunda forma priorizando seguridad.

