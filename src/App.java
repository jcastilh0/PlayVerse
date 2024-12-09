import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class App {
    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        boolean sair = false;
        boolean logado = false;
        String usuarioAtual = null;

        System.out.println("                                                                           ");
        System.out.println("██████╗ ██╗      █████╗ ██╗   ██╗██╗   ██╗███████╗██████╗ ███████╗███████╗");
        System.out.println("██╔══██╗██║     ██╔══██╗╚██╗ ██╔╝██║   ██║██╔════╝██╔══██╗██╔════╝██╔════╝");
        System.out.println("██████╔╝██║     ███████║ ╚████╔╝ ██║   ██║█████╗  ██████╔╝███████╗█████╗  ");
        System.out.println("██╔═══╝ ██║     ██╔══██║  ╚██╔╝  ╚██╗ ██╔╝██╔══╝  ██╔══██╗╚════██║██╔══╝  ");
        System.out.println("██║     ███████╗██║  ██║   ██║    ╚████╔╝ ███████╗██║  ██║███████║███████╗");
        System.out.println("╚═╝     ╚══════╝╚═╝  ╚═╝   ╚═╝     ╚═══╝  ╚══════╝╚═╝  ╚═╝╚══════╝╚══════╝");
        System.out.println("                                                                           ");
        System.out.println("Bem-vindo ao PlayVerse!");
        System.out.println("Reviva os melhores momentos dos jogos clássicos com nossa plataforma cheia de diversão e nostalgia.");
        System.out.println("\n");

        while (!sair) {
            if (!logado) {
                System.out.println("O que deseja fazer agora?");
                System.out.println("1. Login");
                System.out.println("2. Cadastro");
                System.out.println("3. Sair");
                System.out.print("Escolha uma opção: ");

                int opcaoInicial = lerInteiro();

                switch (opcaoInicial) {
                    case 1:
                        try {
                            usuarioAtual = login();
                            if (usuarioAtual != null) {
                                logado = true;
                            }
                        } catch (Exception e) {
                            System.out.println("Erro durante o login: " + e.getMessage());
                        }
                        break;
                    case 2:
                        cadastro();
                        break;
                    case 3:
                        sair = true;
                        break;
                    default:
                        System.out.println("Opção inválida! Tente novamente.");
                }
            } else {
                sair  = menuJogos(usuarioAtual, logado);
            }
        }

        System.out.println("\nObrigado por jogar! Até breve.");
    }

    private static List<String> catalogoJogos(){
        List<String> jogos = Arrays.asList(
        "1. Jogo da Forca", "2. Blackjack", "3. Pedra, Papel e Tesoura - Em breve", "4. Adivinhe o Número - Em breve", "5. Jogo da Velha - Em breve",
        "6. Corrida de Dados - Em breve", "7. Quiz de Perguntas e Respostas - Em breve", "8. Cálculo Mental Rápido - Em breve", "9. Caça ao Tesouro - Em breve", "10. Simulador de Labirinto - Em breve");
        return jogos;
    }

    private static boolean menuJogos(String usuarioAtual, boolean logado) {

        boolean sair = false;

        while (!sair) {
            
            System.out.println("                                            ");
            System.out.println("     ██╗ ██████╗  ██████╗  ██████╗ ███████╗");
            System.out.println("     ██║██╔═══██╗██╔════╝ ██╔═══██╗██╔════╝");
            System.out.println("     ██║██║   ██║██║  ███╗██║   ██║███████╗");
            System.out.println("██   ██║██║   ██║██║   ██║██║   ██║╚════██║");
            System.out.println("╚█████╔╝╚██████╔╝╚██████╔╝╚██████╔╝███████║");
            System.out.println(" ╚════╝  ╚═════╝  ╚═════╝  ╚═════╝ ╚══════╝");
            System.out.println("                                            ");

            System.out.println("Disponíveis hoje: ");
            System.out.println("1. Jogo da Forca");
            System.out.println("2. Blackjack");
            System.out.println("3. Ver catálogo de jogos");
            System.out.println("4. Logout");
            System.out.print("Escolha uma opção: ");

            int opcaoJogos = lerInteiro();
    
            switch (opcaoJogos) {
                case 1:
                    jogarForca(usuarioAtual, logado);
                    break;
                case 2:
                    jogarBlackjack(usuarioAtual, logado, usuarioAtual);
                    break;
                case 3:
                System.out.println("\nCatálogo de jogos:");
                for (String jogo : catalogoJogos()) {
                    System.out.println(jogo);
                }
                System.out.println();
                    System.out.println("\nEscolha um jogo ou digite 0 para voltar:");
                    
                    int opcaoCatalogo = lerInteiro();
                    switch (opcaoCatalogo) {
                        case 1:
                            jogarForca(usuarioAtual, logado);
                            break;
                        case 2:
                            jogarBlackjack(usuarioAtual, logado, usuarioAtual);
                            break;
                        case 3:
                            jogarPedraPapelTesoura();
                            break;
                        case 4:
                            jogarAdivinheONumero();
                            break;
                        case 5:
                            jogarJogoDaVelha();
                            break;
                        case 6:
                            jogarCorridaDeDados();
                            break;
                        case 7:
                            jogarQuiz();
                            break;
                        case 8:
                            jogarCalculoMental();
                            break;
                        case 9:
                            jogarCacaAoTesouro();
                            break;
                        case 10:
                            jogarSimuladorDeLabirinto();
                            break;
                        case 0:
                            System.out.println("Voltando ao menu principal...");
                            break;
                        default:
                            System.out.println("Opção inválida! Tente novamente.");
                    }
                    break;
                case 4:
                    sair = true;
                    logado = false;
                    usuarioAtual = null;
                    break;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
        }
        return sair;
    }
    private static int lerInteiro() {
        while (true) {
            try {
                return Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.print("Entrada inválida. Digite um número: ");
            }
        }
    }

    private static String login() throws Exception {
        System.out.println("                                        ");
        System.out.println("██╗      ██████╗  ██████╗ ██╗███╗   ██╗");
        System.out.println("██║     ██╔═══██╗██╔════╝ ██║████╗  ██║");
        System.out.println("██║     ██║   ██║██║  ███╗██║██╔██╗ ██║");
        System.out.println("██║     ██║   ██║██║   ██║██║██║╚██╗██║");
        System.out.println("███████╗╚██████╔╝╚██████╔╝██║██║ ╚████║");
        System.out.println("╚══════╝ ╚═════╝  ╚═════╝ ╚═╝╚═╝  ╚═══╝");
        System.out.println("                                        ");
        System.out.print("\nDigite seu username: ");
        String username = sc.nextLine();
        System.out.print("Digite sua senha: ");
        String password = sc.nextLine();

        try {
            Usuario usuario = UsuarioManager.login(username, password);
            if (usuario != null) {
                System.out.println("       ");
                System.out.println("   ██  ");
                System.out.println("██  ██ ");
                System.out.println("    ██ ");
                System.out.println("██  ██ ");
                System.out.println("   ██  ");
                System.out.println("       ");
                System.out.println("Login realizado com sucesso. Bem-vindo(a), " + usuario.getUsername() + "!");
                return usuario.getUsername();
            } else {
                System.out.println("\n");
                System.out.println("    ██ ");
                System.out.println("██ ██  ");
                System.out.println("   ██  ");
                System.out.println("██ ██  ");
                System.out.println("    ██ ");
                System.out.println("       ");
                throw new Exception("Login ou senha inválidos.");
            }
        } catch (IOException e) {
            System.out.println("\n");
            System.out.println("    ██ ");
            System.out.println("██ ██  ");
            System.out.println("   ██  ");
            System.out.println("██ ██  ");
            System.out.println("    ██ ");
            System.out.println("       ");
            throw new Exception("Erro ao acessar o banco de dados de usuários.");
        }
    }

    private static void cadastro() {
        System.out.println("                                                                   ");
        System.out.println(" ██████╗ █████╗ ██████╗  █████╗ ███████╗████████╗██████╗  ██████╗ ");
        System.out.println("██╔════╝██╔══██╗██╔══██╗██╔══██╗██╔════╝╚══██╔══╝██╔══██╗██╔═══██╗");
        System.out.println("██║     ███████║██║  ██║███████║███████╗   ██║   ██████╔╝██║   ██║");
        System.out.println("██║     ██╔══██║██║  ██║██╔══██║╚════██║   ██║   ██╔══██╗██║   ██║");
        System.out.println("╚██████╗██║  ██║██████╔╝██║  ██║███████║   ██║   ██║  ██║╚██████╔╝");
        System.out.println(" ╚═════╝╚═╝  ╚═╝╚═════╝ ╚═╝  ╚═╝╚══════╝   ╚═╝   ╚═╝  ╚═╝ ╚═════╝ ");
        System.out.println("                                                                   ");

        System.out.print("Digite o username: ");
        String username = sc.nextLine();
        System.out.print("Digite a senha: ");
        String password = sc.nextLine();

        try {
            UsuarioManager.cadastrarUsuario(username, password);
            System.out.println("       ");
            System.out.println("   ██  ");
            System.out.println("██  ██ ");
            System.out.println("    ██ ");
            System.out.println("██  ██ ");
            System.out.println("   ██  ");
            System.out.println("       ");
            System.out.println("Usuário cadastrado com sucesso!");
        } catch (IOException e) {
            System.out.println("\n");
            System.out.println("    ██ ");
            System.out.println("██ ██  ");
            System.out.println("   ██  ");
            System.out.println("██ ██  ");
            System.out.println("    ██ ");
            System.out.println("       ");
            System.out.println("Erro ao salvar o usuário: " + e.getMessage());
        }
    }

    private static void jogarForca(String usuarioAtual, boolean logado) {
        System.out.println("\n");
        System.out.println("     _         U  ___ u    ____      U  ___ u       ____        _            _____     U  ___ u    ____        ____      _      ");
        System.out.println("  U |\"| u       \\\"_ \\/ U /\"___|u     \\\"_ \\/      |  _\"\\   U  /\"\\  u       |\" ___|     \\\"_ \\/ U |  _\"\\ u  U /\"___| U  /\"\\  u  ");
        System.out.println(" _ \\| |/        | | | | \\| |  _ /     | | | |     /| | | |   \\/ _ \\/       U| |_  u     | | | |  \\| |_) |/  \\| | u    \\/ _ \\/   ");
        System.out.println("| |_| |_,-. .-,_| |_| |  | |_| |  .-,_| |_| |     U| |_| |\\  / ___ \\       \\|  _|/  .-,_| |_| |   |  _ <     | |/__   / ___ \\   ");
        System.out.println(" \\___/-(_/   \\_)-\\___/    \\____|   \\_)-\\___/       |____/ u /_/   \\_\\       |_|      \\_)-\\___/    |_| \\_\\     \\____| /_/   \\_\\  ");
        System.out.println("  _//             \\\\      _)(|_         \\\\          |||_     \\\\    >>       )(\\\\,-        \\\\      //   \\\\_   _// \\\\   \\\\    >>  ");
        System.out.println(" (__)            (__)    (__)__)       (__)        (__)_)   (__)  (__)     (__)(_/       (__)    (__)  (__) (__)(__) (__)  (__) ");
        System.out.println("\n");
    
        System.out.println("Bem-vindo ao Jogo da Forca!");
        System.out.println("\nSobre o jogo:");
        System.out.println("\n O Jogo da Forca é um clássico desafio de palavras.");
        System.out.println(" Seu objetivo é adivinhar a palavra secreta, letra por letra.");
        System.out.println(" Você tem um número limitado de tentativas, então escolha com sabedoria!");
        System.out.println(" Mostre suas habilidades e descubra a palavra antes que seja tarde demais!");
        System.out.println("\n");
        boolean sair = false;

        while (!sair) {
            System.out.println("O que deseja fazer agora?");
            System.out.println("1. Iniciar Partida");
            System.out.println("2. Voltar ao menu");
            System.out.print("Escolha uma opção: ");

            int opcao = lerInteiro();

            switch (opcao) {
                case 1:
                    iniciarPartidaForca();
                    break;
                case 2:
                    sair = true;
                    break;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
        }
    }
    
    
    private static void jogarBlackjack(String usuarioAtual, boolean logado, String jogador) {
        System.out.println("\n");
        System.out.println(".------..------..------..------..------..------..------..------..------.");
        System.out.println("|B.--. ||L.--. ||A.--. ||C.--. ||K.--. ||J.--. ||A.--. ||C.--. ||K.--. |");
        System.out.println("| :(): || :/\\: || (\\/) || :/\\: || :/\\: || :(): || (\\/) || :/\\: || :/\\: |");
        System.out.println("| ()() || (__) || :\\/: || :\\/: || :\\/: || ()() || :\\/: || :\\/: || :\\/: |");
        System.out.println("| '--'B|| '--'L|| '--'A|| '--'C|| '--'K|| '--'J|| '--'A|| '--'C|| '--'K|");
        System.out.println("`------'`------'`------'`------'`------'`------'`------'`------'`------'");
        System.out.println("\n");

        System.out.println("Bem-vindo ao Blackjack!");
        System.out.println("\nSobre o jogo:");
        System.out.println("\n Blackjack, também conhecido como 21, é um dos jogos de cartas mais populares do mundo.");
        System.out.println(" Seu objetivo é simples: alcançar a pontuação mais próxima de 21 sem ultrapassá-la.");
        System.out.println(" Você competirá contra o dealer (a casa), tentando superar sua pontuação.");
        System.out.println(" Cartas numeradas valem seu próprio número, figuras valem 10 e o Ás vale 1 ou 11.");
        System.out.println(" Use estratégia e sorte para vencer! Boa sorte!");
        System.out.println("\n");

        boolean sair = false;

        while (!sair) {
            System.out.println("O que deseja fazer agora?");
            System.out.println("1. Iniciar Partida");
            System.out.println("2. Voltar o menu");
            System.out.print("Escolha uma opção: ");

            int opcao = lerInteiro();

            switch (opcao) {
                case 1:
                    iniciarPartidaBlackjack(jogador);
                    break;
                case 2:
                sair = true;
                break;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
            
        }
    }


    private static void iniciarPartidaForca() {
        Forca forca = new Forca();
        forca.iniciarPartida();
        
    }

    private static void iniciarPartidaBlackjack(String jogador){
        Blackjack blackjack = new Blackjack();
        blackjack.jogar(jogador);
    }

    private static void jogarPedraPapelTesoura() {

        System.out.println("\n");
        System.out.println("Bem-vindo ao Pedra, Papel e Tesoura!");
        System.out.println("\nSobre o jogo:");
        System.out.println("\n Pedra, Papel e Tesoura é um jogo simples de escolhas. Você enfrenta o computador.");
        System.out.println(" As regras são: Pedra vence Tesoura, Tesoura vence Papel e Papel vence Pedra.");
        System.out.println(" Faça sua escolha e veja quem ganha. Boa sorte!");
        System.out.println("\n");
    
        boolean sair = false;
    
        while (!sair) {
            System.out.println("O que deseja fazer agora?");
            System.out.println("1. Voltar ao menu");
            System.out.print("Escolha uma opção: ");
    
            int opcao = lerInteiro();
    
            switch (opcao) {
                case 1:
                    sair = true;
                    break;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
        }
    }
    
    private static void jogarAdivinheONumero() {

        System.out.println("\n");
        System.out.println("Bem-vindo ao Adivinhe o Número!");
        System.out.println("\nSobre o jogo:");
        System.out.println("\n Adivinhe o Número é um jogo de dedução e sorte. Um número é escolhido aleatoriamente.");
        System.out.println(" Sua tarefa é adivinhar o número escolhido pelo sistema.");
        System.out.println(" Receberá dicas se está mais alto ou mais baixo até acertar.");
        System.out.println(" Boa sorte e divirta-se!");
        System.out.println("\n");
    
        boolean sair = false;
    
        while (!sair) {
            System.out.println("O que deseja fazer agora?");
            System.out.println("1. Voltar ao menu");
            System.out.print("Escolha uma opção: ");
    
            int opcao = lerInteiro();
    
            switch (opcao) {
                case 1:
                    sair = true;
                    break;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
        }
    }
    
    private static void jogarJogoDaVelha() {

        System.out.println("\n");
        System.out.println("Bem-vindo ao Jogo da Velha!");
        System.out.println("\nSobre o jogo:");
        System.out.println("\n Jogo da Velha é um jogo de estratégia simples para dois jogadores.");
        System.out.println(" O objetivo é alinhar 3 marcas em linha, coluna ou diagonal antes do adversário.");
        System.out.println(" As marcas são 'X' e 'O'. Quem alinhar primeiro vence!");
        System.out.println(" Boa sorte!");
        System.out.println("\n");
    
        boolean sair = false;
    
        while (!sair) {
            System.out.println("O que deseja fazer agora?");
            System.out.println("1. Voltar ao menu");
            System.out.print("Escolha uma opção: ");
    
            int opcao = lerInteiro();
    
            switch (opcao) {
                case 1:
                    sair = true;
                    break;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
        }
    }
    
    private static void jogarCorridaDeDados() {

        System.out.println("\n");
        System.out.println("Bem-vindo à Corrida de Dados!");
        System.out.println("\nSobre o jogo:");
        System.out.println("\n Corrida de Dados é um jogo simples onde você compete contra o computador.");
        System.out.println(" Cada jogador rola dois dados e o maior valor vence.");
        System.out.println(" O primeiro a alcançar 10 pontos ganha a corrida!");
        System.out.println(" Boa sorte e que os dados estejam a seu favor!");
        System.out.println("\n");
    
        boolean sair = false;
    
        while (!sair) {
            System.out.println("O que deseja fazer agora?");
            System.out.println("1. Voltar ao menu");
            System.out.print("Escolha uma opção: ");
    
            int opcao = lerInteiro();
    
            switch (opcao) {
                case 1:
                    sair = true;
                    break;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
        }
    }
    
    private static void jogarQuiz() {

        System.out.println("\n");
        System.out.println("Bem-vindo ao Quiz de Perguntas e Respostas!");
        System.out.println("\nSobre o jogo:");
        System.out.println("\n Teste seus conhecimentos em diversas áreas com perguntas de múltipla escolha.");
        System.out.println(" Para cada pergunta, escolha a alternativa correta. Pontue e veja como você se sai!");
        System.out.println(" Boa sorte e aproveite!");
        System.out.println("\n");
    
        boolean sair = false;
    
        while (!sair) {
            System.out.println("O que deseja fazer agora?");
            System.out.println("1. Voltar ao menu");
            System.out.print("Escolha uma opção: ");
    
            int opcao = lerInteiro();
    
            switch (opcao) {
                case 1:
                    sair = true;
                    break;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
        }
    }
    
    private static void jogarCalculoMental() {

        System.out.println("\n");
        System.out.println("Bem-vindo ao Cálculo Mental Rápido!");
        System.out.println("\nSobre o jogo:");
        System.out.println("\n Resolva operações matemáticas simples em um curto período de tempo.");
        System.out.println(" Teste sua agilidade mental e melhore suas habilidades de cálculo.");
        System.out.println(" Pontue corretamente e desafie seus limites!");
        System.out.println(" Boa sorte e divirta-se!");
        System.out.println("\n");
    
        boolean sair = false;
    
        while (!sair) {
            System.out.println("O que deseja fazer agora?");
            System.out.println("1. Voltar ao menu");
            System.out.print("Escolha uma opção: ");
    
            int opcao = lerInteiro();
    
            switch (opcao) {
                case 1:
                    sair = true;
                    break;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
        }
    }
    
    private static void jogarCacaAoTesouro() {

        System.out.println("\n");
        System.out.println("Bem-vindo à Caça ao Tesouro!");
        System.out.println("\nSobre o jogo:");
        System.out.println("\n Encontre o tesouro escondido em um tabuleiro de coordenadas.");
        System.out.println(" Você recebe dicas se está próximo ou distante do tesouro.");
        System.out.println(" Boa sorte em sua caça e aproveite o desafio!");
        System.out.println("\n");
    
        boolean sair = false;
    
        while (!sair) {
            System.out.println("O que deseja fazer agora?");
            System.out.println("1. Voltar ao menu");
            System.out.print("Escolha uma opção: ");
    
            int opcao = lerInteiro();
    
            switch (opcao) {
                case 1:
                    sair = true;
                    break;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
        }
    }
    
    private static void jogarSimuladorDeLabirinto() {

        System.out.println("\n");
        System.out.println("Bem-vindo ao Simulador de Labirinto!");
        System.out.println("\nSobre o jogo:");
        System.out.println("\n Navegue por um labirinto representado por texto.");
        System.out.println(" Escolha suas direções (norte, sul, leste, oeste) e tente encontrar a saída.");
        System.out.println(" Tome cuidado com becos sem saída!");
        System.out.println(" Boa sorte e divirta-se!");
        System.out.println("\n");
    
        boolean sair = false;
    
        while (!sair) {
            System.out.println("O que deseja fazer agora?");
            System.out.println("1. Voltar ao menu");
            System.out.print("Escolha uma opção: ");
    
            int opcao = lerInteiro();
    
            switch (opcao) {
                case 1:
                    sair = true;
                    break;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
        }
    }

}
