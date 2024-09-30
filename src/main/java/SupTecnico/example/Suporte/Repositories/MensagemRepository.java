package SupTecnico.example.Suporte.Repositories;

import SupTecnico.example.Suporte.Entity.Mensagem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MensagemRepository extends JpaRepository<Mensagem, Long> {
}
