package com.ilussencio.interfaces;

import com.ilussencio.models.Cliente;

import java.sql.SQLException;
import java.util.List;

public interface ClienteInterface {
    public Cliente findById(int id) throws SQLException;
    public List<Cliente> findAll() throws SQLException;
    public void insert(Cliente carro) throws SQLException;
    public void update(Cliente carro) throws SQLException;
    public void delete(int id);
}
