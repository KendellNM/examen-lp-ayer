package com.example.demo.entity;

import java.util.Set;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "usuarios")
public class Usuario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")	
	private Long id;
	
	@Column(name = "username")
	private String username;

	@Column(name = "password")
	private String password;

	@Column(name = "estado")
	private char estado;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "empleado_id", referencedColumnName = "id", nullable = false)
	private Empleado empleado;
	
	@ManyToMany
	@JoinTable(
			name="usuario_rol",
			joinColumns = @JoinColumn(name="usuario_id", referencedColumnName = "id"),
			inverseJoinColumns = @JoinColumn(name="rol_id", referencedColumnName = "id")
			)
	private Set<Rol> roles;
	
}