/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import dataservices.dao.CategoriaDAO;
import java.util.List;

/**
 *
 * @author felipe_baumgartel
 */
public class Categoria {

    private int cd_categoria;
    private String ds_categoria;

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Categoria other = (Categoria) obj;
        if (this.cd_categoria != other.cd_categoria) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return cd_categoria + "|" + ds_categoria;
    }

    public Categoria() {
    }

    public Categoria(String ds_categoria) {
        this.ds_categoria = ds_categoria;
    }

    

    public int getCd_categoria() {
        return cd_categoria;
    }

    public void setCd_categoria(int cd_categoria) {
        this.cd_categoria = cd_categoria;
    }

    public String getDs_categoria() {
        return ds_categoria;
    }

    public void setDs_categoria(String ds_categoria) {
        this.ds_categoria = ds_categoria;
    }

    public boolean verificarExistenciaDeCategoria(Categoria a) {
        List<Categoria> lista = dao.list();

        for (int i = 0; i < lista.size(); i++) {
            if (a.equals(lista.get(i))) {
                return true;
            }
        }
        return false;
    }
    CategoriaDAO dao = new CategoriaDAO();

}
