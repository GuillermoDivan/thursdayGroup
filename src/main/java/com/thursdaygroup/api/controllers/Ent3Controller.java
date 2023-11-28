package com.thursdaygroup.api.controllers;
import com.thursdaygroup.api.entities.ent1.Ent1ReadDTO;
import com.thursdaygroup.api.entities.ent3.*;
import com.thursdaygroup.api.services.Ent3.Ent3Service;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ent3")
@CrossOrigin(origins = "*") //Esta anotación debe hacerse en tod.o controller especificando
//urls habilitadas (o dejando * para todas...), pero también se puede gestionar como una clase
//de seguridad global. Aplicar en el futuro.
@RequiredArgsConstructor
public class Ent3Controller {

    private final Ent3Service ent3Service;

    @PostMapping(path = "/",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Transactional
    public ResponseEntity<Ent3ReadDTO> saveEnt3(@RequestBody @Valid Ent3CreateDTO Ent3CreateDTO) {
        var readDto = this.ent3Service.save(Ent3CreateDTO);
        return ResponseEntity.ok().body(readDto);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Ent3ReadDTO> findEnt3ById(@PathVariable Long id){
        var readDto = this.ent3Service.findById(id);
        return ResponseEntity.ok().body(readDto);
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<Ent3ReadDTO> findEnt3ByEmail(@PathVariable String email){
        var readDto = this.ent3Service.findByEmail(true, email);
        return ResponseEntity.ok().body(readDto);
    }

    @GetMapping("/inactive/email/{email}")
    public ResponseEntity<Ent3ReadDTO> findEnt3ByEmailAndInactive(@PathVariable String email){
        var readDto = this.ent3Service.findByEmail(false, email);
        return ResponseEntity.ok().body(readDto);
    }

    @GetMapping("/all")
    public ResponseEntity<Page<Ent3ReadDTO>> findAllEnt3(Pageable paging){
        var readDtoPage = this.ent3Service.findAll(true, paging);
        return ResponseEntity.ok().body(readDtoPage);
    }

    @GetMapping("/inactive/all")
    public ResponseEntity<Page<Ent3ReadDTO>> findAllEnt3ByInactive(Pageable paging){
        var readDtoPage = this.ent3Service.findAll(false, paging);
        return ResponseEntity.ok().body(readDtoPage);
    }

    //InProgress: lo ideal sería trabajar también con hora... y no pedirlos string, sino localDateTime, pero por ahora anda!
    @GetMapping("/date/{date}")
    public ResponseEntity<Page<Ent3ReadDTO>> findAllByDate(@PathVariable String date, Pageable paging){
        var readDto = this.ent3Service.findAllByDate(true, date, paging);
        return ResponseEntity.ok().body(readDto);
    }

    @GetMapping("/inactive/date/{date}")
    public ResponseEntity<Page<Ent3ReadDTO>> findAllByDateAndInactive(@PathVariable String date, Pageable paging){
        var readDto = this.ent3Service.findAllByDate(false, date, paging);
        return ResponseEntity.ok().body(readDto);
    }

    @GetMapping("/dates/{date1}/{date2}")
    public ResponseEntity<Page<Ent3ReadDTO>> findAllBetweenDates(@PathVariable String date1, @PathVariable String date2, Pageable paging){
        var readDto = this.ent3Service.findAllBetweenDates(true, date1, date2, paging);
        return ResponseEntity.ok().body(readDto);
    }

    @GetMapping("/inactive/dates/{date1}/{date2}")
    public ResponseEntity<Page<Ent3ReadDTO>> findAllBetweenDatesAndInactive(@PathVariable String date1, @PathVariable String date2, Pageable paging){
        var readDto = this.ent3Service.findAllBetweenDates(false, date1, date2, paging);
        return ResponseEntity.ok().body(readDto);
    }

    @PutMapping("/id/{id}")
    @Transactional
    public ResponseEntity<Ent3ReadDTO> updateEnt3(@RequestBody @Valid Ent3UpdateDTO Ent3UpdateDTO){
        var readDto = this.ent3Service.update(Ent3UpdateDTO);
        return ResponseEntity.ok().body(readDto);
    }

    @PutMapping("/toggle/{id}")
    @Transactional
    public ResponseEntity<Boolean> hideEnt3(@PathVariable Long id){
        boolean result= this.ent3Service.toggle(id);
        if (result) {
            return ResponseEntity.ok().body(true);
        } else { return ResponseEntity.badRequest().body(false); }
    }

    //Método Delete de la db hecho desde controller a db pero comentado para no usar por error.
    /*@DeleteMapping("/id/{id}")
    @Transactional
    public ResponseEntity<Boolean> deleteEnt3(@PathVariable Long id){
        boolean result = this.ent3Service.delete(id);
        if (result) {
            return ResponseEntity.ok().body(true);
        } else { return ResponseEntity.badRequest().body(false); }
    }*/

    }