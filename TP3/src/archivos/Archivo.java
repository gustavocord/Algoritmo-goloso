package archivos;

import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import logica.Arbitro;
import logica.Equipo;
import logica.Torneo;

public class Archivo {
	
// como en el archivo no se agrega se deberia agregar en el main las fechas
	static String miDirectorio() {
		File miDir = new File(".");
		String dir = "";
		try {
			dir = miDir.getCanonicalPath() + "\\src\\archivos\\calendario.JSON";
			return dir;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dir;
	}


	

	public static Torneo leerJSON() {
		String archivo=miDirectorio();
		Gson gson = new Gson();
		Torneo ret = null;

		try {
			BufferedReader br = new BufferedReader(new FileReader(archivo));
			ret = gson.fromJson(br, Torneo.class);
		} catch (IOException e) {

			e.printStackTrace();
		}

		return ret;
	}
	
	
	public static void  imprimirJSON(String archivo) {
		Gson gson = new Gson();
		Torneo ret = null;

		try {
			BufferedReader br = new BufferedReader(new FileReader(archivo));
			ret = gson.fromJson(br, Torneo.class);
			System.out.println(ret);
			
		} catch (IOException e) {

			e.printStackTrace();
		}


	}
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//para prueba
		///// Archivo.imprimirJSON(miDirectorio());
      

		
		
		

	}

}
