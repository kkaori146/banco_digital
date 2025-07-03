package br.com.cdb.bancodigita.controller;

import br.com.cdb.bancodigita.entity.Conta;
import br.com.cdb.bancodigita.service.ContaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Map;
import java.util.HashMap;

//Define o caminho base para as requisições 
@RestController
@RequestMapping("/contas")
public class ContaController {

    @Autowired
    private ContaService contaService;

    //Endpoint para criar uma nova conta
    @PostMapping("/add")
    public ResponseEntity<Conta> criarConta(@RequestParam Long clienteId, @RequestParam Long numero) {
        Conta conta = contaService.criarConta(clienteId, numero);
        if (conta != null) {
            return ResponseEntity.ok(conta);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    //Endpoint para buscar conta pelo ID
    @GetMapping("/{id}")
    public ResponseEntity<Conta> getConta(@PathVariable Long id) {
        Optional<Conta> conta = contaService.getContaById(id);
        return conta.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    //Endpoint para consultar saldo e CPF do cliente da conta
    @GetMapping("/{id}/saldo")
    public ResponseEntity<Map<String, Object>> getSaldo(@PathVariable Long id) {
        Optional<Conta> conta = contaService.getContaById(id);
        return conta.map(c -> {
            Map<String, Object> response = new HashMap<>();
            response.put("saldo", c.getSaldo());
            response.put("cpf", c.getCliente().getCpf());
            return ResponseEntity.ok(response);
        }).orElse(ResponseEntity.notFound().build());
    }

    //Endpoint para realizar depósito em uma conta pelo ID
    @PostMapping("/{id}/deposito")
    public ResponseEntity<Conta> deposito(@PathVariable Long id, @RequestParam Double valor) {
        return ResponseEntity.ok(contaService.depositar(id, valor));
    }

    //Endpoint para realizar saque na conta pelo ID
    @PostMapping("/{id}/saque")
    public ResponseEntity<Conta> saque(@PathVariable Long id, @RequestParam Double valor) {
        return ResponseEntity.ok(contaService.sacar(id, valor));
    }

    //Endpoint para realizar transferência por PIX entre contas
    @PostMapping("/{id}/pix")
    public ResponseEntity<String> pix(@PathVariable Long id,
                                      @RequestParam Long destinoId,
                                      @RequestParam Double valor) {
        contaService.transferir(id, destinoId, valor);
        return ResponseEntity.ok("Pix realizado com sucesso");
    }

    //Endpoint para realizar transferência tradicional entre contas
    @PostMapping("/{id}/transferencia")
    public ResponseEntity<String> transferir(@PathVariable Long id,
                                             @RequestParam Long destinoId,
                                             @RequestParam Double valor) {
        contaService.transferir(id, destinoId, valor);
        return ResponseEntity.ok("Transferência realizada com sucesso");
    }

    //Endpoint para aplicar taxa de manutenção na conta
    @PutMapping("/{id}/manutencao")
    public ResponseEntity<Conta> manutencao(@PathVariable Long id, @RequestParam Double taxa) {
        return ResponseEntity.ok(contaService.aplicarManutencao(id, taxa));
    }

    //Endpoint para aplicar rendimento percentual na conta
    @PutMapping("/{id}/rendimentos")
    public ResponseEntity<Conta> rendimento(@PathVariable Long id, @RequestParam Double percentual) {
        return ResponseEntity.ok(contaService.aplicarRendimento(id, percentual));
    }
}
