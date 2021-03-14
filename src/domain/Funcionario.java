/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import dataservices.dao.FuncionarioDAO;
import java.util.List;

/**
 *
 * @author felipe_baumgartel
 */
public class Funcionario extends Pessoas{
    private int cd_funcionario;
    private double vl_salario;
    private String ds_cargo;

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Funcionario other = (Funcionario) obj;
        if (this.cd_funcionario != other.cd_funcionario) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return cd_funcionario + "|" + nm_pessoas;
    }

    public Funcionario(double vl_salario, String ds_cargo, String nm_pessoas, String nr_cpf, String nr_rg, Contato contato, Endereco endereco) {
        super(nm_pessoas, nr_cpf, nr_rg, contato, endereco);
        this.vl_salario = vl_salario;
        this.ds_cargo = ds_cargo;
    }

    

    public Funcionario() {
    }
    
    public int getCd_funcionario() {
        return cd_funcionario;
    }

    public void setCd_funcionario(int cd_funcionario) {
        this.cd_funcionario = cd_funcionario;
    }

    public double getVl_salario() {
        return vl_salario;
    }

    public void setVl_salario(double vl_salario) {
        this.vl_salario = vl_salario;
    }

    public String getDs_cargo() {
        return ds_cargo;
    }

    public void setDs_cargo(String ds_cargo) {
        this.ds_cargo = ds_cargo;
    }

    public boolean verificarExistenciaDeFuncionario(Funcionario a) {
        List<Funcionario> lista = dao.list();

        for (int i = 0; i < lista.size(); i++) {
            if (a.equals(lista.get(i))) {
                return true;
            }
        }
        return false;
    }
    FuncionarioDAO dao = new FuncionarioDAO();
    
}
