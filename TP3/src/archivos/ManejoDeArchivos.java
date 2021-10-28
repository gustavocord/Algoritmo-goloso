package archivos;

import java.io.BufferedReader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;



public class ManejoDeArchivos {
	
	
	static String miDirectorio() {
		   File miDir = new File (".");
		   String dir="";
		     try {
		        dir= miDir.getCanonicalPath()+"\\src\\archivos\\archivo.txt";
		       return dir;
		       }
		     catch(Exception e) {
		       e.printStackTrace();
		       }
		     return dir;
	}
	
	
	
	
	private String generarJSONPretty()
	{
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json = gson.toJson(this);
		
		return json;
	}
	
	public void generarJSON(String archivo)
	{
		String json = this.generarJSONPretty();
		
		try{
			FileWriter writer = new FileWriter(archivo);
			writer.write(json);
			writer.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}


	
	
	
	   public static void main(String[] args) throws FileNotFoundException, IOException  {
		  
		     }	
	
	    }


