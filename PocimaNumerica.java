
public class PocimaNumerica extends Pocima{

	
	//ESTADO
	
	private int numero;

	
	//CONSTRUCTOR
	
	public PocimaNumerica(String nombre, int numero) {
		super(nombre);
		this.numero = numero;
	}

	
	//COMPORTAMIENTO
	
	//OVERRIDES
	
	@Override	//Retorna siempre el número de esta pócima
	public void aplicarPocima(Atributo atributo) {
		// TODO Auto-generated method stub
		atributo.setValorNumerico(numero);
	}
	
}
