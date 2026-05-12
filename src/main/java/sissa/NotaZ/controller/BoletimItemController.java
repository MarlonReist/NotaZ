package sissa.NotaZ.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sissa.NotaZ.dto.BoletimItemRequestDTO;
import sissa.NotaZ.dto.BoletimItemResponseDTO;
import sissa.NotaZ.services.BoletimItemService;

import java.util.List;

@RestController
@RequestMapping(value = "/boletins-itens")
public class BoletimItemController {

    private final BoletimItemService boletimItemService;

    public BoletimItemController(BoletimItemService boletimItemService) {
        this.boletimItemService = boletimItemService;
    }

    @PostMapping
    public ResponseEntity<BoletimItemResponseDTO> salvarBoletimItem(@RequestBody @Valid BoletimItemRequestDTO dto) {
        BoletimItemResponseDTO dtoSalvar = boletimItemService.salvar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(dtoSalvar);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<BoletimItemResponseDTO> buscarPorId(@PathVariable Long id) {
        BoletimItemResponseDTO obj = boletimItemService.buscarPorId(id);
        return ResponseEntity.ok(obj);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deletarPorId(@PathVariable Long id) {
        boletimItemService.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<BoletimItemResponseDTO>> listarTodos() {
        List<BoletimItemResponseDTO> listDto = boletimItemService.listarTodos();
        return ResponseEntity.ok(listDto);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<BoletimItemResponseDTO> atualizar(@PathVariable Long id, @RequestBody @Valid BoletimItemRequestDTO dto) {
        BoletimItemResponseDTO dtoAtualizado = boletimItemService.atualizar(id, dto);
        return ResponseEntity.ok(dtoAtualizado);
    }
}
