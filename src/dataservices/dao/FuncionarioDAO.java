/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataservices.dao;

import dataservices.conexao.Conexao;
import domain.Contato;
import domain.Endereco;
import domain.Funcionario;
import domain.Pessoas;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author felipe_baumgartel
 */
public class FuncionarioDAO {

    private Conexao conexao;
    private Connection connection;

    public FuncionarioDAO() {
        conexao = new Conexao();
        connection = conexao.abrirConexao();
    }

    public void save(Funcionario funcionario) {
        int id = 0;

        String sql = "INSERT INTO tb_pessoas(nm_pessoas, nr_cpf, nr_rg, TB_Contato_Cd_Contato, TB_Endereco_Cd_Endereco) VALUES (?,?,?,?,?)";
        String sql2 = "INSERT INTO tb_endereco(nm_rua, nm_cidade, nm_estado, nm_bairro, nr_endereco, nr_cep) VALUES (?,?,?,?,?,?)";
        String sql3 = "INSERT INTO tb_contato(nr_celular, nr_fixo, email, nr_fax) VALUES (?,?,?,?)";
        String sql4 = "INSERT INTO tb_funcionario(vl_salario, ds_cargo, tb_pessoas_cd_pessoas) VALUES (?,?,?)";

        //Como possui auto increment necessita destes selects
        String sql5 = "SELECT MAX(cd_endereco) FROM tb_endereco";
        String sql6 = "SELECT MAX(cd_contato) FROM tb_contato";
        String sql7 = "SELECT MAX(cd_pessoas) FROM tb_pessoas";

        try {

            PreparedStatement pstmt = (PreparedStatement) connection.prepareStatement(sql);
            PreparedStatement pstmt2 = (PreparedStatement) connection.prepareStatement(sql2);
            PreparedStatement pstmt3 = (PreparedStatement) connection.prepareStatement(sql3);
            PreparedStatement pstmt4 = (PreparedStatement) connection.prepareStatement(sql4);
            PreparedStatement pstmt5 = (PreparedStatement) connection.prepareStatement(sql5);
            PreparedStatement pstmt6 = (PreparedStatement) connection.prepareStatement(sql6);
            PreparedStatement pstmt7 = (PreparedStatement) connection.prepareStatement(sql7);

            pstmt2.setString(1, funcionario.getEndereco().getNm_rua());
            pstmt2.setString(2, funcionario.getEndereco().getNm_cidade());
            pstmt2.setString(3, funcionario.getEndereco().getNm_estado());
            pstmt2.setString(4, funcionario.getEndereco().getNm_bairro());
            pstmt2.setInt(5, funcionario.getEndereco().getNr_endereco());
            pstmt2.setString(6, funcionario.getEndereco().getNr_cep());
            pstmt2.execute();

            pstmt3.setString(1, funcionario.getContato().getNr_celular());
            pstmt3.setString(2, funcionario.getContato().getNr_fixo());
            pstmt3.setString(3, funcionario.getContato().getEmail());
            pstmt3.setString(4, funcionario.getContato().getNr_fax());
            pstmt3.execute();

            pstmt.setString(1, funcionario.getNm_pessoas());
            pstmt.setString(2, funcionario.getNr_cpf());
            pstmt.setString(3, funcionario.getNr_rg());

            ResultSet res5 = pstmt5.executeQuery(sql5);
            while (res5.next()) {
                id = res5.getInt("MAX(cd_endereco)");
            }
            pstmt.setInt(5, id);

            ResultSet res6 = pstmt6.executeQuery(sql6);
            while (res6.next()) {
                id = res6.getInt("MAX(cd_contato)");
            }
            pstmt.setInt(4, id);
            pstmt.execute();

            ResultSet res7 = pstmt7.executeQuery(sql7);
            while (res7.next()) {
                id = res7.getInt("MAX(cd_pessoas)");
                
            }
            pstmt4.setDouble(1, funcionario.getVl_salario());
            pstmt4.setString(2, funcionario.getDs_cargo());
            pstmt4.setInt(3, id);
            pstmt4.execute();
            

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public Pessoas getById(long id) {
        String sql = "SELECT * FROM tb_funcionario WHERE cd_funcionario = " + id;

        Funcionario funcionario = new Funcionario();
        Endereco endereco = new Endereco();
        Contato contato = new Contato();
        Pessoas pessoas = new Pessoas();

        try {
            PreparedStatement pstmt = (PreparedStatement) connection.prepareStatement(sql);
            ResultSet res = pstmt.executeQuery(sql);

            while (res.next()) {

                funcionario.setCd_funcionario(res.getInt("cd_funcionario"));
                int idPessoa = res.getInt("TB_Pessoas_Cd_Pessoas");
                funcionario.setVl_salario(res.getDouble("vl_salario"));
                funcionario.setDs_cargo(res.getString("ds_cargo"));

                String sql4 = "SELECT * FROM TB_pessoas WHERE cd_pessoas = " + idPessoa + " ORDER BY cd_pessoas ASC";
                PreparedStatement pstmt4 = (PreparedStatement) connection.prepareStatement(sql4);
                ResultSet res4 = pstmt4.executeQuery(sql4);

                while (res4.next()) {
                    funcionario.setNm_pessoas(res4.getString("nm_pessoas"));
                    funcionario.setNr_cpf(res4.getString("nr_cpf"));
                    funcionario.setNr_rg(res4.getString("nr_rg"));
                    int idEnd = res4.getInt("TB_Endereco_Cd_Endereco");
                    int idCont = res4.getInt("TB_Contato_Cd_Contato");

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
                    funcionario.setEndereco(endereco);

                    String sql3 = "SELECT * FROM TB_contato WHERE cd_contato = " + idCont + " ORDER BY  ASC";
                    PreparedStatement pstmt3 = (PreparedStatement) connection.prepareStatement(sql3);
                    ResultSet res3 = pstmt3.executeQuery(sql3);

                    while (res2.next()) {
                        contato.setCd_contato(res3.getInt("cd_contato"));
                        contato.setNr_celular(res3.getString("nr_celular"));
                        contato.setEmail(res3.getString("email"));
                        contato.setNr_fax(res3.getString("nr_fax"));
                        contato.setNr_fixo(res3.getString("nr_fixo"));
                    }
                    funcionario.setContato(contato);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return funcionario;
    }

    public List<Funcionario> list() {
        String sql = "SELECT * FROM tb_funcionario ORDER BY cd_funcionario ASC";

        List<Funcionario> lista = new ArrayList<>();

        try {
            PreparedStatement pstmt = (PreparedStatement) connection.prepareStatement(sql);
            ResultSet res = pstmt.executeQuery(sql);

            while (res.next()) {
                Funcionario funcionario = new Funcionario();
                Endereco endereco = new Endereco();
                Contato contato = new Contato();

                funcionario.setCd_funcionario(res.getInt("cd_funcionario"));
                int idPess = res.getInt("tb_pessoas_cd_pessoas");
                funcionario.setVl_salario(res.getDouble("vl_salario"));
                funcionario.setDs_cargo(res.getString("ds_cargo"));

                String sql2 = "SELECT * FROM TB_pessoas WHERE cd_pessoas = " + idPess;
                PreparedStatement pstmt2 = (PreparedStatement) connection.prepareStatement(sql2);
                ResultSet res2 = pstmt2.executeQuery(sql2);

                while (res2.next()) {
                    funcionario.setNm_pessoas(res2.getString("nm_pessoas"));
                    funcionario.setNr_cpf(res2.getString("nr_cpf"));
                    funcionario.setNr_rg(res2.getString("nr_rg"));
                    int idEnd = res2.getInt("TB_Endereco_Cd_Endereco");
                    int idCont = res2.getInt("TB_Contato_Cd_Contato");

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
                    funcionario.setEndereco(endereco);

                    String sql4 = "SELECT * FROM TB_contato WHERE cd_contato = " + idCont;
                    PreparedStatement pstmt4 = (PreparedStatement) connection.prepareStatement(sql4);
                    ResultSet res4 = pstmt4.executeQuery(sql4);

                    while (res4.next()) {
                        contato.setNr_celular(res4.getString("nr_celular"));
                        contato.setEmail(res4.getString("email"));
                        contato.setNr_fax(res4.getString("nr_fax"));
                        contato.setNr_fixo(res4.getString("nr_fixo"));
                    }
                    funcionario.setContato(contato);
                }

                lista.add(funcionario);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return lista;
    }

    public void update(Funcionario funcionario) {
        String sql = "UPDATE tb_pessoas SET nm_pessoas = ?, nr_cpf = ?, nr_rg = ? WHERE cd_pessoas = ?";
        String sql2 = "UPDATE tb_endereco SET nm_rua = ?, nm_cidade = ?, nm_estado = ?, nm_bairro = ?, nr_endereco = ?, nr_cep = ? WHERE cd_endereco = ?";
        String sql3 = "UPDATE tb_contato SET nr_celular = ?, nr_fixo = ?, email = ?, nr_fax = ? WHERE cd_contato = ?";
        String sql4 = "UPDATE tb_funcionario SET vl_salario = ?, ds_cargo = ? WHERE cd_funcionario = ?";

        try {
            PreparedStatement pstmt4 = (PreparedStatement) connection.prepareStatement(sql4);
            pstmt4.setDouble(1, funcionario.getVl_salario());
            pstmt4.setString(2, funcionario.getDs_cargo());
            pstmt4.setInt(3, funcionario.getCd_funcionario());
            pstmt4.execute();

            String sql10 = "SELECT tb_pessoas_cd_pessoas FROM tb_funcionario";
            PreparedStatement pstmt1 = (PreparedStatement) connection.prepareStatement(sql);
            PreparedStatement pstmt10 = (PreparedStatement) connection.prepareStatement(sql10);
            ResultSet res10 = pstmt10.executeQuery(sql10);
            while (res10.next()) {
                int cd_pessoas = res10.getInt("tb_pessoas_cd_pessoas");

                pstmt1.setString(1, funcionario.getNm_pessoas());
                pstmt1.setString(2, funcionario.getNr_cpf());
                pstmt1.setString(3, funcionario.getNr_rg());
                pstmt1.setInt(4, cd_pessoas);
                pstmt1.execute();

                String sql11 = "SELECT tb_endereco_cd_endereco FROM tb_pessoas WHERE cd_pessoas = " + cd_pessoas;
                PreparedStatement pstmt2 = (PreparedStatement) connection.prepareStatement(sql2);
                PreparedStatement pstmt11 = (PreparedStatement) connection.prepareStatement(sql11);
                ResultSet res11 = pstmt11.executeQuery(sql11);
                while (res11.next()) {
                    int cd_endereco = res11.getInt("tb_endereco_cd_endereco");

                    pstmt2.setString(1, funcionario.getEndereco().getNm_rua());
                    pstmt2.setString(2, funcionario.getEndereco().getNm_cidade());
                    pstmt2.setString(3, funcionario.getEndereco().getNm_estado());
                    pstmt2.setString(4, funcionario.getEndereco().getNm_bairro());
                    pstmt2.setInt(5, funcionario.getEndereco().getNr_endereco());
                    pstmt2.setString(6, funcionario.getEndereco().getNr_cep());
                    pstmt2.setInt(6, cd_endereco);
                    pstmt2.execute();
                }
                String sql12 = "SELECT tb_contato_cd_contato FROM tb_contato";
                PreparedStatement pstmt3 = (PreparedStatement) connection.prepareStatement(sql3);
                PreparedStatement pstmt12 = (PreparedStatement) connection.prepareStatement(sql2);
                ResultSet res12 = pstmt12.executeQuery(sql12);
                while (res12.next()) {
                    int cd_contato = res12.getInt("tb_contato_cd_contato");

                    pstmt3.setString(1, funcionario.getContato().getNr_celular());
                    pstmt3.setString(2, funcionario.getContato().getNr_fixo());
                    pstmt3.setString(3, funcionario.getContato().getEmail());
                    pstmt3.setString(4, funcionario.getContato().getNr_fax());
                    pstmt3.setInt(5, cd_contato);
                    pstmt3.execute();
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
