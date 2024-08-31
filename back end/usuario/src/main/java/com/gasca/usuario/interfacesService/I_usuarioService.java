package com.gasca.usuario.interfacesService;


import java.util.List;
import java.util.Optional;

import com.gasca.usuario.models.usuario;


public interface I_usuarioService {
    
    public String save(usuario usuario);    
    public List<usuario> findAll();
    // List<usuario> filtroUsuario(String filtro);
    public Optional<usuario> findOne(String id);
    public int delete(String id);
    public List<usuario> cambiarTipoDocumento();
    public List<usuario> actualizarContraseña(String actualizarContraseña);
    public List<usuario> iniciosesionNotificar(String iniciosesionNotificar);
    public List<usuario> UsuariosSinActualizar(String UsuariosSinActualizar);

    
}