package logica;

public class Arbitro {

	private int numero ;
	
	Arbitro(int nro){
		this.numero=nro;
	}

	public int getNro() {
		return numero;
	}

	public void setNro(int nro) {
		this.numero = nro;
	}

	@Override
	public String toString() {
		return "Arbitro [nro=" + numero + "]";
	}
	
	
	
}
