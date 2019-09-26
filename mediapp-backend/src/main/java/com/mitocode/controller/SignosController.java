package com.mitocode.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.mitocode.model.Signos;
import com.mitocode.service.ISignosService;
import com.mitocode.exception.ModelNotFoundException;

@RestController
@RequestMapping("/signos")
public class SignosController {
	
	@Autowired
	private ISignosService service;
	
	@GetMapping	
	public ResponseEntity<List<Signos>> listar(){
		List<Signos> lista = service.listar();
		return new ResponseEntity<List<Signos>>(lista, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Signos> leerPorId(@PathVariable("id") Integer id) {
		Signos obj = service.leerPorId(id);
		if(obj == null) {
			throw new ModelNotFoundException("ID NO ENCONTRADO: " + id);
		}
		return new ResponseEntity<Signos>(obj, HttpStatus.OK);
	}
	
	@GetMapping("/hateoas/{id}")
	public Resource<Signos> leerPorIdHateoas(@PathVariable("id") Integer id) {
		Signos obj = service.leerPorId(id);
		if(obj == null) {
			throw new ModelNotFoundException("ID NO ENCONTRADO: " + id);
		}
		
		Resource<Signos> resource = new Resource<Signos>(obj);
		// localhost:8080/pacientes/{id}
		ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).leerPorId(id));
		resource.add(linkTo.withRel("signos-resource"));
		return resource;
		
	}
	
	@PostMapping
	public ResponseEntity<Object> registrar(@Valid @RequestBody Signos sig) {
		Signos signos = service.registrar(sig);
		// localhost:8080/pacientes/1
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(signos.getIdSignos()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	@PutMapping
	public ResponseEntity<Object>  modificar(@Valid @RequestBody Signos sig) {
		service.modificar(sig);
		return new ResponseEntity<Object>(HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object>  eliminar(@PathVariable("id") Integer id) {
		Signos obj = service.leerPorId(id);
		if(obj == null) {
			throw new ModelNotFoundException("ID NO ENCONTRADO: " + id);
		}else {
			service.eliminar(id);
		}
		return new ResponseEntity<Object>(obj, HttpStatus.OK);
	}


}
