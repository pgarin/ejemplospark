package com.ejemplo.spark.servicios;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import com.ejemplo.spark.beans.Alumno;

public class AlumnoService {

	public static Map<String, Alumno> alumnos = new HashMap<>();
	private static final AtomicInteger contador = new AtomicInteger(0);
	
	public Alumno findById(String id) {
		return alumnos.get(id);
	}
	
	public Alumno add(String nombre, String apellido) {
		int cnt = contador.incrementAndGet();
		Alumno a = new Alumno(cnt, nombre, apellido);
		alumnos.put(String.valueOf(cnt), a);
		return a;
	}
	
	public Alumno update (String id, String nombre, String apellido) {
		Alumno a = alumnos.get(id);
		if (a != null) {
			if (nombre != null)
				a.setNombre(nombre);
			if (apellido != null) {
				a.setApellido(apellido);
			}
		}
		alumnos.put(id, a);
		return a;
	}
	
	
	public void delete (String id) {
		alumnos.remove(id);
	}
	
	public List findAll() {
		return new ArrayList<>(alumnos.values());
	}
	
	public AlumnoService() {
		
	}
	
	
}
