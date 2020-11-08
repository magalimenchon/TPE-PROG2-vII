
public class EstrategiaTimbero implements Estrategia{

	@Override	//En cada ronda, el jugador elige al azar el atributo por el cual se desea competir
	public String elegirAtributo(Carta cartaElegida) {
		// TODO Auto-generated method stub
		return cartaElegida.getAtributoAletorio();
	}

}
