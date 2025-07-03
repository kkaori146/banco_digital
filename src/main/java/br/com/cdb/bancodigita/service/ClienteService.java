package br.com.cdb.bancodigita.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cdb.bancodigita.repository.ClienteRepository;
import br.com.cdb.bancodigita.entity.Cliente;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;
	
	//Cria e salva um novo Cliente com nome e CPF
	public Cliente salvarCliente(String nome, Long cpf)
	{
		Cliente cliente = new Cliente();
		cliente.setCpf(cpf);
		cliente.setNome(nome);
		return clienteRepository.save(cliente);
	}
	
	//Retorna a lista de todos os Clientes cadastrados
	public List<Cliente> getClientes(){
		return clienteRepository.findAll();
	}

	//Busca um Cliente por ID
	public Optional<Cliente> getClienteById(Long id) {
	    return clienteRepository.findById(id);
	}

	//Remove um Cliente pelo ID
	public void deletarCliente(Long id) {
	    clienteRepository.deleteById(id);
	}

}
