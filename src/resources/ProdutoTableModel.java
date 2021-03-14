/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package resources;

import domain.Produto;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author felipe_baumgartel
 */
public class ProdutoTableModel extends GenericTableModel<Produto> {

    private final String[] colunas = {"Código", "Nome", "Valor", "Quantidade", "Categoria", "Fornecedor", "Posição em Prateleira"};

    private List<Produto> list;

    private ProdutoTableModel() {
        list = new ArrayList<>();
    }

    public ProdutoTableModel(List<Produto> list) {
        this();
        setData(list);
    }

    @Override
    public void add(Produto entity) {
        list.add(0, entity);
        super.fireTableDataChanged();
    }

    @Override
    public void clear() {
        this.list.clear();
        super.fireTableDataChanged();
    }

    @Override
    public boolean contains(Produto entity) {
        return list.contains(entity);
    }

    @Override
    public Produto getValueAT(int row) {
        return list.get(row);
    }

    @Override
    public int indexOf(Produto entity) {
        return list.indexOf(entity);
    }

    @Override
    public List<Produto> list() {
        return list;
    }

    @Override
    public void remove(Produto entity) {
        list.remove(entity);
        super.fireTableDataChanged();
    }

    @Override
    public void setData(List<Produto> list) {
        this.list.clear();
        this.list.addAll(list);
        super.fireTableDataChanged();
    }

    @Override
    public void updateItem(int idx, Produto entity) {
        list.set(idx, entity);
        super.fireTableDataChanged();
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    @Override
    public int getRowCount() {
        return list.size();
    }

    @Override
    public Object getValueAt(int linha, int coluna) {
        Produto produto = list.get(linha);
        switch (coluna) {
            case 0:
                return produto.getCd_produto();
            case 1:
                return produto.getNm_produto();
            case 2:
                return produto.getVl_preco();
            case 3:
                return produto.getVl_quantidade();
            case 4:
                return produto.getCategoria().getDs_categoria();
            case 5:
                return produto.getFornecedor().getNm_fornecedor();
            case 6:
                return produto.getEstoque().getPrateleira();

        }
        return null;
    }

    @Override
    public String getColumnName(int col) {
        return colunas[col];
    }

    public String[] getColumnNames() {
        return colunas;
    }
}
