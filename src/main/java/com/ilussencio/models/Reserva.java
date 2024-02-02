package com.ilussencio.models;

import java.time.Instant;
import java.sql.Date;

public class Reserva {
    private int id;
    private Cliente cliente;
    private Carro carro;
    private Date data_locacao;
    private int qtd_dias;

    public Reserva(int id, Cliente cliente, Carro carro, Date data_locacao, int qtd_dias) {
        this.id = id;
        this.cliente = cliente;
        this.carro = carro;
        this.data_locacao = data_locacao;
        this.qtd_dias = qtd_dias;
    }

    public Reserva() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Carro getCarro() {
        return carro;
    }

    public void setCarro(Carro carro) {
        this.carro = carro;
    }

    public Date getData_locacao() {
        return data_locacao;
    }

    public void setData_locacao(Date data_locacao) {
        this.data_locacao = data_locacao;
    }

    public int getQtd_dias() {
        return qtd_dias;
    }

    public void setQtd_dias(int qtd_dias) {
        this.qtd_dias = qtd_dias;
    }

    @Override
    public String toString() {
        return "Reserva{" +
                "id=" + id +
                ", cliente=" + cliente +
                ", carro=" + carro +
                ", data_locacao=" + data_locacao +
                ", qtd_dias=" + qtd_dias +
                '}';
    }
}
