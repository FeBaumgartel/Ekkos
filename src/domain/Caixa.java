/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

/**
 *
 * @author felipe_baumgartel
 */
public class Caixa {
    private int cd_controle_de_caixa;
    private double vl_saldo;

    public Caixa(int cd_controle_de_caixa, double vl_saldo) {
        this.cd_controle_de_caixa = cd_controle_de_caixa;
        this.vl_saldo = vl_saldo;
    }

    public Caixa() {
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Caixa other = (Caixa) obj;
        if (this.cd_controle_de_caixa != other.cd_controle_de_caixa) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Caixa{" + "cd_controle_de_caixa=" + cd_controle_de_caixa + ", vl_saldo=" + vl_saldo + '}';
    }

    public int getCd_controle_de_caixa() {
        return cd_controle_de_caixa;
    }

    public void setCd_controle_de_caixa(int cd_controle_de_caixa) {
        this.cd_controle_de_caixa = cd_controle_de_caixa;
    }

    public double getVl_saldo() {
        return vl_saldo;
    }

    public void setVl_saldo(double vl_saldo) {
        this.vl_saldo = vl_saldo;
    }
}
