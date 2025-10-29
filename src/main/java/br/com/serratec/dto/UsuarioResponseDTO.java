package br.com.serratec.dto;

import java.util.List;
import java.util.stream.Collectors;

import br.com.serratec.entity.Usuario;
import br.com.serratec.entity.UsuarioPerfil;

public class UsuarioResponseDTO {

    private Long id;
    private String username;
    private List<String> perfis; // nomes dos perfis

    public UsuarioResponseDTO() {
    }

    public UsuarioResponseDTO(Usuario usuario) {
        this.id = usuario.getId();
        this.username = usuario.getUsername();
        this.perfis = usuario.getPerfis().stream()
                .map(UsuarioPerfil::getPerfil)   // pega o Perfil dentro de UsuarioPerfil
                .map(perfil -> perfil.getNome()) // pega o nome do Perfil
                .collect(Collectors.toList());
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<String> getPerfis() {
		return perfis;
	}

	public void setPerfis(List<String> perfis) {
		this.perfis = perfis;
	}

    
}