/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package classesJava;

import jakarta.annotation.ManagedBean;
import jakarta.inject.Named;
import jakarta.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author bartolomeu
 */
@ManagedBean
@Named(value = "carrosBean")
@SessionScoped
public class CarrosBean implements Serializable {

    private Carro objCarro = new Carro();
    private List<Carro> listaCarro = new ArrayList<>();
    private CarrosDAO objCarroDAO = new CarrosDAO();

    /**
     * Creates a new instance of CarrosBean
     */
    public CarrosBean() {
    }

    public void listar() {
        listaCarro = objCarroDAO.listar();
    }

    public void adicionar() {
        objCarroDAO.salvar(objCarro);
        objCarro = new Carro();
        listar();
    }

    public void editar() {
        objCarroDAO.editar(objCarro);
        objCarro = new Carro();
        listar();
    }

    public void excluir() {
        objCarroDAO.excluir(objCarro.getId());
        objCarro = new Carro();
        listar();
    }

    public void limpar() {
        objCarro = new Carro();
    }

    public void selecionar(Carro c) {
        objCarro = c;
    }

    public Carro getObjCarro() {
        return objCarro;
    }

    public void setObjCarro(Carro objCarro) {
        this.objCarro = objCarro;
    }

    public List<Carro> getListaCarro() {
        return listaCarro;
    }

    public void setListaCarro(List<Carro> listaCarro) {
        this.listaCarro = listaCarro;
    }

    public CarrosDAO getObjCarroDAO() {
        return objCarroDAO;
    }

    public void setObjCarroDAO(CarrosDAO objCarroDAO) {
        this.objCarroDAO = objCarroDAO;
    }

    
}
