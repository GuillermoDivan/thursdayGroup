package com.thursdaygroup.api.mappers;
import com.thursdaygroup.api.entities.ent2.*;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class Ent2Mapper {

    private final ModelMapper modelMapper;

    public Ent2DTO convertEnt2ToDTO(Ent2 ent2){
        Ent2DTO ent2DTO = modelMapper.map(ent2, Ent2DTO.class);
        return ent2DTO;
    }

    public Ent2 convertDTOToEnt2(Ent2DTO ent2DTO){
        Ent2 ent2 = modelMapper.map(ent2DTO, Ent2.class);
        return ent2;
    }

    //DISCLAIMER: EL SIGUIENTE MÉTODO ES SÓLO PARA LA MANYTOMANY.
    public Ent2DTO convertEnt2ToDTOWhitoutCascade(Ent2 ent2){
        Ent2DTO ent2DTO = modelMapper.map(ent2, Ent2DTO.class);
        //Mappea tod-o a DTO normalmente, pero si se envía la relación a Ent1 se genera
        //La cascada cíclica entre las entidades, entonces...
        ent2DTO.getEnt1List().forEach(e->e.setEnt2List(null));
        //Al hacer la conversión, este lambda settea en nulo todos los valores.
        return ent2DTO;
    }
    }