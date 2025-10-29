package br.com.serratec.dto;

public class LoginResponseDTO {

    private String token;
    private String tipo;
    private String username;

    public LoginResponseDTO() {
    }

    public LoginResponseDTO(String token, String tipo, String username) {
        this.token = token;
        this.tipo = tipo;
        this.username = username;
    }

    public String getToken() {
        return this.token;
    }
    public void setToken(String token) {
        this.token = token;
    }

    public String getTipo() {
        return this.tipo;
    }
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getUsername() {
        return this.username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
}