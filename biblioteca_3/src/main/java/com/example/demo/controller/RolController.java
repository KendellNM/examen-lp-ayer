package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.example.demo.entity.Rol;
import com.example.demo.service.RolService;


import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/roles")
public class RolController {

	@Autowired
	private RolService rolService;
	
	@GetMapping
	public ResponseEntity<List<Rol>> readAll(){
		try {
			List<Rol> Rol = rolService.readAll();
			if(Rol.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(Rol, HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@PostMapping
	public ResponseEntity<Rol> crear(@Valid @RequestBody Rol cat){
		try {
			Rol c = rolService.create(cat);
			return new ResponseEntity<>(c, HttpStatus.CREATED);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	@GetMapping("/{id}")
	public ResponseEntity<Rol> getAutorId(@PathVariable("id") Long id){
		try {
			Rol c = rolService.read(id).get();
			return new ResponseEntity<>(c, HttpStatus.CREATED);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<Rol> delRol(@PathVariable("id") Long id){
		try {
			rolService.delete(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	@PutMapping("/{id}")
	public ResponseEntity<?> updateRol(@PathVariable("id") Long id, @Valid @RequestBody Rol cat){

			Optional<Rol> c = rolService.read(id);
			if(c.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);

			}else {
				return new ResponseEntity<>(rolService.update(cat), HttpStatus.OK);

			}		
		
	}
	
}
