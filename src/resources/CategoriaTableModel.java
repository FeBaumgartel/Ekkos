/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package resources;

import domain.Categoria;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author felipe_baumgartel
 */
public class CategoriaTableModel extends GenericTableModel<Categoria> {
    private final String[] colunas = {"Código", "Descrição"};

    private List<Categoria> list;

    private CategoriaTableModel() {
        list = new ArrayList<>();
    }

    public CategoriaTableModel(List<Categoria> list) {
        this();
        setData(list);
    }

    @Override
    public void add(Categoria entity) {
        list.add(0, entity);
        super.fireTableDataChanged();
    }

    @Override
    public void clear() {
        this.list.clear();
        super.fireTableDataChanged();
    }

    @Override
    public boolean contains(Categoria entity) {
        return list.contains(entity);
    }

    @Override
    public Categoria getValueAT(int row) {
        return list.get(row);
    }

    @Override
    public int indexOf(Categoria entity) {
        return list.indexOf(entity);
    }

    @Override
    public List<Categoria> list() {
        return list;
    }

    @Override
    public void remove(Categoria entity) {
        list.remove(entity);
        super.fireTableDataChanged();
    }

    @Override
    public void setData(List<Categoria> list) {
        this.list.clear();
        this.list.addAll(list);
        super.fireTableDataChanged();
    }

    @Override
    public void updateItem(int idx, Categoria entity) {
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
        Categoria categoria = list.get(linha);
        switch (coluna) {
            case 0:
                return categoria.getCd_categoria();
            case 1:
                return categoria.getDs_categoria();
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
