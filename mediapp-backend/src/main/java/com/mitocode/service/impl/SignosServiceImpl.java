package com.mitocode.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import com.mitocode.model.Signos;
import com.mitocode.repo.ISignosRepo;
import com.mitocode.service.ISignosService;

@Service
public class SignosServiceImpl implements ISignosService{
	
	@Autowired	
	private ISignosRepo repo;

	@Override
	public Signos registrar(Signos obj) {

		return repo.save(obj);
	}

	@Override
	public Signos modificar(Signos obj) {
		
		return repo.save(obj);
	}

	@Override
	public Signos leerPorId(Integer id) {
		
		return repo.findOne(id);
	}

	@PreAuthorize("@restAuthServiceImpl.hasAccess('listar')")
	//@PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
	@Override
	public List<Signos> listar() {
		return repo.findAll();
	}

	@Override
	public void eliminar(Integer id) {
		repo.delete(id);
	}

}
