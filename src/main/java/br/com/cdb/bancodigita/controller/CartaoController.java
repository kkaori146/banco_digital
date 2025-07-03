package br.com.cdb.bancodigita.controller;

import br.com.cdb.bancodigita.entity.Cartao;
import br.com.cdb.bancodigita.service.CartaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Map;
import java.util.HashMap;



@RestController
@RequestMapping("/cartoes")
public class CartaoController {

    @Autowired
    private CartaoService cartaoService;

    //Endpoint para emitir novo cartão
    @PostMapping
    public ResponseEntity<Cartao> emitirCartao(@RequestParam Long clienteId,
                                               @RequestParam String tipo) {
        return ResponseEntity.ok(cartaoService.emitirCartao(clienteId, tipo));
    }

    //Endpoint para buscar os dados do cartão pelo ID
    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getCartao(@PathVariable Long id) {
        Optional<Cartao> optionalCartao = cartaoService.getCartaoById(id);
        
        //Retorna 404 se o cartão não for encontrado
        if (optionalCartao.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        //Resposta com os dados do cartão
        Cartao cartao = optionalCartao.get();
        Map<String, Object> response = new HashMap<>();

        response.put("id", cartao.getId());
        response.put("tipo", cartao.getTipo());
        response.put("limite", cartao.getLimite() != null ? cartao.getLimite() : "não se aplica");
        response.put("limiteDiario", cartao.getLimiteDiario() != null ? cartao.getLimiteDiario() : "não se aplica");
        response.put("fatura", cartao.getFatura() != null ? cartao.getFatura() : "não se aplica");
        response.put("ativo", cartao.getAtivo());
        response.put("senha", cartao.getSenha());
        response.put("clienteNome", cartao.getCliente().getNome());
        response.put("clienteCpf", cartao.getCliente().getCpf());

        return ResponseEntity.ok(response);
    }

    //Endpoint para realizar pagamento com cartão
    @PostMapping("/{id}/pagamento")
    public ResponseEntity<String> realizarPagamento(@PathVariable Long id,
                                                    @RequestParam Double valor,
                                                    @RequestParam String senha) {
        cartaoService.realizarPagamento(id, valor, senha);
        return ResponseEntity.ok("Pagamento realizado com sucesso");
    }

    //Endpoint para alterar o limite do cartão
    @PutMapping("/{id}/limite")
    public ResponseEntity<Cartao> alterarLimite(@PathVariable Long id,
                                                @RequestParam Double novoLimite) {
        return ResponseEntity.ok(cartaoService.alterarLimite(id, novoLimite));
    }

    //Endpoint para ativar ou desativar o cartão
    @PutMapping("/{id}/status")
    public ResponseEntity<Cartao> alterarStatus(@PathVariable Long id,
                                                @RequestParam boolean ativo) {
        return ResponseEntity.ok(cartaoService.alterarStatus(id, ativo));
    }

    //Endpoint para alterar a senha do cartão
    @PutMapping("/{id}/senha")
    public ResponseEntity<String> alterarSenha(@PathVariable Long id,
                                               @RequestParam String novaSenha) {
        cartaoService.alterarSenha(id, novaSenha);
        return ResponseEntity.ok("Senha alterada com sucesso");
    }

    //Endpoint para consultar a fatura do cartão
    @GetMapping("/{id}/fatura")
    public ResponseEntity<Map<String, Object>> consultarFatura(@PathVariable Long id) {
        Cartao cartao = cartaoService.getCartaoById(id)
                .orElseThrow(() -> new RuntimeException("Cartão não encontrado"));

        Map<String, Object> response = new HashMap<>();
        response.put("fatura", cartao.getFatura());
        response.put("cpf", cartao.getCliente().getCpf());

        return ResponseEntity.ok(response);
    }

    //Endpoint para pagar a fatura do cartão
    @PostMapping("/{id}/fatura/pagamento")
    public ResponseEntity<String> pagarFatura(@PathVariable Long id,
                                              @RequestParam Double valor) {
        cartaoService.pagarFatura(id, valor);
        return ResponseEntity.ok("Fatura paga com sucesso");
    }

    //Endpoint para alterar o limite diário do cartão
    @PutMapping("/{id}/limite-diario")
    public ResponseEntity<Cartao> alterarLimiteDiario(@PathVariable Long id,
                                                      @RequestParam Double novoLimiteDiario) {
        return ResponseEntity.ok(cartaoService.alterarLimiteDiario(id, novoLimiteDiario));
    }
}
