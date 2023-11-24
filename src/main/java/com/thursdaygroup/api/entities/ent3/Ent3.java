package com.thursdaygroup.api.entities.ent3;
import com.thursdaygroup.api.entities.ent1.Ent1;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

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
    private String noUpdateableInfo;
    private String email;
    private LocalDateTime date;
    private boolean active;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ent1_id")
    private Ent1 ent1;

    public Ent3(Ent3CreateDTO ent3CreateDTO) {
        this.publicInfo = ent3CreateDTO.publicInfo();
        this.secretInfo = ent3CreateDTO.secretInfo();
        this.noUpdateableInfo = ent3CreateDTO.noUpdateableInfo();
        this.email = ent3CreateDTO.email();

        //Hay atributos que se settean por defecto en determinado estado.
        this.date = LocalDateTime.now();
        this.active = true;

        //Para crear la relación ManyToOne se requiere en el constructor Many
        //Que se genere un objeto vacío del One (del atributo) y luego se settee
        //Con el id pasado por JSON desde front.
        this.ent1 = new Ent1();
        this.ent1.setId(ent3CreateDTO.Ent1Id());

    }

}