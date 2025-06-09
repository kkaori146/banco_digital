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
	
	public Cliente salvarCliente(String nome, Long cpf)
	{
		Cliente cliente = new Cliente();
		cliente.setCpf(cpf);
		cliente.setNome(nome);
		return clienteRepository.save(cliente);
	}
	
	public List<Cliente> getClientes(){
		return clienteRepository.findAll();
	}

	public Optional<Cliente> getClienteById(Long id) {
	    return clienteRepository.findById(id);
	}

	public void deletarCliente(Long id) {
	    clienteRepository.deleteById(id);
	}

}
