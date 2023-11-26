package com.thursdaygroup.api.services.Ent3;
import com.thursdaygroup.api.entities.ent3.*;
import com.thursdaygroup.api.repositories.Ent3Repository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class Ent3ServiceImpl implements Ent3Service {

    public final Ent3Repository ent3Repository;

    @Override
    public Ent3ReadDTO save(Ent3CreateDTO ent3CreateDTO) {
        Ent3 ent3 = new Ent3(ent3CreateDTO);
        this.ent3Repository.save(ent3);
        return new Ent3ReadDTO(ent3);
    }

    @Override
    public Ent3ReadDTO findById(Long id) throws EntityNotFoundException {
        Ent3 ent3 = this.ent3Repository.findById(id).orElseThrow(EntityNotFoundException::new);
        return new Ent3ReadDTO(ent3);
    }

    @Override
    public Ent3ReadDTO findByEmail(boolean active, String email) throws EntityNotFoundException {
        Ent3 ent3 = this.ent3Repository.findByEmailAndActive(email, active).orElseThrow(EntityNotFoundException::new);
        return new Ent3ReadDTO(ent3);
    }

    @Override
    public Page<Ent3ReadDTO> findAll(boolean active, Pageable paging) {
        return this.ent3Repository.findAllByActive(active, paging).map(Ent3ReadDTO::new);
    }

    @Override
    public Page<Ent3ReadDTO> findAllByDate(boolean active, LocalDateTime date, Pageable paging) {
        return this.ent3Repository.findAllByDateAndActive(date, active, paging).map(Ent3ReadDTO::new);
    }

    @Override
    public Page<Ent3ReadDTO> findAllBetweenDates(boolean active, LocalDateTime date3, LocalDateTime date2, Pageable paging) {
        return this.ent3Repository.findAllBetweenDatesAndActive(date3, date2, active, paging).map(Ent3ReadDTO::new);
    }

    @Override
    public Page<Ent3ReadDTO> findAllByEnt1Id(boolean active, Long ent1Id, Pageable paging) {
        return this.ent3Repository.findAllByEnt1IdAndActive(ent1Id, active, paging).map(Ent3ReadDTO::new);
    }

    @Override
    public Ent3ReadDTO update(Ent3UpdateDTO ent3UpdateDTO) throws EntityNotFoundException {
        Ent3 ent3 = this.ent3Repository.findById(ent3UpdateDTO.id()).orElseThrow(EntityNotFoundException::new);
        if (ent3.isActive()) {
            if (ent3UpdateDTO.publicInfo() != null) {
                ent3.setPublicInfo(ent3UpdateDTO.publicInfo());
            }
            if (ent3UpdateDTO.secretInfo() != null) {
                ent3.setSecretInfo(ent3UpdateDTO.secretInfo());
            }
            if (ent3UpdateDTO.email() != null) {
                ent3.setEmail(ent3UpdateDTO.email());
            }
        }
        this.ent3Repository.save(ent3);
        return new Ent3ReadDTO(ent3);
    }

    @Override
    public boolean toggle(Long id) {
        Ent3 ent3 = this.ent3Repository.findById(id).orElseThrow(EntityNotFoundException::new);
        ent3.setActive(!ent3.isActive());
        return true;
    }
    //Para simplificar el proceso de eliminar/recuperar con el atributo booleano, se hizo un método
    //"toggle", que básicamente cambia la posición del atributo a la opuesta en la que estaba.

    @Override
    public boolean delete(Long id) {
        Ent3 ent3 = this.ent3Repository.findById(id).orElseThrow(EntityNotFoundException::new);
        this.ent3Repository.delete(ent3);
        return true;
    }

}