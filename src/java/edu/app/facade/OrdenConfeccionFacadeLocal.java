/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.app.facade;

import edu.app.entity.OrdenConfeccion;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Andrés
 */
@Local
public interface OrdenConfeccionFacadeLocal {

    void create(OrdenConfeccion ordenConfeccion);

    void edit(OrdenConfeccion ordenConfeccion);

    void remove(OrdenConfeccion ordenConfeccion);

    OrdenConfeccion find(Object id);

    List<OrdenConfeccion> findAll();

    List<OrdenConfeccion> findRange(int[] range);

    int count();
    
}
