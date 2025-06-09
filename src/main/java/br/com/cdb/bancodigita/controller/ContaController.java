package br.com.cdb.bancodigita.controller;

import br.com.cdb.bancodigita.entity.Conta;
import br.com.cdb.bancodigita.service.ContaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Map;
import java.util.HashMap;

@RestController
@RequestMapping("/contas")
public class ContaController {

    @Autowired
    private ContaService contaService;

    @PostMapping("/add")
    public ResponseEntity<Conta> criarConta(@RequestParam Long clienteId, @RequestParam Long numero) {
        Conta conta = contaService.criarConta(clienteId, numero);
        if (conta != null) {
            return ResponseEntity.ok(conta);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Conta> getConta(@PathVariable Long id) {
        Optional<Conta> conta = contaService.getContaById(id);
        return conta.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

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


    @PostMapping("/{id}/deposito")
    public ResponseEntity<Conta> deposito(@PathVariable Long id, @RequestParam Double valor) {
        return ResponseEntity.ok(contaService.depositar(id, valor));
    }

    @PostMapping("/{id}/saque")
    public ResponseEntity<Conta> saque(@PathVariable Long id, @RequestParam Double valor) {
        return ResponseEntity.ok(contaService.sacar(id, valor));
    }

    @PostMapping("/{id}/pix")
    public ResponseEntity<String> pix(@PathVariable Long id,
                                      @RequestParam Long destinoId,
                                      @RequestParam Double valor) {
        contaService.transferir(id, destinoId, valor);
        return ResponseEntity.ok("Pix realizado com sucesso");
    }


    @PostMapping("/{id}/transferencia")
    public ResponseEntity<String> transferir(@PathVariable Long id,
                                             @RequestParam Long destinoId,
                                             @RequestParam Double valor) {
        contaService.transferir(id, destinoId, valor);
        return ResponseEntity.ok("Transferência realizada com sucesso");
    }

    @PutMapping("/{id}/manutencao")
    public ResponseEntity<Conta> manutencao(@PathVariable Long id, @RequestParam Double taxa) {
        return ResponseEntity.ok(contaService.aplicarManutencao(id, taxa));
    }

    @PutMapping("/{id}/rendimentos")
    public ResponseEntity<Conta> rendimento(@PathVariable Long id, @RequestParam Double percentual) {
        return ResponseEntity.ok(contaService.aplicarRendimento(id, percentual));
    }
}
