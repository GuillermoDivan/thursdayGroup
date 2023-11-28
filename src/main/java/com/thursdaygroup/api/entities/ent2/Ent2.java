package com.thursdaygroup.api.entities.ent2;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.thursdaygroup.api.entities.ent1.Ent1;
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
public class Ent2 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String publicInfo;
    private String secretInfo;
    private String noUpdatableInfo;
    @Column(unique = true)
    private String email;
    private LocalDate date;
    private boolean active = true;
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "ent2List", cascade = CascadeType.ALL)
    private List<Ent1> ent1List;
}


