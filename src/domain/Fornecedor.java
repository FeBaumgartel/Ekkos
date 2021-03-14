/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import dataservices.dao.FornecedorDAO;
import java.util.List;

/**
 *
 * @author felipe_baumgartel
 */
public class Fornecedor {

    private int cd_fornecedor;
    private String nm_fornecedor;
    private String nr_cnpj;
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
        final Fornecedor other = (Fornecedor) obj;
        if (this.cd_fornecedor != other.cd_fornecedor) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return cd_fornecedor + "|" + nm_fornecedor;
    }

    public Fornecedor() {
    }

    public Fornecedor(String nm_fornecedor, String nr_cnpj, Contato contato, Endereco endereco) {
        this.nm_fornecedor = nm_fornecedor;
        this.nr_cnpj = nr_cnpj;
        this.contato = contato;
        this.endereco = endereco;
    }

    public int getCd_fornecedor() {
        return cd_fornecedor;
    }

    public void setCd_fornecedor(int cd_fornecedor) {
        this.cd_fornecedor = cd_fornecedor;
    }

    public String getNm_fornecedor() {
        return nm_fornecedor;
    }

    public void setNm_fornecedor(String nm_fornecedor) {
        this.nm_fornecedor = nm_fornecedor;
    }

    public String getNr_cnpj() {
        return nr_cnpj;
    }

    public void setNr_cnpj(String nr_cnpj) {
        this.nr_cnpj = nr_cnpj;
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

    public boolean verificarExistenciaDeFornecedor(Fornecedor a) {
        List<Fornecedor> lista = dao.list();

        for (int i = 0; i < lista.size(); i++) {
            if (a.equals(lista.get(i))) {
                return true;
            }
        }
        return false;
    }
    FornecedorDAO dao = new FornecedorDAO();
}
