package com.thursdaygroup.api.services.Ent3;
import com.thursdaygroup.api.entities.ent3.*;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.time.LocalDateTime;

public interface Ent3Service {
    Ent3ReadDTO save(Ent3CreateDTO ent3CreateDTO);
    Ent3ReadDTO findById(Long id) throws EntityNotFoundException;
    Ent3ReadDTO findByEmail(boolean active, String email) throws EntityNotFoundException;
    Page<Ent3ReadDTO> findAll(boolean active, Pageable paging);
    Page<Ent3ReadDTO>  findAllByDate(boolean active, LocalDateTime date, Pageable paging);
    Page<Ent3ReadDTO>  findAllBetweenDates(boolean active, LocalDateTime date3, LocalDateTime date2, Pageable paging);
    Page<Ent3ReadDTO> findAllByEnt1Id(boolean active, Long ent1Id, Pageable paging);
    Ent3ReadDTO update(Ent3UpdateDTO ent3UpdateDTO) throws EntityNotFoundException;
    boolean toggle (Long id) throws EntityNotFoundException;
    boolean delete (Long id);
}