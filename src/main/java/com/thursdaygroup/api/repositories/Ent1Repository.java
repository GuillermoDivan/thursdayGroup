package com.thursdaygroup.api.repositories;
import com.thursdaygroup.api.entities.ent1.Ent1;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface Ent1Repository extends JpaRepository<Ent1, Long> {
    Optional<Ent1> findByEmailAndActive(String email, Boolean active);
    //Si los parámetros son de la misma entidad no es necesario hacer query.
    //Optional es necesario para que funcione el .orElseThrow() del método del service.
    Page<Ent1> findAllByActive(Boolean active, Pageable paging);

    @Query("SELECT E1 FROM Ent1 E1 INNER JOIN E1.ent2List E2 WHERE E2.id = :ent2Id and E1.active = :active")
    Page<Ent1>findAllByEnt2IdAndActive(Long ent2Id, Boolean active, Pageable paging);

    @Query("SELECT E1 FROM Ent1 E1 INNER JOIN E1.ent3List E3 WHERE E3.id = :ent3Id and E1.active = :active")
    Page<Ent1>findAllByEnt3IdAndActive(Long ent3Id, Boolean active, Pageable paging);

    @Query("""
    SELECT E FROM Ent1 E 
    WHERE E.date = :date1 and E.active = :active 
    """)
    Page<Ent1>findAllByDateAndActive(LocalDate date1, Boolean active, Pageable paging);

    @Query("""
    SELECT E FROM Ent1 E 
    WHERE E.date BETWEEN :dateBefore AND :dateAfter 
    AND E.active = :active 
    """)
    Page<Ent1>findAllBetweenDatesAndActive(LocalDate dateBefore, LocalDate dateAfter, Boolean active, Pageable paging);

}
