package archivos;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;



public class ManejoDeArchivos {
	
	
	static void muestraContenido(String archivo) throws FileNotFoundException, IOException {
	      FileInputStream f = new FileInputStream(archivo);
	      Scanner scanner = new Scanner(f);
	    
	      while( scanner.hasNextInt() )
	    	  System.out.println( scanner.nextInt() );
	}
	
	
	   public static void main(String[] args) throws IOException {
	        muestraContenido("archivo.txt");
	    }

}
