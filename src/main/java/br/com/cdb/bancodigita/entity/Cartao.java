package br.com.cdb.bancodigita.entity;

import jakarta.persistence.*;

@Entity
public class Cartao {

	//ID como chave primária (geração automática do valor)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tipo; // "CREDITO" ou "DEBITO"

    private Double limite;

    private Double limiteDiario;

    private Double fatura;

    private Boolean ativo;

    private String senha;

    //Define o relacionamento muitos para um com o Cliente
    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    // Getters e Setters
    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getTipo() { return tipo; }

    public void setTipo(String tipo) { this.tipo = tipo; }

    public Double getLimite() { return limite; }

    public void setLimite(Double limite) { this.limite = limite; }

    public Double getLimiteDiario() { return limiteDiario; }

    public void setLimiteDiario(Double limiteDiario) { this.limiteDiario = limiteDiario; }

    public Double getFatura() { return fatura; }

    public void setFatura(Double fatura) { this.fatura = fatura; }

    public Boolean getAtivo() { return ativo; }

    public void setAtivo(Boolean ativo) { this.ativo = ativo; }

    public String getSenha() { return senha; }

    public void setSenha(String senha) { this.senha = senha; }

    public Cliente getCliente() { return cliente; }

    public void setCliente(Cliente cliente) { this.cliente = cliente; }
}
