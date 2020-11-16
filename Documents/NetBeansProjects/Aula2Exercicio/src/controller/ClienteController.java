/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.ArrayList;
import model.bean.Cliente;
import model.dao.ClienteDAO;

/**
 *
 * @author Pedro
 */
public class ClienteController {
    
    public void create(String nome, String cpf, String telefone, String dataAniversario, String email){
        
        Cliente cli = new Cliente();
        ClienteDAO cliDAO = new ClienteDAO();
        
        cli.setNome(nome);
        cli.setCpf(cpf);
        cli.setTelefone(telefone);
        cli.setDataAniversario(dataAniversario);
        cli.setEmail(email);
        cliDAO.create(cli);
        
        
        
    }
    
   public ArrayList<Cliente> read(){
       ClienteDAO cliDAO = new ClienteDAO();
       return cliDAO.read();
       
   }
   
   public void update(int idcliente, String nome, String cpf, String telefone, String dataAniversario, String email){
       Cliente cliente = new Cliente();
       
       cliente.setIdCliente(idcliente);
       cliente.setNome(nome);
       cliente.setCpf(cpf);
       cliente.setTelefone(telefone);
       cliente.setDataAniversario(dataAniversario);
       cliente.setEmail(email);
       
       ClienteDAO clienteDAO = new ClienteDAO();
       clienteDAO.update(cliente);
       
   }
   
   public void delete(int idcliente){
       Cliente cliente = new Cliente();
       ClienteDAO cliDAO = new ClienteDAO();
       
       cliente.setIdCliente(idcliente);
       
       cliDAO.delete(cliente);
}
    
    
}
