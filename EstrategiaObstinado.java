
public class EstrategiaObstinado implements Estrategia{
	
	private String nombreAtributoElegido;
	
	public EstrategiaObstinado(String nombreAtributoElegido) {
		this.nombreAtributoElegido = nombreAtributoElegido;
	}

	@Override	//Siempre elige el mismo atributo para competir, ronda tras ronda.
	public String elegirAtributo(Carta cartaElegida) {
		// TODO Auto-generated method stub
		Atributo atributoBuscado = cartaElegida.getAtributoPorNombre(nombreAtributoElegido);
		
		if(atributoBuscado == null){
			//si el atributo elegido no existe elige uno dentro de los atributos posibles
			this.nombreAtributoElegido = cartaElegida.getAtributoAletorio();
			atributoBuscado = cartaElegida.getAtributoPorNombre(nombreAtributoElegido);
		}
		
		return atributoBuscado.getNombre();
	}

}
