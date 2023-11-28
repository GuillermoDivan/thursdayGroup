package com.thursdaygroup.api.entities.ent1;
import com.thursdaygroup.api.entities.ent2.Ent2;
import com.thursdaygroup.api.entities.ent3.Ent3;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ent1 {
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
    //Fetch type eager implica que la info relacional de ambas tablas sea traída al buscar
    // cualquiera de las dos. Cuando se busca una entidad se lista automáticamente a los
    // objetos relacionados. Puede tener demoras y problemas de escalabilidad si es mucha data.
    //Ejemplos de uso: estudiante con notas, orden con productos.
    //Fetch type lazy implica que sólo traerá la información de los objetos cuando sea explícitamente
    //consultada.
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "ent1List")
    private List<Ent2> ent2List;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "ent1")
    private List<Ent3> ent3List;

    public Ent1(Ent1CreateDTO ent1CreateDTO) {
        this.publicInfo = ent1CreateDTO.publicInfo();
        this.secretInfo = ent1CreateDTO.secretInfo();
        this.noUpdatableInfo = ent1CreateDTO.noUpdatableInfo();
        this.email = ent1CreateDTO.email();
        //Hay atributos que se settean por defecto en determinado estado.
        this.date = LocalDate.now();
        this.active = true;

        //Los atributos ent2List y ent3List no son ingresados por este DTO y el objeto se crea
        //con ellos nulos. -> ent3List (OneToMany) se va a cargar al crear los ent3 relacionados.
        //-> ent2List se va a cargar generando primero las entidades ent1 y ent2 sin relación y luego
        //estableciendo la relación desde otro endpoint (porque lo que las relaciona es una
        // tabla intermedia de ids).

    }
}
