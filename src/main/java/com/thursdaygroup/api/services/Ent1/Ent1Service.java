package com.thursdaygroup.api.services.Ent1;
import com.thursdaygroup.api.entities.ent1.*;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;

public interface Ent1Service {
    Ent1ReadDTO save(Ent1CreateDTO ent1CreateDTO);
    Ent1ReadDTO relateEnt1ToEnt2(Long id1, Long id2) throws EntityNotFoundException;
    Ent1ReadDTO findById(Long id) throws EntityNotFoundException;
    Ent1ReadDTO findByEmail(boolean active, String email) throws EntityNotFoundException;
    Page<Ent1ReadDTO> findAll(boolean active, Pageable paging);
    Page<Ent1ReadDTO>  findAllByDate(boolean active, String date, Pageable paging);
    Page<Ent1ReadDTO>  findAllBetweenDates(boolean active, String date1, String date2, Pageable paging);
    Page<Ent1ReadDTO> findAllByEnt2Id(boolean active, Long ent2Id, Pageable paging);
    Page<Ent1ReadDTO> findAllByEnt3Id(boolean active, Long ent3Id, Pageable paging);
    Ent1ReadDTO update(Ent1UpdateDTO ent1UpdateDTO) throws EntityNotFoundException;
    boolean hide (Long id) throws EntityNotFoundException;
    boolean reactive (Long id) throws EntityNotFoundException;
    boolean delete (Long id);
}
