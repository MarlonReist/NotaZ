package sissa.NotaZ.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sissa.NotaZ.dto.FrequenciaRequestDTO;
import sissa.NotaZ.dto.FrequenciaResponseDTO;
import sissa.NotaZ.services.FrequenciaService;

import java.util.List;

@RestController
@RequestMapping(value = "/frequencias")
public class FrequenciaController {

    private final FrequenciaService frequenciaService;

    public FrequenciaController(FrequenciaService frequenciaService) {
        this.frequenciaService = frequenciaService;
    }

    @PostMapping
    public ResponseEntity<FrequenciaResponseDTO> salvarFrequencia(@RequestBody @Valid FrequenciaRequestDTO dto) {
        FrequenciaResponseDTO dtoSalvar = frequenciaService.salvar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(dtoSalvar);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<FrequenciaResponseDTO> buscarPorId(@PathVariable Long id) {
        FrequenciaResponseDTO obj = frequenciaService.buscarPorId(id);
        return ResponseEntity.ok(obj);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deletarPorId(@PathVariable Long id) {
        frequenciaService.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<FrequenciaResponseDTO>> listarTodos() {
        List<FrequenciaResponseDTO> listDto = frequenciaService.listarTodos();
        return ResponseEntity.ok(listDto);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<FrequenciaResponseDTO> atualizar(@PathVariable Long id, @RequestBody @Valid FrequenciaRequestDTO dto) {
        FrequenciaResponseDTO dtoAtualizado = frequenciaService.atualizar(id, dto);
        return ResponseEntity.ok(dtoAtualizado);
    }
}
