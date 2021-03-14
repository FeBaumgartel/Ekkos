/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataservices.dao;

import dataservices.conexao.Conexao;
import domain.Contato;
import domain.Endereco;
import domain.Fornecedor;
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
public class FornecedorDAO {

    private Conexao conexao;
    private Connection connection;

    public FornecedorDAO() {
        conexao = new Conexao();
        connection = conexao.abrirConexao();
    }

    public void save(Fornecedor fornecedor) {
        int id = 0;

        String sql1 = "INSERT INTO tb_fornecedor(nm_fornecedor, nr_cnpj, TB_Contato_Cd_Contato, TB_Endereco_Cd_Endereco) VALUES (?,?,?,?)";
        String sql2 = "INSERT INTO tb_endereco(nm_rua, nm_cidade, nm_estado, nm_bairro, nr_endereco, nr_cep) VALUES (?,?,?,?,?,?)";
        String sql3 = "INSERT INTO tb_contato(nr_celular, nr_fixo, email, nr_fax) VALUES (?,?,?,?)";

        //Como possui auto increment necessita destes selects
        String sql4 = "SELECT MAX(cd_endereco) FROM tb_endereco";
        String sql5 = "SELECT MAX(cd_contato) FROM tb_contato";

        try {

            PreparedStatement pstmt1 = (PreparedStatement) connection.prepareStatement(sql1);
            PreparedStatement pstmt2 = (PreparedStatement) connection.prepareStatement(sql2);
            PreparedStatement pstmt3 = (PreparedStatement) connection.prepareStatement(sql3);
            PreparedStatement pstmt4 = (PreparedStatement) connection.prepareStatement(sql4);
            PreparedStatement pstmt5 = (PreparedStatement) connection.prepareStatement(sql5);

            pstmt2.setString(1, fornecedor.getEndereco().getNm_rua());
            pstmt2.setString(2, fornecedor.getEndereco().getNm_cidade());
            pstmt2.setString(3, fornecedor.getEndereco().getNm_estado());
            pstmt2.setString(4, fornecedor.getEndereco().getNm_bairro());
            pstmt2.setInt(5, fornecedor.getEndereco().getNr_endereco());
            pstmt2.setString(6, fornecedor.getEndereco().getNr_cep());
            pstmt2.execute();

            pstmt3.setString(1, fornecedor.getContato().getNr_celular());
            pstmt3.setString(2, fornecedor.getContato().getNr_fixo());
            pstmt3.setString(3, fornecedor.getContato().getEmail());
            pstmt3.setString(4, fornecedor.getContato().getNr_fax());
            pstmt3.execute();

            ResultSet res4 = pstmt4.executeQuery(sql4);
            while (res4.next()) {
                id = res4.getInt("MAX(cd_endereco)");
            }
            pstmt1.setInt(4, id);

            ResultSet res5 = pstmt5.executeQuery(sql5);
            while (res5.next()) {
                id = res5.getInt("MAX(cd_contato)");
            }
            pstmt1.setInt(3, id);
            pstmt1.setString(1, fornecedor.getNm_fornecedor());
            pstmt1.setString(2, fornecedor.getNr_cnpj());
            pstmt1.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public Fornecedor getById(long id) {
        String sql = "SELECT * FROM tb_fornecedor WHERE cd_fornecedor = " + id;

        Fornecedor fornecedor = new Fornecedor();
        Endereco endereco = new Endereco();
        Contato contato = new Contato();

        try {
            PreparedStatement pstmt = (PreparedStatement) connection.prepareStatement(sql);
            ResultSet res = pstmt.executeQuery(sql);

            while (res.next()) {

                fornecedor.setCd_fornecedor(res.getInt("cd_fornecedor"));
                fornecedor.setNm_fornecedor(res.getString("nm_fornecedor"));
                fornecedor.setNr_cnpj(res.getString("nr_cnpj"));
                int idEnd = res.getInt("TB_Endereco_Cd_Endereco");
                int idCont = res.getInt("TB_Contato_Cd_Contato");

                String sql2 = "SELECT * FROM TB_endereco WHERE cd_endereco = " + idEnd + " ORDER BY cd_endereco ASC";
                PreparedStatement pstmt2 = (PreparedStatement) connection.prepareStatement(sql2);
                ResultSet res2 = pstmt2.executeQuery(sql2);

                while (res2.next()) {
                    endereco.setCd_endereco(res2.getInt("cd_endereco"));
                    endereco.setNm_rua(res2.getString("nm_rua"));
                    endereco.setNm_bairro(res2.getString("nm_bairro"));
                    endereco.setNm_cidade(res2.getString("nm_cidade"));
                    endereco.setNm_rua(res2.getString("nm_rua"));
                    endereco.setNr_cep(res2.getString("nr_cep"));
                    endereco.setNr_endereco(res2.getInt("nr_endereco"));
                }
                fornecedor.setEndereco(endereco);

                String sql3 = "SELECT * FROM TB_contato WHERE cd_contato = " + idCont + " ORDER BY  ASC";
                PreparedStatement pstmt3 = (PreparedStatement) connection.prepareStatement(sql3);
                ResultSet res3 = pstmt3.executeQuery(sql3);

                while (res2.next()) {
                    contato.setCd_contato(res.getInt("cd_contato"));
                    contato.setNr_celular(res3.getString("nr_celular"));
                    contato.setEmail(res3.getString("email"));
                    contato.setNr_fax(res3.getString("nr_fax"));
                    contato.setNr_fixo(res3.getString("nr_fixo"));
                }
                fornecedor.setContato(contato);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return fornecedor;
    }

    public List<Fornecedor> list() {
        String sql = "SELECT * FROM tb_fornecedor ORDER BY cd_fornecedor ASC";

        List<Fornecedor> lista = new ArrayList<>();

        try {
            PreparedStatement pstmt = (PreparedStatement) connection.prepareStatement(sql);
            ResultSet res = pstmt.executeQuery(sql);

            while (res.next()) {
                Fornecedor fornecedor = new Fornecedor();
                Endereco endereco = new Endereco();
                Contato contato = new Contato();

                fornecedor.setCd_fornecedor(res.getInt("cd_fornecedor"));
                fornecedor.setNm_fornecedor(res.getString("nm_fornecedor"));
                fornecedor.setNr_cnpj(res.getString("nr_cnpj"));
                    int idEnd = res.getInt("TB_Endereco_Cd_Endereco");
                    int idCont = res.getInt("TB_Contato_Cd_Contato");

                    String sql3 = "SELECT * FROM TB_endereco WHERE cd_endereco = " + idEnd;
                    PreparedStatement pstmt3 = (PreparedStatement) connection.prepareStatement(sql3);
                    ResultSet res3 = pstmt3.executeQuery(sql3);

                    while (res3.next()) {
                        endereco.setNm_rua(res3.getString("nm_rua"));
                        endereco.setNm_bairro(res3.getString("nm_bairro"));
                        endereco.setNm_cidade(res3.getString("nm_cidade"));
                        endereco.setNm_rua(res3.getString("nm_rua"));
                        endereco.setNr_cep(res3.getString("nr_cep"));
                        endereco.setNr_endereco(res3.getInt("nr_endereco"));
                    }
                    fornecedor.setEndereco(endereco);

                    String sql4 = "SELECT * FROM TB_contato WHERE cd_contato = " + idCont;
                    PreparedStatement pstmt4 = (PreparedStatement) connection.prepareStatement(sql4);
                    ResultSet res4 = pstmt4.executeQuery(sql4);

                    while (res4.next()) {
                        contato.setNr_celular(res4.getString("nr_celular"));
                        contato.setEmail(res4.getString("email"));
                        contato.setNr_fax(res4.getString("nr_fax"));
                        contato.setNr_fixo(res4.getString("nr_fixo"));
                    }
                    fornecedor.setContato(contato);

                lista.add(fornecedor);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return lista;
    }

    public void update(Fornecedor fornecedor) {
        String sql1 = "UPDATE tb_fornecedor SET nm_fornecedor = ?, nr_cnpj = ? WHERE cd_fornecedor = ?";
        String sql2 = "UPDATE tb_endereco SET nm_rua = ?, nm_cidade = ?, nm_estado = ?, nm_bairro = ?, nr_endereco = ?, nr_cep = ? WHERE cd_endereco = ?";
        String sql3 = "UPDATE tb_contato SET nr_celular = ?, nr_fixo = ?, email = ?, nr_fax = ? WHERE cd_contato = ?";
        

        try {
            PreparedStatement pstmt1 = (PreparedStatement) connection.prepareStatement(sql1);
            pstmt1.setString(1, fornecedor.getNm_fornecedor());
            pstmt1.setString(2, fornecedor.getNr_cnpj());
            pstmt1.setInt(3, fornecedor.getCd_fornecedor());
            pstmt1.execute();
            int cd_fornecedor = fornecedor.getCd_fornecedor();
            

                String sql10 = "SELECT tb_endereco_cd_endereco FROM tb_fornecedor WHERE cd_fornecedor = " + cd_fornecedor;
                PreparedStatement pstmt2 = (PreparedStatement) connection.prepareStatement(sql2);
                PreparedStatement pstmt10 = (PreparedStatement) connection.prepareStatement(sql10);
                ResultSet res10 = pstmt10.executeQuery(sql10);
                while (res10.next()) {
                    int cd_endereco = res10.getInt("tb_endereco_cd_endereco");

                    pstmt2.setString(1, fornecedor.getEndereco().getNm_rua());
                    pstmt2.setString(2, fornecedor.getEndereco().getNm_cidade());
                    pstmt2.setString(3, fornecedor.getEndereco().getNm_estado());
                    pstmt2.setString(4, fornecedor.getEndereco().getNm_bairro());
                    pstmt2.setInt(5, fornecedor.getEndereco().getNr_endereco());
                    pstmt2.setString(6, fornecedor.getEndereco().getNr_cep());
                    pstmt2.setInt(6, cd_endereco);
                    pstmt2.execute();
                }
                String sql11 = "SELECT tb_contato_cd_contato FROM tb_fornecedor WHERE cd_fornecedor = " + cd_fornecedor;
                PreparedStatement pstmt3 = (PreparedStatement) connection.prepareStatement(sql3);
                PreparedStatement pstmt11 = (PreparedStatement) connection.prepareStatement(sql1);
                ResultSet res11 = pstmt11.executeQuery(sql11);
                while (res11.next()) {
                    int cd_contato = res11.getInt("tb_contato_cd_contato");

                    pstmt3.setString(1, fornecedor.getContato().getNr_celular());
                    pstmt3.setString(2, fornecedor.getContato().getNr_fixo());
                    pstmt3.setString(3, fornecedor.getContato().getEmail());
                    pstmt3.setString(4, fornecedor.getContato().getNr_fax());
                    pstmt3.setInt(5, cd_contato);
                    pstmt3.execute();
                }
            

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
