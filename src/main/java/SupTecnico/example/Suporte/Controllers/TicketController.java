package SupTecnico.example.Suporte.Controllers;

import SupTecnico.example.Suporte.Entity.Ticket;
import SupTecnico.example.Suporte.Repositories.TicketRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @PostMapping(consumes = "application/json")
    public ResponseEntity<Ticket> criarTicket(@RequestBody Ticket ticket) {
        Ticket ticketSalvo = ticketRepository.save(ticket);
        return ResponseEntity.status(HttpStatus.CREATED).body(ticketSalvo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Ticket> atualizarTicket(@PathVariable Long id, @RequestBody Ticket ticketAtualizado) {
        Ticket ticketExistente = ticketRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ticket não encontrado"));
        ticketExistente.setDescricao(ticketAtualizado.getDescricao());
        ticketExistente.setCategoria(ticketAtualizado.getCategoria());
        Ticket ticketSalvo = ticketRepository.save(ticketExistente);
        return ResponseEntity.ok(ticketSalvo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarTicketPorId(@PathVariable Long id) {
        ticketRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> deletarTodosTickets() {
        ticketRepository.deleteAll();
        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
}
