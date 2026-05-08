package sissa.NotaZ.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sissa.NotaZ.dto.DisciplinaRequestDTO;
import sissa.NotaZ.dto.DisciplinaResponseDTO;
import sissa.NotaZ.services.DisciplinaService;

import java.util.List;

@RestController
@RequestMapping(value = "/disciplinas")
public class DisciplinaController {

    private final DisciplinaService disciplinaService;

    public DisciplinaController(DisciplinaService disciplinaService) {
        this.disciplinaService = disciplinaService;
    }

    @PostMapping
    public ResponseEntity<DisciplinaResponseDTO> salvarDisciplina(@RequestBody @Valid DisciplinaRequestDTO dto) {
        DisciplinaResponseDTO dtoSalvar = disciplinaService.salvar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(dtoSalvar);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<DisciplinaResponseDTO> buscarPorId(@PathVariable Long id) {
        DisciplinaResponseDTO obj = disciplinaService.buscarPorId(id);
        return ResponseEntity.ok(obj);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deletarPorId(@PathVariable Long id) {
        disciplinaService.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<DisciplinaResponseDTO>> listarTodos() {
        List<DisciplinaResponseDTO> listDto = disciplinaService.listarTodos();
        return ResponseEntity.ok(listDto);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<DisciplinaResponseDTO> atualizar(@PathVariable Long id, @RequestBody @Valid DisciplinaRequestDTO dto) {
        DisciplinaResponseDTO dtoAtualizado = disciplinaService.atualizar(id, dto);
        return ResponseEntity.ok(dtoAtualizado);
    }
}
