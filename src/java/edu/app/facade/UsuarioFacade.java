/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.app.facade;

import edu.app.entity.Usuario;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Andrés
 */
@Stateless
public class UsuarioFacade extends AbstractFacade<Usuario> implements UsuarioFacadeLocal {

    @PersistenceContext(unitName = "DNA_SPORTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuarioFacade() {
        super(Usuario.class);
    }

    public Usuario verificarUsuario(String correoIn, String contraseniaIn) {
        try {
            Query consul = em.createQuery("SELECT u FROM Usuario u WHERE u.contrasenia = :contraseniaIn AND u.correo = :correoIn");
            consul.setParameter("correoIn", correoIn);
            consul.setParameter("contraseniaIn", contraseniaIn);

            return (Usuario) consul.getSingleResult();
        } catch (Exception e) {
            System.out.println("edu.app.facade.UsuarioFacade.verificarUsuario()" + e.getMessage());
            return new Usuario();
        }
    }
}
