package com.thursdaygroup.api.entities.ent2;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.thursdaygroup.api.entities.ent1.Ent1;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
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
    private String email;
    private LocalDateTime date;
    private boolean active = true;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "ent1_ent2_asoc",
            joinColumns = { @JoinColumn(name = "Ent2_id") },
            inverseJoinColumns = { @JoinColumn(name = "Ent1_id") })
    private List<Ent1> ent1List;
}


