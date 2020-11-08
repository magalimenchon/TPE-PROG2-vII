
public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		//MAZO
		
			String mazoPath = "./src/autos.json"/*"./src/superheroes.json"*/;
			Mazo mazo = new Mazo();
			mazo.importarMazo(mazoPath);
		
		//JUGADORES
			
			//Estrategias
			EstrategiaAmbicioso ambicioso = new EstrategiaAmbicioso();
			EstrategiaObstinado obstinado = new EstrategiaObstinado("fuerza");
			
			Jugador primerJugador = new Jugador("Juan", ambicioso);
			Jugador segundoJugador = new Jugador("María", obstinado);
	     
			
		//JUEGO
			
			//Pócimas
			PocimaPorcentaje fortalecedora = new PocimaPorcentaje("Fortalecedora", 20);
			PocimaPorcentaje fortalecedoraPlus = new PocimaPorcentaje("Fortalecedora Plus", 50);
			PocimaNumerica numeroMagico = new PocimaNumerica("Numero Mágico", 23);
			PocimaNumerica quieroValeCuatro = new PocimaNumerica("Quiero vale cuatro", 4);
			PocimaCombinada cocktail = new PocimaCombinada("Cocktail", numeroMagico, fortalecedora);
			PocimaCombinada surtida = new PocimaCombinada("Surtida", cocktail, fortalecedoraPlus);
		
			int maximoRondas = 20;
			Juego juego = new Juego(primerJugador, segundoJugador, mazo, maximoRondas);
			
			juego.addPocima(numeroMagico);
			juego.addPocima(fortalecedora);
			juego.addPocima(fortalecedoraPlus);
			juego.addPocima(cocktail);
			juego.addPocima(surtida);
			juego.addPocima(quieroValeCuatro);
			
			juego.jugar();
			
			System.out.println(juego.getRegistroPartidas());
	     
	}

}
