package SupTecnico.example.Suporte.Service;

import SupTecnico.example.Suporte.Entity.Tecnico;
import SupTecnico.example.Suporte.Entity.Usuario;
import SupTecnico.example.Suporte.Repositories.TecnicoRepository;
import SupTecnico.example.Suporte.Repositories.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TecnicoService {

    private final TecnicoRepository tecnicoRepository;
    private final UsuarioRepository usuarioRepository;

    public TecnicoService(TecnicoRepository tecnicoRepository, UsuarioRepository usuarioRepository) {
        this.tecnicoRepository = tecnicoRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public List<Tecnico> listarTecnicos() {
        return tecnicoRepository.findAll();
    }

    public Tecnico buscarTecnicoPorId(Long id) {
        return tecnicoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Técnico não encontrado"));
    }

    public Tecnico criarTecnico(Tecnico tecnico) {
        if (tecnico.getUsuario() == null || tecnico.getUsuario().getId() == null) {
            throw new RuntimeException("Usuário inválido");
        }

        Usuario usuarioExistente = usuarioRepository.findById(tecnico.getUsuario().getId())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        tecnico.setUsuario(usuarioExistente);
        return tecnicoRepository.save(tecnico);
    }

    public Tecnico atualizarTecnico(Long id, Tecnico tecnicoAtualizado) {
        Tecnico tecnicoExistente = tecnicoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Técnico não encontrado"));

        tecnicoExistente.setNome(tecnicoAtualizado.getNome());
        tecnicoExistente.setCpf(tecnicoAtualizado.getCpf());
        tecnicoExistente.setDataNasc(tecnicoAtualizado.getDataNasc());

        return tecnicoRepository.save(tecnicoExistente);
    }

    public void deletarTecnicoPorId(Long id) {
        tecnicoRepository.deleteById(id);
    }

    public void deletarTodosTecnicos() {
        tecnicoRepository.deleteAll();
    }
}
