package edu.app.controlador;

import edu.app.entity.Bodega;
import edu.app.entity.Insumos;
import edu.app.entity.Proveedor;
import edu.app.facade.BodegaFacadeLocal;
import edu.app.facade.InsumosFacadeLocal;
import edu.app.facade.ProveedorFacadeLocal;
import java.io.Serializable;
import java.util.ArrayList;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;

@Named(value = "inventarioView")
@ViewScoped
public class InventarioView implements Serializable {

    @EJB
    InsumosFacadeLocal insumosFacadeLocal;
    @EJB
    ProveedorFacadeLocal proveedorFacadeLocal;
    @EJB
    BodegaFacadeLocal bodegaFacadeLocal;

    private ArrayList<Insumos> listaInsumos = new ArrayList<>();
    private ArrayList<Bodega> listaBodega = new ArrayList<>();
    private ArrayList<Proveedor> listaProveedor = new ArrayList<>();
    private Insumos objInsumos = new Insumos();

    private int nitProveedor;
    private int idBodega;

    @PostConstruct
    public void cantidadInsumos() {
        listaBodega.addAll(bodegaFacadeLocal.findAll());
        listaProveedor.addAll(proveedorFacadeLocal.findAll());
        listaInsumos.addAll(insumosFacadeLocal.findAll());
    }

    public InventarioView() {

    }

    public void nuevoInsumo() {
        try {
            Proveedor nombreProveedor = proveedorFacadeLocal.find(nitProveedor);
            objInsumos.setProveedorNit(nombreProveedor);
            Bodega nombreBodega = bodegaFacadeLocal.find(idBodega);
            objInsumos.setBodegaIdBodega(nombreBodega);
            insumosFacadeLocal.create(objInsumos);
            objInsumos = new Insumos();
        } catch (Exception e) {
            System.out.println("edu.app.controlador.InventarioView.nuevoInsumo()" + e.getMessage());
        }
    }

    public void eliminarInsumos(Insumos insumos) {
        try {
            insumosFacadeLocal.remove(insumos);
            listaInsumos.remove(insumos);
        } catch (Exception e) {
            System.out.println("edu.app.controlador.InventarioView.eliminarInsumos()" + e.getMessage());
        }
    }

    public void editarInsumos(Insumos insumos) {
        try {
            insumosFacadeLocal.edit(insumos);

        } catch (Exception e) {
            System.out.println("edu.app.controlador.InventarioView.editarInsumos()" + e.getMessage());
        }
    }

    public ArrayList<Insumos> getListaInsumos() {
        return listaInsumos;
    }

    public void setListaInsumos(ArrayList<Insumos> listaInsumos) {
        this.listaInsumos = listaInsumos;
    }

    public Insumos getObjInsumos() {
        return objInsumos;
    }

    public void setObjInsumos(Insumos objInsumos) {
        this.objInsumos = objInsumos;
    }

    public ArrayList<Bodega> getListaBodega() {
        return listaBodega;
    }

    public void setListaBodega(ArrayList<Bodega> listaBodega) {
        this.listaBodega = listaBodega;
    }

    public ArrayList<Proveedor> getListaProveedor() {
        return listaProveedor;
    }

    public void setListaProveedor(ArrayList<Proveedor> listaProveedor) {
        this.listaProveedor = listaProveedor;
    }

    public int getNitProveedor() {
        return nitProveedor;
    }

    public void setNitProveedor(int nitProveedor) {
        this.nitProveedor = nitProveedor;
    }

    public int getIdBodega() {
        return idBodega;
    }

    public void setIdBodega(int idBodega) {
        this.idBodega = idBodega;
    }

}
