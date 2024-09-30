package SupTecnico.example.Suporte.Controllers;

import SupTecnico.example.Suporte.Entity.Tecnico;
import SupTecnico.example.Suporte.Entity.Usuario;
import SupTecnico.example.Suporte.Repositories.TecnicoRepository;
import SupTecnico.example.Suporte.Repositories.UsuarioRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tecnicos")
public class TecnicoController {

    private final TecnicoRepository tecnicoRepository;
    private final UsuarioRepository usuarioRepository;

    public TecnicoController(TecnicoRepository tecnicoRepository, UsuarioRepository usuarioRepository) {
        this.tecnicoRepository = tecnicoRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @GetMapping
    public List<Tecnico> listarTecnicos() {
        return tecnicoRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tecnico> buscarTecnicoPorId(@PathVariable Long id) {
        Tecnico tecnico = tecnicoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Técnico não encontrado"));
        return ResponseEntity.ok(tecnico);
    }

    @PostMapping("/salvar")
    public ResponseEntity<Tecnico> criarTecnico(@RequestBody Tecnico tecnico) {
        if (tecnico.getUsuario() == null || tecnico.getUsuario().getId() == null) {
            return ResponseEntity.badRequest().body(null);
        }

        Usuario usuarioExistente = usuarioRepository.findById(tecnico.getUsuario().getId())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        tecnico.setUsuario(usuarioExistente);

        Tecnico tecnicoSalvo = tecnicoRepository.save(tecnico);
        return ResponseEntity.status(HttpStatus.CREATED).body(tecnicoSalvo);
    }

    @PutMapping("update/{id}")
    public ResponseEntity<Tecnico> atualizarTecnico(@PathVariable Long id, @RequestBody Tecnico tecnicoAtualizado) {
        Tecnico tecnicoExistente = tecnicoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Técnico não encontrado"));

        tecnicoExistente.setNome(tecnicoAtualizado.getNome());
        tecnicoExistente.setCpf(tecnicoAtualizado.getCpf());
        tecnicoExistente.setDataNasc(tecnicoAtualizado.getDataNasc());

        Tecnico tecnicoSalvo = tecnicoRepository.save(tecnicoExistente);
        return ResponseEntity.ok(tecnicoSalvo);
    }

    @DeleteMapping("deletar/{id}")
    public ResponseEntity<Void> deletarTecnicoPorId(@PathVariable Long id) {
        tecnicoRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/todos")
    public ResponseEntity<Void> deletarTodosTecnicos() {
        tecnicoRepository.deleteAll();
        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
}