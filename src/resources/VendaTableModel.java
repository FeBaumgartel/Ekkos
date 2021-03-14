/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package resources;

import domain.Venda;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author felipe_baumgartel
 */
public class VendaTableModel extends GenericTableModel<Venda> {
    private final String[] colunas = {"Código", "Valor", "Data", "Funcionário",
        "Cliente"};

    private List<Venda> list;

    private VendaTableModel() {
        list = new ArrayList<>();
    }

    public VendaTableModel(List<Venda> list) {
        this();
        setData(list);
    }

    @Override
    public void add(Venda entity) {
        list.add(0, entity);
        super.fireTableDataChanged();
    }

    @Override
    public void clear() {
        this.list.clear();
        super.fireTableDataChanged();
    }

    @Override
    public boolean contains(Venda entity) {
        return list.contains(entity);
    }

    @Override
    public Venda getValueAT(int row) {
        return list.get(row);
    }

    @Override
    public int indexOf(Venda entity) {
        return list.indexOf(entity);
    }

    @Override
    public List<Venda> list() {
        return list;
    }

    @Override
    public void remove(Venda entity) {
        list.remove(entity);
        super.fireTableDataChanged();
    }

    @Override
    public void setData(List<Venda> list) {
        this.list.clear();
        this.list.addAll(list);
        super.fireTableDataChanged();
    }

    @Override
    public void updateItem(int idx, Venda entity) {
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
        Venda venda = list.get(linha);
        switch (coluna) {
            case 0:
                return venda.getCd_venda();
            case 1:
                return venda.getVl_venda();
            case 2:
                return venda.getDt_venda();
            case 3:
                return venda.getFuncionario().getNm_pessoas();
            case 4:
                return venda.getCliente().getNm_pessoas();

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
