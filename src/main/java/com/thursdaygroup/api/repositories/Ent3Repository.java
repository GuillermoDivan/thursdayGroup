package com.thursdaygroup.api.repositories;
import com.thursdaygroup.api.entities.ent3.Ent3;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface Ent3Repository extends JpaRepository<Ent3, Long> {
    Optional<Ent3> findByEmailAndActive(String email, Boolean active);
    //Si los parámetros son de la misma entidad no es necesario hacer query.
    //Optional es necesario para que funcione el .orElseThrow() del método del service.
    Page<Ent3> findAllByActive(Boolean active, Pageable paging);

    @Query("SELECT E3 FROM Ent3 E3 INNER JOIN E3.ent1 E1 WHERE E1.id = :ent1Id and E3.active = :active")
    Page<Ent3>findAllByEnt1IdAndActive(Long ent1Id, Boolean active, Pageable paging);

    @Query("""
    SELECT E FROM Ent3 E 
    WHERE E.date = :date and E.active = :active 
    """)
    Page<Ent3>findAllByDateAndActive(LocalDateTime date, Boolean active, Pageable paging);

    @Query("""
    SELECT E FROM Ent3 E 
    WHERE E.date BETWEEN :date1 AND :date2 
    AND E.active = :active 
    """)
    Page<Ent3>findAllBetweenDatesAndActive(LocalDateTime date1, LocalDateTime date2, Boolean active, Pageable paging);

}
