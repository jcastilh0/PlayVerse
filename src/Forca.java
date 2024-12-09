import java.io.IOException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Forca {

    public Forca() {
    }

    public Forca(String palavraSecreta) {
        this.palavraSecreta = palavraSecreta.toLowerCase();
    }

    private String palavraSecreta;
    private final Set<Character> letrasAdivinhadas = new HashSet<>();
    private int tentativasRestantes = 6;

    public String jogar(char letra) {
        if (letrasAdivinhadas.contains(letra)) {
            return "Letra já foi usada.";
        }

        letrasAdivinhadas.add(letra);

        if (!palavraSecreta.contains(String.valueOf(letra))) {
            tentativasRestantes--;
            return "Letra incorreta.";
        }

        return "Letra correta.";
    }

    public String palavraRevelada() {
        StringBuilder revelada = new StringBuilder();
        for (char c : palavraSecreta.toCharArray()) {
            revelada.append(letrasAdivinhadas.contains(c) ? c : "_");
        }
        return revelada.toString();
    }

    public int getTentativasRestantes() {
        return tentativasRestantes;
    }

    public boolean ganhou() {
        return palavraRevelada().equals(palavraSecreta);
    }

    public void exibirMensagemTentativas() {
        switch (tentativasRestantes) {
            case 5:
                System.out.println("Você perdeu um braço!");
                break;
            case 4:
                System.out.println("Você perdeu outro braço!");
                break;
            case 3:
                System.out.println("Você perdeu uma perna!");
                break;
            case 2:
                System.out.println("Você perdeu outra perna!");
                break;
            case 1:
                System.out.println("Só sobrou sua cabeça!");
                break;
            case 0:
                System.out.println("Você perdeu!");
                break;
            default:
                break;
        }
    }

    @SuppressWarnings("resource")
    public void iniciarPartida() {
        Scanner sc = new Scanner(System.in);

        try {
            String palavraSecreta = PalavraSecretaManager.selecionarPalavraAleatoria();
            this.palavraSecreta = palavraSecreta.toLowerCase();

            while (getTentativasRestantes() > 0 && !ganhou()) {
                System.out.println("\nPalavra: " + palavraRevelada());
                System.out.println("Tentativas restantes: " + getTentativasRestantes());
                System.out.print("Digite uma letra: ");
                String input = sc.nextLine().toLowerCase();

                if (input.isEmpty() || input.length() > 1) {
                    System.out.println("Entrada inválida! Digite apenas uma letra.");
                    continue;
                }

                if (!input.matches("[a-zA-Z]")) {
                    System.out.println("Entrada inválida! Digite apenas letras.");
                    continue;
                }

                char letra = input.charAt(0);

                String resultado = jogar(letra);
                System.out.println(resultado);

                exibirMensagemTentativas();

                if (ganhou()) {
                    System.out.println("\n");
                    System.out.println("Você venceu! A palavra era: " + palavraSecreta);
                    return;
                }
            }

            if (getTentativasRestantes() == 0) {
                System.out.println("\n");
                System.out.println("Você perdeu! A palavra era: " + palavraSecreta);
            }
        } catch (IOException e) {
            System.out.println("Erro ao acessar o banco de palavras: " + e.getMessage());
        }
    }
}
