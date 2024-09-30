package SupTecnico.example.Suporte.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String cpf;

    @Temporal(TemporalType.DATE)
    private Date dataNasc;

    private String email;

    @OneToMany(mappedBy = "usuario")
    @JsonIgnore
    private List<Tecnico> tecnicos;

    @OneToMany(mappedBy = "usuario")
    @JsonIgnore
    private List<Ticket> tickets;

    public Usuario(Long id) {
        this.id = id;
    }
}