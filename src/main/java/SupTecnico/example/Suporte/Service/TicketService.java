package SupTecnico.example.Suporte.Service;

import SupTecnico.example.Suporte.Entity.Ticket;
import SupTecnico.example.Suporte.Repositories.TicketRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketService {

    private final TicketRepository ticketRepository;

    public TicketService(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    public List<Ticket> listarTickets() {
        return ticketRepository.findAll();
    }

    public Ticket criarTicket(Ticket ticket) {
        return ticketRepository.save(ticket);
    }

    public Ticket atualizarTicket(Long id, Ticket ticketAtualizado) {
        Ticket ticketExistente = ticketRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ticket não encontrado"));
        ticketExistente.setDescricao(ticketAtualizado.getDescricao());
        ticketExistente.setCategoria(ticketAtualizado.getCategoria());
        return ticketRepository.save(ticketExistente);
    }

    public void deletarTicketPorId(Long id) {
        ticketRepository.deleteById(id);
    }

    public void deletarTodosTickets() {
        ticketRepository.deleteAll();
    }
}
