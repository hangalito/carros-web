/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package classesJava;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author bartolomeu
 */
public class CarrosDAO {
    String sql;
    Connection conexao;
    PreparedStatement ps;
    ResultSet rs;

    public void mensagem(String msg){
        FacesContext.getCurrentInstance().addMessage(msg,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "", msg));
    }

    public void salvar(Carro carro) {
        try {
            conexao = ConectaDB.getConnection();
            sql = "INSERT INTO tbCarros (marca, modelo, cor, ano) values(?,?,?,?)";
            ps = conexao.prepareStatement(sql);
            ps.setString(1, carro.getMarca());
            ps.setString(2, carro.getModelo());
            ps.setString(3, carro.getCor());
            ps.setInt(4, carro.getAno());
            ps.execute();
            ConectaDB.fechaConexao();
            mensagem("Carro cadastrado com sucesso!");
        } catch (SQLException ex) {
            Logger.getLogger(CarrosDAO.class.getName()).log(Level.SEVERE, null, ex);
            mensagem("Erro ao cadastrar!");
        }
    }

    public void editar(Carro carro) {
        try {
            conexao = ConectaDB.getConnection();
            sql = "update tbCarros set"
                    + "marca = ?"
                    + "modelo = ?"
                    + "cor = ?"
                    + "ano = ?"
                    + "where idCarro = ?";
            ps = conexao.prepareStatement(sql);
            ps.setString(1, carro.getMarca());
            ps.setString(2, carro.getModelo());
            ps.setString(3, carro.getCor());
            ps.setInt(4, carro.getAno());
            ps.setInt(5, carro.getId());
            ps.execute();
            ConectaDB.fechaConexao();
            mensagem("Carro alterado com sucesso!");
        } catch(SQLException ex) {
            Logger.getLogger(CarrosDAO.class.getName()).log(Level.SEVERE, null, ex);
            mensagem("Erro ao editar!");
        }
    }

    public void excluir(int id) {
        try {
            conexao = ConectaDB.getConnection();
            sql = "delete from tbCarros where idCarro = ?";
            ps = conexao.prepareStatement(sql);
            ps.setInt(1, id);
            ps.execute();
            ConectaDB.fechaConexao();
            mensagem("Carro excluído com sucesso!");
        } catch (SQLException ex) {
            Logger.getLogger(CarrosDAO.class.getName()).log(Level.SEVERE, null, ex);
            mensagem("Erro ao excluir");
        }
    }

    public List<Carro> listar() {
        try {
            conexao = ConectaDB.getConnection();
            sql = "select * from tbCarros";
            ps = conexao.prepareStatement(sql);
            rs = ps.executeQuery();
            List<Carro> listaCarros = new ArrayList<>();
            while (rs.next()) {
                Carro carro = new Carro();
                carro.setId(rs.getInt("idCarro"));
                carro.setMarca(rs.getString("marca"));
                carro.setModelo(rs.getString("modelo"));
                carro.setCor(rs.getString("cor"));
                carro.setAno(rs.getInt("ano"));
                listaCarros.add(carro);
            }
            return listaCarros;
        } catch (SQLException ex) {
            Logger.getLogger(CarrosDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
}
