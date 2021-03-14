/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import dataservices.dao.VendaDAO;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author felipe_baumgartel
 */
public class Venda {

    private int cd_venda;
    private double vl_venda;
    private LocalDate dt_venda;
    private Cliente cliente;
    private Funcionario funcionario;

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Venda other = (Venda) obj;
        if (this.cd_venda != other.cd_venda) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Venda{" + "cd_venda=" + cd_venda + ", vl_venda=" + vl_venda + ", dt_venda=" + dt_venda + ", cliente=" + cliente + ", funcionario=" + funcionario + '}';
    }

    

    public int getCd_venda() {
        return cd_venda;
    }

    public void setCd_venda(int cd_venda) {
        this.cd_venda = cd_venda;
    }

    public double getVl_venda() {
        return vl_venda;
    }

    public void setVl_venda(double vl_venda) {
        this.vl_venda = vl_venda;
    }

    public LocalDate getDt_venda() {
        return dt_venda;
    }

    public void setDt_venda(LocalDate dt_venda) {
        this.dt_venda = dt_venda;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public Venda(int cd_venda, double vl_venda, LocalDate dt_venda, Cliente cliente, Funcionario funcionario) {
        this.cd_venda = cd_venda;
        this.vl_venda = vl_venda;
        this.dt_venda = dt_venda;
        this.cliente = cliente;
        this.funcionario = funcionario;
    }    

    public Venda() {
    }

    public boolean verificarExistenciaDeVenda(Venda a) {
        List<Venda> lista = dao.list();

        for (int i = 0; i < lista.size(); i++) {
            if (a.equals(lista.get(i))) {
                return true;
            }
        }
        return false;
    }
    VendaDAO dao = new VendaDAO();
}
