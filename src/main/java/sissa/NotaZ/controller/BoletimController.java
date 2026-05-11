package sissa.NotaZ.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sissa.NotaZ.dto.BoletimRequestDTO;
import sissa.NotaZ.dto.BoletimResponseDTO;
import sissa.NotaZ.services.BoletimService;

import java.util.List;

@RestController
@RequestMapping(value = "/boletins")
public class BoletimController {

    private final BoletimService boletimService;

    public BoletimController(BoletimService boletimService) {
        this.boletimService = boletimService;
    }

    @PostMapping
    public ResponseEntity<BoletimResponseDTO> salvarBoletim(@RequestBody @Valid BoletimRequestDTO dto) {
        BoletimResponseDTO dtoSalvar = boletimService.salvar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(dtoSalvar);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<BoletimResponseDTO> buscarPorId(@PathVariable Long id) {
        BoletimResponseDTO obj = boletimService.buscarPorId(id);
        return ResponseEntity.ok(obj);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deletarPorId(@PathVariable Long id) {
        boletimService.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<BoletimResponseDTO>> listarTodos() {
        List<BoletimResponseDTO> listDto = boletimService.listarTodos();
        return ResponseEntity.ok(listDto);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<BoletimResponseDTO> atualizar(@PathVariable Long id, @RequestBody @Valid BoletimRequestDTO dto) {
        BoletimResponseDTO dtoAtualizado = boletimService.atualizar(id, dto);
        return ResponseEntity.ok(dtoAtualizado);
    }
}
