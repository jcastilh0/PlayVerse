import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PalavraSecretaManager {
    private static final String ARQUIVO_PALAVRAS = "src/palavras.txt";

    public static String selecionarPalavraAleatoria() throws IOException {
        List<String> palavras = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(ARQUIVO_PALAVRAS))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Adiciona a palavra à lista, removendo espaços extras
                palavras.add(line.trim());
            }
        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo: " + e.getMessage());
            throw e;
        }

        // Verifica se o arquivo estava vazio
        if (palavras.isEmpty()) {
            throw new IOException("Nenhuma palavra encontrada no banco de dados.");
        }

        // Escolhe uma palavra aleatória da lista
        Random random = new Random();
        return palavras.get(random.nextInt(palavras.size()));
    }

    public static void main(String[] args) {
        try {
            String palavra = selecionarPalavraAleatoria();
            System.out.println("Palavra secreta: " + palavra);
        } catch (IOException e) {
            System.err.println("Erro: " + e.getMessage());
        }
    }
}
