package archivos;

import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Archivo {

	static String miDirectorio() {
		File miDir = new File(".");
		String dir = "";
		try {
			dir = miDir.getCanonicalPath() + "\\src\\archivos\\archivo.txt";
			return dir;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dir;
	}

	private String generarJSONPretty() {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json = gson.toJson(this);

		return json;
	}

	public void generarJSON(String archivo) {
		String json = this.generarJSONPretty();

		try {
			FileWriter writer = new FileWriter(archivo);
			writer.write(json);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


}
