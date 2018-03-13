package com.ejemplo.spark;

import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.delete;

import static spark.Spark.put;

import static spark.Spark.port;

import java.util.List;

import com.ejemplo.spark.beans.Alumno;
import com.ejemplo.spark.servicios.AlumnoService;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ServicioREST {
	
	private static AlumnoService alumnoService = new AlumnoService();
	
	private static ObjectMapper objMap = new ObjectMapper(); 
	
	public static void main (String[] args) {
		port(8080);
		
		get("/", (req, res) -> "Guenassss");
		
		get("/alumno", (req, res) -> {
			res.status(200);
			
			List alumnos = alumnoService.findAll();
			
			if (alumnos.isEmpty()) {
				return objMap.writeValueAsString("No existen alumnos");
			} else {
				return objMap.writeValueAsString(alumnos);
			}
		});
		
		get("/alumno/:id", (req, res) -> {
			
			String id = req.params(":id");
			Alumno a = alumnoService.findById(id);
			
			if (a != null) {
				return objMap.writeValueAsString(a);
			} else {
				res.status(404);
				return objMap.writeValueAsString("No existe el alumno indicado.");
			}
		});
		
		post("/alumno/add", (req, res) -> {
			
			String nombre = req.queryParams("nombre");
			String apellido = req.queryParams("apellido");
			
			Alumno a = alumnoService.add(nombre, apellido);
			res.status(201);
			return objMap.writeValueAsString(a);
		});
		
		put("/alumno/:id", (req, res) -> {
			
			String id = req.params(":id");
			Alumno a = alumnoService.findById(id);
			if (a != null) {
				String nombre = req.queryParams("nombre");
				String apellido = req.queryParams("apellido");
				a = alumnoService.update(id, nombre, apellido);
				
				res.status(201);
				return objMap.writeValueAsString(a); 
			} else {
				res.status(404);
				return objMap.writeValueAsString("No existe el alumno indicado.");
			}
		});
		
		delete("/alumno/:id", (req, res) -> {
			String id = req.params(":id");
			Alumno a = alumnoService.findById(id);
			
			if (a != null) {
				alumnoService.delete(id);
				res.status(201);
				return objMap.writeValueAsString(id);
			} else {
				res.status(404);
				return objMap.writeValueAsString("No existe el alumno indicado.");
			}
		});
		
	}
	
}
