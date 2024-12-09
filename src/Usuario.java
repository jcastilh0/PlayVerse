public class Usuario {
    private String username;
    private String password;

    public Usuario(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return username + ";" + password;
    }

    public static Usuario fromString(String line) {
        String[] parts = line.split(";");
        return new Usuario(parts[0], parts[1]);
    }

}
