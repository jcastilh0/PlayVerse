import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class UsuarioManager {
    private static final String ARQUIVO_USUARIOS = "usuarios.txt";

    public static void cadastrarUsuario(String username, String password) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ARQUIVO_USUARIOS, true))) {
            writer.write(new Usuario(username, password).toString());
            writer.newLine();
        }
    }

    public static Usuario login(String username, String password) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(ARQUIVO_USUARIOS))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Usuario user = Usuario.fromString(line);
                if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                    return user;
                }
            }
        }
        return null;
    }

}
