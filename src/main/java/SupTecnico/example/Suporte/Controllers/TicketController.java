package SupTecnico.example.Suporte.Controllers;

import SupTecnico.example.Suporte.Entity.Ticket;
import SupTecnico.example.Suporte.Repositories.TicketRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tickets")
public class TicketController {

    private final TicketRepository ticketRepository;

    public TicketController(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    @GetMapping
    public List<Ticket> listarTickets() {
        return ticketRepository.findAll();
    }

    @PostMapping
    public Ticket criarTicket(@RequestBody Ticket ticket) {
        return ticketRepository.save(ticket);
    }

    @PutMapping("/{id}")
    public Ticket atualizarTicket(@PathVariable Long id, @RequestBody Ticket ticketAtualizado) {
        Ticket ticketExistente = ticketRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ticket não encontrado"));
        ticketExistente.setDescricao(ticketAtualizado.getDescricao());
        ticketExistente.setCategoria(ticketAtualizado.getCategoria());
        return ticketRepository.save(ticketExistente);
    }

    @DeleteMapping("/{id}")
    public void deletarTicketPorId(@PathVariable Long id) {
        ticketRepository.deleteById(id);
    }

    @DeleteMapping
    public void deletarTodosTickets() {
        ticketRepository.deleteAll();
    }
}

