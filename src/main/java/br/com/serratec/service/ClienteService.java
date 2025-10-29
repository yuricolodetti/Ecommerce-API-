package br.com.serratec.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.serratec.dto.ClienteRequestDTO;
import br.com.serratec.dto.ClienteResponseDTO;
import br.com.serratec.dto.EnderecoRequestDTO;
import br.com.serratec.entity.Cliente;
import br.com.serratec.entity.Endereco;
import br.com.serratec.repository.ClienteRepository;
import br.com.serratec.repository.EnderecoRepository;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private RestTemplate restTemplate; // pode ser configurado como @Bean em uma @Configuration

    public List<ClienteResponseDTO> listar() {
        return clienteRepository.findAll().stream()
                .map(ClienteResponseDTO::new)
                .collect(Collectors.toList());
    }

    public ClienteResponseDTO buscar(Long id) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
        return new ClienteResponseDTO(cliente);
    }

    public ClienteResponseDTO inserir(ClienteRequestDTO dto) {
        Cliente cliente = new Cliente();
        cliente.setNome(dto.getNome());
        cliente.setCpf(dto.getCpf());
        cliente.setEmail(dto.getEmail());
        cliente.setTelefone(dto.getTelefone());

        Endereco endereco = montarEnderecoAutomatico(dto.getEndereco());
        enderecoRepository.save(endereco);
        cliente.setEndereco(endereco);

        return new ClienteResponseDTO(clienteRepository.save(cliente));
    }

    public ClienteResponseDTO atualizar(Long id, ClienteRequestDTO dto) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        cliente.setNome(dto.getNome());
        cliente.setCpf(dto.getCpf());
        cliente.setEmail(dto.getEmail());
        cliente.setTelefone(dto.getTelefone());

        Endereco endereco = (cliente.getEndereco() != null) ? cliente.getEndereco() : new Endereco();
        preencherEnderecoAutomatico(endereco, dto.getEndereco());
        enderecoRepository.save(endereco);
        cliente.setEndereco(endereco);

        return new ClienteResponseDTO(clienteRepository.save(cliente));
    }

    public void deletar(Long id) {
        clienteRepository.deleteById(id);
    }


//pegar endereço
    private Endereco montarEnderecoAutomatico(EnderecoRequestDTO dtoEnd) {
        if (dtoEnd == null || dtoEnd.getCep() == null || dtoEnd.getCep().isBlank()) {
            throw new RuntimeException("CEP é obrigatório");
        }
        Endereco endereco = new Endereco();
        preencherEnderecoAutomatico(endereco, dtoEnd);
        return endereco;
    }

    private void preencherEnderecoAutomatico(Endereco endereco, EnderecoRequestDTO dtoEnd) {
        String cepLimpo = dtoEnd.getCep().replaceAll("\\D", "");
        EnderecoViaCepResponse via = restTemplate.getForObject(
                "https://viacep.com.br/ws/" + cepLimpo + "/json/", EnderecoViaCepResponse.class);

        if (via == null || via.getCep() == null || Boolean.TRUE.equals(via.getErro())) {
            throw new RuntimeException("CEP inválido ou não encontrado");
        }

        endereco.setCep(via.getCep());
        endereco.setLogradouro(via.getLogradouro());
        endereco.setBairro(via.getBairro());
        endereco.setCidade(via.getLocalidade());
        endereco.setEstado(via.getUf());
        endereco.setNumero(dtoEnd.getNumero());
        endereco.setComplemento(dtoEnd.getComplemento());
    }

    
    static class EnderecoViaCepResponse {
        private String cep;
        private String logradouro;
        private String bairro;
        private String localidade;
        private String uf;
        private Boolean erro;
		public String getCep() {
			return cep;
		}
		public void setCep(String cep) {
			this.cep = cep;
		}
		public String getLogradouro() {
			return logradouro;
		}
		public void setLogradouro(String logradouro) {
			this.logradouro = logradouro;
		}
		public String getBairro() {
			return bairro;
		}
		public void setBairro(String bairro) {
			this.bairro = bairro;
		}
		public String getLocalidade() {
			return localidade;
		}
		public void setLocalidade(String localidade) {
			this.localidade = localidade;
		}
		public String getUf() {
			return uf;
		}
		public void setUf(String uf) {
			this.uf = uf;
		}
		public Boolean getErro() {
			return erro;
		}
		public void setErro(Boolean erro) {
			this.erro = erro;
		}

        
    }
}