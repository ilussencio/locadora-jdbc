package com.ilussencio.interfaces;

import com.ilussencio.models.Cliente;
import com.ilussencio.models.Reserva;

import java.sql.SQLException;
import java.util.List;

public interface ReservaInterface {
    public List<Reserva> findAll() throws SQLException;
    public void insert(Reserva reserva) throws SQLException;
    public List<Reserva> findByCarro(int id) throws SQLException;
    public List<Reserva> findByCliente(int id) throws SQLException;
}
