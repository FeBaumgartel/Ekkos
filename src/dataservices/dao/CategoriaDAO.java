/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataservices.dao;

import dataservices.conexao.Conexao;
import domain.Categoria;
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
 * @author Felipe
 */
public class CategoriaDAO {

    private Conexao conexao;
    private Connection connection;

    public CategoriaDAO() {
        conexao = new Conexao();
        connection = conexao.abrirConexao();
    }

    public void save(Categoria categoria) {
        //Instrução sql
        String sql = "Insert into tb_categoria(ds_categoria) values(?);";

        try {

            PreparedStatement pstmt = (PreparedStatement) connection.prepareStatement(sql);
            //setar os parâmetros
            pstmt.setString(1, categoria.getDs_categoria());
            pstmt.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Categoria getById(long id) {
        String sql = "SELECT * FROM tb_categoria where cd_categoria = " + id;
        Categoria categoria = new Categoria();

        try {
            PreparedStatement pstmt = (PreparedStatement) connection.prepareStatement(sql);
            ResultSet res = pstmt.executeQuery(sql);

            while (res.next()) {
                categoria.setCd_categoria(res.getInt("cd_categoria"));
                categoria.setDs_categoria(res.getString("ds_categoria"));
            }

        } catch (SQLException ex) {
            Logger.getLogger(CategoriaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return categoria;
    }

    public List<Categoria> list() {
        String sql = "SELECT * FROM tb_categoria order by cd_categoria asc";
        List<Categoria> lista = new ArrayList<>();

        try {
            PreparedStatement pstmt = (PreparedStatement) connection.prepareStatement(sql);
            ResultSet res = pstmt.executeQuery(sql);

            while (res.next()) {
                Categoria categoria = new Categoria();

                categoria.setCd_categoria(res.getInt("cd_categoria"));
                categoria.setDs_categoria(res.getString("ds_categoria"));

                lista.add(categoria);
            }

        } catch (SQLException ex) {
            Logger.getLogger(CategoriaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }

    public void update(Categoria categoria) {
        String sql = "update tb_categoria set ds_categoria = ? where cd_categoria = ?";

        try {

            PreparedStatement pstmt = (PreparedStatement) connection.prepareStatement(sql);
            //setar os parâmetros
            pstmt.setInt(2, categoria.getCd_categoria());
            pstmt.setString(1, categoria.getDs_categoria());
            pstmt.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
