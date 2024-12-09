import java.io.IOException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Forca {
    private String palavraSecreta;
    private final Set<Character> letrasAdivinhadas = new HashSet<>();
    private int tentativasRestantes = 6;

    public Forca() {
    }

    public boolean jogar(char letra) {
        if (!letrasAdivinhadas.contains(letra)) {
            letrasAdivinhadas.add(letra);
            if (!palavraSecreta.contains(String.valueOf(letra))) {
                tentativasRestantes--;
            }
        }
        return tentativasRestantes > 0 && !palavraRevelada().equals(palavraSecreta);
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

    @SuppressWarnings("resource")
    public void iniciarPartida(){
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
    
                char letra = input.charAt(0);
    
                if (!jogar(letra)&&!ganhou()) {
                    System.out.println("Letra já foi usada ou não está correta.");
                }
    
                if (ganhou()) {
                    System.out.println("\n");
                    System.out.println("  ____        _         ____         _         ____   U _____ u   _   _       ____     ");
                    System.out.println("U|  _\"\\ u U  /\"\\  u  U |  _\"\\ u  U  /\"\\  u  U | __\")u \\| ___\"|/  | \\ |\"|     / __\"| u  ");
                    System.out.println("\\| |_) |/  \\/ _ \\/    \\| |_) |/   \\/ _ \\/    \\|  _ \\/  |  _|\"   <|  \\| |>   <\\___ \\/   ");
                    System.out.println(" |  __/    / ___ \\     |  _ <     / ___ \\     | |_) |  | |___   U| |\\  |u    u___) |   ");
                    System.out.println(" |_|      /_/   \\_\\    |_| \\_\\   /_/   \\_\\    |____/   |_____|   |_| \\_|     |____/>>  ");
                    System.out.println(" ||>>_     \\\\    >>    //   \\\\_   \\\\    >>   _|| \\\\_   <<   >>   ||   \\\\,-.   )(  (__) ");
                    System.out.println("(__)__)   (__)  (__)  (__)  (__) (__)  (__) (__) (__) (__) (__)  (_\")  (_/   (__)      ");
                    System.out.println("\n");
                    System.out.println("Você venceu! A palavra era: " + palavraSecreta);
                    System.out.println("\n");
                    return;
                }
            }
    
            if (getTentativasRestantes() == 0) {
                System.out.println("\n");
                System.out.println("  ____    U _____ u    ____       ____    U _____ u    _   _  ");
                System.out.println("U|  _\"\\ u \\| ___\"|/ U |  _\"\\ u   |  _\"\\   \\| ___\"|/ U |\"|u| | ");
                System.out.println("\\| |_) |/  |  _|\"    \\| |_) |/  /| | | |   |  _|\"    \\| |\\| | ");
                System.out.println(" |  __/    | |___     |  _ <    U| |_| |\\  | |___     | |_| | ");
                System.out.println(" |_|       |_____|    |_| \\_\\    |____/ u  |_____|   <<\\___/  ");
                System.out.println(" ||>>_     <<   >>    //   \\\\_    |||_     <<   >>  (__) )(   ");
                System.out.println("(__)__)   (__) (__)  (__)  (__)  (__)_)   (__) (__)     (__)  ");
                System.out.println("\n");

                System.out.println("Que pena! A palavra era: " + palavraSecreta);
            }
        } catch (IOException e) {
            System.out.println("Erro ao acessar o banco de palavras: " + e.getMessage());
        }
    }

}
