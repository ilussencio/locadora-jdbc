package com.ilussencio.models;

public class Carro {
    private int id;
    private String placa;
    private String marca;
    private String modelo;
    private Float valor;
    private boolean status = false;

    public Carro(){};

    public Carro(int id, String placa, String marca, String modelo, boolean status, Float valor) {
        this.id = id;
        this.placa = placa;
        this.marca = marca;
        this.modelo = modelo;
        this.status = status;
        this.valor = valor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Float getValor() {
        return valor;
    }

    public void setValor(Float valor) {
        this.valor = valor;
    }

    public boolean isStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "[" +
                "id=" + id +
                ", placa='" + placa + '\'' +
                ", marca='" + marca + '\'' +
                ", modelo='" + modelo + '\'' +
                ", valor=" + valor + +'\''+
                ", status=" + status +
                ']';
    }
}
