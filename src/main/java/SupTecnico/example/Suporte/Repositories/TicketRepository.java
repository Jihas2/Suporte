package SupTecnico.example.Suporte.Repositories;

import SupTecnico.example.Suporte.Entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
}

