package SupTecnico.example.Suporte.Entity;

import SupTecnico.example.Suporte.Enum.Categoria;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String descricao;

    @ManyToOne
    @JsonBackReference
    private Usuario usuario;

    @Enumerated(EnumType.STRING)
    private Categoria categoria;

    public Ticket(Long id) {
        this.id = id;
    }
}
