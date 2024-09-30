package SupTecnico.example.Suporte.Repositories;

import SupTecnico.example.Suporte.Entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
