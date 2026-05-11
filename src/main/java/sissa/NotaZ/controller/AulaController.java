package sissa.NotaZ.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sissa.NotaZ.dto.AulaRequestDTO;
import sissa.NotaZ.dto.AulaResponseDTO;
import sissa.NotaZ.services.AulaService;

import java.util.List;

@RestController
@RequestMapping(value = "/aulas")
public class AulaController {

    private final AulaService aulaService;

    public AulaController(AulaService aulaService) {
        this.aulaService = aulaService;
    }

    @PostMapping
    public ResponseEntity<AulaResponseDTO> salvarAula(@RequestBody @Valid AulaRequestDTO dto) {
        AulaResponseDTO dtoSalvar = aulaService.salvar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(dtoSalvar);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<AulaResponseDTO> buscarPorId(@PathVariable Long id) {
        AulaResponseDTO obj = aulaService.buscarPorId(id);
        return ResponseEntity.ok(obj);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deletarPorId(@PathVariable Long id) {
        aulaService.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<AulaResponseDTO>> listarTodos() {
        List<AulaResponseDTO> listDto = aulaService.listarTodos();
        return ResponseEntity.ok(listDto);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<AulaResponseDTO> atualizar(@PathVariable Long id, @RequestBody @Valid AulaRequestDTO dto) {
        AulaResponseDTO dtoAtualizado = aulaService.atualizar(id, dto);
        return ResponseEntity.ok(dtoAtualizado);
    }
}
