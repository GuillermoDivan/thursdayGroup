package com.thursdaygroup.api.controllers;
import com.thursdaygroup.api.entities.ent2.Ent2DTO;
import com.thursdaygroup.api.services.Ent2.Ent2Service;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/ent2")
@RequiredArgsConstructor
public class Ent2Controller {

    private final Ent2Service ent2Service;

    @PostMapping("/save")
    @Transactional //@Transactional Puede ir en Service, de hecho quizás sería mejor en Service.
    public ResponseEntity<Ent2DTO> saveEnt2(@RequestBody Ent2DTO ent2DTO) {
        var dto = this.ent2Service.save(ent2DTO);
        return ResponseEntity.ok().body(dto);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Ent2DTO> findEnt2ById(@PathVariable Long id){
        var dto = this.ent2Service.findById(id);
        return ResponseEntity.ok().body(dto);
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<Ent2DTO> findEnt2ByEmail(@PathVariable String email){
        var dto = this.ent2Service.findByEmail(true, email);
        return ResponseEntity.ok().body(dto);
    }

    @GetMapping("/inactive/email/{email}")
    public ResponseEntity<Ent2DTO> findEnt2ByEmailAndInactive(@PathVariable String email){
        var dto = this.ent2Service.findByEmail(false, email);
        return ResponseEntity.ok().body(dto);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Ent2DTO>> findAllEnt2(){ //En el caso de listas, se retorna una response entity de la lista.
        var dto = this.ent2Service.findAll(true);
        return ResponseEntity.ok().body(dto);
    }

    @GetMapping("/inactive/all")
    public ResponseEntity<List<Ent2DTO>> findAllEnt2ByInactive(){ //En el caso de listas, se retorna una response entity de la lista.
        var dto = this.ent2Service.findAll(false);
        return ResponseEntity.ok().body(dto);
    }

    /* Este y todos los endpoints que requieren pasar fecha por url no están hechos porque aún no entiendo cómo. >.< #InProgress.
    //Idem la ManyToMany. u.u
    @GetMapping("/date/{date}")
    public ResponseEntity<List<Ent2DTO>> findAllByDate(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss") LocalDateTime date){ //En el caso de listas, se retorna una response entity de la lista.
        var dto = this.ent2Service.findAllByDate(true, date);
        return ResponseEntity.ok().body(dto);
    }
*/

    @PutMapping("/id/{id}")
    @Transactional
    public ResponseEntity<Ent2DTO> updateEnt2(@RequestBody Ent2DTO ent2DTO){
        var dto = this.ent2Service.update(ent2DTO);
        return ResponseEntity.ok().body(dto);
    }

    @PutMapping("/hide/{id}")
    @Transactional
    public ResponseEntity<Boolean> hideEnt2(@PathVariable Long id){
        boolean result= this.ent2Service.hide(id);
        if (result) {
            return ResponseEntity.ok().body(true);
        } else { return ResponseEntity.badRequest().body(false); }
    }

    @PutMapping("/reactive/{id}")
    @Transactional
    public ResponseEntity<Boolean> reactiveEnt2(@PathVariable Long id){
        boolean result = this.ent2Service.reactive(id);
        if (result) {
            return ResponseEntity.ok().body(true);
        } else { return ResponseEntity.badRequest().body(false); }
    }

    //Método Delete de la db hecho desde controller a db pero comentado para no usar por error.
    @DeleteMapping("/id/{id}")
    @Transactional
    public ResponseEntity<Boolean> deleteEnt2(@PathVariable Long id){
        boolean result = this.ent2Service.delete(id);
        if (result) {
            return ResponseEntity.ok().body(true);
        } else { return ResponseEntity.badRequest().body(false); }
    }



}