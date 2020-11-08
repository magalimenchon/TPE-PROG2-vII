import java.util.ArrayList;


public class Carta {
	
	
	//ESTADO
	
	private String nombre;
	private ArrayList<Atributo> atributos;
	private Pocima pocima;
	private boolean usoPocima = false;
	
	
	//CONSTRUCTOR
	
	public Carta(String nombre) {
		this.nombre = nombre;
		this.atributos = new ArrayList<Atributo>();
	}
	
	
	//GETTERS & SETTERS
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	private ArrayList<Atributo> getAtributos() { 
		return new ArrayList<>(this.atributos);
	}
	
	public Pocima getPocima() {
		return this.pocima;
	}
	
	public void setPocima(Pocima pocima) {
		this.pocima = pocima;
	}
	
	public boolean isUsoPocima() {
		return usoPocima;
	}
	
	//COMPORTAMIENTO


	//Agrega un atributo al conjunto de atributos de la carta.
	public void addAtributo(Atributo nuevoAtributo){
		if(!this.contieneAtributo(nuevoAtributo))
				atributos.add(nuevoAtributo);
	}
	
	//Retorna la cantidad de atributos que tiene la carta.
	public int getCantidadAtributos() {
		return atributos.size();
	}
	
	
	//ESTRATEGIA SOBRE LA CARTA
	
	//TIMBERO
	//Retorna el nombre de un atributo aleatorio, dentro de la cantidad de atributos que tiene la carta.
	public String getAtributoAletorio() {
		
		int pos = (int) Math.floor( (Math.random() * getCantidadAtributos()) );
		//casting porque random retorna double y la posición debe ser int
		
		return atributos.get(pos).getNombre();
	}
	
	//AMBISIOSO
	//Retorna el nombre del atributo con mayor valor, dentro de la cantidad de atributos que tiene la carta.
	public String getAtributoMayorValor() {
		
		Atributo atributoMayorValor = atributos.get(0);
		
		for(int i = 1; i < atributos.size(); i++){
			if(atributoMayorValor.compareTo(atributos.get(i)) > 0){
				atributoMayorValor = atributos.get(i);
			}
		}
		
		return atributoMayorValor.getNombre();
	}
	
	//OBSTINADO
	//Busca un atributo por nombre en el conjunto de atributos.
	public Atributo getAtributoPorNombre(String nombreBuscado) {
		
		for (int i = 0; i < atributos.size(); i++) {
			Atributo atributoActual = atributos.get(i);
			if(atributoActual.getNombre().equals(nombreBuscado)) {
				return atributoActual;
			}
		}
		return null;
	}
	
	//Retorna si la carta contiene un atributo.
	public boolean contieneAtributo(Atributo atributoBuscado) {
		return atributos.contains(atributoBuscado);
	}
	
	//Son cartas iguales si tienen la misma cantidad y tipos de atributos.
	public boolean cartasIguales(Carta cartaAComparar){	
		if (this.getCantidadAtributos() == cartaAComparar.getCantidadAtributos()) {
			//si tienen la misma cantidad de atributos
			if (atributos.containsAll(cartaAComparar.getAtributos()))
				//si la carta tiene todos los atributos de la otra
				      return true;
		}
	    return false;
	}
	
	//Aplicar una pocima a un atributo
	public void aplicarPocima(Atributo atributo){
		if(this.tienePocima() && !this.isUsoPocima() && 
		this.getAtributoPorNombre(atributo.getNombre()) != null){
			this.pocima.aplicarPocima(atributo);
			usoPocima = true;
		}
	}
	//Saber si una carta tiene Pocima
	public boolean tienePocima(){
		return this.getPocima() != null;
	}
	//Saber el nombre de la pocima de la carta
	public String getNombrePocima() {
		return this.pocima.getNombre();
	}
	
	
	//OVERRIDES
	
	@Override	//Redefine el llamado al objeto.
	public String toString(){
		return this.nombre;
	}
	@Override	//Defino la noción de iguales porque en mazo uso contains en método contieneCarta.
	public boolean equals(Object objeto) {
		Carta otroCarta =(Carta) objeto;
		try {
			return this.getNombre().equals(otroCarta.getNombre());
		}
		catch(Exception e){
			return false;
		}
	}
	
	
}
