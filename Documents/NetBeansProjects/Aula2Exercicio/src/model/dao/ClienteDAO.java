/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import connection.ConnectionFactory;
import java.sql.Connection;
import model.bean.Cliente;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author Pedro
 */
public class ClienteDAO {

    public void create(Cliente cliente) {

        Connection con = ConnectionFactory.getConnection();
        PreparedStatement prep = null;

        try {
            prep = con.prepareStatement("INSERT INTO cliente (nome, cpf, telefone, dataaniversario, email) VALUES (?,?,?,?,?)");
            prep.setString(1, cliente.getNome());
            prep.setString(2, cliente.getCpf());
            prep.setString(3, cliente.getTelefone());
            prep.setString(4, cliente.getDataAniversario());
            prep.setString(5, cliente.getEmail());

            con.setAutoCommit(false);
            
            prep.executeUpdate();      
            con.commit();
            
            JOptionPane.showMessageDialog(null, "Salvo com sucesso!");

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar" + ex);
            try {
                con.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } finally {
              ConnectionFactory.CloseConnection(con, prep);
            try {
                con.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    public ArrayList<Cliente> read() {

        Connection con = ConnectionFactory.getConnection();
        PreparedStatement prep = null;
        ResultSet rs = null;

        ArrayList<Cliente> listaClientes = new ArrayList<>();

        try {
            prep = con.prepareStatement("SELECT * FROM cliente ORDER BY idcliente");
            rs = prep.executeQuery();

            while (rs.next()) {

                Cliente cliente = new Cliente();
                cliente.setIdCliente(rs.getInt("idcliente"));
                cliente.setNome(rs.getString("nome"));
                cliente.setCpf(rs.getString("cpf"));
                cliente.setTelefone(rs.getString("telefone"));
                cliente.setDataAniversario(rs.getString("dataaniversario"));
                cliente.setEmail(rs.getString("email"));
                listaClientes.add(cliente);
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao ler os clientes!", "erro", JOptionPane.ERROR_MESSAGE);
        } finally {
            ConnectionFactory.CloseConnection(con, prep, rs);
        }
        return listaClientes;
    }

    public void update(Cliente cliente) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement prep = null;

        try {
            prep = con.prepareStatement("UPDATE cliente set nome = ?, cpf = ?, telefone = ?, dataaniversario = ?, email = ? WHERE idcliente = ? ");

            prep.setString(1, cliente.getNome());
            prep.setString(2, cliente.getCpf());
            prep.setString(3, cliente.getTelefone());
            prep.setString(4, cliente.getDataAniversario());
            prep.setString(5, cliente.getEmail());
            prep.setInt(6, cliente.getIdCliente());

            prep.executeUpdate();

            JOptionPane.showMessageDialog(null, "Atualizado com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar!" + ex);
        } finally {
            ConnectionFactory.CloseConnection(con, prep);
        }

    }

    public void delete(Cliente cliente) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement prep = null;

        try {
            prep = con.prepareStatement("DELETE FROM cliente WHERE idcliente = ? ");
            prep.setInt(1, cliente.getIdCliente());

            prep.executeUpdate();

            JOptionPane.showMessageDialog(null, "Excluído com sucesso!");

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir!" + ex);
        } finally {
            ConnectionFactory.CloseConnection(con, prep);
        }

    }

}
