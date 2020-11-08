public class Jugador {
	
	
	//ESTADO
	
	private String nombre;
	private Mazo cartas;
	private Estrategia estrategia;
	
	//CONSTRUCTOR
	
	public Jugador(String nombre, Estrategia estrategia) {
		this.nombre = nombre;
		this.estrategia = estrategia;
		this.cartas = new Mazo();
	}
	
	
	//GETTERS & SETTERS
	
	public String getNombre() {
		return nombre;
	}
	
	public Estrategia getEstragia() {
		return estrategia;
	}

	public void setEstragia(Estrategia estrategia) {
		this.estrategia = estrategia;
	}
	
	
	//COMPORTAMIENTO
	
	//Coloca la carta al final del mazo.
	public void moverAlFinalDelMazo(Carta carta){
		cartas.quitarCarta(carta);
		cartas.addCarta(carta);
	}
	
	//Agrega una carta a su mazo.
	public void addCarta(Carta nuevaCarta){
		cartas.addCarta(nuevaCarta);
	}
	
	//Quita la carta del mazo.
	public void quitarCarta(Carta carta){
			cartas.quitarCarta(carta);
	}
	
	//Retorna cantidad de cartas del jugador.
	public int getTotalCartas(){
		return cartas.getTamañoMazo();
	}
	
	//Retorna el nombre de un atributo elegido segun la estrategia.
	public String elegirAtributo(Carta carta){
		return this.estrategia.elegirAtributo(carta);
	}
	
	//Retorna la primera carta.
	public Carta tomarCarta(){
		return cartas.getPrimeraCarta();
	}
	
	//OVERRIDES
	
	@Override	//Redefine el llamado al objeto.
	public String toString() {
		return getNombre();
	}

	
}
