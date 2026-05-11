package sissa.NotaZ.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sissa.NotaZ.dto.NotaRequestDTO;
import sissa.NotaZ.dto.NotaResponseDTO;
import sissa.NotaZ.services.NotaService;

import java.util.List;

@RestController
@RequestMapping(value = "/notas")
public class NotaController {

    private final NotaService notaService;

    public NotaController(NotaService notaService) {
        this.notaService = notaService;
    }

    @PostMapping
    public ResponseEntity<NotaResponseDTO> salvarNota(@RequestBody @Valid NotaRequestDTO dto) {
        NotaResponseDTO dtoSalvar = notaService.salvar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(dtoSalvar);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<NotaResponseDTO> buscarPorId(@PathVariable Long id) {
        NotaResponseDTO obj = notaService.buscarPorId(id);
        return ResponseEntity.ok(obj);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deletarPorId(@PathVariable Long id) {
        notaService.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<NotaResponseDTO>> listarTodos() {
        List<NotaResponseDTO> listDto = notaService.listarTodos();
        return ResponseEntity.ok(listDto);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<NotaResponseDTO> atualizar(@PathVariable Long id, @RequestBody @Valid NotaRequestDTO dto) {
        NotaResponseDTO dtoAtualizado = notaService.atualizar(id, dto);
        return ResponseEntity.ok(dtoAtualizado);
    }
}
