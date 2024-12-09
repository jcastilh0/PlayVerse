import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Blackjack {
    private List<Carta> maoJogador;
    private List<Carta> maoDealer;
    private Baralho baralho;
    private Scanner scanner;

    public Blackjack() {
        maoJogador = new ArrayList<>();
        maoDealer = new ArrayList<>();
        baralho = new Baralho();
        scanner = new Scanner(System.in);
    }

    public void jogar(String jogador) {
        boolean continuarJogando = true;

        while (continuarJogando) {
            try {
                iniciarRodada();

                if (verificarBlackjackNatural(jogador)) {
                    continuarJogando = perguntarNovaRodada();
                    continue;
                }

                boolean jogadorAtivo = true;
                boolean dealerAtivo = true;

                while (jogadorAtivo || dealerAtivo) {
                    if (jogadorAtivo) {
                        mostrarCartasJogador();
                        int escolha = obterEscolha("\nVocê quer continuar ou parar? ([1] Continuar, [2] Parar): ", 1, 2);

                        if (escolha == 1) {
                            maoJogador.add(baralho.distribuir());
                            if (calcularPontuacao(maoJogador) > 21) {
                                System.out.println("                          *        *     ");
                                System.out.println("   (                    (  `     (  `    ");
                                System.out.println(" ( )\\      (       (    )\\))(    )\\))(   ");
                                System.out.println(" )((_)     )\\      )\\  ((_)()\\  ((_)()\\  ");
                                System.out.println("((_)_   _ ((_)  _ ((_) (_()((_) (_()((_) ");
                                System.out.println(" | _ ) | | | | | | | | |  \\/  | |  \\/  | ");
                                System.out.println(" | _ \\ | |_| | | |_| | | |\\/| | | |\\/| | ");
                                System.out.println(" |___/  \\___/   \\___/  |_|  |_| |_|  |_| ");
                                System.out.println("                                          ");
                                System.out.println("\nVocê estourou! BUUMM!");
                                jogadorAtivo = false;
                            }
                        } else {
                            jogadorAtivo = false;
                        }
                    }

                    if (dealerAtivo && !jogadorAtivo) {
                        if (calcularPontuacao(maoDealer) < 17) {
                            maoDealer.add(baralho.distribuir());
                        } else {
                            dealerAtivo = false;
                        }
                    }
                }

                mostrarResultados(jogador);
                continuarJogando = perguntarNovaRodada();
            } catch (InputMismatchException e) {
                System.out.println("\nEntrada inválida! Digite números.");
                scanner.nextLine(); // Limpa a entrada incorreta
            } catch (Exception e) {
                System.out.println("Erro inesperado: " + e.getMessage());
            }
        }
    }

    private void iniciarRodada() {
        maoJogador.clear();
        maoDealer.clear();
        baralho.embaralhar();
        maoJogador.add(baralho.distribuir());
        maoJogador.add(baralho.distribuir());
        maoDealer.add(baralho.distribuir());
        maoDealer.add(baralho.distribuir());
    }

    private boolean verificarBlackjackNatural(String jogador) {
        int pontuacaoJogador = calcularPontuacao(maoJogador);
        int pontuacaoDealer = calcularPontuacao(maoDealer);

        if (pontuacaoJogador == 21 && pontuacaoDealer == 21) {
            mostrarEmpate();
            return true;
        } else if (pontuacaoJogador == 21) {
            mostrarVitoria(jogador, pontuacaoJogador);
            return true;
        } else if (pontuacaoDealer == 21) {
            mostrarDerrota();
            return true;
        }
        return false;
    }

    private void mostrarCartasJogador() {
        System.out.println("\n══════════════════════════════════════════════════════════");
        System.out.println("    Suas Cartas: " + maoJogador);
        System.out.println("    Sua Pontuação: " + calcularPontuacao(maoJogador));
        System.out.println("    Carta visível do dealer: " + maoDealer.get(0));
        System.out.println("══════════════════════════════════════════════════════════");
    }

    private void mostrarResultados(String jogador) {
        int pontuacaoJogador = calcularPontuacao(maoJogador);
        int pontuacaoDealer = calcularPontuacao(maoDealer);

        System.out.println("\n══════════════════════════════════════════════════════════");
        mostrarCartas(maoJogador, "Sua mão final");
        mostrarCartas(maoDealer, "Mão do dealer");
        System.out.println("Sua pontuação: " + pontuacaoJogador);
        System.out.println("Pontuação do dealer: " + pontuacaoDealer);
        System.out.println("══════════════════════════════════════════════════════════");

        if (pontuacaoJogador > 21 && pontuacaoDealer > 21) {
            mostrarEmpate();  // Ambos estouraram, é empate
        } else if (pontuacaoJogador > 21 || (pontuacaoDealer <= 21 && pontuacaoDealer > pontuacaoJogador)) {
            mostrarDerrota(); // Jogador estourou ou dealer tem mais pontos
        } else if (pontuacaoJogador == pontuacaoDealer) {
            mostrarEmpate();  // Empate quando as pontuações são iguais
        } else {
            mostrarVitoria(jogador, pontuacaoJogador);  // Jogador vence
        }
    }

    private int calcularPontuacao(List<Carta> mao) {
        int pontuacao = 0;
        int ases = 0;

        for (Carta carta : mao) {
            switch (carta.getValor()) {
                case "J": case "Q": case "K":
                    pontuacao += 10;
                    break;
                case "A":
                    pontuacao += 11;
                    ases++;
                    break;
                default:
                    pontuacao += Integer.parseInt(carta.getValor());
            }
        }

        while (pontuacao > 21 && ases > 0) {
            pontuacao -= 10;
            ases--;
        }
        return pontuacao;
    }

    private void mostrarCartas(List<Carta> mao, String titulo) {
        System.out.println(titulo + ": " + mao);
    }

    private int obterEscolha(String mensagem, int opcaoMin, int opcaoMax) {
        int escolha;
        while (true) {
            try {
                System.out.print(mensagem);
                escolha = scanner.nextInt();
                if (escolha >= opcaoMin && escolha <= opcaoMax) {
                    break;
                } else {
                    System.out.println("Escolha inválida! Digite uma opção entre " + opcaoMin + " e " + opcaoMax + ".");
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida! Certifique-se de digitar um número.");
                scanner.nextLine(); // Limpa a entrada
            }
        }
        return escolha;
    }

    private boolean perguntarNovaRodada() {
        return obterEscolha("Deseja jogar outra rodada? [1] Sim [2] Não: ", 1, 2) == 1;
    }

    private void mostrarEmpate() {
        System.out.println("\n");
        System.out.println(".------..------..------..------..------..------.");
        System.out.println("|E.--. ||M.--. ||P.--. ||A.--. ||T.--. ||E.--. |");
        System.out.println("| (\\/) || (\\/) || :/\\: || (\\/) || :/\\: || (\\/) |");
        System.out.println("| :\\/ :|| :\\/ :|| (__) || :\\/ :|| (__) || :\\/ :|");
        System.out.println("| '--'E|| '--'M|| '--'P|| '--'A|| '--'T|| '--'E|");
        System.out.println("`------'`------'`------'`------'`------'`------'");        
        System.out.println("\n");
        System.out.println("Empate! Com empate ninguém ganha e ninguém perde, só que todo mundo perde porque ninguém ganha! HAHAHA");
        System.out.println();
    }

    private void mostrarVitoria(String jogador, int pontuacaoJogador) {
        System.out.println("\n");
        System.out.println(".------..------..------..------..------..------.");
        System.out.println("|G.--. ||A.--. ||N.--. ||H.--. ||O.--. ||U.--. |");
        System.out.println("| :/\\: || (\\/) || :(): || :/\\: || :/\\: || (\\/) |");
        System.out.println("| :\\/ :|| :\\/ :|| ()() || (__) || :\\/ :|| :\\/ :|");
        System.out.println("| '--'G|| '--'A|| '--'N|| '--'H|| '--'O|| '--'U|");
        System.out.println("`------'`------'`------'`------'`------'`------'");        
        System.out.println("\n");   
        System.out.println("Parabéns! Você venceu com " + pontuacaoJogador + " pontos!");
        System.out.println();
        try {
            PontuacaoManager.salvarPontuacao(jogador, pontuacaoJogador);
        } catch (IOException e) {
            System.out.println("Erro ao salvar a pontuação: " + e.getMessage());
        }
    }

    private void mostrarDerrota() {
        System.out.println("\n");
        System.out.println(".------..------..------..------..------..------.");
        System.out.println("|P.--. ||E.--. ||R.--. ||D.--. ||E.--. ||U.--. |");
        System.out.println("| :/\\: || (\\/) || :(): || :/\\: || (\\/) || (\\/) |");
        System.out.println("| (__) || :\\/: || ()() || (__) || :\\/: || :\\/: |");
        System.out.println("| '--'P|| '--'E|| '--'R|| '--'D|| '--'E|| '--'U|");
        System.out.println("`------'`------'`------'`------'`------'`------'");
        System.out.println("\n");
        System.out.println("Parece que está sem sorte! Tente novamente.");
        System.out.println();

    }
}
