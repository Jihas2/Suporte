package SupTecnico.example.Suporte.Controllers;

import SupTecnico.example.Suporte.Entity.Tecnico;
import SupTecnico.example.Suporte.Repositories.TecnicoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tecnicos")
public class TecnicoController {

    private final TecnicoRepository tecnicoRepository;

    public TecnicoController(TecnicoRepository tecnicoRepository) {
        this.tecnicoRepository = tecnicoRepository;
    }

    @PostMapping
    public ResponseEntity<Tecnico> criarTecnico(@RequestBody Tecnico tecnico) {
        if (tecnico.getUsuario() == null || tecnico.getUsuario().getId() == null) {
            return ResponseEntity.badRequest().build();
        }

        Tecnico tecnicoSalvo = tecnicoRepository.save(tecnico);
        return ResponseEntity.status(HttpStatus.CREATED).body(tecnicoSalvo);
    }

    @GetMapping
    public List<Tecnico> listarTecnicos() {
        return tecnicoRepository.findAll();
    }

    @GetMapping("/{id}")
    public Tecnico buscarTecnicoPorId(@PathVariable Long id) {
        return tecnicoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Técnico não encontrado"));
    }

    @PutMapping("/{id}")
    public Tecnico atualizarTecnico(@PathVariable Long id, @RequestBody Tecnico tecnicoAtualizado) {
        Tecnico tecnicoExistente = tecnicoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Técnico não encontrado"));

        tecnicoExistente.setNome(tecnicoAtualizado.getNome());
        tecnicoExistente.setCpf(tecnicoAtualizado.getCpf());
        tecnicoExistente.setDataNasc(tecnicoAtualizado.getDataNasc());

        return tecnicoRepository.save(tecnicoExistente);
    }

    @DeleteMapping("/{id}")
    public void deletarTecnicoPorId(@PathVariable Long id) {
        tecnicoRepository.deleteById(id);
    }

    @DeleteMapping
    public void deletarTodosTecnicos() {
        tecnicoRepository.deleteAll();
    }
}


