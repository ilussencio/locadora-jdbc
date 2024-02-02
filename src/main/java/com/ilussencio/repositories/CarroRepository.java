package com.ilussencio.repositories;

import com.ilussencio.interfaces.CarroInterface;
import com.ilussencio.models.Carro;
import com.ilussencio.models.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CarroRepository implements CarroInterface {

    @Value("${spring.datasource.url")
    private String url = "jdbc:postgresql://100.112.213.156/locadora";

    @Value("${spring.datasource.username}")
    private String username = "dev";

    @Value("${spring.datasource.password}")
    private String password = "P@1308word";

    private Connection conexao;

    public CarroRepository() {
        try {
            conexao = DriverManager.getConnection(url, username, password);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public Carro findById(int id) throws SQLException {
        Carro carro = new Carro();
        try(var statement = conexao.prepareStatement("select * from carro where id = ?;")){
            statement.setInt(1, id);
            try(var resultset = statement.executeQuery()) {
                while (resultset.next()) {
                    carro.setId(resultset.getInt("id"));
                    carro.setPlaca(resultset.getString("placa"));
                    carro.setMarca(resultset.getString("marca"));
                    carro.setModelo(resultset.getString("modelo"));
                    carro.setStatus(resultset.getBoolean("status"));
                    carro.setValor(resultset.getFloat("valor"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return carro;
    }

    @Override
    public List<Carro> findAll() throws SQLException {
        List<Carro> carros = new ArrayList<>();
        try(var statement = conexao.prepareStatement("select * from carro");
            var resultset = statement.executeQuery()
        ){
            while (resultset.next()) {
                Carro carro = new Carro();
                carro.setId(resultset.getInt("id"));
                carro.setPlaca(resultset.getString("placa"));
                carro.setMarca(resultset.getString("marca"));
                carro.setModelo(resultset.getString("modelo"));
                carro.setStatus(resultset.getBoolean("status"));
                carro.setValor(resultset.getFloat("valor"));
                carros.add(carro);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return carros;
    }

    @Override
    public void insert(Carro carro) throws SQLException {
        var sql = "insert into carro (placa, marca, modelo, status, valor) values (?,?,?,?,?);";

        try(var statement = conexao.prepareStatement(sql)){
            statement.setString(1, carro.getPlaca());
            statement.setString(2, carro.getMarca());
            statement.setString(3, carro.getModelo());
            statement.setBoolean(4, carro.getStatus());
            statement.setFloat(5, carro.getValor());
            statement.executeUpdate();

            System.out.println("(REPOSITORY) Carro inserido com sucesso");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Carro carro) throws SQLException {
        try(var statement = conexao.prepareStatement("update carro set placa = ?, marca = ?, modelo = ?, status = ?, valor = ? where id = ?;")){
            statement.setString(1, carro.getPlaca());
            statement.setString(2, carro.getMarca());
            statement.setString(3, carro.getModelo());
            statement.setFloat(4,carro.getValor());
            statement.setBoolean(5, carro.getStatus());
            statement.setInt(6, carro.getId());
            statement.executeUpdate();
            System.out.println("(REPOSITORY) Carro atualizado com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        var sql = "delete from carro where id = ?;";
        try(var statement = conexao.prepareStatement(sql)){
            statement.setInt(1, id);
            statement.executeUpdate();
            System.out.println("(REPOSITORY) Carro deletado com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
