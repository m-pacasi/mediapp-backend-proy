package com.mitocode.service;

import java.util.List;

import com.mitocode.dto.UsuarioRolDTO;
import com.mitocode.model.Menu;

public interface IMenuService extends ICRUD<Menu>{
	
	List<Menu> listarMenuPorUsuario(String nombre);
	
	List<UsuarioRolDTO> listarUsuarioRol(String nombre);
}
