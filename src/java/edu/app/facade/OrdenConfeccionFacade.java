/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.app.facade;

import edu.app.entity.OrdenConfeccion;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Andrés
 */
@Stateless
public class OrdenConfeccionFacade extends AbstractFacade<OrdenConfeccion> implements OrdenConfeccionFacadeLocal {

    @PersistenceContext(unitName = "DNA_SPORTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public OrdenConfeccionFacade() {
        super(OrdenConfeccion.class);
    }
    
    public List<OrdenConfeccion> TablaAdmin(){
        try {
            Query consul = em.createQuery("SELECT o FROM OrdenConfeccion o WHERE o.estadoPedido = 'En proceso'"); 
            List<OrdenConfeccion>listado = consul.getResultList();
            return listado;
        } catch (Exception e) {
            System.out.println("edu.app.facade.OrdenConfeccionFacade.TablaAdmin()" + e.getMessage()); 
            return null;
        }
    }
}
