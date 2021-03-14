/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import dataservices.dao.ClienteDAO;
import java.util.List;

/**
 *
 * @author felipe_baumgartel
 */
public class Cliente extends Pessoas {

    private int cd_cliente;

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Cliente other = (Cliente) obj;
        if (this.cd_cliente != other.cd_cliente) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return cd_cliente + '|' + nm_pessoas;
    }

    public Cliente() {
    }

    public Cliente(int cd_cliente) {
        this.cd_cliente = cd_cliente;
    }

    public Cliente(String nm_pessoas, String nr_cpf, String nr_rg, Contato contato, Endereco endereco) {
        super(nm_pessoas, nr_cpf, nr_rg, contato, endereco);
    }

    

    public int getCd_cliente() {
        return cd_cliente;
    }

    public void setCd_cliente(int cd_cliente) {
        this.cd_cliente = cd_cliente;
    }

    public boolean verificarExistenciaDeCliente(Cliente a) {
        List<Cliente> lista = dao.list();

        for (int i = 0; i < lista.size(); i++) {
            if (a.equals(lista.get(i))) {
                return true;
            }
        }
        return false;
    }
    ClienteDAO dao = new ClienteDAO();

}
