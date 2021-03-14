/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataservices.dao;

import dataservices.conexao.Conexao;
import domain.Categoria;
import domain.Estoque;
import domain.Fornecedor;
import domain.Produto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author felipe_baumgartel
 */
public class ProdutoDAO {

    private Conexao conexao;
    private Connection connection;

    public ProdutoDAO() {
        conexao = new Conexao();
        connection = conexao.abrirConexao();
    }

    public void save(Produto produto) {
        int id = 0;

        String sql1 = "INSERT INTO tb_produto(nm_produto, ds_produto, vl_preco, vl_quantidade, tb_categoRia_cd_categoria, tb_fornecedor_cd_fornecedor, tb_estoque_cd_estoque) VALUES (?,?,?,?,?,?,?)";
        String sql2 = "INSERT INTO tb_estoque(prateleira) VALUES (?)";

        //Como possui auto increment necessita destes selects
        String sql3 = "SELECT MAX(cd_estoque) FROM tb_estoque";

        try {

            PreparedStatement pstmt1 = (PreparedStatement) connection.prepareStatement(sql1);
            PreparedStatement pstmt2 = (PreparedStatement) connection.prepareStatement(sql2);
            PreparedStatement pstmt3 = (PreparedStatement) connection.prepareStatement(sql3);

            pstmt1.setString(1, produto.getNm_produto());
            pstmt1.setString(2, produto.getDs_produto());
            pstmt1.setDouble(3, produto.getVl_preco());
            pstmt1.setInt(4, produto.getVl_quantidade());
            pstmt1.setInt(5, produto.getCategoria().getCd_categoria());
            pstmt1.setInt(6, produto.getFornecedor().getCd_fornecedor());

            pstmt2.setString(1, produto.getEstoque().getPrateleira());
            pstmt2.execute();
            
            ResultSet res3 = pstmt3.executeQuery(sql3);
            while (res3.next()) {
                id = res3.getInt("MAX(cd_estoque)");
            }
            pstmt1.setInt(7, id);
            pstmt1.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public Produto getById(long id) {
        String sql = "SELECT * FROM tb_produto WHERE cd_produto = " + id;

        Produto produto = new Produto();
        Estoque estoque = new Estoque();
        Categoria categoria = new Categoria();
        Fornecedor fornecedor = new Fornecedor();

        try {
            PreparedStatement pstmt = (PreparedStatement) connection.prepareStatement(sql);
            ResultSet res = pstmt.executeQuery(sql);

            while (res.next()) {

                produto.setCd_produto(res.getInt("cd_produto"));
                produto.setNm_produto(res.getString("nm_produto"));
                produto.setDs_produto(res.getString("ds_produto"));
                produto.setVl_preco(res.getDouble("vl_preco"));
                produto.setVl_quantidade(res.getInt("vl_quantidade"));
                int idCat = res.getInt("TB_Categoria_Cd_Categoria");
                int idForn = res.getInt("TB_Fornecedor_Cd_Fornecedor");
                int idEst = res.getInt("TB_Estoque_Cd_Estoque");

                String sql2 = "SELECT * FROM TB_categotria WHERE cd_categoria = " + idCat + " ORDER BY cd_categoria ASC";
                PreparedStatement pstmt2 = (PreparedStatement) connection.prepareStatement(sql2);
                ResultSet res2 = pstmt2.executeQuery(sql2);

                while (res2.next()) {
                    categoria.setCd_categoria(res2.getInt("cd_categoria"));
                    categoria.setDs_categoria(res2.getString("ds_categoria"));
                }
                produto.setCategoria(categoria);

                String sql3 = "SELECT * FROM TB_fornecedor WHERE cd_fornecedor = " + idForn + " ORDER BY cd_fornecedor ASC";
                PreparedStatement pstmt3 = (PreparedStatement) connection.prepareStatement(sql3);
                ResultSet res3 = pstmt3.executeQuery(sql3);

                while (res2.next()) {
                    fornecedor.setCd_fornecedor(res.getInt("cd_fornecedor"));
                    fornecedor.setNm_fornecedor(res3.getString("nm_fornecedor"));
                    fornecedor.setNr_cnpj(res3.getString("nr_cnpj"));
                }
                produto.setFornecedor(fornecedor);

                String sql4 = "SELECT * FROM TB_estoque WHERE cd_estoque = " + idEst + " ORDER BY cd_estoque ASC";
                PreparedStatement pstmt4 = (PreparedStatement) connection.prepareStatement(sql4);
                ResultSet res4 = pstmt4.executeQuery(sql4);

                while (res4.next()) {
                    estoque.setCd_estoque(res4.getInt("cd_estoque"));
                    estoque.setPrateleira(res4.getString("prateleira"));
                }
                produto.setEstoque(estoque);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return produto;
    }

    public List<Produto> list() {
        String sql = "SELECT * FROM tb_produto ORDER BY cd_produto ASC";

        List<Produto> lista = new ArrayList<>();

        try {
            PreparedStatement pstmt = (PreparedStatement) connection.prepareStatement(sql);
            ResultSet res = pstmt.executeQuery(sql);

            while (res.next()) {

                Produto produto = new Produto();
                Estoque estoque = new Estoque();
                Categoria categoria = new Categoria();
                Fornecedor fornecedor = new Fornecedor();

                produto.setCd_produto(res.getInt("cd_produto"));
                produto.setNm_produto(res.getString("nm_produto"));
                produto.setDs_produto(res.getString("ds_produto"));
                produto.setVl_preco(res.getDouble("vl_preco"));
                produto.setVl_quantidade(res.getInt("vl_quantidade"));
                int idCat = res.getInt("TB_Categoria_Cd_Categoria");
                int idForn = res.getInt("TB_Fornecedor_Cd_Fornecedor");
                int idEst = res.getInt("TB_Estoque_Cd_Estoque");

                String sql2 = "SELECT * FROM TB_categoria WHERE cd_categoria = " + idCat + " ORDER BY cd_categoria ASC";
                PreparedStatement pstmt2 = (PreparedStatement) connection.prepareStatement(sql2);
                ResultSet res2 = pstmt2.executeQuery(sql2);

                while (res2.next()) {
                    categoria.setCd_categoria(res2.getInt("cd_categoria"));
                    categoria.setDs_categoria(res2.getString("ds_categoria"));
                }
                produto.setCategoria(categoria);

                String sql3 = "SELECT * FROM TB_fornecedor WHERE cd_fornecedor = " + idForn + " ORDER BY cd_fornecedor ASC";
                PreparedStatement pstmt3 = (PreparedStatement) connection.prepareStatement(sql3);
                ResultSet res3 = pstmt3.executeQuery(sql3);

                while (res3.next()) {
                    fornecedor.setCd_fornecedor(res3.getInt("cd_fornecedor"));
                    fornecedor.setNm_fornecedor(res3.getString("nm_fornecedor"));
                    fornecedor.setNr_cnpj(res3.getString("nr_cnpj"));
                }
                produto.setFornecedor(fornecedor);

                String sql4 = "SELECT * FROM TB_estoque WHERE cd_estoque = " + idEst + " ORDER BY cd_estoque ASC";
                PreparedStatement pstmt4 = (PreparedStatement) connection.prepareStatement(sql4);
                ResultSet res4 = pstmt4.executeQuery(sql4);

                while (res4.next()) {
                    estoque.setCd_estoque(res4.getInt("cd_estoque"));
                    estoque.setPrateleira(res4.getString("Prateleira"));
                }
                produto.setEstoque(estoque);

                lista.add(produto);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return lista;
    }

    public void update(Produto produto) {
        String sql1 = "INSERT INTO tb_produto(nm_produto, ds_produto, vl_preco, vl_quantidade, tb_categotia_cd_categoria, tb_fornecedor_cd_fornecedor, tb_estoque_cd_estoque) VALUES (?,?,?,?,?,?,?)";
        String sql2 = "INSERT INTO tb_estoque(prateleira) VALUES (?)";

        try {

            PreparedStatement pstmt1 = (PreparedStatement) connection.prepareStatement(sql1);
            PreparedStatement pstmt2 = (PreparedStatement) connection.prepareStatement(sql2);

            pstmt1.setString(1, produto.getNm_produto());
            pstmt1.setString(2, produto.getDs_produto());
            pstmt1.setDouble(3, produto.getVl_preco());
            pstmt1.setInt(4, produto.getVl_quantidade());
            pstmt1.setInt(5, produto.getCategoria().getCd_categoria());
            pstmt1.setInt(6, produto.getFornecedor().getCd_fornecedor());
            pstmt1.execute();

            pstmt2.setString(1, produto.getEstoque().getPrateleira());

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
