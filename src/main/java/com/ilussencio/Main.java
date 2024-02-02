package com.ilussencio;


import com.ilussencio.models.Carro;
import com.ilussencio.models.Cliente;
import com.ilussencio.models.Reserva;
import com.ilussencio.repositories.CarroRepository;
import com.ilussencio.repositories.ClienteRepository;
import com.ilussencio.repositories.ReservaRepository;

import javax.xml.crypto.Data;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException {
        Scanner scan =  new Scanner(System.in);
        ClienteRepository clienteRepository = new ClienteRepository();
        CarroRepository carroRepository = new CarroRepository();
        ReservaRepository reservaRepository = new ReservaRepository();

        int opcao = 0;

        while(true) {
            System.out.println("==============MENU================");
            System.out.println("Entre com uma opção:");
            System.out.println("(1) Listar todos os clientes");
            System.out.println("(2) Cadastrar cliente");
            System.out.println("(3) Deletar cliente");
            System.out.println("(4) Listar todos os carros");
            System.out.println("(5) Cadastrar carro");
            System.out.println("(6) Deletar carro");
            System.out.println("(7) Alter carro");
            System.out.println("(8) Cadastrar Reserva");
            System.out.println("(9) Listar todas as reservas");
            System.out.println("(10) Listar por cliente");
            System.out.println("(11) Listar por carro");
            System.out.printf("Entre com um opção: ");

            opcao = scan.nextInt();
            switch (opcao) {
                case 1:
                    System.out.println("Listar todos os clientes");
                    for(Cliente c : clienteRepository.findAll()){
                        System.out.println(c);
                    }
                    break;
                case 2:
                    System.out.println("Cadastrar cliente:");
                    Cliente cliente =  new Cliente();
                    scan.nextLine();
                    System.out.printf("Nome:");
                    cliente.setNome(scan.nextLine());
                    System.out.printf("Telefone:");
                    cliente.setTelefone(scan.nextLine());
                    System.out.printf("CPF:");
                    cliente.setCpf(scan.nextLine());
                    clienteRepository.insert(cliente);
                    break;
                case 3:
                    System.out.println("Deletar cliente:");
                    scan.nextLine();
                    System.out.printf("Entre com o ID:");
                    clienteRepository.delete(scan.nextInt());
                    break;
                case 4:
                    System.out.println("Listar todos os Carros");
                    for(Carro c : carroRepository.findAll()){
                        System.out.println(c);
                    }
                    break;
                case 5:
                    System.out.println("Cadastrar Carro");
                    scan.nextLine();
                    Carro carro = new Carro();
                    System.out.printf("Entre com a marca: ");
                    carro.setMarca(scan.nextLine());

                    System.out.printf("Entre com a modelo: ");
                    carro.setModelo(scan.nextLine());

                    System.out.printf("Entre com a placa: ");
                    carro.setPlaca(scan.nextLine());

                    System.out.printf("Entre com o valor da diaria (R$):");
                    carro.setValor(scan.nextFloat());

                    carroRepository.insert(carro);
                    break;
                case 6:
                    System.out.println("Deletar carro Carro");
                    scan.nextLine();
                    System.out.printf("Entre com o ID:");
                    carroRepository.delete(scan.nextInt());
                    break;

                case 7:
                    System.out.println("Alterar carro");

                    for(Carro c : carroRepository.findAll()){
                        System.out.println(c);
                    }
                    scan.nextLine();
                    System.out.printf("Entre com o ID para alterar:");
                    Carro carroUpdate = carroRepository.findById(scan.nextInt());
                    scan.nextLine();
                    System.out.printf("Entre com a marca ("+carroUpdate.getMarca()+"): ");
                    carroUpdate.setMarca(scan.nextLine());

                    System.out.printf("Entre com a modelo ("+carroUpdate.getModelo()+"): ");
                    carroUpdate.setModelo(scan.nextLine());

                    System.out.printf("Entre com a placa ("+carroUpdate.getPlaca()+"): ");
                    carroUpdate.setPlaca(scan.nextLine());

                    carroRepository.update(carroUpdate);

                    break;
                case 8:
                    System.out.println("Selecione um cliente");
                    for(Cliente c : clienteRepository.findAll()){
                        System.out.println(c);
                    }
                    System.out.printf("Id do cliente: ");
                    Cliente cliente_reserva = clienteRepository.findById(scan.nextInt());

                    System.out.println("Selecione um carro: ");
                    for(Carro c : carroRepository.findAll()){
                        System.out.println(c);
                    }
                    System.out.printf("Id do carro: ");
                    Carro carro_reserva = carroRepository.findById(scan.nextInt());


                    System.out.printf("Data da reserva: ");
                    Date data_locacao = new Date((long) scan.nextFloat());

                    System.out.printf("Quantidade de dias: ");
                    float qtd_dias = (float) scan.nextInt();

                    System.out.println("==========================");
                    System.out.println("Resumo da reserva:");
                    System.out.println("Cliente: ");
                    System.out.println(cliente_reserva);
                    System.out.println("Carro: ");
                    System.out.println(carro_reserva);
                    System.out.println("Quantidade de dias: "+ qtd_dias);
                    System.out.println("Data da locacao: "+data_locacao);
                    System.out.println("Valor total: " + (qtd_dias * carro_reserva.getValor()));
                    System.out.printf("Deseja confirmar? (1 - sim | 2 - não): ");
                    int opcao_reserva = scan.nextInt();

                    if(opcao_reserva == 1){
                        Reserva reserva = new Reserva();
                        reserva.setCarro(carro_reserva);
                        reserva.setCliente(cliente_reserva);
                        reserva.setData_locacao(data_locacao);
                        reserva.setQtd_dias((int) qtd_dias);
                        reservaRepository.insert(reserva);

                    }else{
                        System.out.println("Obrigado!");
                    }
                    break;
                case 9:
                    System.out.println("Listar todas as reservas:");
                    for(Reserva r : reservaRepository.findAll()){
                        System.out.println(r);
                    }
                    break;
                case 10:
                    System.out.println("Listar por cliente:");
                    System.out.printf("Entre com o ID do cliente: ");
                    for(Reserva r : reservaRepository.findByCliente(scan.nextInt())){
                        System.out.println(r);
                    }
                    break;
                case 11:
                    System.out.println("Listar por carro:");
                    System.out.printf("Entre com o ID do carro: ");
                    for(Reserva r : reservaRepository.findByCarro(scan.nextInt())){
                        System.out.println(r);
                    }
                    break;
                default:
                    System.out.printf("Opção não encontrada!");
            }

        }
    }
}