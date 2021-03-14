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
public class Pessoas {
    private int cd_pessoas;
    protected String nm_pessoas;
    private String nr_cpf;
    private String nr_rg;
    private Contato contato;
    private Endereco endereco;

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Pessoas other = (Pessoas) obj;
        if (this.cd_pessoas != other.cd_pessoas) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Pessoas{" + "cd_pessoas=" + cd_pessoas + ", nm_pessoas=" + nm_pessoas + ", nr_cpf=" + nr_cpf + ", nr_rg=" + nr_rg + ", contato=" + contato + ", endereco=" + endereco + '}';
    }

    public int getCd_pessoas() {
        return cd_pessoas;
    }

    public void setCd_pessoas(int cd_pessoas) {
        this.cd_pessoas = cd_pessoas;
    }

    public String getNm_pessoas() {
        return nm_pessoas;
    }

    public void setNm_pessoas(String nm_pessoas) {
        this.nm_pessoas = nm_pessoas;
    }

    public String getNr_cpf() {
        return nr_cpf;
    }

    public void setNr_cpf(String nr_cpf) {
        this.nr_cpf = nr_cpf;
    }

    public String getNr_rg() {
        return nr_rg;
    }

    public void setNr_rg(String nr_rg) {
        this.nr_rg = nr_rg;
    }

    public Contato getContato() {
        return contato;
    }

    public void setContato(Contato contato) {
        this.contato = contato;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public Pessoas(String nm_pessoas, String nr_cpf, String nr_rg, Contato contato, Endereco endereco) {
        this.nm_pessoas = nm_pessoas;
        this.nr_cpf = nr_cpf;
        this.nr_rg = nr_rg;
        this.contato = contato;
        this.endereco = endereco;
    }

    public Pessoas() {
    }

}
