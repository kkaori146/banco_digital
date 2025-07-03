package br.com.cdb.bancodigita.service;

import br.com.cdb.bancodigita.entity.Cartao;
import br.com.cdb.bancodigita.entity.Cliente;
import br.com.cdb.bancodigita.entity.Conta;
import br.com.cdb.bancodigita.repository.CartaoRepository;
import br.com.cdb.bancodigita.repository.ClienteRepository;
import br.com.cdb.bancodigita.repository.ContaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartaoService {

    @Autowired
    private CartaoRepository cartaoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ContaRepository contaRepository;

    //Emite cartão para o cliente (CRÉDITO ou DÉBITO)
    public Cartao emitirCartao(Long clienteId, String tipo) {
        Cliente cliente = clienteRepository.findById(clienteId).orElseThrow();

        Cartao cartao = new Cartao();
        cartao.setCliente(cliente);
        cartao.setTipo(tipo.toUpperCase());
        cartao.setAtivo(true);
        cartao.setSenha("1234");
        cartao.setFatura(0.0);

        //Configura limites dependendo do tipo de cartão
        if (tipo.equalsIgnoreCase("CREDITO")) {
            cartao.setLimite(1000.0);
            cartao.setLimiteDiario(null);
        } else if (tipo.equalsIgnoreCase("DEBITO")) {
            cartao.setLimite(null);
            cartao.setLimiteDiario(500.0);
        }

        return cartaoRepository.save(cartao);
    }

    //Busca o cartão pelo ID
    public Optional<Cartao> getCartaoById(Long id) {
        return cartaoRepository.findById(id);
    }

    //Realiza pagamento usando o cartão, verificando status e limites
    public void realizarPagamento(Long id, Double valor, String senha) {
        Cartao cartao = cartaoRepository.findById(id).orElseThrow();

        if (!cartao.getAtivo()) {
            throw new RuntimeException("Cartão inativo");
        }

        if (!cartao.getSenha().equals(senha)) {
            throw new RuntimeException("Senha incorreta");
        }

        if (cartao.getTipo().equalsIgnoreCase("CREDITO")) {
            if (valor > cartao.getLimite()) {
                throw new RuntimeException("Limite insuficiente");
            }
            cartao.setLimite(cartao.getLimite() - valor);
            cartao.setFatura(cartao.getFatura() + valor);

        } else if (cartao.getTipo().equalsIgnoreCase("DEBITO")) {
            if (valor > cartao.getLimiteDiario()) {
                throw new RuntimeException("Limite diário excedido");
            }

            Cliente cliente = cartao.getCliente();
            Conta conta = contaRepository.findAll().stream()
                    .filter(c -> c.getCliente().getId().equals(cliente.getId()))
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("Conta do cliente não encontrada"));

            //Verifica saldo disponível
            if (valor > conta.getSaldo()) {
                throw new RuntimeException("Saldo insuficiente na conta");
            }

            //Deduz o valor do saldo e do limite diário
            conta.setSaldo(conta.getSaldo() - valor);
            cartao.setLimiteDiario(cartao.getLimiteDiario() - valor);

            contaRepository.save(conta);
        }

        cartaoRepository.save(cartao);
    }

    //Altera o limite do cartão e salva no banco
    public Cartao alterarLimite(Long id, Double novoLimite) {
        Cartao cartao = cartaoRepository.findById(id).orElseThrow();
        cartao.setLimite(novoLimite);
        return cartaoRepository.save(cartao);
    }

    //Altera o status (ativo/inativo) do cartão
    public Cartao alterarStatus(Long id, boolean ativo) {
        Cartao cartao = cartaoRepository.findById(id).orElseThrow();
        cartao.setAtivo(ativo);
        return cartaoRepository.save(cartao);
    }

    //Altera a senha do cartão
    public void alterarSenha(Long id, String novaSenha) {
        Cartao cartao = cartaoRepository.findById(id).orElseThrow();
        cartao.setSenha(novaSenha);
        cartaoRepository.save(cartao);
    }

    //Retorna o valor atual da fatura do cartão
    public Double getFatura(Long id) {
        Cartao cartao = cartaoRepository.findById(id).orElseThrow();
        return cartao.getFatura();
    }

    //Realiza pagamento parcial ou total da fatura
    public void pagarFatura(Long id, Double valor) {
        Cartao cartao = cartaoRepository.findById(id).orElseThrow();
        if (valor > cartao.getFatura()) {
            throw new RuntimeException("Valor excede a fatura");
        }
        //Deduz valor pago da fatura e aumenta limite disponível
        cartao.setFatura(cartao.getFatura() - valor);
        cartao.setLimite(cartao.getLimite() + valor);
        cartaoRepository.save(cartao);
    }

    //Altera o limite diário do cartão e salva
    public Cartao alterarLimiteDiario(Long id, Double novoLimiteDiario) {
        Cartao cartao = cartaoRepository.findById(id).orElseThrow();
        cartao.setLimiteDiario(novoLimiteDiario);
        return cartaoRepository.save(cartao);
    }
}
