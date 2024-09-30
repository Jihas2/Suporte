package SupTecnico.example.Suporte.Controllers;

import SupTecnico.example.Suporte.Entity.Avaliacao;
import SupTecnico.example.Suporte.Repositories.AvaliacaoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/avaliacoes")
public class AvaliacaoController {

    private final AvaliacaoRepository avaliacaoRepository;

    public AvaliacaoController(AvaliacaoRepository avaliacaoRepository) {
        this.avaliacaoRepository = avaliacaoRepository;
    }

    @GetMapping
    public List<Avaliacao> listarAvaliacoes() {
        return avaliacaoRepository.findAll();
    }

    @PostMapping("/salvar")
    public ResponseEntity<Avaliacao> criarAvaliacao(@RequestBody Avaliacao avaliacao) {
        Avaliacao novaAvaliacao = avaliacaoRepository.save(avaliacao);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaAvaliacao);
    }

    @PutMapping("update/{id}")
    public ResponseEntity<Avaliacao> atualizarAvaliacao(@PathVariable Long id, @RequestBody Avaliacao avaliacaoAtualizada) {
        Avaliacao avaliacaoExistente = avaliacaoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Avaliação não encontrada"));
        avaliacaoExistente.setNota(avaliacaoAtualizada.getNota());
        Avaliacao avaliacaoSalva = avaliacaoRepository.save(avaliacaoExistente);
        return ResponseEntity.ok(avaliacaoSalva);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Void> deletarAvaliacaoPorId(@PathVariable Long id) {
        avaliacaoRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/todos")
    public ResponseEntity<Void> deletarTodasAvaliacoes() {
        avaliacaoRepository.deleteAll();
        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
}
