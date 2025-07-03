package br.com.cdb.bancodigita.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cdb.bancodigita.entity.Cliente;
import br.com.cdb.bancodigita.service.ClienteService;

@RestController
@RequestMapping("/cliente")
public class ClienteController {
	
	@Autowired
	private ClienteService clienteService;
	
	//CRIAR UM NOVO CLIENTE
	@PostMapping("/add")
	public ResponseEntity<String> addCliente(@RequestBody Cliente cliente)
	{
		Cliente clienteAdicionado = clienteService.salvarCliente(cliente.getNome(), cliente.getCpf());
			
		if(clienteAdicionado != null) {
			return new ResponseEntity<>("Cliente: "+cliente.getNome()+ " adicionado com sucesso", HttpStatus.CREATED);
		}
		else {
			return new ResponseEntity<>("Nome ou CPF Inválido", HttpStatus.NOT_ACCEPTABLE);
			
		}
	}
	
	//LISTA TODOS OS CLIENTES
	@GetMapping("/listAll")
	public ResponseEntity<List<Cliente>> getAllClientes(){
		List<Cliente> clientes  = clienteService.getClientes();
		return new ResponseEntity<List<Cliente>>(clientes, HttpStatus.OK);
	}
	
	//OBTEM OS DETALHES DE UM CLIENTE
	@GetMapping("/details/{id}")
	public ResponseEntity<Cliente> getClienteById(@PathVariable Long id){
		Optional<Cliente> cliente = clienteService.getClienteById(id);
		return cliente.map(ResponseEntity::ok).orElseGet(()-> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}
	
	//ATUALIZA INFORMAÇÕES DE UM CLIENTE
	@PutMapping("/update/{id}")
    public ResponseEntity<Cliente> atualizarCliente(@PathVariable Long id, @RequestBody Cliente novoCliente) {
        Optional<Cliente> clienteExistente = clienteService.getClienteById(id);

        if (clienteExistente.isPresent()) {
            Cliente cliente = clienteExistente.get();
            cliente.setNome(novoCliente.getNome());
            cliente.setCpf(novoCliente.getCpf());
            Cliente atualizado = clienteService.salvarCliente(cliente.getNome(), cliente.getCpf());
            atualizado.setId(id);
            return new ResponseEntity<>(atualizado, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
	
    // DELETA CLIENTE PELO ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deletarCliente(@PathVariable Long id) {
    	//Verifica se o cliente existe antes de deletar
        Optional<Cliente> cliente = clienteService.getClienteById(id);
        if (cliente.isPresent()) {
            clienteService.deletarCliente(id);
            //Retorna 204 (sem conteúdo) se a exclusão for bem-sucedida
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
        	//Retorna 404 se o cliente não for encontrado
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
