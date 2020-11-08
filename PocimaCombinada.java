
public class PocimaCombinada extends Pocima{

	
	//ESTADO
	
	private Pocima primeraPocima;
	private Pocima segundaPocima;

	
	//CONSTRUCTOR
	
	public PocimaCombinada(String nombre, Pocima primeraPocima, Pocima segundaPocima) {
		super(nombre);
		this.primeraPocima = primeraPocima;
		this.segundaPocima = segundaPocima;
	}
	
	
	//COMPORTAMIENTO
	
	//OVERRIDES
	
	@Override	//Concatena la aplicación de diferentes pócimas
	public void aplicarPocima(Atributo atributo) {
		// TODO Auto-generated method stub
		primeraPocima.aplicarPocima(atributo);
		segundaPocima.aplicarPocima(atributo);
	}
	
}
