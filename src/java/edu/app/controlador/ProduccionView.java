package edu.app.controlador;

import edu.app.entity.OrdenConfeccion;
import edu.app.facade.OrdenConfeccionFacadeLocal;
import edu.app.facade.UsuarioFacadeLocal;
import java.io.Serializable;
import java.util.ArrayList;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;

@Named(value = "produccionView")
@ViewScoped
public class ProduccionView implements Serializable {

    @EJB
    OrdenConfeccionFacadeLocal ordenConfeccionFacadeLocal;
    @EJB
    UsuarioFacadeLocal usuarioFacadeLocal;
    @Inject
    UsuarioSesion usuarioSesion;

    private ArrayList<OrdenConfeccion> cantidad = new ArrayList<>();
    private OrdenConfeccion orden = new OrdenConfeccion();

    @PostConstruct
    public void ordenesCreadas() {
        cantidad.addAll(ordenConfeccionFacadeLocal.findAll());
    }

    public void eliminarOrdenes(OrdenConfeccion ordenes) {
        try {
            ordenConfeccionFacadeLocal.remove(ordenes);
            cantidad.remove(ordenes);
        } catch (Exception e) {
            System.out.println("edu.app.controlador.ProduccionView.eliminarOrdenes()" + e.getMessage());
        }
    }

    public ProduccionView() {
    }

    public void crearOrdenConfeccion() {
        try {
            orden.setUsuarioIdUsuario(usuarioSesion.getIngresar());
            ordenConfeccionFacadeLocal.create(orden);
        } catch (Exception e) {
            System.out.println("edu.app.controlador.ProduccionView.crearOrdenConfeccion()" + e.getMessage());
        }
    }

    public void eliminarOrdenConfeccion(OrdenConfeccion ordenes) {
        try {
            ordenConfeccionFacadeLocal.remove(ordenes);
            cantidad.remove(ordenes);
        } catch (Exception e) {
            System.out.println("edu.app.controlador.ProduccionView.eliminarOrdenConfeccion()" + e.getMessage());
        }
    }

    public void editarOrdenConfeccion(OrdenConfeccion ordenes) {
        try {
            ordenConfeccionFacadeLocal.edit(ordenes);
        } catch (Exception e) {
            System.out.println("edu.app.controlador.ProduccionView.editarOrdenConfeccion()" + e.getMessage());
        }
    }

    public ArrayList<OrdenConfeccion> getCantidad() {
        return cantidad;
    }

    public void setCantidad(ArrayList<OrdenConfeccion> cantidad) {
        this.cantidad = cantidad;
    }

    public OrdenConfeccion getOrden() {
        return orden;
    }

    public void setOrden(OrdenConfeccion orden) {
        this.orden = orden;
    }
    
}
