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
import edu.eci.pdsw.samples.entities.Item;
import edu.eci.pdsw.samples.entities.ItemRentado;
import edu.eci.pdsw.samples.entities.TipoItem;
import edu.eci.pdsw.samples.services.ServiciosAlquiler;
import edu.eci.pdsw.samples.services.ServiciosAlquilerItemsStub;
import java.util.ArrayList;

/**
 * Diseno de pruebas:
 * 
 *      void registrarCliente(Cliente p)
 * 
 * Clases de equivalencia:
 *  
 *      CE1: Descripcion: Registrar un cliente con un documento ya existente
 *           Resultado esperado: "El cliente con documento ya esta registrado."
 *          
 *      CE2: Descripcion: Registrar un cliente con un documento < 0
 *           Resultado esperado: "Documento no valido"
 * 
 *      CE3: Descripcion: Registrar un cliente con un nombre numerico
 *           Resultado esperado:  "Tipo de nombre no valido"
 *  
 * Clases de frontera:
 * 
 *      CF1: Descripcion: Registrar dos o mas clientes alquilando el mismo item al mismo tiempo
 *           Resultado esperado: "Item alquilado por dos o maa clientes al mismo tiempo"
 */
public class ClientesTest {

    public ClientesTest() {
    }
    
    @Before
    public void setUp() {
    }
    

    @Test(expected = ExcepcionServiciosAlquiler.class)
    public void CE1() throws ExcepcionServiciosAlquiler{
        ServiciosAlquiler sa = ServiciosAlquilerItemsStub.getInstance();
        
        Cliente c = new Cliente("Camilo Gomez", 2026115895, "2781942", "KRA 43#17-a60", "camigo@hotmail.com");
        Cliente c2 = new Cliente("Andres Gomez", 2026115895, "2781942", "KRA 43#17-a60", "andigo@hotmail.com");
        
        sa.registrarCliente(c);
        sa.registrarCliente(c2);
    }
    
    @Test
    public void CE2() throws ExcepcionServiciosAlquiler{
        ServiciosAlquiler sa = ServiciosAlquilerItemsStub.getInstance();
        
        Cliente c = new Cliente("Buggy Gomez", -1036182560, "2781942", "KRA 43#17-a60", "bugigo@hotmail.com");
        sa.registrarCliente(c);
        Cliente cli = sa.consultarCliente(c.getDocumento());
        assertTrue("Documento no valido", cli.getDocumento() < 0);
    }
    
    @Test
    public void CE3() throws ExcepcionServiciosAlquiler{
        ServiciosAlquiler sa = ServiciosAlquilerItemsStub.getInstance();
        
        Cliente c = new Cliente("1a3d23ff24", 1432629611, "2781942", "KRA 43#17-a60", "camigo@hotmail.com");
        sa.registrarCliente(c);
        assertTrue("Tipo de nombre no valido", c.getNombre().matches(".*[^\\s][0-9]+.*"));
    }
    
    @Test
    public void CF1() throws ExcepcionServiciosAlquiler{
        ServiciosAlquiler sa = ServiciosAlquilerItemsStub.getInstance();
        
        TipoItem ti1=new TipoItem(1,"Video");
        
        Item i1=new Item(ti1, 1, "Los 4 Fantasticos", "Los 4 Fantásticos  es una película de superhéroes  basada en la serie de cómic homónima de Marvel.", java.sql.Date.valueOf("2005-06-08"), 2000, "DVD", "Ciencia Ficcion");
        
        sa.registrarItem(i1);
        
        ItemRentado ir1=new ItemRentado(i1, java.sql.Date.valueOf("2017-01-01"), java.sql.Date.valueOf("2017-01-05"));
        
        ArrayList<ItemRentado> list1 = new ArrayList<>();
        list1.add(ir1);
                
        Cliente c1=new Cliente("Wei Cheng", 1021583659, "6788952", "KRA 109#34-C30", "sleeping@hotmail.com", false,list1);
        Cliente c2=new Cliente("The Kid", 1024562123, "6584562", "KRA 59#27-a22", "bastion@hotmail.com", false,list1);
        
        sa.registrarCliente(c1);
        sa.registrarCliente(c2);
        
        sa.registrarAlquilerCliente(java.sql.Date.valueOf("2017-01-01"), c1.getDocumento(), i1, 4);
        sa.registrarAlquilerCliente(java.sql.Date.valueOf("2017-01-01"), c2.getDocumento(), i1, 4);
        
        assertEquals("Item alquilado por dos o maa clientes al mismo tiempo", c1.getRentados().get(0),c2.getRentados().get(0));
    }
    
}
