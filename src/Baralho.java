import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Baralho {
    private List<Carta> cartas;
    private Random random;

    public Baralho() {
        this.cartas = new ArrayList<>();
        this.random = new Random();
        inicializarBaralho();
    }

    
    private void inicializarBaralho() {
        String[] valores = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
        String[] naipes = {"Espadas", "Copas", "Ouros", "Paus"};

        for (String naipe : naipes) {
            for (String valor : valores) {
                cartas.add(new Carta(valor, naipe));
            }
        }
    }

    
    public void embaralhar() {
        Collections.shuffle(cartas, random);
    }

    
    public Carta distribuir() {
        if (cartas.isEmpty()) {
            throw new IllegalStateException("O baralho está vazio!");
        }
        return cartas.remove(cartas.size() - 1);
    }

   
    public List<Carta> getCartas() {
        return cartas;
    }

}
