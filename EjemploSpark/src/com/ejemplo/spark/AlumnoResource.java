package com.ejemplo.spark;

import static spark.Spark.get;
import static spark.Spark.port;

public class AlumnoResource {
	
	public static void main (String[] args) {
		port(8080);
		get("/listar", (req, res) -> {
			res.status(200);
			res.type("application/json");
			return "{nombre:pablo, apellido: garin}";
		});
		
		
	}
	
}
