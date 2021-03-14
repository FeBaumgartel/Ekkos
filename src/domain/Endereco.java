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
public class Endereco {

    private int cd_endereco;
    private String nm_rua;
    private String nm_cidade;
    private String nm_estado;
    private String nm_bairro;
    private int nr_endereco;
    private String nr_cep;

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Endereco other = (Endereco) obj;
        if (this.cd_endereco != other.cd_endereco) {
            return false;
        }
        return true;
    }

    public int getCd_endereco() {
        return cd_endereco;
    }

    public void setCd_endereco(int cd_endereco) {
        this.cd_endereco = cd_endereco;
    }

    public String getNm_rua() {
        return nm_rua;
    }

    public void setNm_rua(String nm_rua) {
        this.nm_rua = nm_rua;
    }

    public String getNm_cidade() {
        return nm_cidade;
    }

    public void setNm_cidade(String nm_cidade) {
        this.nm_cidade = nm_cidade;
    }

    public String getNm_estado() {
        return nm_estado;
    }

    public void setNm_estado(String nm_estado) {
        this.nm_estado = nm_estado;
    }

    public String getNm_bairro() {
        return nm_bairro;
    }

    public void setNm_bairro(String nm_bairro) {
        this.nm_bairro = nm_bairro;
    }

    public int getNr_endereco() {
        return nr_endereco;
    }

    public void setNr_endereco(int nr_endereco) {
        this.nr_endereco = nr_endereco;
    }

    public String getNr_cep() {
        return nr_cep;
    }

    public void setNr_cep(String nr_cep) {
        this.nr_cep = nr_cep;
    }

    public Endereco(String nm_rua, String nm_cidade, String nm_estado, String nm_bairro, int nr_endereco, String nr_cep) {
        this.nm_rua = nm_rua;
        this.nm_cidade = nm_cidade;
        this.nm_estado = nm_estado;
        this.nm_bairro = nm_bairro;
        this.nr_endereco = nr_endereco;
        this.nr_cep = nr_cep;
    }

    public Endereco() {
    }

    @Override
    public String toString() {
        return "Endereco{" + "cd_endereco=" + cd_endereco + ", nm_rua=" + nm_rua + ", nm_cidade=" + nm_cidade + ", nm_estado=" + nm_estado + ", nm_bairro=" + nm_bairro + ", nr_endereco=" + nr_endereco + ", nr_cep=" + nr_cep + '}';
    }

}
