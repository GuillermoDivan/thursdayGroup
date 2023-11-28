package com.thursdaygroup.api.services.Ent2;
import com.thursdaygroup.api.entities.ent2.Ent2;
import com.thursdaygroup.api.entities.ent2.*;
import com.thursdaygroup.api.mappers.Ent2Mapper;
import com.thursdaygroup.api.repositories.Ent2Repository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class Ent2Service {

    private final Ent2Repository ent2Repository;
    private final Ent2Mapper ent2Mapper;

    public Ent2DTO save(Ent2DTO ent2DTO) throws ValidationException{
        if (ent2DTO.getPublicInfo() == null) { throw new ValidationException("publicInfo no debe ser nula"); }
        if (ent2DTO.getSecretInfo() == null) { throw new ValidationException("secretInfo no debe ser nula"); }
        if (ent2DTO.getNoUpdatableInfo()  == null) { throw new ValidationException("noUpdatableInfo no debe ser nula"); }
        if (ent2DTO.getEmail() == null) { throw new ValidationException("email no debe ser nulo"); }
        //Podrían implementarse acá otras validaciones (ej: que email sea email, largo caracteres, etc.
        //Sin anotaciones debe hacerse de modo manual en los ifs... pero bueno, eviten esto y usen anotaciones en DTO
        // o parámetro del controller. Las anotaciones son sus amigas, jajaja.
        Ent2 ent2 = ent2Mapper.convertDTOToEnt2(ent2DTO);
        ent2.setDate(LocalDate.now()); //Por alguna razón no se pudo settear en constructor...
        this.ent2Repository.save(ent2);
        return ent2Mapper.convertEnt2ToDTO(ent2);
    }

    public Ent2DTO findById(Long id) throws EntityNotFoundException{
        Ent2 ent2 = this.ent2Repository.findById(id).orElseThrow(EntityNotFoundException::new);
        return ent2Mapper.convertEnt2ToDTO(ent2);
    }

    public Ent2DTO findByEmail(boolean active, String email) throws EntityNotFoundException{
        Ent2 ent2 = this.ent2Repository.findByEmailAndActive(email, active).orElseThrow(EntityNotFoundException::new);
        return ent2Mapper.convertEnt2ToDTO(ent2);
    }

    public List<Ent2DTO> findAll(boolean active) {
        return this.ent2Repository.findAllByActive(active).stream().map(ent2Mapper::convertEnt2ToDTO).collect(Collectors.toList());
        //Llama al repositorio para la query de buscar todos por activo. Para mapear en listas requiere primero de stream, luego mapear
        //Y por último aclararle que se lo debe pasar a Lista.
    }

    public List<Ent2DTO> findAllByDate(boolean active, String date) {
        LocalDate date1 = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        return this.ent2Repository.findAllByDateAndActive(date1, active).stream().map(ent2Mapper::convertEnt2ToDTO).collect(Collectors.toList());
    }

    public List<Ent2DTO> findAllBetweenDates(boolean active, String date1, String date2) {
        LocalDate dateBefore = LocalDate.parse(date1, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        LocalDate dateAfter = LocalDate.parse(date2, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        return this.ent2Repository.findAllBetweenDatesAndActive(dateBefore, dateAfter, active).stream().map(ent2Mapper::convertEnt2ToDTO).collect(Collectors.toList());
    }

    public List<Ent2DTO> findAllByEnt1Id(boolean active, Long ent2Id) {
        return this.ent2Repository.findAllByEnt1IdAndActive(ent2Id, active).stream().map(ent2Mapper::convertEnt2ToDTO).collect(Collectors.toList());
    }

    public Ent2DTO update(Ent2DTO ent2DTO) throws EntityNotFoundException {
        Ent2 ent2 = this.ent2Repository.findById(ent2DTO.getId()).orElseThrow(EntityNotFoundException::new);
        if (ent2.isActive()){
            if (ent2DTO.getPublicInfo() != null) { ent2.setPublicInfo(ent2DTO.getPublicInfo());}
            if (ent2DTO.getSecretInfo()  != null) { ent2.setSecretInfo(ent2DTO.getSecretInfo());}
            if (ent2DTO.getEmail() != null) { ent2.setEmail(ent2DTO.getEmail());}
            //Como esta entidad sólo tiene un DTO, es necesario pedir la id y otros datos por parámetro (y url)...
            //O utilizar las anotaciones @JsonProperty para Write_only, para evitar mostrar.
            //También hay que corroborar en este método que no todos los datos que son posibles
            // de ingresar (fecha, noUpdatable) sean modificados. Un perno (?) Usen varios dto, jajajajaja.
        }
        this.ent2Repository.save(ent2);
        return ent2Mapper.convertEnt2ToDTO(ent2);
    }

    public boolean hide(Long id) {
        Ent2 ent2 = this.ent2Repository.findById(id).orElseThrow(EntityNotFoundException::new);
        if (ent2.isActive()) {
            ent2.setActive(false);
        } return true;
    }

    public boolean reactive(Long id) {
        Ent2 ent2 = this.ent2Repository.findById(id).orElseThrow(EntityNotFoundException::new);
        if (!ent2.isActive()) {
            ent2.setActive(true);
        } return true;
    }

    public boolean delete(Long id) {
        Ent2 ent2 = this.ent2Repository.findById(id).orElseThrow(EntityNotFoundException::new);
        this.ent2Repository.delete(ent2);
        return true;
    }
}