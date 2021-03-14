/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

/**
 *
 * @author Felipe
 */
public class tb_produto_tb_venda {
    private Produto produto;
    private Venda venda;
    private int quantidade;

    public tb_produto_tb_venda(Produto produto, Venda venda, int quantidade) {
        this.produto = produto;
        this.venda = venda;
        this.quantidade = quantidade;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Venda getVenda() {
        return venda;
    }

    public void setVenda(Venda venda) {
        this.venda = venda;
    }

    public tb_produto_tb_venda() {
    }

    @Override
    public String toString() {
        return "tb_produto_tb_venda{" + "produto=" + produto + ", venda=" + venda + ", quantidade=" + quantidade + '}';
    }
    
}
