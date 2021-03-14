 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import dataservices.dao.CompraDAO;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author felipe_baumgartel
 */
public class Compra {

    private int cd_compra;
    private double vl_compra;
    private LocalDate dt_compra;
    private Funcionario funcionario;
    private Fornecedor fornecedor;

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Compra other = (Compra) obj;
        if (this.cd_compra != other.cd_compra) {
            return false;
        }
        return true;
    }

    public Compra(int cd_compra, double vl_compra, LocalDate dt_compra, Funcionario funcionario, Fornecedor fornecedor) {
        this.cd_compra = cd_compra;
        this.vl_compra = vl_compra;
        this.dt_compra = dt_compra;
        this.funcionario = funcionario;
        this.fornecedor = fornecedor;
    }

    

    public int getCd_compra() {
        return cd_compra;
    }

    public void setCd_compra(int cd_compra) {
        this.cd_compra = cd_compra;
    }

    public double getVl_compra() {
        return vl_compra;
    }

    public void setVl_compra(double vl_compra) {
        this.vl_compra = vl_compra;
    }

    public LocalDate getDt_compra() {
        return dt_compra;
    }

    public void setDt_compra(LocalDate dt_compra) {
        this.dt_compra = dt_compra;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }

    @Override
    public String toString() {
        return "Compra{" + "cd_compra=" + cd_compra + ", vl_compra=" + vl_compra + ", dt_compra=" + dt_compra + ", funcionario=" + funcionario + ", fornecedor=" + fornecedor + '}';
    }


    public Compra() {
    }

    public boolean verificarExistenciaDeCompra(Compra a) {
        List<Compra> lista = dao.list();

        for (int i = 0; i < lista.size(); i++) {
            if (a.equals(lista.get(i))) {
                return true;
            }
        }
        return false;
    }
    CompraDAO dao = new CompraDAO();
}

