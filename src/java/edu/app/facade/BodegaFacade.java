/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.app.facade;

import edu.app.entity.Bodega;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Andrés
 */
@Stateless
public class BodegaFacade extends AbstractFacade<Bodega> implements BodegaFacadeLocal {

    @PersistenceContext(unitName = "DNA_SPORTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public BodegaFacade() {
        super(Bodega.class);
    }
    
}
