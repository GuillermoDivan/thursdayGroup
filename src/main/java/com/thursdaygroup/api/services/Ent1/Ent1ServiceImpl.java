package com.thursdaygroup.api.services.Ent1;
import com.thursdaygroup.api.entities.ent1.*;
import com.thursdaygroup.api.entities.ent2.Ent2;
import com.thursdaygroup.api.repositories.Ent1Repository;
import com.thursdaygroup.api.repositories.Ent2Repository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class Ent1ServiceImpl implements Ent1Service{

    public final Ent1Repository ent1Repository;
    public final Ent2Repository ent2Repository; //Necesité inyectar este repository por la relación ManyToMany.


    @Override
    public Ent1ReadDTO save(Ent1CreateDTO ent1CreateDTO) {
        Ent1 ent1 = new Ent1(ent1CreateDTO);
        this.ent1Repository.save(ent1);
        return new Ent1ReadDTO(ent1);
    }

    @Override
    public Ent1ReadDTO relateEnt1ToEnt2(Long id1, Long id2) throws EntityNotFoundException {
        Ent1 ent1 = this.ent1Repository.findById(id1).orElseThrow(EntityNotFoundException::new);
        Ent2 ent2 = this.ent2Repository.findById(id2).orElseThrow(EntityNotFoundException::new);
        ent1.getEnt2List().add(ent2);
        //La relación es de los dos lados, al settear a una, se le settea a la otra.
        //Repetiré el método en Ent2 simplemente por si se quiere probar hacer la relación desde la otra entidad.
        this.ent1Repository.save(ent1);
        return new Ent1ReadDTO(ent1);
    }

    @Override
    public Ent1ReadDTO findById(Long id) throws EntityNotFoundException{
        Ent1 ent1 = this.ent1Repository.findById(id).orElseThrow(EntityNotFoundException::new);
        return new Ent1ReadDTO(ent1);
    }

    @Override
    public Ent1ReadDTO findByEmail(boolean active, String email) throws EntityNotFoundException{
        Ent1 ent1 = this.ent1Repository.findByEmailAndActive(email, active).orElseThrow(EntityNotFoundException::new);
        return new Ent1ReadDTO(ent1);
    }

    @Override
    public Page<Ent1ReadDTO> findAll(boolean active, Pageable paging) {
        return this.ent1Repository.findAllByActive(active, paging).map(Ent1ReadDTO::new);
    }

    @Override
    public Page<Ent1ReadDTO> findAllByDate(boolean active, LocalDateTime date, Pageable paging) {
        return this.ent1Repository.findAllByDateAndActive(date, active, paging).map(Ent1ReadDTO::new);
    }

    @Override
    public Page<Ent1ReadDTO> findAllBetweenDates(boolean active, LocalDateTime date1, LocalDateTime date2, Pageable paging) {
        return this.ent1Repository.findAllBetweenDatesAndActive(date1, date2, active, paging).map(Ent1ReadDTO::new);
    }

    @Override
    public Page<Ent1ReadDTO> findAllByEnt2Id(boolean active, Long ent2Id, Pageable paging) {
        return this.ent1Repository.findAllByEnt2IdAndActive(ent2Id, active, paging).map(Ent1ReadDTO::new);
    }

    @Override
    public Page<Ent1ReadDTO> findAllByEnt3Id(boolean active, Long ent3Id, Pageable paging) {
        return this.ent1Repository.findAllByEnt3IdAndActive(ent3Id, active, paging).map(Ent1ReadDTO::new);
    }

    @Override
    public Ent1ReadDTO update(Ent1UpdateDTO ent1UpdateDTO) throws EntityNotFoundException {
        Ent1 ent1 = this.ent1Repository.findById(ent1UpdateDTO.id()).orElseThrow(EntityNotFoundException::new);
        if (ent1.isActive()){
            if (ent1UpdateDTO.publicInfo() != null) { ent1.setPublicInfo(ent1UpdateDTO.publicInfo());}
            if (ent1UpdateDTO.secretInfo() != null) { ent1.setSecretInfo(ent1UpdateDTO.secretInfo());}
            if (ent1UpdateDTO.email() != null) { ent1.setEmail(ent1UpdateDTO.email());}
        }
        this.ent1Repository.save(ent1);
        return new Ent1ReadDTO(ent1);
    }

    @Override
    public boolean hide(Long id) {
        Ent1 ent1 = this.ent1Repository.findById(id).orElseThrow(EntityNotFoundException::new);
        if (ent1.isActive()) {
            ent1.setActive(false);
        } return true;
    }

    @Override
    public boolean reactive(Long id) {
        Ent1 ent1 = this.ent1Repository.findById(id).orElseThrow(EntityNotFoundException::new);
        if (!ent1.isActive()) {
            ent1.setActive(true);
        } return true;
    }

    @Override
    public boolean delete(Long id) {
        Ent1 ent1 = this.ent1Repository.findById(id).orElseThrow(EntityNotFoundException::new);
        this.ent1Repository.delete(ent1);
        return true;
    }
}
