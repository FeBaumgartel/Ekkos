/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataservices.dao;

import dataservices.conexao.Conexao;
import domain.Venda;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author felipe_baumgartel
 */
public class VendaDAO {
    private Conexao conexao;
    private Connection connection;

    public VendaDAO() {
        conexao = new Conexao();
        connection = conexao.abrirConexao();
    }

    public void save(Venda venda) {
        int id = 0;

        String sql1 = "INSERT INTO tb_venda(vl_venda, dt_venda, tb_cliente_cd_cliente, tb_funcionario_cd_funcionario) VALUES (?,?,?,?)";
        String sql2 = "INSERT INTO tb_produto_tb_venda(tb_produto_cd_produto, tb_venda_cd_venda, vl_quantidade) VALUES (?,?,?)";

        

        try {

            PreparedStatement pstmt1 = (PreparedStatement) connection.prepareStatement(sql1);
            PreparedStatement pstmt2 = (PreparedStatement) connection.prepareStatement(sql2);

            pstmt1.setDouble(1, venda.getVl_venda());
            pstmt1.setDate(2, Date.valueOf(venda.getDt_venda()));
            pstmt1.setDouble(3, venda.getCliente().getCd_cliente());
            pstmt1.setInt(4, venda.getFuncionario().getCd_funcionario());
            pstmt1.execute();

            pstmt2.setString(1, venda.getEstoque().getPrateleira());

            ResultSet res3 = pstmt3.executeQuery(sql3);
            while (res3.next()) {
                id = res3.getInt("MAX(cd_estoque)");
            }
            pstmt1.setInt(7, id);
            pstmt2.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public Venda getById(long id) {
        String sql = "SELECT * FROM tb_venda WHERE cd_venda = " + id;

        Venda venda = new Venda();
        Estoque estoque = new Estoque();
        Categoria categoria = new Categoria();
        Fornecedor fornecedor = new Fornecedor();

        try {
            PreparedStatement pstmt = (PreparedStatement) connection.prepareStatement(sql);
            ResultSet res = pstmt.executeQuery(sql);

            while (res.next()) {

                venda.setCd_venda(res.getInt("cd_venda"));
                venda.setNm_venda(res.getString("nm_venda"));
                venda.setDs_venda(res.getString("ds_venda"));
                venda.setVl_preco(res.getDouble("vl_preco"));
                venda.setVl_quantidade(res.getInt("vl_quantidade"));
                int idCat = res.getInt("TB_Categoria_Cd_Categoria");
                int idForn = res.getInt("TB_Fornecedor_Cd_Fornecedor");
                int idEst = res.getInt("TB_Estoque_Cd_Estoque");

                String sql2 = "SELECT * FROM TB_categotria WHERE cd_categoria = " + idCat + " ORDER BY cd_categoria ASC";
                PreparedStatement pstmt2 = (PreparedStatement) connection.prepareStatement(sql2);
                ResultSet res2 = pstmt2.executeQuery(sql2);

                while (res2.next()) {
                    categoria.setCd_categoria(res2.getInt("cd_categotria"));
                    categoria.setDs_categoria(res2.getString("ds_categoria"));
                }
                venda.setCategoria(categoria);

                String sql3 = "SELECT * FROM TB_fornecedor WHERE cd_fornecedor = " + idForn + " ORDER BY cd_fornecedor ASC";
                PreparedStatement pstmt3 = (PreparedStatement) connection.prepareStatement(sql3);
                ResultSet res3 = pstmt3.executeQuery(sql3);

                while (res2.next()) {
                    fornecedor.setCd_fornecedor(res.getInt("cd_fornecedor"));
                    fornecedor.setNm_fornecedor(res3.getString("nm_fornecedor"));
                    fornecedor.setNr_cnpj(res3.getString("nr_cnpj"));
                }
                venda.setFornecedor(fornecedor);

                String sql4 = "SELECT * FROM TB_estoque WHERE cd_estoque = " + idEst + " ORDER BY cd_categoria ASC";
                PreparedStatement pstmt4 = (PreparedStatement) connection.prepareStatement(sql4);
                ResultSet res4 = pstmt4.executeQuery(sql4);

                while (res4.next()) {
                    estoque.setCd_estoque(res4.getInt("cd_estoque"));
                    estoque.setPrateleira(res4.getString("prateleira"));
                }
                venda.setEstoque(estoque);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return venda;
    }

    public List<Venda> list() {
        String sql = "SELECT * FROM tb_venda ORDER BY cd_venda ASC";

        List<Venda> lista = new ArrayList<>();

        try {
            PreparedStatement pstmt = (PreparedStatement) connection.prepareStatement(sql);
            ResultSet res = pstmt.executeQuery(sql);

            while (res.next()) {
                Venda venda = new Venda();
                Estoque estoque = new Estoque();
                Categoria categoria = new Categoria();
                Fornecedor fornecedor = new Fornecedor();

                venda.setCd_venda(res.getInt("cd_venda"));
                venda.setNm_venda(res.getString("nm_venda"));
                venda.setDs_venda(res.getString("ds_venda"));
                venda.setVl_preco(res.getDouble("vl_preco"));
                venda.setVl_quantidade(res.getInt("vl_quantidade"));
                int idCat = res.getInt("TB_Categoria_Cd_Categoria");
                int idForn = res.getInt("TB_Fornecedor_Cd_Fornecedor");
                int idEst = res.getInt("TB_Estoque_Cd_Estoque");

                String sql2 = "SELECT * FROM TB_categotria WHERE cd_categoria = " + idCat + " ORDER BY cd_categoria ASC";
                PreparedStatement pstmt2 = (PreparedStatement) connection.prepareStatement(sql2);
                ResultSet res2 = pstmt2.executeQuery(sql2);

                while (res2.next()) {
                    categoria.setCd_categoria(res2.getInt("cd_categotria"));
                    categoria.setDs_categoria(res2.getString("ds_categoria"));
                }
                venda.setCategoria(categoria);

                String sql3 = "SELECT * FROM TB_fornecedor WHERE cd_fornecedor = " + idForn + " ORDER BY cd_fornecedor ASC";
                PreparedStatement pstmt3 = (PreparedStatement) connection.prepareStatement(sql3);
                ResultSet res3 = pstmt3.executeQuery(sql3);

                while (res2.next()) {
                    fornecedor.setCd_fornecedor(res.getInt("cd_fornecedor"));
                    fornecedor.setNm_fornecedor(res3.getString("nm_fornecedor"));
                    fornecedor.setNr_cnpj(res3.getString("nr_cnpj"));
                }
                venda.setFornecedor(fornecedor);

                String sql4 = "SELECT * FROM TB_estoque WHERE cd_estoque = " + idEst + " ORDER BY cd_categoria ASC";
                PreparedStatement pstmt4 = (PreparedStatement) connection.prepareStatement(sql4);
                ResultSet res4 = pstmt4.executeQuery(sql4);

                while (res4.next()) {
                    estoque.setCd_estoque(res4.getInt("cd_estoque"));
                    estoque.setPrateleira(res4.getString("prateleira"));
                }
                venda.setEstoque(estoque);

                lista.add(venda);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return lista;
    }

    public void update(Venda venda) {
        String sql1 = "INSERT INTO tb_venda(nm_venda, ds_venda, vl_preco, vl_quantidade, tb_categotia_cd_categoria, tb_fornecedor_cd_fornecedor, tb_estoque_cd_estoque) VALUES (?,?,?,?,?,?,?)";
        String sql2 = "INSERT INTO tb_estoque(prateleira) VALUES (?)";

        try {

            PreparedStatement pstmt1 = (PreparedStatement) connection.prepareStatement(sql1);
            PreparedStatement pstmt2 = (PreparedStatement) connection.prepareStatement(sql2);

            pstmt1.setString(1, venda.getNm_venda());
            pstmt1.setString(2, venda.getDs_venda());
            pstmt1.setDouble(3, venda.getVl_preco());
            pstmt1.setInt(4, venda.getVl_quantidade());
            pstmt1.setInt(5, venda.getCategoria().getCd_categoria());
            pstmt1.setInt(6, venda.getFornecedor().getCd_fornecedor());
            pstmt1.execute();

            pstmt2.setString(1, venda.getEstoque().getPrateleira());

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
