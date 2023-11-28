package com.thursdaygroup.api.repositories;
import com.thursdaygroup.api.entities.ent2.Ent2;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface Ent2Repository extends JpaRepository<Ent2, Long> {
    Optional<Ent2> findByEmailAndActive(String email, Boolean active);
    //Si los parámetros son de la misma entidad no es necesario hacer query.
    //Optional es necesario para que funcione el .orElseThrow() del método del service.
    List<Ent2> findAllByActive(Boolean active);

    @Query("SELECT E2 FROM Ent2 E2 INNER JOIN E2.ent1List E1 WHERE E1.id = :ent1Id and E2.active = :active")
    List<Ent2> findAllByEnt1IdAndActive(Long ent1Id, Boolean active);

    @Query("""
    SELECT E FROM Ent2 E 
    WHERE E.date = :date1 and E.active = :active 
    """)
    List<Ent2> findAllByDateAndActive(LocalDate date1, Boolean active);

    @Query("""
    SELECT E FROM Ent2 E 
    WHERE E.date BETWEEN :dateBefore AND :dateAfter 
    AND E.active = :active 
    """)
    List<Ent2> findAllBetweenDatesAndActive(LocalDate dateBefore, LocalDate dateAfter, Boolean active);

}
