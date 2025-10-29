package br.com.serratec.dto;

public class TokenResponseDTO {

    private String token;
    private String tipo;
    private String username;

    public TokenResponseDTO() {
    }

    public TokenResponseDTO(String token, String tipo, String username) {
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