package SupTecnico.example.Suporte.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Tecnico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String cpf;

    @Temporal(TemporalType.DATE)
    private Date dataNasc;

    @ManyToMany
    @JoinTable(name = "tecnico_avaliacao",
            joinColumns = @JoinColumn(name = "tecnico_id"),
            inverseJoinColumns = @JoinColumn(name = "avaliacao_id"))
    @JsonManagedReference
    private List<Avaliacao> avaliacoes;

    @ManyToOne
    @JsonBackReference
    private Usuario usuario;
}