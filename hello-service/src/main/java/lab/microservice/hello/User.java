package lab.microservice.hello;

public class User {
    private Long id;
    private String name;
    private String email;
    private int port;

    public User() {}

    public User(Long id, String name, String email, int port) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.port = port;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", port=" + port +
                '}';
    }
}
