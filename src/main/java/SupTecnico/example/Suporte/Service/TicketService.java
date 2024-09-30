package SupTecnico.example.Suporte.Service;

import SupTecnico.example.Suporte.Entity.Ticket;
import SupTecnico.example.Suporte.Repositories.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class TicketService {

    @Autowired
    private TicketRepository ticketRepository;

    public Ticket criarTicket(Ticket ticket) {
        try {
            return ticketRepository.save(ticket);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Erro ao criar ticket: " + e.getMessage(), e);
        }
    }

    public Ticket findById(Long id) {
        return ticketRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Ticket com o ID " + id + " não encontrado"));
    }

    public Ticket atualizarTicket(Long id, Ticket ticketAtualizado) {
        try {
            Ticket ticketExistente = findById(id);
            ticketExistente.setDescricao(ticketAtualizado.getDescricao());
            ticketExistente.setCategoria(ticketAtualizado.getCategoria());
            return ticketRepository.save(ticketExistente);
        } catch (ResponseStatusException e) {
            throw e;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao atualizar ticket: " + e.getMessage(), e);
        }
    }

    public List<Ticket> listarTickets() {
        try {
            return ticketRepository.findAll();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao listar tickets: " + e.getMessage(), e);
        }
    }

    public void deletarTicketPorId(Long id) {
        try {
            ticketRepository.deleteById(id);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao deletar ticket: " + e.getMessage(), e);
        }
    }

    public void deletarTodosTickets() {
        try {
            ticketRepository.deleteAll();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao deletar todos os tickets: " + e.getMessage(), e);
        }
    }
}
