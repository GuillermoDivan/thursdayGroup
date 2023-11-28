package com.thursdaygroup.api.entities.ent3;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.thursdaygroup.api.entities.ent1.Ent1;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ent3 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String publicInfo;
    private String secretInfo;
    private String noUpdatableInfo;
    @Column(unique = true)
    private String email;
    private LocalDate date;
    private boolean active;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ent1_id")
    private Ent1 ent1;

    public Ent3(Ent3CreateDTO ent3CreateDTO) {
        this.publicInfo = ent3CreateDTO.publicInfo();
        this.secretInfo = ent3CreateDTO.secretInfo();
        this.noUpdatableInfo = ent3CreateDTO.noUpdatableInfo();
        this.email = ent3CreateDTO.email();

        //Hay atributos que se settean por defecto en determinado estado.
        this.date = LocalDate.now();
        this.active = true;

        //Para crear la relación ManyToOne se requiere en el constructor Many
        //Que se genere un objeto vacío del One (del atributo) y luego se settee
        //Con el id pasado por JSON desde front.
        this.ent1 = new Ent1();
        this.ent1.setId(ent3CreateDTO.Ent1Id());

    }

}