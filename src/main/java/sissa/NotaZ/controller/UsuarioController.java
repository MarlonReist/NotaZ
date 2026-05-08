package sissa.NotaZ.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sissa.NotaZ.dto.UsuarioRequestDTO;
import sissa.NotaZ.dto.UsuarioResponseDTO;
import sissa.NotaZ.services.UsuarioService;

import java.util.List;

@RestController
@RequestMapping(value = "/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;


    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping
    public ResponseEntity<UsuarioResponseDTO> salvarUsuario(@RequestBody @Valid UsuarioRequestDTO dto) {
        UsuarioResponseDTO dtoSalvar = usuarioService.salvar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(dtoSalvar);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<UsuarioResponseDTO> buscarPorId(@PathVariable Long id) {
        UsuarioResponseDTO obj = usuarioService.buscarPorId(id);
        return ResponseEntity.ok(obj);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deletarPorId(@PathVariable Long id) {
        usuarioService.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<UsuarioResponseDTO>> listarTodos() {
        List<UsuarioResponseDTO> listDto = usuarioService.listarTodos();
        return ResponseEntity.ok(listDto);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<UsuarioResponseDTO> atualizar(@PathVariable Long id, @RequestBody @Valid UsuarioRequestDTO dto) {
        UsuarioResponseDTO dtoAtualizado = usuarioService.atualizar(id, dto);
        return ResponseEntity.ok(dtoAtualizado);
    }

    @PutMapping (value = "/{id}/desativar")
    public ResponseEntity<UsuarioResponseDTO> desativar(@PathVariable Long id){
        UsuarioResponseDTO usuarioDesativado = usuarioService.desativar(id);
        return ResponseEntity.ok().body(usuarioDesativado);
    }

    @PutMapping (value = "/{id}/ativar")
    public ResponseEntity<UsuarioResponseDTO> ativar(@PathVariable Long id){
        UsuarioResponseDTO usuarioAtivado = usuarioService.ativar(id);
        return ResponseEntity.ok().body(usuarioAtivado);
    }
}
