
public class PocimaPorcentaje extends Pocima{

	
	//ESTADO
	
	private int porcentaje;
	
	
	//CONSTRUCTOR
	
	public PocimaPorcentaje(String nombre, int porcentaje) {
		super(nombre);
		this.porcentaje = porcentaje;
	}

	
	//COMPORTAMIENTO
	
	//OVERRIDES
	
	@Override	//Incrementa o decrementa el valor de un atributo seg�n el porcentaje de esta p�cima
	public void aplicarPocima(Atributo atributo) {
		// TODO Auto-generated method stub
		atributo.setValorNumerico(atributo.getValorNumerico() + ((atributo.getValorNumerico()*porcentaje) / 100));
	}
	
}
