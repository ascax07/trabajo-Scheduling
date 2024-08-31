package com.gasca.usuario.Taks;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.gasca.usuario.models.usuario;
import com.gasca.usuario.service.emailService;
import com.gasca.usuario.service.usuarioService;



@Component
public class Taks {

    @Autowired
    private usuarioService data;

    @Autowired
    private emailService email;

    @Scheduled(cron = "0 0 8 * * ?") // notifica a las 8:00 AM cada día
    public void sendNotificationcron() {
        var listaUsuario=data.cambiarTipoDocumento();
        for (usuario usuario : listaUsuario) {
            System.out.println("usuario que requiere actualizar documento "+ 
            usuario.getNombre());
            email.cambiarTipoDocumento(usuario);
        }
    }


    @Scheduled(fixedRate = 60000)
    public void notifyLogin() {
        var listaUsuario = data.iniciosesionNotificar("");
        for (usuario usuario : listaUsuario) {
            System.out.println("Usuario que requiere iniciar sesión: " + usuario.getNombre());
            email.iniciosesionNotificar(usuario.getCorreo_electronico());
        }
    }

    @Scheduled(cron = "0 */30 * * * ?") // Cada 30 minutos  
    public void notifyPasswordUpdate() {
        var listaUsuario = data.actualizarContraseña("");
        for (usuario usuario : listaUsuario) {
            System.out.println("Usuario que requiere actualizar contraseña: " + usuario.getNombre());
            email.actualizarContraseña(usuario.getCorreo_electronico());
        }
    }

    @Scheduled(cron = "0 0 8 * * ?") // A las 8:00 AM cada día
public void enviarRecordatorioActualizacion() {
    var listaUsuario = data.UsuariosSinActualizar("");
    for (usuario usuario : listaUsuario) {
        System.out.println("Usuario requiere actualizar los datos: " + usuario.getNombre());
        email.UsuariosSinActualizar(usuario.getCorreo_electronico());
    }
}



}