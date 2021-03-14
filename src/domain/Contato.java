/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.util.List;

/**
 *
 * @author felipe_baumgartel
 */
public class Contato {

    private int cd_contato;
    private String nr_celular;
    private String nr_fixo;
    private String email;
    private String nr_fax;

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Contato other = (Contato) obj;
        if (this.cd_contato != other.cd_contato) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Contato{" + "cd_contato=" + cd_contato + ", nr_celular=" + nr_celular + ", nr_fixo=" + nr_fixo + ", email=" + email + ", nr_fax=" + nr_fax + '}';
    }

    public Contato(String nr_celular, String nr_fixo, String email, String nr_fax) {
        this.nr_celular = nr_celular;
        this.nr_fixo = nr_fixo;
        this.email = email;
        this.nr_fax = nr_fax;
    }

    public Contato() {
    }

    public int getCd_contato() {
        return cd_contato;
    }

    public void setCd_contato(int cd_contato) {
        this.cd_contato = cd_contato;
    }

    public String getNr_celular() {
        return nr_celular;
    }

    public void setNr_celular(String nr_celular) {
        this.nr_celular = nr_celular;
    }

    public String getNr_fixo() {
        return nr_fixo;
    }

    public void setNr_fixo(String nr_fixo) {
        this.nr_fixo = nr_fixo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNr_fax() {
        return nr_fax;
    }

    public void setNr_fax(String nr_fax) {
        this.nr_fax = nr_fax;
    }

}
