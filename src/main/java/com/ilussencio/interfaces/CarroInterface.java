package com.ilussencio.interfaces;

import com.ilussencio.models.Carro;
import com.ilussencio.models.Cliente;

import java.sql.SQLException;
import java.util.List;

public interface CarroInterface {
    public Carro findById(int id) throws SQLException;
    public List<Carro> findAll() throws SQLException;
    public void insert(Carro carro) throws SQLException;
    public void update(Carro carro) throws SQLException;
    public void delete(int id);
}
