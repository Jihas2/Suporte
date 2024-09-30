package SupTecnico.example.Suporte.Controllers;

import SupTecnico.example.Suporte.Entity.Mensagem;
import SupTecnico.example.Suporte.Repositories.MensagemRepository;
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

    @PostMapping
    public Mensagem criarMensagem(@RequestBody Mensagem mensagem) {
        return mensagemRepository.save(mensagem);
    }

    @PutMapping("/{id}")
    public Mensagem atualizarMensagem(@PathVariable Long id, @RequestBody Mensagem mensagemAtualizada) {
        Mensagem mensagemExistente = mensagemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Mensagem não encontrada"));
        mensagemExistente.setConteudo(mensagemAtualizada.getConteudo());
        return mensagemRepository.save(mensagemExistente);
    }

    @DeleteMapping("/{id}")
    public void deletarMensagemPorId(@PathVariable Long id) {
        mensagemRepository.deleteById(id);
    }

    @DeleteMapping
    public void deletarTodasMensagens() {
        mensagemRepository.deleteAll();
    }
}

