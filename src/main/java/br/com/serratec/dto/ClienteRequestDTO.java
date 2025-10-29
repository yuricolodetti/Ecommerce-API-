package br.com.serratec.dto;

public class ClienteRequestDTO {

    private String nome;
    private String cpf;
    private String email;
    private String telefone;
    private EnderecoRequestDTO endereco;
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public EnderecoRequestDTO getEndereco() {
		return endereco;
	}
	public void setEndereco(EnderecoRequestDTO endereco) {
		this.endereco = endereco;
	}

   
}