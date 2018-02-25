/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.pdsw.samples.managedbeans;

import edu.eci.pdsw.samples.entities.Cliente;
import edu.eci.pdsw.samples.entities.Item;
import edu.eci.pdsw.samples.entities.ItemRentado;
import edu.eci.pdsw.samples.services.ExcepcionServiciosAlquiler;
import edu.eci.pdsw.samples.services.ServiciosAlquiler;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author 2106913
 */
@ManagedBean(name = "AlquilerItems")
@SessionScoped
public class AlquilerItemsBean implements Serializable {

    ServiciosAlquiler sp = ServiciosAlquiler.getInstance();
    
    private Cliente nuevoCliente;
    private Cliente seleccionCliente;
    private int codigo;
    private int dias;
    private long costo;

    public AlquilerItemsBean() {
        nuevoCliente = new Cliente();
        seleccionCliente = new Cliente();
        codigo = 0;
        dias = 0;
        costo = 0;
    }

    public Cliente getNuevoCliente() {
        return nuevoCliente;
    }

    public void setNuevoCliente(Cliente nuevoCliente) {
        this.nuevoCliente = nuevoCliente;
    }

    public Cliente getSeleccionCliente() {
        return seleccionCliente;
    }

    public void setSeleccionCliente(Cliente seleccionCliente) {
        this.seleccionCliente = seleccionCliente;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public int getDias() {
        return dias;
    }

    public void setDias(int dias) {
        this.dias = dias;
    }

    public long getCosto() {
        return costo;
    }

    public void setCosto(long costo) {
        this.costo = costo;
    }
    
    public List<Cliente> getConsultarClientes() throws ExcepcionServiciosAlquiler{
        return sp.consultarClientes();
    }
    
    public List<ItemRentado> getConsultarItemsCliente() throws ExcepcionServiciosAlquiler {
        return sp.consultarItemsCliente(seleccionCliente.getDocumento());
    }
    
    public List<Item> getConsultarItemsDisponibles() throws ExcepcionServiciosAlquiler{
        return sp.consultarItemsDisponibles();
    }
    
    public long getConsultarMultaAlquiler() throws ExcepcionServiciosAlquiler{
        long total = 0;
        List<ItemRentado> lista = seleccionCliente.getRentados();
        if (lista.size() > 0 && lista != null) {
            for (int i = 0; i < lista.size(); i++) {
                total += sp.consultarMultaAlquiler(lista.get(i).getItem().getId(), java.sql.Date.valueOf(LocalDate.now()));
            }
        }
        return total;
    }
    
    public void consultarCostoAlquiler() throws ExcepcionServiciosAlquiler {
        if (codigo > 0) {
            setCosto(sp.consultarCostoAlquiler(codigo, dias));
        }
    }

    public void registrarAlquiler() throws ExcepcionServiciosAlquiler{
        if (codigo > 0) {
            Item nuevoItem = sp.consultarItem(codigo);
            sp.registrarAlquilerCliente(java.sql.Date.valueOf(LocalDate.now()), seleccionCliente.getDocumento(), nuevoItem, dias);
            setDias(0);
            setCodigo(0);
        }

    }
    
    public void registrarNuevoCliente() throws ExcepcionServiciosAlquiler {
        sp.registrarCliente(nuevoCliente);
        nuevoCliente = new Cliente();
    }
}
