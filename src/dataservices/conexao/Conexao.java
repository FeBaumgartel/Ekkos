/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataservices.conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author felipe_baumgartel
 */
public class Conexao {
    
    private Connection conexao;

    private final String URL = "jdbc:mysql://localhost:3306/ekkos";

    private final String USER = "root";

    private final String PASSWORD = "";

    private final String TPCONEXAO = "com.mysql.jdbc.Driver";

    public Connection abrirConexao() {
        try {

            Class.forName(TPCONEXAO);
           conexao  = (Connection) DriverManager.getConnection(URL, USER, PASSWORD);
            }catch (ClassNotFoundException | SQLException ex){
              ex.printStackTrace();
            }
            return conexao;

        }
      
   
    public void fecharConexao() {
        if (conexao != null) {
            try {
                conexao.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
