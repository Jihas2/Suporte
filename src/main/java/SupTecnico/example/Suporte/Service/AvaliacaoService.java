package SupTecnico.example.Suporte.Service;

import SupTecnico.example.Suporte.Entity.Avaliacao;
import SupTecnico.example.Suporte.Repositories.AvaliacaoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AvaliacaoService {

    private final AvaliacaoRepository avaliacaoRepository;

    public AvaliacaoService(AvaliacaoRepository avaliacaoRepository) {
        this.avaliacaoRepository = avaliacaoRepository;
    }

    public List<Avaliacao> listarAvaliacoes() {
        return avaliacaoRepository.findAll();
    }

    public Avaliacao criarAvaliacao(Avaliacao avaliacao) {
        return avaliacaoRepository.save(avaliacao);
    }

    public Avaliacao atualizarAvaliacao(Long id, Avaliacao avaliacaoAtualizada) {
        Avaliacao avaliacaoExistente = avaliacaoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Avaliação não encontrada"));
        avaliacaoExistente.setNota(avaliacaoAtualizada.getNota());
        return avaliacaoRepository.save(avaliacaoExistente);
    }

    public void deletarAvaliacaoPorId(Long id) {
        avaliacaoRepository.deleteById(id);
    }

    public void deletarTodasAvaliacoes() {
        avaliacaoRepository.deleteAll();
    }
}