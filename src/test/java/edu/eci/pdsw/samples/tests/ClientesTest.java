/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.pdsw.samples.tests;

import edu.eci.pdsw.samples.services.ExcepcionServiciosAlquiler;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import edu.eci.pdsw.samples.entities.Cliente;
import edu.eci.pdsw.samples.services.ServiciosAlquiler;
import edu.eci.pdsw.samples.services.ServiciosAlquilerItemsStub;
import java.util.List;

/**
 * Diseno de pruebas:
 * 
 *      void registrarCliente(Cliente p)
 * 
 * Clases de equivalencia:
 *  
 *      CE1: Descripcion: Registrar un cliente con un documento ya existente
 *           Resultado esperado: "El cliente con documento ya esta registrado"
 *          
 *      CE2: Descripcion: Registrar un cliente con un documento < 0
 *           Resultado esperado: "Documento no valido"
 * 
 *      CE3: Descripcion: Registrar un cliente con un documento no numerico
 *           Resultado esperado:  "Tipo de documento no valido"
 *  
 * Clases de frontera:
 * 
 *      CF1: Descripcion: Registrar dos o mas clientes alquilando el mismo item
 *           Resultado esperado: "Item alquilado por dos o maa clientes"
 */
public class ClientesTest {

    public ClientesTest() {
    }
    
    @Before
    public void setUp() {
    }
    
  
    @Test
    public void CE2() throws ExcepcionServiciosAlquiler{
        try {
            ServiciosAlquiler sa = ServiciosAlquilerItemsStub.getInstance();

            sa.registrarCliente(new Cliente("Camilo Gomez", -1036182560, "2781942", "KRA 43#17-a60", "camigo@hotmail.com"));
            List<Cliente> cli = sa.consultarClientes();
            assertTrue(cli.get(0).getDocumento() < 0);
        } catch (ExcepcionServiciosAlquiler ex) {
            throw new ExcepcionServiciosAlquiler("Documento no valido");
        }
    }
    
    
    
    
    
    
    
}
