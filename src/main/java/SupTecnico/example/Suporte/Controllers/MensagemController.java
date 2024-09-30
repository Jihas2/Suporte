package SupTecnico.example.Suporte.Controllers;

import SupTecnico.example.Suporte.Entity.Mensagem;
import SupTecnico.example.Suporte.Repositories.MensagemRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/mensagens")
public class MensagemController {

    private final MensagemRepository mensagemRepository;

    public MensagemController(MensagemRepository mensagemRepository) {
        this.mensagemRepository = mensagemRepository;
    }

    @GetMapping
    public List<Mensagem> listarMensagens() {
        return mensagemRepository.findAll();
    }

    @PostMapping("/salvar")
    public ResponseEntity<Mensagem> criarMensagem(@RequestBody Mensagem mensagem) {
        Mensagem novaMensagem = mensagemRepository.save(mensagem);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaMensagem);
    }

    @PutMapping("update/{id}")
    public ResponseEntity<Mensagem> atualizarMensagem(@PathVariable Long id, @RequestBody Mensagem mensagemAtualizada) {
        Mensagem mensagemExistente = mensagemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Mensagem não encontrada"));
        mensagemExistente.setConteudo(mensagemAtualizada.getConteudo());
        Mensagem mensagemSalva = mensagemRepository.save(mensagemExistente);
        return ResponseEntity.ok(mensagemSalva);
    }

    @DeleteMapping("deletar/{id}")
    public ResponseEntity<Void> deletarMensagemPorId(@PathVariable Long id) {
        mensagemRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/todos")
    public ResponseEntity<Void> deletarTodasMensagens() {
        mensagemRepository.deleteAll();
        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
}
