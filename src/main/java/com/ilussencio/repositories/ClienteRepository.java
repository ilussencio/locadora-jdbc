package com.ilussencio.repositories;

import com.ilussencio.interfaces.ClienteInterface;
import com.ilussencio.models.Cliente;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteRepository implements ClienteInterface {

    @Value("${spring.datasource.url")
    private String url = "jdbc:postgresql://100.112.213.156/locadora";

    @Value("${spring.datasource.username}")
    private String username = "dev";

    @Value("${spring.datasource.password}")
    private String password = "P@1308word";

    private Connection conexao;

    public ClienteRepository() {
        try {
            conexao = DriverManager.getConnection(url, username, password);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public Cliente findById(int id) throws SQLException {

        Cliente cliente = new Cliente();
        try(var statement = conexao.prepareStatement("select * from cliente where id = ?;")){
            statement.setInt(1, id);
            try(var resultset = statement.executeQuery()) {
                while (resultset.next()) {
                    cliente.setId(resultset.getInt("id"));
                    cliente.setNome(resultset.getString("nome"));
                    cliente.setTelefone(resultset.getString("telefone"));
                    cliente.setCpf(resultset.getString("cpf"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cliente;
    }

    @Override
    public List<Cliente> findAll() throws SQLException {
        List<Cliente> clientes = new ArrayList<>();
        try(var statement = conexao.prepareStatement("select * from cliente");
            var resultset = statement.executeQuery()
        ){
            while (resultset.next()) {
                Cliente cl = new Cliente();
                cl.setId(resultset.getInt("id"));
                cl.setNome(resultset.getString("nome"));
                cl.setTelefone(resultset.getString("telefone"));
                cl.setCpf(resultset.getString("cpf"));
                clientes.add(cl);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clientes;
    }

    @Override
    public void insert(Cliente cliente) throws SQLException {
        var sql = "insert into cliente (nome, telefone, cpf) values (?,?,?);";
        try(var statement = conexao.prepareStatement(sql)){
            statement.setString(1, cliente.getNome());
            statement.setString(2, cliente.getTelefone());
            statement.setString(3, cliente.getCpf());
            statement.executeUpdate();
            System.out.println("(REPOSITORY) Cliente inserido com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Cliente cliente) throws SQLException {
       try(var statement = conexao.prepareStatement("update cliente set nome = ?, telefone = ?, cpf = ? where id = ?;")){
           statement.setString(1, cliente.getNome());
           statement.setString(2, cliente.getTelefone());
           statement.setString(3, cliente.getCpf());
           statement.setInt(4, cliente.getId());
           statement.executeUpdate();
           System.out.println("(REPOSITORY) Cliente atualizado com sucesso!");
       } catch (SQLException e) {
           e.printStackTrace();
       }
    }

    @Override
    public void delete(int id) {
        var sql = "delete from cliente where id = ?;";
        try(var statement = conexao.prepareStatement(sql)){
            statement.setInt(1, id);
            statement.executeUpdate();
            System.out.println("(REPOSITORY) Cliente deletado com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
