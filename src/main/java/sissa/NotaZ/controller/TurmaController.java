package sissa.NotaZ.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sissa.NotaZ.dto.TurmaRequestDTO;
import sissa.NotaZ.dto.TurmaResponseDTO;
import sissa.NotaZ.services.TurmaService;

import java.util.List;

@RestController
@RequestMapping(value = "/turmas")
public class TurmaController {

    private final TurmaService turmaService;

    public TurmaController(TurmaService turmaService) {
        this.turmaService = turmaService;
    }

    @PostMapping
    public ResponseEntity<TurmaResponseDTO> salvarTurma(@RequestBody @Valid TurmaRequestDTO dto) {
        TurmaResponseDTO dtoSalvar = turmaService.salvar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(dtoSalvar);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<TurmaResponseDTO> buscarPorId(@PathVariable Long id) {
        TurmaResponseDTO obj = turmaService.buscarPorId(id);
        return ResponseEntity.ok(obj);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deletarPorId(@PathVariable Long id) {
        turmaService.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<TurmaResponseDTO>> listarTodos() {
        List<TurmaResponseDTO> listDto = turmaService.listarTodos();
        return ResponseEntity.ok(listDto);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<TurmaResponseDTO> atualizar(@PathVariable Long id, @RequestBody @Valid TurmaRequestDTO dto) {
        TurmaResponseDTO dtoAtualizado = turmaService.atualizar(id, dto);
        return ResponseEntity.ok(dtoAtualizado);
    }
}
