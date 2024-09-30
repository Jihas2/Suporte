package SupTecnico.example.Suporte.Service;

import SupTecnico.example.Suporte.Entity.Mensagem;
import SupTecnico.example.Suporte.Repositories.MensagemRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MensagemService {

    private final MensagemRepository mensagemRepository;

    public MensagemService(MensagemRepository mensagemRepository) {
        this.mensagemRepository = mensagemRepository;
    }

    public List<Mensagem> listarMensagens() {
        return mensagemRepository.findAll();
    }

    public Mensagem criarMensagem(Mensagem mensagem) {
        return mensagemRepository.save(mensagem);
    }

    public Mensagem atualizarMensagem(Long id, Mensagem mensagemAtualizada) {
        Mensagem mensagemExistente = mensagemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Mensagem não encontrada"));
        mensagemExistente.setConteudo(mensagemAtualizada.getConteudo());
        return mensagemRepository.save(mensagemExistente);
    }

    public void deletarMensagemPorId(Long id) {
        mensagemRepository.deleteById(id);
    }

    public void deletarTodasMensagens() {
        mensagemRepository.deleteAll();
    }
}
