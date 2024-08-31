package com.gasca.usuario.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gasca.usuario.interfaces.I_usuario;
import com.gasca.usuario.interfacesService.I_usuarioService;
import com.gasca.usuario.models.usuario;



@Service
public class usuarioService implements I_usuarioService{
    
    @Autowired
    private I_usuario data;




    @Override
    public List<usuario> findAll() {
        List<usuario> listaUsuario = (List<usuario>) data.findAll();
        
        return listaUsuario;
    }


//   @Override
//     public List<usuario> filtroUsuario(String filtro) {
//         return data.filtroUsuario(filtro);
//     }


    @Override
    public Optional<usuario> findOne(String id) {
        Optional<usuario> usuario = data.findById(id);
        
        return usuario;
    }

    @Override
    public int delete(String id) {
        data.deleteById(id);
        return 1;
    }

    @Override
    public String save(usuario usuario) {
        data.save(usuario);
        return usuario.getId();
    }


    @Override
    public List<usuario> cambiarTipoDocumento() {
        List<usuario> listaUsuario = data.cambiarTipoDocumento();
        return listaUsuario;
    }

    
    @Override
    public List<usuario> actualizarContraseña(String actualizarContraseña) {
        List<usuario> listaUsuario = data.actualizarContraseña(actualizarContraseña);
        return listaUsuario;
    }

    
    @Override
    public List<usuario> iniciosesionNotificar(String iniciosesionNotificar) {
        List<usuario> listaUsuario = data.iniciosesionNotificar(iniciosesionNotificar);
        return listaUsuario;
    }


    @Override
    public List<usuario> UsuariosSinActualizar(String UsuariosSinActualizar) {
        List<usuario> listaUsuario = data.UsuariosSinActualizar(UsuariosSinActualizar);
        return listaUsuario;
    }



}
