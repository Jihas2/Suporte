package SupTecnico.example.Suporte.Service;

import SupTecnico.example.Suporte.Entity.Mensagem;
import SupTecnico.example.Suporte.Repositories.MensagemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class MensagemService {

    @Autowired
    private MensagemRepository mensagemRepository;

    public Mensagem criarMensagem(Mensagem mensagem) {
        try {
            return mensagemRepository.save(mensagem);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Erro ao criar mensagem: " + e.getMessage(), e);
        }
    }

    public Mensagem findById(Long id) {
        return mensagemRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Mensagem com o ID " + id + " não encontrada"));
    }

    public Mensagem atualizarMensagem(Long id, Mensagem mensagemAtualizada) {
        try {
            Mensagem mensagemExistente = findById(id);
            mensagemExistente.setConteudo(mensagemAtualizada.getConteudo());
            return mensagemRepository.save(mensagemExistente);
        } catch (ResponseStatusException e) {
            throw e;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao atualizar mensagem: " + e.getMessage(), e);
        }
    }

    public List<Mensagem> listarMensagens() {
        try {
            return mensagemRepository.findAll();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao listar mensagens: " + e.getMessage(), e);
        }
    }

    public void deletarMensagemPorId(Long id) {
        try {
            mensagemRepository.deleteById(id);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao deletar mensagem: " + e.getMessage(), e);
        }
    }

    public void deletarTodasMensagens() {
        try {
            mensagemRepository.deleteAll();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao deletar todas as mensagens: " + e.getMessage(), e);
        }
    }
}
