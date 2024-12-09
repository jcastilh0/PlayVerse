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
                    System.out.println("\n");
                    System.out.print("Deseja jogar outra rodada? [1] Sim [2] Não: ");
                    int resposta = scanner.nextInt();
                    scanner.nextLine();
                    continuarJogando = resposta == 1;
                    continue;
                }

                boolean jogadorAtivo = true;
                boolean dealerAtivo = true;

                while (jogadorAtivo || dealerAtivo) {
                    if (jogadorAtivo) {
                        mostrarCartasJogador();
                        System.out.println("\n");
                        System.out.print("Você quer continuar tentando a sorte ou quer parar a rodada por aqui? ([1] Continuar ou [2] Parar)? ");
                        int escolha = scanner.nextInt();
                        scanner.nextLine();

                        if (escolha == 1) {
                            maoJogador.add(baralho.distribuir());
                            if (calcularPontuacao(maoJogador) > 21) {
                                System.out.println("\n");
                                System.out.println("Você estourou! BUUMM!");
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
                System.out.println("\n");
                System.out.print("Deseja jogar outra rodada? [1] Sim [2] Não: ");
                int resposta = scanner.nextInt();
                scanner.nextLine();
                continuarJogando = resposta == 1;

            } catch (InputMismatchException e) {
                System.out.println("\n");
                System.out.println("Entrada inválida! Certifique-se de digitar números.");
                scanner.nextLine();
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
            System.out.println("\n");
            mostrarCartas(maoJogador, "Sua mão final");
            mostrarCartas(maoDealer, "Mão do dealer");
            System.out.println("Empate! Ambos têm Blackjack.");
            return true;
        } else if (pontuacaoJogador == 21) {
            System.out.println("\n");
            mostrarCartas(maoJogador, "Sua mão final");
            System.out.println("\n");
            System.out.println(".------..------..------..------..------..------.");
            System.out.println("|G.--. ||A.--. ||N.--. ||H.--. ||O.--. ||U.--. |");
            System.out.println("| :/\\: || (\\/) || :(): || :/\\: || :/\\: || (\\/) |");
            System.out.println("| :\\/: || :\\/: || ()() || (__) || :\\/: || :\\/: |");
            System.out.println("| '--'G|| '--'A|| '--'N|| '--'H|| '--'O|| '--'U|");
            System.out.println("`------'`------'`------'`------'`------'`------'");
            System.out.println("\n");   
            System.out.println("Você tem um Blackjack natural! Parabéns! Pode apostar na loteria porque sua sorte está ótima HAHA!");
            try {
                PontuacaoManager.salvarPontuacao(jogador, pontuacaoJogador);
            } catch (IOException e) {
                System.out.println("\n");
                System.out.println("Erro ao salvar a pontuação: " + e.getMessage());
            }
            return true;
        } else if (pontuacaoDealer == 21) {
            System.out.println("\n");
            mostrarCartas(maoJogador, "Sua mão final");
            mostrarCartas(maoDealer, "Mão do dealer");
            System.out.println("\n");
            System.out.println(".------..------..------..------..------..------.");
            System.out.println("|P.--. ||E.--. ||R.--. ||D.--. ||E.--. ||U.--. |");
            System.out.println("| :/\\: || (\\/) || :(): || :/\\: || (\\/) || (\\/) |");
            System.out.println("| (__) || :\\/: || ()() || (__) || :\\/: || :\\/: |");
            System.out.println("| '--'P|| '--'E|| '--'R|| '--'D|| '--'E|| '--'U|");
            System.out.println("`------'`------'`------'`------'`------'`------'");
            System.out.println("\n");
            System.out.println("O dealer tem um Blackjack natural. Você perdeu. Tente de novo que sua sorte melhora.");
            return true;
        }
        return false;
    }

    private void mostrarCartasJogador() {

        System.out.println("\n");
        System.out.println("══════════════════════════════════════════════════════════");
        System.out.println("    Suas Cartas: " + maoJogador);
        System.out.println("    Sua Pontuação: " + calcularPontuacao(maoJogador));
        System.out.println("══════════════════════════════════════════════════════════");
        System.out.println("    Carta visível do dealer: " + maoDealer.get(0));
        System.out.println("══════════════════════════════════════════════════════════");

    }

    private void mostrarResultados(String jogador) {
        int pontuacaoJogador = calcularPontuacao(maoJogador);
        int pontuacaoDealer = calcularPontuacao(maoDealer);

        System.out.println("\n");
        mostrarCartas(maoJogador, "Sua mão final");
        mostrarCartas(maoDealer, "Mão do dealer (completa)");

        System.out.println("\n");
        System.out.println("Sua pontuação: " + pontuacaoJogador);
        System.out.println("Pontuação do dealer: " + pontuacaoDealer);

        if (pontuacaoJogador > 21 || (pontuacaoDealer <= 21 && pontuacaoDealer > pontuacaoJogador)) {
            System.out.println("\n");
            System.out.println(".------..------..------..------..------..------.");
            System.out.println("|P.--. ||E.--. ||R.--. ||D.--. ||E.--. ||U.--. |");
            System.out.println("| :/\\: || (\\/) || :(): || :/\\: || (\\/) || (\\/) |");
            System.out.println("| (__) || :\\/: || ()() || (__) || :\\/: || :\\/: |");
            System.out.println("| '--'P|| '--'E|| '--'R|| '--'D|| '--'E|| '--'U|");
            System.out.println("`------'`------'`------'`------'`------'`------'");
            System.out.println("\n");
            System.out.println("Parece que está sem sorte!");
        } else if (pontuacaoJogador == pontuacaoDealer) {
            System.out.println("\n");
            System.out.println(".------..------..------..------..------..------.");
            System.out.println("|E.--. ||M.--. ||P.--. ||A.--. ||T.--. ||E.--. |");
            System.out.println("| (\\/) || (\\/) || :/\\: || (\\/) || :/\\: || (\\/) |");
            System.out.println("| :\\/: || :\\/: || (__) || :\\/: || (__) || :\\/: |");
            System.out.println("| '--'E|| '--'M|| '--'P|| '--'A|| '--'T|| '--'E|");
            System.out.println("`------'`------'`------'`------'`------'`------'");
            System.out.println("\n");

            System.out.println("Empate! Com empate ninguém ganha e ninguém perde, só que todo mundo perde porque ninguém ganha! HAHAHA");
        } else {
            System.out.println("\n");
            System.out.println(".------..------..------..------..------..------.");
            System.out.println("|G.--. ||A.--. ||N.--. ||H.--. ||O.--. ||U.--. |");
            System.out.println("| :/\\: || (\\/) || :(): || :/\\: || :/\\: || (\\/) |");
            System.out.println("| :\\/: || :\\/: || ()() || (__) || :\\/: || :\\/: |");
            System.out.println("| '--'G|| '--'A|| '--'N|| '--'H|| '--'O|| '--'U|");
            System.out.println("`------'`------'`------'`------'`------'`------'");
            System.out.println("\n");   
            System.out.println("Olha a sorte está do seu lado, parabéns! Jogue mais uma rodada!");
            try {
                PontuacaoManager.salvarPontuacao(jogador, pontuacaoJogador);
            } catch (IOException e) {
                System.out.println("\n");
                System.out.println("Erro ao salvar a pontuação: " + e.getMessage());
            }
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

}
