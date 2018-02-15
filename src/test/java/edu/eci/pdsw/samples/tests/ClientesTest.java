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

/**
 * Diseno de pruebas:
 * 
 *      void registrarCliente(Cliente p)
 * 
 * Clases de equivalencia:
 *  
 *      CE1: Descripción: Registrar un cliente con un documento ya existente
 *           Resultado esperado: "El cliente ya se encuentra registrado"
 *          
 *      CE2: Descripción: Registrar un cliente con un documento < 0
 *           Resultado esperado: "Documento no valido"
 * 
 *      CE3: Descripción: Registrar un cliente con un documento no numerico
 *           Resultado esperado:  "Tipo de documento no valido"
 *  
 */
public class ClientesTest {

    public ClientesTest() {
    }
    
    @Before
    public void setUp() {
    }
    
  
    @Test
    public void CE1() throws ExcepcionServiciosAlquiler{
    	//Cliente c = new 
    }
    
    
    
    
    
    
    
}
