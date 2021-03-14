/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package resources;

import domain.Funcionario;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author felipe_baumgartel
 */
public class FuncionarioTableModel extends GenericTableModel<Funcionario> {
    private final String[] colunas = {"Código", "Nome", "Telefone", "CPF",
        "Rua", "Nº Endereço", "Bairro"};

    private List<Funcionario> list;

    private FuncionarioTableModel() {
        list = new ArrayList<>();
    }

    public FuncionarioTableModel(List<Funcionario> list) {
        this();
        setData(list);
    }

    @Override
    public void add(Funcionario entity) {
        list.add(0, entity);
        super.fireTableDataChanged();
    }

    @Override
    public void clear() {
        this.list.clear();
        super.fireTableDataChanged();
    }

    @Override
    public boolean contains(Funcionario entity) {
        return list.contains(entity);
    }

    @Override
    public Funcionario getValueAT(int row) {
        return list.get(row);
    }

    @Override
    public int indexOf(Funcionario entity) {
        return list.indexOf(entity);
    }

    @Override
    public List<Funcionario> list() {
        return list;
    }

    @Override
    public void remove(Funcionario entity) {
        list.remove(entity);
        super.fireTableDataChanged();
    }

    @Override
    public void setData(List<Funcionario> list) {
        this.list.clear();
        this.list.addAll(list);
        super.fireTableDataChanged();
    }

    @Override
    public void updateItem(int idx, Funcionario entity) {
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
        Funcionario funcionario = list.get(linha);
        switch (coluna) {
            case 0:
                return funcionario.getCd_funcionario();
            case 1:
                return funcionario.getNm_pessoas();
            case 2:
                return funcionario.getContato().getNr_fixo();
            case 3:
                return funcionario.getNr_cpf();
            case 4:
                return funcionario.getEndereco().getNm_rua();
            case 5:
                return funcionario.getEndereco().getNr_endereco();
            case 6:
                return funcionario.getEndereco().getNm_bairro();

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
