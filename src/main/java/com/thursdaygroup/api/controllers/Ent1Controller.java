package com.thursdaygroup.api.controllers;
import com.thursdaygroup.api.entities.ent1.*;
import com.thursdaygroup.api.services.Ent1.Ent1Service;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/ent1")
@CrossOrigin(origins = "*") //Esta anotación debe hacerse en tod.o controller especificando
//urls habilitadas (o dejando * para todas...), pero también se puede gestionar como una clase
//de seguridad global. Aplicar en el futuro.
@RequiredArgsConstructor
public class Ent1Controller {

    private final Ent1Service ent1Service;

    @PostMapping(path = "/",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Transactional //@Transactional Puede ir en Service, de hecho quizás sería mejor en Service.
    public ResponseEntity<Ent1ReadDTO> saveEnt1(@RequestBody @Valid Ent1CreateDTO Ent1CreateDTO) {
        var readDto = this.ent1Service.save(Ent1CreateDTO);
        return ResponseEntity.ok().body(readDto);
        //Convendría devolver "created" en lugar de "ok". Para hacerlo sin modificar el objeto retorno se debe incluir la uri.
        //Modificado en Ent3!
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Ent1ReadDTO> findEnt1ById(@PathVariable Long id){
        var readDto = this.ent1Service.findById(id);
        return ResponseEntity.ok().body(readDto);
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<Ent1ReadDTO> findEnt1ByEmail(@PathVariable String email){
        var readDto = this.ent1Service.findByEmail(true, email);
        return ResponseEntity.ok().body(readDto);
    }

    @GetMapping("/inactive/email/{email}")
    public ResponseEntity<Ent1ReadDTO> findEnt1ByEmailAndInactive(@PathVariable String email){
        var readDto = this.ent1Service.findByEmail(false, email);
        return ResponseEntity.ok().body(readDto);
    }

    @GetMapping("/all")
    public ResponseEntity<Page<Ent1ReadDTO>> findAllEnt1(Pageable paging){
        var readDtoPage = this.ent1Service.findAll(true, paging);
        return ResponseEntity.ok().body(readDtoPage);
    }

    @GetMapping("/inactive/all")
    public ResponseEntity<Page<Ent1ReadDTO>> findAllEnt1ByInactive(Pageable paging){
        var readDtoPage = this.ent1Service.findAll(false, paging);
        return ResponseEntity.ok().body(readDtoPage);
    }

    //InProgress: lo ideal sería trabajar también con hora... y no pedirlos string, sino localDateTime, pero por ahora anda!
    @GetMapping("/date/{date}")
    public ResponseEntity<Page<Ent1ReadDTO>> findAllByDate(@PathVariable String date, Pageable paging){
        var readDto = this.ent1Service.findAllByDate(true, date, paging);
        return ResponseEntity.ok().body(readDto);
    }

    @GetMapping("/dates/{date1}/{date2}")
    public ResponseEntity<Page<Ent1ReadDTO>> findAllBetweenDates(@PathVariable String date1, @PathVariable String date2, Pageable paging){
        var readDto = this.ent1Service.findAllBetweenDates(true, date1, date2, paging);
        return ResponseEntity.ok().body(readDto);
    }

    /*
    // ManyToMany in progress... no me juzguen, jajaja
    @PutMapping("/id1/{id1}/id2/{id2}")
    @Transactional
    public ResponseEntity<Ent1ReadDTO> relateEnt1ToEnt2(@PathVariable Long id1, @PathVariable Long id2){
        var readDto = this.Ent1Service.relateEnt1ToEnt2(id1, id2);
        return ResponseEntity.ok().body(readDto);
    }*/

    @PutMapping(path = "/id/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Transactional
    public ResponseEntity<Ent1ReadDTO> updateEnt1(@RequestBody @Valid Ent1UpdateDTO ent1UpdateDTO){
        var readDto = this.ent1Service.update(ent1UpdateDTO);
        return ResponseEntity.ok().body(readDto);
    }

    @PutMapping("/hide/{id}")
    @Transactional
    public ResponseEntity<Boolean> hideEnt1(@PathVariable Long id){
        boolean result= this.ent1Service.hide(id);
        if (result) {
            return ResponseEntity.ok().body(true);
        } else { return ResponseEntity.badRequest().body(false); }
    }

    @PutMapping("/reactive/{id}")
    @Transactional
    public ResponseEntity<Boolean> reactiveEnt1(@PathVariable Long id){
        boolean result = this.ent1Service.reactive(id);
        if (result) {
            return ResponseEntity.ok().body(true);
        } else { return ResponseEntity.badRequest().body(false); }
    }

    //Método Delete de la db hecho desde controller a db pero comentado para no usar por error.
     /*@DeleteMapping("/id/{id}")
    @Transactional
    public ResponseEntity<Boolean> deleteEnt1(@PathVariable Long id){
        boolean result = this.ent1Service.delete(id);
        if (result) {
            return ResponseEntity.ok().body(true);
        } else { return ResponseEntity.badRequest().body(false); }
    }*/
}