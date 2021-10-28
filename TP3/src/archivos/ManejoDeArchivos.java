package archivos;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;



public class ManejoDeArchivos {
	
	
	static void muestraContenido(String archivo) throws FileNotFoundException, IOException {
		try {  
		FileInputStream f = new FileInputStream(archivo);
	      Scanner scanner = new Scanner(f);
		
	      while( scanner.hasNextLine() )
	    	  System.out.println( scanner.nextLine() );
		}
		
	 catch(Exception e) {
	       e.printStackTrace();
	       }
}
	
	
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
	
	
	
	   public static void main(String[] args) throws FileNotFoundException, IOException  {
		   muestraContenido(miDirectorio());
		  
		     }	
	
	    }


