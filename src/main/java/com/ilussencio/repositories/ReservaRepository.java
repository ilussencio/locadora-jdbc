package com.ilussencio.repositories;

import com.ilussencio.interfaces.ReservaInterface;
import com.ilussencio.models.Reserva;
import org.springframework.beans.factory.annotation.Value;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReservaRepository implements ReservaInterface {

    @Value("${spring.datasource.url")
    private String url = "jdbc:postgresql://100.112.213.156/locadora";

    @Value("${spring.datasource.username}")
    private String username = "dev";

    @Value("${spring.datasource.password}")
    private String password = "P@1308word";

    private Connection conexao;

    public ReservaRepository() {
        try {
            conexao = DriverManager.getConnection(url, username, password);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void insert (Reserva reserva) throws SQLException{
        var sql = "insert into reserva (id_cliente, id_carro, data_locacao, qtd_dias) values (?,?,?,?);";
        try(var statement = conexao.prepareStatement(sql)){
            statement.setInt(1, reserva.getCliente().getId());
            statement.setInt(2, reserva.getCarro().getId());
            statement.setDate(3, reserva.getData_locacao());
            statement.setInt(4, reserva.getQtd_dias());
            statement.executeUpdate();
            System.out.println("(REPOSITORY) Reserva cadastrada com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Reserva> findAll() throws SQLException {
        List<Reserva> reservas = new ArrayList<>();
        try(var statement = conexao.prepareStatement("select * from reserva");
            var resultset = statement.executeQuery()
        ){
            while (resultset.next()) {
                Reserva reserva = new Reserva();
                reserva.setId(resultset.getInt("id"));
                reserva.setCarro(new CarroRepository().findById(resultset.getInt("id_carro")));
                reserva.setCliente(new ClienteRepository().findById(resultset.getInt("id_cliente")));
                reserva.setData_locacao(resultset.getDate("data_locacao"));
                reserva.setQtd_dias(resultset.getInt("qtd_dias"));
                reservas.add(reserva);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reservas;
    }

    @Override
    public List<Reserva> findByCliente(int id) throws SQLException {
        List<Reserva> reservas = new ArrayList<>();
        try(var statement = conexao.prepareStatement("select * from reserva where id_cliente = ?");){
            statement.setInt(1, id);

            var resultset = statement.executeQuery();
            while (resultset.next()) {
                Reserva reserva = new Reserva();
                reserva.setId(resultset.getInt("id"));
                reserva.setCarro(new CarroRepository().findById(resultset.getInt("id_carro")));
                reserva.setCliente(new ClienteRepository().findById(resultset.getInt("id_cliente")));
                reserva.setData_locacao(resultset.getDate("data_locacao"));
                reserva.setQtd_dias(resultset.getInt("qtd_dias"));
                reservas.add(reserva);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reservas;
    }

    @Override
    public List<Reserva> findByCarro(int id) throws SQLException {
        List<Reserva> reservas = new ArrayList<>();
        try(var statement = conexao.prepareStatement("select * from reserva where id_carro = ?")){
            statement.setInt(1, id);
            var resultset = statement.executeQuery();
            while (resultset.next()) {
                Reserva reserva = new Reserva();
                reserva.setId(resultset.getInt("id"));
                reserva.setCarro(new CarroRepository().findById(resultset.getInt("id_carro")));
                reserva.setCliente(new ClienteRepository().findById(resultset.getInt("id_cliente")));
                reserva.setData_locacao(resultset.getDate("data_locacao"));
                reserva.setQtd_dias(resultset.getInt("qtd_dias"));
                reservas.add(reserva);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reservas;
    }
}
