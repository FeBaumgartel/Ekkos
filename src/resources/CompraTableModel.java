/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package resources;

import domain.Compra;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author felipe_baumgartel
 */
public class CompraTableModel extends GenericTableModel<Compra> {

    private final String[] colunas = {"Código", "Valor", "Data", "Funcionário",
        "Fornecedor"};

    private List<Compra> list;

    private CompraTableModel() {
        list = new ArrayList<>();
    }

    public CompraTableModel(List<Compra> list) {
        this();
        setData(list);
    }

    @Override
    public void add(Compra entity) {
        list.add(0, entity);
        super.fireTableDataChanged();
    }

    @Override
    public void clear() {
        this.list.clear();
        super.fireTableDataChanged();
    }

    @Override
    public boolean contains(Compra entity) {
        return list.contains(entity);
    }

    @Override
    public Compra getValueAT(int row) {
        return list.get(row);
    }

    @Override
    public int indexOf(Compra entity) {
        return list.indexOf(entity);
    }

    @Override
    public List<Compra> list() {
        return list;
    }

    @Override
    public void remove(Compra entity) {
        list.remove(entity);
        super.fireTableDataChanged();
    }

    @Override
    public void setData(List<Compra> list) {
        this.list.clear();
        this.list.addAll(list);
        super.fireTableDataChanged();
    }

    @Override
    public void updateItem(int idx, Compra entity) {
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
        Compra compra = list.get(linha);
        switch (coluna) {
            case 0:
                return compra.getCd_compra();
            case 1:
                return compra.getVl_compra();
            case 2:
                return compra.getDt_compra();
            case 3:
                return compra.getFuncionario().getNm_pessoas();
            case 4:
                return compra.getFornecedor().getNm_fornecedor();

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
