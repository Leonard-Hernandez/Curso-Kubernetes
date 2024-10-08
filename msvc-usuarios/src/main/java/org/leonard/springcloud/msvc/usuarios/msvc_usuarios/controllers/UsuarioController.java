package org.leonard.springcloud.msvc.usuarios.msvc_usuarios.controllers;

import org.leonard.springcloud.msvc.usuarios.msvc_usuarios.Services.UsuarioService;
import org.leonard.springcloud.msvc.usuarios.msvc_usuarios.models.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class UsuarioController {
    @Autowired
    private UsuarioService userService;

    @GetMapping
    public List<Usuario> listar() {
        return userService.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> detalle(@PathVariable Long id){
        Optional<Usuario> usuarioOptional = userService.porId(id);
        if (usuarioOptional.isPresent()){
            return ResponseEntity.ok(usuarioOptional.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Usuario crear(@RequestBody Usuario usuario){
        return userService.guardar(usuario);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editar(@RequestBody Usuario usuario, @PathVariable Long id){
        Optional<Usuario> o = userService.porId(id);
        if (o.isPresent()){
            Usuario usuarioDb = o.get();
            usuarioDb.setNombre(usuario.getNombre());
            usuarioDb.setEmail(usuario.getEmail());
            usuarioDb.setPassword(usuario.getPassword());

            return ResponseEntity.status(HttpStatus.CREATED).body(userService.guardar(usuarioDb));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id){
        Optional<Usuario> o = userService.porId(id);
        if (o.isPresent()){
            userService.eliminar(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
