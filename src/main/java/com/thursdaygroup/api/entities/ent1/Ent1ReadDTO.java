package com.thursdaygroup.api.entities.ent1;
import com.thursdaygroup.api.entities.ent2.Ent2DTO;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public record Ent1ReadDTO(Long id, String publicInfo, String noUpdatableInfo, String email,
   LocalDate date, List<Ent2DTO> ent2DTOList){

public Ent1ReadDTO(Ent1 ent1) {
    this(ent1.getId(), ent1.getPublicInfo(), ent1.getNoUpdatableInfo(),
        ent1.getEmail(), ent1.getDate(),

        //Aquí comienza un if ternario, necesario para settear la relación ManyToMany (y que no estalle cuando
            //se crea una Ent1 sin settearle una relación a Ent2) Se lee como sigue:

        ent1.getEnt2List() != null? //Si en ent2List de Ent1 es distinto de nulo... hacés esto...
        ent1.getEnt2List().stream().map(e->{
        var ent2DTO = new Ent2DTO();
        ent2DTO.setId(e.getId());
        return ent2DTO;
    }).collect(Collectors.toList())
            :null //Y si ent2List de Ent1 es nulo... hacés esto otro.
            );

    //El pedazo en el medio del if ternario es una lambda (las lambdas también son amigas, jajaja).
    //Acá toma cada elemento de la lista Ent2List y genera un ent2DTO nuevo por c/u seteándole el id que le corresponde.
    //De este modo se settea la relación entre ent1 y ent2 mostrando lista de objetos ent2DTO en el Ent1ReadDTO.

    //Nota... sólo puse en la lambda el id (el dto va a traer sólo id). Se podrían pasar también los otros valores.
    //El único cuidado a tener es al pasarle la relación con Ent1, porque ahí es donde armé una redundancia cíclica
    //Al estar usando el manyToMany en cascada. :D :D
}
}
