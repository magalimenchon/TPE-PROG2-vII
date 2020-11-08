
public class EstrategiaAmbicioso implements Estrategia{

	@Override	//Elige el atributo que posea el mayor valor de su carta del turno.
	public String elegirAtributo(Carta cartaElegida) {
		// TODO Auto-generated method stub
		return cartaElegida.getAtributoMayorValor();
	}

}
