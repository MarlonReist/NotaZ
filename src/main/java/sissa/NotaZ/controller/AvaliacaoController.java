package sissa.NotaZ.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sissa.NotaZ.dto.AvaliacaoRequestDTO;
import sissa.NotaZ.dto.AvaliacaoResponseDTO;
import sissa.NotaZ.services.AvaliacaoService;

import java.util.List;

@RestController
@RequestMapping(value = "/avaliacoes")
public class AvaliacaoController {

    private final AvaliacaoService avaliacaoService;

    public AvaliacaoController(AvaliacaoService avaliacaoService) {
        this.avaliacaoService = avaliacaoService;
    }

    @PostMapping
    public ResponseEntity<AvaliacaoResponseDTO> salvarAvaliacao(@RequestBody @Valid AvaliacaoRequestDTO dto) {
        AvaliacaoResponseDTO dtoSalvar = avaliacaoService.salvar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(dtoSalvar);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<AvaliacaoResponseDTO> buscarPorId(@PathVariable Long id) {
        AvaliacaoResponseDTO obj = avaliacaoService.buscarPorId(id);
        return ResponseEntity.ok(obj);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deletarPorId(@PathVariable Long id) {
        avaliacaoService.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<AvaliacaoResponseDTO>> listarTodos() {
        List<AvaliacaoResponseDTO> listDto = avaliacaoService.listarTodos();
        return ResponseEntity.ok(listDto);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<AvaliacaoResponseDTO> atualizar(@PathVariable Long id, @RequestBody @Valid AvaliacaoRequestDTO dto) {
        AvaliacaoResponseDTO dtoAtualizado = avaliacaoService.atualizar(id, dto);
        return ResponseEntity.ok(dtoAtualizado);
    }
}
