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
public class Estoque {

    private int cd_estoque;
    private String Prateleira;

    public int getCd_estoque() {
        return cd_estoque;
    }

    public void setCd_estoque(int cd_estoque) {
        this.cd_estoque = cd_estoque;
    }

    public String getPrateleira() {
        return Prateleira;
    }

    public void setPrateleira(String Prateleira) {
        this.Prateleira = Prateleira;
    }

    

    public Estoque(String Prateleira) {
        this.Prateleira = Prateleira;
    }

    @Override
    public String toString() {
        return "Estoque{" + "cd_estoque=" + cd_estoque + ", Prateleira=" + Prateleira + '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Estoque other = (Estoque) obj;
        if (this.cd_estoque != other.cd_estoque) {
            return false;
        }
        return true;
    }

    public Estoque() {
    }

}
