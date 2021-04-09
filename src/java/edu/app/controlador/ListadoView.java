package edu.app.controlador;

import edu.app.entity.Color;
import edu.app.entity.Insumos;
import edu.app.entity.Pedido;
import edu.app.entity.Producto;
import edu.app.facade.ColorFacadeLocal;
import edu.app.facade.InsumosFacadeLocal;
import edu.app.facade.PedidoFacadeLocal;
import edu.app.facade.ProductoFacadeLocal;
import java.io.Serializable;
import java.util.ArrayList;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;

@Named(value = "listadoView")
@ViewScoped
public class ListadoView implements Serializable {

    @EJB
    InsumosFacadeLocal insumosFacadeLocal;
    @EJB
    PedidoFacadeLocal pedidoFacadeLocal;
    @EJB
    ColorFacadeLocal colorFacadeLocal;
    @EJB
    ProductoFacadeLocal productoFacadeLocal;
    @Inject
    UsuarioSesion usuarioSesion;

    private int idProducto;

    private Insumos objInsumos = new Insumos();
    private ArrayList<Insumos> listaInsumos = new ArrayList<>();
    private Pedido objPedido = new Pedido();
    private ArrayList<Pedido> listaPedido = new ArrayList<>();
    private Color objColor = new Color();
    private ArrayList<Color> listaColores = new ArrayList<>();
    private ArrayList<Producto> cantidadProducots = new ArrayList<>();

    private String validacionPedido = "";

    @PostConstruct
    public void listarInsumos() {
        try {
            cantidadProducots.addAll(productoFacadeLocal.findAll());
            listaPedido.addAll(pedidoFacadeLocal.findAll());
        } catch (Exception e) {
            System.out.println("edu.app.controlador.ListadoView.listarInsumos() " + e.getMessage());
        }
    }

    public ListadoView() {
    }

    public void crearPedido() {
        try {
            Producto codigo = productoFacadeLocal.find(idProducto);
            objPedido.setProductoCodigoProducto(codigo);
            objPedido.setUsuarioIdUsuario(usuarioSesion.getIngresar());
            pedidoFacadeLocal.create(objPedido);
            objPedido = new Pedido();
        } catch (Exception e) {
            System.out.println("edu.app.controlador.ListadoView.crearPedido() " + e.getMessage());
        }
    }

    public void eliminarPedido(Pedido pedidos) {
        try {
            pedidoFacadeLocal.remove(pedidos);
            listaPedido.remove(pedidos);
            validacionPedido = "Pedido Eliminado exitosamente";
        } catch (Exception e) {
            System.out.println("edu.app.controlador.ListadoView.eliminarPedido() " + e.getMessage());
            validacionPedido = "El pedido no se pudo eliminar";
        }
    }
    
    public void editarPedido(Pedido pedidos){
        try {
            pedidoFacadeLocal.edit(pedidos);
            System.out.println(pedidos.getProductoCodigoProducto());
        } catch (Exception e) {
            System.out.println("edu.app.controlador.ListadoView.editarPedido() " + e.getMessage());
        }
    }

    public Insumos getObjInsumos() {
        return objInsumos;
    }

    public void setObjInsumos(Insumos objInsumos) {
        this.objInsumos = objInsumos;
    }

    public ArrayList<Insumos> getListaInsumos() {
        return listaInsumos;
    }

    public void setListaInsumos(ArrayList<Insumos> listaInsumos) {
        this.listaInsumos = listaInsumos;
    }

    public Pedido getObjPedido() {
        return objPedido;
    }

    public void setObjPedido(Pedido objPedido) {
        this.objPedido = objPedido;
    }

    public ArrayList<Pedido> getListaPedido() {
        return listaPedido;
    }

    public void setListaPedido(ArrayList<Pedido> listaPedido) {
        this.listaPedido = listaPedido;
    }

    public Color getObjColor() {
        return objColor;
    }

    public void setObjColor(Color objColor) {
        this.objColor = objColor;
    }

    public ArrayList<Color> getListaColores() {
        return listaColores;
    }

    public void setListaColores(ArrayList<Color> listaColores) {
        this.listaColores = listaColores;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public ArrayList<Producto> getCantidadProducots() {
        return cantidadProducots;
    }

    public void setCantidadProducots(ArrayList<Producto> cantidadProducots) {
        this.cantidadProducots = cantidadProducots;
    }

    public String getValidacionPedido() {
        return validacionPedido;
    }

    public void setValidacionPedido(String validacionPedido) {
        this.validacionPedido = validacionPedido;
    }
    
}
