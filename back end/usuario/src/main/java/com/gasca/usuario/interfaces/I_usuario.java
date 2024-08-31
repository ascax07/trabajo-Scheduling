package com.gasca.usuario.interfaces;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.gasca.usuario.models.usuario;

@Repository
public interface I_usuario extends CrudRepository<usuario, String> {

    // @Query ("SELECT u FROM usuario u WHERE u.nombre LIKE %?1% OR u.correo_electronico LIKE %?1% OR u.tipo_documento LIKE %?1% OR u.estado LIKE %?1%")
    // List<usuario> filtroUsuario(String filtro);

    @Query ("SELECT u FROM usuario u WHERE TIMESTAMPDIFF(YEAR, u.fecha_nacimiento, NOW())>=18 AND tipo_documento = 'TI'")
    List<usuario> cambiarTipoDocumento();

    @Query("SELECT u FROM usuario u WHERE  DATEDIFF(NOW(), u.fecha_ultima_act_contrasena) >= 0")
    List<usuario> actualizarContraseña(String actualizarContraseña);

    @Query("SELECT u FROM usuario u WHERE  DATEDIFF(NOW(), u.fecha_ultimo_inicio_sesion) >= 0")
    List<usuario> iniciosesionNotificar(String iniciosesionNotificar);

    @Query("SELECT u FROM usuario u WHERE DATEDIFF(NOW(), u.fecha_ultima_act_contrasena) > 90")
    List<usuario> UsuariosSinActualizar(String UsuariosSinActualizar);


}
