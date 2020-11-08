import java.util.ArrayList;


public class Juego {
	
	
	//ESTADO
	
	private Jugador primerJugador;
	private Jugador segundoJugador;
	private Mazo mazo;
	private String registroPartidas = "";
	private static int cantidadRondas = 1;
	private final int rondaMaxima; 
	private Jugador ganadorRonda;
	private ArrayList<Pocima> pocimas;
	
	
	//CONSTRUCTOR
	
	public Juego(Jugador primerJugador, Jugador segundoJugador, Mazo mazo, int rondaMaxima) {
		super();
		this.primerJugador = primerJugador;
		this.segundoJugador = segundoJugador;
		this.mazo = mazo;
		this.rondaMaxima = rondaMaxima;
		this.ganadorRonda = primerJugador;
		this.pocimas = new ArrayList<>();
	}
	
	
	//GETTERS & SETTERS
	
	public Jugador getPrimerJugador() {
		return primerJugador;
	}

	public Jugador getSegundoJugador() {
		return segundoJugador;
	}

	public Mazo getMazo() {
		return mazo;
	}

	public String getRegistroPartidas() {
		return registroPartidas;
	}

	public int getCantidadRondas() {
		return cantidadRondas;
	}

	public Jugador getGanadorRonda() {
		return ganadorRonda;
	}
	
	private void setGanadorRonda(Jugador ganadorRonda) {
		this.ganadorRonda = ganadorRonda;
	}

	public int getRondaMaxima() {
		return rondaMaxima;
	}


	//COMPORTAMIENTO
	
	//Procesa todo el juego
	public void jugar(){
		repartirCartas();
		while((getCantidadRondas() <= this.rondaMaxima) && (primerJugador.getTotalCartas() > 0) &&
				(segundoJugador.getTotalCartas() > 0)){
			//mientras no se supere el limite de rondas y que los jugadores no se queden sin cartas
			iniciarRonda();
			cantidadRondas++;
		}	
	}
	
	//Para repartir las cartas, primero las mezcla, luego reparte las pocimas y al final las reparte
	public void repartirCartas(){
		mazo.mezclarCartas();
		this.repartirPocimas();
		int totalCartas = mazo.getTamañoMazo();
	
		for(int i = 1; i <= totalCartas; i++){
			if(i % 2 != 0)
				primerJugador.addCarta(mazo.getPrimeraCarta());
			else
				segundoJugador.addCarta(mazo.getPrimeraCarta());
		}
	}
	
	//Procesa toda una ronda
	public void iniciarRonda(){
		
		Carta cartaPrimerJugador = primerJugador.tomarCarta();
		Carta cartaSegundoJugador = segundoJugador.tomarCarta();
		String atributoElegido;
		
		if(ganadorRonda.equals(primerJugador))
			atributoElegido = ganadorRonda.elegirAtributo(cartaPrimerJugador);
		else
			atributoElegido = ganadorRonda.elegirAtributo(cartaSegundoJugador);
		
		Atributo atributoPrimerJugador = cartaPrimerJugador.getAtributoPorNombre(atributoElegido);
		Atributo atributoSegundoJugador = cartaSegundoJugador.getAtributoPorNombre(atributoElegido);
		
		addRegistroInicialRonda(atributoElegido, cartaPrimerJugador, cartaSegundoJugador, 
								atributoPrimerJugador, atributoSegundoJugador);
		definirGanadorRonda(cartaPrimerJugador, cartaSegundoJugador, atributoPrimerJugador, 
							atributoSegundoJugador);
		addRegistroFinalRonda();
	}
	
	//Completa el registro con los datos iniciales de la ronda
	public void addRegistroInicialRonda(String atributo, Carta cartaJ1, Carta cartaJ2, 
										Atributo atributoJ1, Atributo atributoJ2){
		
		addRegistroPartidas("\n------- Ronda "+ getCantidadRondas() +" -------\n");
		addRegistroPartidas("\n El jugador "+ ganadorRonda +" selecciona competir por el atributo " +atributo);
	}
	
	//Completa el registro con los datos finales de la ronda
	public void addRegistroFinalRonda(){
		
		addRegistroPartidas("\n Gana la ronda "+ ganadorRonda +" y queda con "+ 
							ganadorRonda.getTotalCartas() +" cartas ");
		
		if(ganadorRonda != primerJugador)
			addRegistroPartidas(" ("+ primerJugador +" posee ahora "+ 
								primerJugador.getTotalCartas()+" cartas) \n");
		else
			addRegistroPartidas(" ("+ segundoJugador +" posee ahora "+ 
								segundoJugador.getTotalCartas()+" cartas) \n");
	}

	//Agrega el string para imprimir las partidas
	public void addRegistroPartidas(String nuevoRegistro) {
		this.registroPartidas += nuevoRegistro;
	}
	
	//Define ganador de la ronda
	public void definirGanadorRonda(Carta cartaJ1, Carta cartaJ2, Atributo atributoJ1, Atributo atributoJ2){
		
		evaluarCartas(cartaJ1, cartaJ2, atributoJ1, atributoJ2);
		
		int comparacionValorAtributos = atributoJ1.compareTo(atributoJ2);
		if(comparacionValorAtributos == 0){
			primerJugador.moverAlFinalDelMazo(cartaJ1);
			segundoJugador.moverAlFinalDelMazo(cartaJ2);
			addRegistroPartidas(". Hubo empate");
		}
		else{
			if(comparacionValorAtributos > 0)
				setGanadorRonda(primerJugador);
			else
				setGanadorRonda(segundoJugador);
			recibirCartas(cartaJ1, cartaJ2);
		}
	}
	
	//El ganador recibe cartas
	public void recibirCartas(Carta cartaJ1, Carta cartaJ2){
		ganadorRonda.moverAlFinalDelMazo(cartaJ1);
		ganadorRonda.moverAlFinalDelMazo(cartaJ2);
	}
	
	
	//POCIMAS
	
	//Agrega pocimas
	public void addPocima(Pocima pocima){
		if(!pocimas.contains(pocima)){
			pocimas.add(pocima);
		}
	}
	
	//Reparte las pocimas en el mazo.
	public void repartirPocimas(){
		for(Pocima pocima: pocimas)
			mazo.getCartaAleatoria().setPocima(pocima);
	}
	
	//Analiza las cartas de cada jugador
	private void evaluarCartas(Carta cartaJ1, Carta cartaJ2, Atributo atributoJ1, Atributo atributoJ2){
		evaluarCarta(cartaJ1, atributoJ1, primerJugador);
		evaluarCarta(cartaJ2, atributoJ2, segundoJugador);
	}
	
	//Analiza la carta y aplica pocima a la carta del jugador si la tiene
	private void evaluarCarta(Carta carta, Atributo atributo, Jugador jugador){
		addRegistroPartidas("\n La carta de "+ jugador +" es "+ carta +" con "+ atributo);//por redefinir toStrings
		carta.aplicarPocima(atributo);
		if(carta.tienePocima())
			addRegistroPartidas(", se aplicó pócima "+ carta.getNombrePocima() +" valor resultante "+ atributo.getValorNumerico());
	}
}
