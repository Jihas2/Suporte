package SupTecnico.example.Suporte.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Avaliacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int nota;

    @ManyToMany(mappedBy = "avaliacoes")
    @JsonBackReference
    private List<Tecnico> tecnicos;

    public Avaliacao(Long id) {
        this.id = id;
    }
}

