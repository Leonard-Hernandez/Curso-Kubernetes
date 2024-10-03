package org.leonard.springcloud.msvc.usuarios.msvc_usuarios.Services;

import org.leonard.springcloud.msvc.usuarios.msvc_usuarios.models.entity.Usuario;

import java.util.List;
import java.util.Optional;

public interface UsuarioService {

    List<Usuario> listar();

    Optional<Usuario> porId(Long id);

    Usuario guardar(Usuario usuario);

    void eliminar(Long id);

}
