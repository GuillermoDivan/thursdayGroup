package com.thursdaygroup.api.entities.ent2;
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
    private String noUpdateableInfo;
    private String email;
    private LocalDateTime date;
    private boolean active;
    @ManyToMany(fetch = FetchType.LAZY)
    private List<Ent1> ent1List;
}


