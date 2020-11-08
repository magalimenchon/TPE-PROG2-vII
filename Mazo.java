import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;


public class Mazo {
	
	
	//ESTADO
	
	private ArrayList<Carta> cartasMazo;
	private Carta moldeCarta;
	
	
	//CONSTRUCTOR
	
	public Mazo() {
		cartasMazo = new ArrayList<Carta>();
	}
	
	
	//GETTERS & SETTERS
	
	public ArrayList<Carta> getCartas() {
		return new ArrayList<>(this.cartasMazo);
	}

	public Carta getMoldeCarta() {
		return moldeCarta;
	}

	private void setMoldeCarta(Carta moldeCarta) {	//El molde carta siempre es 0 y es único.
		this.moldeCarta = moldeCarta;
	}

	
	//COMPORTAMIENTO
	
	//Agrega una carta al mazo
	public void addCarta(Carta nuevaCarta){
		if(cartasMazo.size() == 0){ //el mazo está vacío
			this.cartasMazo.add(nuevaCarta);
			this.setMoldeCarta(nuevaCarta);
		}
		else if (perteneceMazo(nuevaCarta) && !contieneCarta(nuevaCarta)){
			//Primero compara por atributo y después por nombre, queda implícito que no agrega idénticas.
			this.cartasMazo.add(nuevaCarta);
		}
	}
	//Quita carta del mazo
	public void quitarCarta(Carta carta){
		cartasMazo.remove(carta);
	}
	
	//Pertenece al Mazo si las cartas son iguales
	public boolean perteneceMazo(Carta cartaAComparar){
		return moldeCarta.cartasIguales(cartaAComparar);
	}
	
	//Verifica si hay una carta con el mismo nombre en el mazo.
	public boolean contieneCarta(Carta cartaBuscada){
		return cartasMazo.contains(cartaBuscada);
	}

	//Dá la primera carta del mazo (como la da, la quita del mazo)
	public Carta getPrimeraCarta(){
		Carta primeraCarta = cartasMazo.get(0);
		cartasMazo.remove(0);
		return primeraCarta;
	}
	
	//Mezcla las cartas
	public void mezclarCartas() {
		Collections.shuffle(cartasMazo);
	}
	
	//Importa Mazo
	public void importarMazo(String jsonFile) {
        //URL url = getClass().getResource(jsonFile);
        File jsonInputFile = new File(jsonFile);
        InputStream is;
        try {
            is = new FileInputStream(jsonInputFile);
            // Creo el objeto JsonReader de Json.
            JsonReader reader = Json.createReader(is);
            // Obtenemos el JsonObject a partir del JsonReader.
            JsonArray cartas = (JsonArray) reader.readObject().getJsonArray("cartas");
            for (JsonObject carta : cartas.getValuesAs(JsonObject.class)) {
            	//---Inicializo y cargo el nombre a una carta nueva
                String nombreCarta = carta.getString("nombre");	//Obtiene nombre de la carta
                Carta nuevaCarta = new Carta(nombreCarta);	//la inicializa con su nombre
                JsonObject atributos = (JsonObject) carta.getJsonObject("atributos");//Obtiene atributos
                
                for (String nombreAtributo:atributos.keySet()){//por cada atributo obtenido
                	//---Obtiene el valor de ese atributo
                	int valorNumericoAtributo = atributos.getInt(nombreAtributo);
                	//Se lo asigna al nuevo atributo con su nombre
                	Atributo nuevoAtributo = new Atributo(nombreAtributo, valorNumericoAtributo);
					nuevaCarta.addAtributo(nuevoAtributo);	//agrego el atributo a la carta
                }
                this.addCarta(nuevaCarta);
            }

            reader.close();

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
	
	//Devuelve la cantidad total de cartas del mazo
	public int getTamañoMazo() {
		return cartasMazo.size();
	}
	
	//Obtiene una carta random
	public Carta getCartaAleatoria() {
				
		int pos = (int) Math.floor( (Math.random() * cartasMazo.size()) );
		//casting porque random retorna double y la posición debe ser int
				
		return cartasMazo.get(pos);
	}
	
	//OVERRIDES
	
	@Override	//Redefine el llamado al objeto.
	public String toString() {
		return "Mazo" + this.getCartas();
	}
	
}
