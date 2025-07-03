package br.com.cdb.bancodigita.service;

import br.com.cdb.bancodigita.entity.Conta;
import br.com.cdb.bancodigita.repository.ContaRepository;
import br.com.cdb.bancodigita.repository.ClienteRepository;
import br.com.cdb.bancodigita.entity.Cliente;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ContaService {

    @Autowired
    private ContaRepository contaRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    public Conta criarConta(Long clienteId, Long numero) {
        Optional<Cliente> cliente = clienteRepository.findById(clienteId);
        if (cliente.isPresent()) {
            Conta conta = new Conta();
            conta.setCliente(cliente.get());
            conta.setNumero(numero);
            conta.setSaldo(0.0);
            return contaRepository.save(conta);
        }
        return null;
    }

    public Optional<Conta> getContaById(Long id) {
        return contaRepository.findById(id);
    }

    public Conta depositar(Long contaId, Double valor) {
        Conta conta = contaRepository.findById(contaId).orElseThrow();
        conta.setSaldo(conta.getSaldo() + valor);
        return contaRepository.save(conta);
    }

    public Conta sacar(Long contaId, Double valor) {
        Conta conta = contaRepository.findById(contaId).orElseThrow();
        if (conta.getSaldo() >= valor) {
            conta.setSaldo(conta.getSaldo() - valor);
            return contaRepository.save(conta);
        }
        throw new RuntimeException("Saldo insuficiente");
    }

    public Conta transferir(Long origemId, Long destinoId, Double valor) {
        Conta origem = contaRepository.findById(origemId).orElseThrow();
        Conta destino = contaRepository.findById(destinoId).orElseThrow();
        if (origem.getSaldo() >= valor) {
            origem.setSaldo(origem.getSaldo() - valor);
            destino.setSaldo(destino.getSaldo() + valor);
            contaRepository.save(origem);
            return contaRepository.save(destino);
        }
        throw new RuntimeException("Saldo insuficiente para transferência");
    }

    public Conta aplicarManutencao(Long id, Double taxa) {
        Conta conta = contaRepository.findById(id).orElseThrow();
        conta.setSaldo(conta.getSaldo() - taxa);
        return contaRepository.save(conta);
    }

    public Conta aplicarRendimento(Long id, Double percentual) {
        Conta conta = contaRepository.findById(id).orElseThrow();
        Double rendimento = conta.getSaldo() * (percentual / 100);
        conta.setSaldo(conta.getSaldo() + rendimento);
        return contaRepository.save(conta);
    }
}
