package SupTecnico.example.Suporte.Repositories;

import SupTecnico.example.Suporte.Entity.Tecnico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TecnicoRepository extends JpaRepository<Tecnico, Long> {
}
