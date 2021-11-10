package archivos;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import logica.Equipo;

public class EquiposJSON implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<Equipo> _listaDeEquipos;
	
	public EquiposJSON(ArrayList<Equipo> calendario) {
		this._listaDeEquipos= calendario;
	}
	public String generarJSON() {
		Gson gson = new GsonBuilder().create();
		String json = gson.toJson(this);
		
		return json;
	}
	public String generarJSONPretty() {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json = gson.toJson(this);
		
		return json;
	}
	public void guardarJSON(String jsonParaGuardar, String archivoDestino) {
		try {
			FileWriter writer = new FileWriter(archivoDestino);
			writer.write(jsonParaGuardar);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static EquiposJSON leerJSON(String archivo) {
		archivo="src/archivos/"+archivo;
		Gson gson = new Gson();
		EquiposJSON ret= null;
		
		try {
			BufferedReader br= new BufferedReader(new FileReader(archivo));
			ret = gson.fromJson(br, EquiposJSON.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return ret;
	}
	public ArrayList<Equipo> getListaDeEquipos() {
		return _listaDeEquipos;
	}
	
}
