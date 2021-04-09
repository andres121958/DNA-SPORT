
package edu.app.controlador;

import edu.app.entity.Proveedor;
import edu.app.facade.ProveedorFacadeLocal;
import java.io.Serializable;
import java.util.ArrayList;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;


@Named(value = "proveedoresView")
@ViewScoped
public class ProveedoresView implements Serializable{
    @EJB
    ProveedorFacadeLocal proveedorFacadeLocal;
    @Inject
    UsuarioSesion usuarioSesion;
    
    private ArrayList<Proveedor> listaProveedores = new ArrayList<>();
    private Proveedor objProveedor = new Proveedor();
    
    @PostConstruct
    public void cantidadProveedores(){
        listaProveedores.addAll(proveedorFacadeLocal.findAll());
    }
  
    public ProveedoresView() {
    }
    
    public void crearProveedor(){
        try {
            objProveedor.setUsuarioIdUsuario(usuarioSesion.getIngresar());
            proveedorFacadeLocal.create(objProveedor);
        } catch (Exception e) {
            System.out.println("edu.app.controlador.ProveedoresView.crearProveedor()" + e.getMessage());
        }
    }

    public void eliminarProveedor(Proveedor proveedores){
        try {
            proveedorFacadeLocal.remove(proveedores);
            listaProveedores.remove(proveedores);
        } catch (Exception e) {
            System.out.println("edu.app.controlador.ProveedoresView.eliminarProveedor()" + e.getMessage());
        }
    }
    
    public void editarProveedor(Proveedor proveedores){
        try {
            proveedorFacadeLocal.edit(proveedores);
        } catch (Exception e) {
            System.out.println("edu.app.controlador.ProveedoresView.editarProveedor()" + e.getMessage());
        }
    }
    
    public ArrayList<Proveedor> getListaProveedores() {
        return listaProveedores;
    }

    public void setListaProveedores(ArrayList<Proveedor> listaProveedores) {
        this.listaProveedores = listaProveedores;
    }

    public Proveedor getObjProveedor() {
        return objProveedor;
    }

    public void setObjProveedor(Proveedor objProveedor) {
        this.objProveedor = objProveedor;
    }

    
    
    
}
