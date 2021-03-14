/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import dataservices.dao.ProdutoDAO;
import java.util.List;

/**
 *
 * @author felipe_baumgartel
 */
public class Produto {

    private int cd_produto;
    private String nm_produto;
    private String ds_produto;
    private double vl_preco;
    private int vl_quantidade;
    private Categoria categoria;
    private Fornecedor fornecedor;
    private Estoque estoque;

    public Produto() {
    }

    public Produto(String nm_produto, String ds_produto, double vl_preco, int vl_quantidade, Categoria categoria, Fornecedor fornecedor, Estoque estoque) {
        this.nm_produto = nm_produto;
        this.ds_produto = ds_produto;
        this.vl_preco = vl_preco;
        this.vl_quantidade = vl_quantidade;
        this.categoria = categoria;
        this.fornecedor = fornecedor;
        this.estoque = estoque;
    }

    public int getCd_produto() {
        return cd_produto;
    }

    public void setCd_produto(int cd_produto) {
        this.cd_produto = cd_produto;
    }

    public String getNm_produto() {
        return nm_produto;
    }

    public void setNm_produto(String nm_produto) {
        this.nm_produto = nm_produto;
    }

    public String getDs_produto() {
        return ds_produto;
    }

    public void setDs_produto(String ds_produto) {
        this.ds_produto = ds_produto;
    }

    public double getVl_preco() {
        return vl_preco;
    }

    public void setVl_preco(double vl_preco) {
        this.vl_preco = vl_preco;
    }

    public int getVl_quantidade() {
        return vl_quantidade;
    }

    public void setVl_quantidade(int vl_quantidade) {
        this.vl_quantidade = vl_quantidade;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }

    public Estoque getEstoque() {
        return estoque;
    }

    public void setEstoque(Estoque estoque) {
        this.estoque = estoque;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        return hash;
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
        final Produto other = (Produto) obj;
        if (this.cd_produto != other.cd_produto) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return cd_produto + "|" + nm_produto;
    }

    public boolean verificarExistenciaDeProduto(Produto a) {
        List<Produto> lista = dao.list();

        for (int i = 0; i < lista.size(); i++) {
            if (a.equals(lista.get(i))) {
                return true;
            }
        }
        return false;
    }
    ProdutoDAO dao = new ProdutoDAO();
}
