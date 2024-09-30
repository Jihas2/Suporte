package SupTecnico.example.Suporte.Service;

import SupTecnico.example.Suporte.Entity.Tecnico;
import SupTecnico.example.Suporte.Entity.Usuario;
import SupTecnico.example.Suporte.Repositories.TecnicoRepository;
import SupTecnico.example.Suporte.Repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class TecnicoService {

    @Autowired
    private TecnicoRepository tecnicoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Tecnico criarTecnico(Tecnico tecnico) {
        try {
            Usuario usuarioExistente = usuarioRepository.findById(tecnico.getUsuario().getId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado"));
            tecnico.setUsuario(usuarioExistente);
            return tecnicoRepository.save(tecnico);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Erro ao criar técnico: " + e.getMessage(), e);
        }
    }

    public Tecnico findById(Long id) {
        return tecnicoRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Técnico com o ID " + id + " não encontrado"));
    }

    public Tecnico atualizarTecnico(Long id, Tecnico tecnicoAtualizado) {
        try {
            Tecnico tecnicoExistente = findById(id);
            tecnicoExistente.setNome(tecnicoAtualizado.getNome());
            tecnicoExistente.setCpf(tecnicoAtualizado.getCpf());
            tecnicoExistente.setDataNasc(tecnicoAtualizado.getDataNasc());
            return tecnicoRepository.save(tecnicoExistente);
        } catch (ResponseStatusException e) {
            throw e;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao atualizar técnico: " + e.getMessage(), e);
        }
    }

    public List<Tecnico> listarTecnicos() {
        try {
            return tecnicoRepository.findAll();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao listar técnicos: " + e.getMessage(), e);
        }
    }

    public void deletarTecnicoPorId(Long id) {
        try {
            tecnicoRepository.deleteById(id);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao deletar técnico: " + e.getMessage(), e);
        }
    }

    public void deletarTodosTecnicos() {
        try {
            tecnicoRepository.deleteAll();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao deletar todos os técnicos: " + e.getMessage(), e);
        }
    }
}
