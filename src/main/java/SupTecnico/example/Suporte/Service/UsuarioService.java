package SupTecnico.example.Suporte.Service;

import SupTecnico.example.Suporte.Entity.Usuario;
import SupTecnico.example.Suporte.Repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Usuario> listarUsuarios() {
        try {
            return usuarioRepository.findAll();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                    "Erro ao listar usuários: " + e.getMessage(), e);
        }
    }

    public Usuario findById(Long id) {
        return usuarioRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                "Usuário com o ID " + id + " não encontrado"));
    }

    public Usuario criarUsuario(Usuario usuario) {
        try {
            return usuarioRepository.save(usuario);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Erro ao cadastrar o usuário: " + e.getMessage(), e);
        }
    }

    public Usuario atualizarUsuario(Long id, Usuario usuarioAtualizado) {
        try {
            Usuario usuarioExistente = findById(id);
            usuarioExistente.setNome(usuarioAtualizado.getNome());
            usuarioExistente.setCpf(usuarioAtualizado.getCpf());
            usuarioExistente.setDataNasc(usuarioAtualizado.getDataNasc());
            usuarioExistente.setEmail(usuarioAtualizado.getEmail());
            return usuarioRepository.save(usuarioExistente);
        } catch (ResponseStatusException e) {
            throw e;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                    "Erro ao atualizar o usuário: " + e.getMessage(), e);
        }
    }

    public void deletarUsuarioPorId(Long id) {
        try {
            Usuario usuario = findById(id); // Verifica se o usuário existe antes de deletar
            usuarioRepository.deleteById(usuario.getId());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                    "Erro ao deletar o usuário: " + e.getMessage(), e);
        }
    }

    public void deletarTodosUsuarios() {
        try {
            usuarioRepository.deleteAll();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                    "Erro ao deletar todos os usuários: " + e.getMessage(), e);
        }
    }
}
