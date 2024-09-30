package SupTecnico.example.Suporte.Service;

import SupTecnico.example.Suporte.Entity.Avaliacao;
import SupTecnico.example.Suporte.Repositories.AvaliacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class AvaliacaoService {

    @Autowired
    private AvaliacaoRepository avaliacaoRepository;

    public Avaliacao criarAvaliacao(Avaliacao avaliacao) {
        try {
            return avaliacaoRepository.save(avaliacao);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Erro ao criar avaliação: " + e.getMessage(), e);
        }
    }

    public Avaliacao findById(Long id) {
        return avaliacaoRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Avaliação com o ID " + id + " não encontrada"));
    }

    public Avaliacao atualizarAvaliacao(Long id, Avaliacao avaliacaoAtualizada) {
        try {
            Avaliacao avaliacaoExistente = findById(id);
            avaliacaoExistente.setNota(avaliacaoAtualizada.getNota());
            return avaliacaoRepository.save(avaliacaoExistente);
        } catch (ResponseStatusException e) {
            throw e;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao atualizar avaliação: " + e.getMessage(), e);
        }
    }

    public List<Avaliacao> listarAvaliacoes() {
        try {
            return avaliacaoRepository.findAll();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao listar avaliações: " + e.getMessage(), e);
        }
    }

    public void deletarAvaliacaoPorId(Long id) {
        try {
            avaliacaoRepository.deleteById(id);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao deletar avaliação: " + e.getMessage(), e);
        }
    }

    public void deletarTodasAvaliacoes() {
        try {
            avaliacaoRepository.deleteAll();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao deletar todas as avaliações: " + e.getMessage(), e);
        }
    }
}
