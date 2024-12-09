import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class PontuacaoManager {
    private static final String ARQUIVO_PONTUACOES = "pontuacoes.txt";

    public static void salvarPontuacao(String jogador, int pontuacao) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ARQUIVO_PONTUACOES, true))) {
            writer.write(jogador + ";" + pontuacao);
            writer.newLine();
        }
    }

    public static Map<String, Integer> carregarPontuacoes() throws IOException {
        Map<String, Integer> pontuacoes = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(ARQUIVO_PONTUACOES))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] partes = line.split(";");
                String jogador = partes[0];
                int pontuacao = Integer.parseInt(partes[1]);
                pontuacoes.put(jogador, pontuacao);
            }
        }
        return pontuacoes;
    }

}
