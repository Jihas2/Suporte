package SupTecnico.example.Suporte.Controllers;

import SupTecnico.example.Suporte.Entity.Avaliacao;
import SupTecnico.example.Suporte.Repositories.AvaliacaoRepository;
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

    @PostMapping
    public Avaliacao criarAvaliacao(@RequestBody Avaliacao avaliacao) {
        return avaliacaoRepository.save(avaliacao);
    }

    @PutMapping("/{id}")
    public Avaliacao atualizarAvaliacao(@PathVariable Long id, @RequestBody Avaliacao avaliacaoAtualizada) {
        Avaliacao avaliacaoExistente = avaliacaoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Avaliação não encontrada"));
        avaliacaoExistente.setNota(avaliacaoAtualizada.getNota());
        return avaliacaoRepository.save(avaliacaoExistente);
    }

    @DeleteMapping("/{id}")
    public void deletarAvaliacaoPorId(@PathVariable Long id) {
        avaliacaoRepository.deleteById(id);
    }

    @DeleteMapping
    public void deletarTodasAvaliacoes() {
        avaliacaoRepository.deleteAll();
    }
}

