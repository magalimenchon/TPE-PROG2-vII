
public abstract class Pocima {

	
	//ESTADO
	
	private String nombre;

	
	//CONSTRUCTOR
	
	protected Pocima(String nombre) {
		this.nombre = nombre;
	}

	
	//GETTERS & SETTERS
	
	public String getNombre() {
		return nombre;
	}

	
	//COMPORTAMIENTO
	
	//Aplica una pócima a un atributo
	public abstract void aplicarPocima(Atributo atributo);
	
	//OVERRIDES
	
	@Override	//Defino la noción de iguales ya que uso equals en Juego -> addPocima
	public boolean equals(Object objeto) {
		Pocima otroPocima =(Pocima) objeto;
		try {
			return this.getNombre().equals(otroPocima.getNombre());
		}
		catch(Exception e){
			return false;
		}
	}
}
