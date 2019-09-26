package com.mitocode.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mitocode.model.Menu;

public interface IMenuRepo extends JpaRepository<Menu, Integer>{
	
	@Query(value="select m.* from menu_rol mr inner join usuario_rol ur on ur.id_rol = mr.id_rol inner join menu m on m.id_menu = mr.id_menu inner join usuario u on u.id_usuario = ur.id_usuario where u.nombre = :nombre", nativeQuery = true)
	List<Object[]> listarMenuPorUsuario(@Param("nombre") String nombre);

	@Query(value="select u.nombre,r.descripcion from usuario_rol ur\r\n" + 
			"	inner join rol r \r\n" + 
			"	on ur.id_rol = r.id_rol \r\n" + 
			"	inner join usuario u \r\n" + 
			"	on u.id_usuario = ur.id_usuario where u.nombre = :nombre ORDER BY u.nombre ASC LIMIT 1", nativeQuery = true)
	List<Object[]> listarUsuarioRol(@Param("nombre") String nombre);
	
}
