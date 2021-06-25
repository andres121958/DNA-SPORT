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
import edu.app.entity.Color;
import edu.app.entity.Insumos;
import edu.app.entity.Producto;
import edu.app.facade.ColorFacadeLocal;
import edu.app.facade.InsumosFacadeLocal;
import edu.app.facade.ProductoFacadeLocal;
import static java.lang.Integer.parseInt;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import org.primefaces.model.charts.ChartData;
import org.primefaces.model.charts.axes.cartesian.CartesianScales;
import org.primefaces.model.charts.axes.cartesian.linear.CartesianLinearAxes;
import org.primefaces.model.charts.line.LineChartDataSet;
import org.primefaces.model.charts.line.LineChartModel;
import org.primefaces.model.charts.line.LineChartOptions;
import org.primefaces.model.charts.optionconfig.title.Title;
import org.primefaces.model.charts.pie.PieChartDataSet;
import org.primefaces.model.charts.pie.PieChartModel;

@Named(value = "produccionView")
@ViewScoped
public class ProduccionView implements Serializable {

    @EJB
    private OrdenConfeccionFacadeLocal ordenConfeccionFacadeLocal;
    @EJB
    private UsuarioFacadeLocal usuarioFacadeLocal;
    @Inject
    private UsuarioSesion usuarioSesion;
    @EJB
    private InsumosFacadeLocal insumosFacadeLocal;
    @EJB
    private ColorFacadeLocal colorFacadeLocal;
    @EJB
    private ProductoFacadeLocal productoFacadeLocal;
    
    private int idProducto;  
    private Insumos objInsumos = new Insumos();
    private ArrayList<Insumos> listaInsumos = new ArrayList<>();
    private Color objColor = new Color();
    private ArrayList<Color> listaColores = new ArrayList<>();
    private ArrayList<Producto> cantidadProducots = new ArrayList<>();
    private ArrayList<String> pedidosUsuarioId = new ArrayList<>();
    private ArrayList<String> listaProductoUsuarioId = new ArrayList<>();
    private ArrayList<String> productosIdUsuario = new ArrayList<>();
    private ArrayList<OrdenConfeccion> listaOrdenConfeccion = new ArrayList<>();
    private String validacionPedido = "";
    private LineChartModel cartesian = new LineChartModel();
    private PieChartModel graficoVentas = new PieChartModel();
    private Color color = new Color();
    private ArrayList<OrdenConfeccion> cantidad = new ArrayList<>();
    private OrdenConfeccion orden = new OrdenConfeccion();
    private ArrayList<OrdenConfeccion> listaProceso = new ArrayList<>();

    @PostConstruct
    public void ordenesCreadas() {
        cantidad.addAll(ordenConfeccionFacadeLocal.findAll());
        listaProceso.addAll(ordenConfeccionFacadeLocal.TablaAdmin());
        for(OrdenConfeccion ordensito : listaProceso){
            System.out.println(ordensito);
        }
    }
    
    public void listarInsumos() {
        try {
            cantidadProducots.addAll(productoFacadeLocal.findAll());
            listaOrdenConfeccion.addAll(ordenConfeccionFacadeLocal.findAll());
            //Inicio de mis pruebas(Juan)
            String verificadorid = usuarioSesion.getIngresar().toString();
            for (int i = 0; i <= listaOrdenConfeccion.size(); i++) {
                if (listaOrdenConfeccion.get(i).getUsuarioIdUsuario().toString().equalsIgnoreCase(verificadorid)) {
                    System.out.println("Cantidad:" + listaOrdenConfeccion.get(i).getCantidad());
                    pedidosUsuarioId.add(listaOrdenConfeccion.get(i).getCantidad());
                    String valorOriginal = listaOrdenConfeccion.get(i).getProductoCodigoProducto().toString();
                    String[] valorSeparado = valorOriginal.split("=");
                    String modificado1 = valorSeparado[1].replace("]", "");
                    String modificado2 = modificado1.replace(" ", "");
                    productosIdUsuario.add(modificado2);
                    System.out.println("VALOR MODIFICADO: " + modificado2);
                    switch(modificado2){
                        case "1":
                            listaProductoUsuarioId.add("Pantalón Diadora Mujer");
                            break;
                        case "2":
                            listaProductoUsuarioId.add("Licras Essentials");
                            break;
                        case "3":
                            listaProductoUsuarioId.add("Chaqueta Deportiva Gris");
                            break;
                        case "4":
                            listaProductoUsuarioId.add("Leggins deportivo control abdomen");
                            break;
                        case "5":
                            listaProductoUsuarioId.add("Top deportivo Danfive para niña");
                            break;
                        case "6":
                            listaProductoUsuarioId.add("Sudadera de tipo Tiro para hombre");
                            break;
                        default:
                            System.out.println("Hubo un problema");
                            break;
                    }
                } else {
                    System.out.println("No es el usuario actual");
                }
            }
            //Fin de mis pruebas(Juan)
            crearCartesianLinerModel();
            crearGraficoVentas();
        } catch (Exception e) {
            System.out.println("edu.app.controlador.ListadoView.listarInsumos() " + e.getMessage());
        }
    }
    
    public void crearGraficoVentas() {
        try {
            graficoVentas = new PieChartModel();
            ChartData informacion = new ChartData();
            PieChartDataSet dataSet = new PieChartDataSet();
            List<Number> valores = new ArrayList<>();
            ArrayList<OrdenConfeccion> listadeOrdenes = new ArrayList<>();
            listadeOrdenes.addAll(ordenConfeccionFacadeLocal.findAll());
            for (int i = 0; i < listadeOrdenes.size(); i++) {
                System.out.println("VALOR: " + listadeOrdenes.get(i));
                valores.add(parseInt(listadeOrdenes.get(i).getCantidad()));
            }
            dataSet.setData(valores);
            List<String> bgColors = new ArrayList<>();
            bgColors.add("rgb(255, 99, 132)");
            bgColors.add("rgb(54, 162, 235)");
            bgColors.add("rgb(246, 255, 51)");
            bgColors.add("rgb(243, 51, 255)");
            bgColors.add("rgb(107, 255, 50)");
            dataSet.setBackgroundColor(bgColors);
            informacion.addChartDataSet(dataSet);
            List<String> labels = new ArrayList<>();
            for (int i = 0; i < listadeOrdenes.size(); i++) {
                System.out.println("NOMBRE:" + listadeOrdenes.get(i).getNombres());
                labels.add(listadeOrdenes.get(i).getNombres());
            }
            informacion.setLabels(labels);
            graficoVentas.setData(informacion);
        } catch (Exception e) {
            System.out.println("ERROR durante la impresion: " + e.getMessage());
        }
    }

    public void crearCartesianLinerModel() {
        try {
            cartesian = new LineChartModel();
            ChartData datos = new ChartData();

//            Cantidades grafiica 1
            LineChartDataSet dataSet = new LineChartDataSet();
            List<Object> listaCarrito = new ArrayList<>();
            listaCarrito.add(12);
            listaCarrito.add(51);
            listaCarrito.add(1);
            listaCarrito.add(6);
            listaCarrito.add(14);

            dataSet.setData(listaCarrito);
            dataSet.setLabel("Ventas");
            dataSet.setYaxisID("left-y-axis");

//            cantidades grafica 2
            LineChartDataSet dataSet2 = new LineChartDataSet();
            List<Object> listaCarrito2 = new ArrayList<>();
            listaCarrito2.add(2);
            listaCarrito2.add(5);
            listaCarrito2.add(21);
            listaCarrito2.add(16);
            listaCarrito2.add(24);

            dataSet2.setData(listaCarrito2);
            dataSet2.setLabel(" ");
            dataSet2.setYaxisID("right-y-axis");

            datos.addChartDataSet(dataSet);
            datos.addChartDataSet(dataSet2);

//            labels
            List<String> labels = new ArrayList<>();
            labels.add("Pepsi");
            labels.add("Cocacola");
            labels.add("Mojarra");
            labels.add("Empanada");
            labels.add("Carne Res");

            datos.setLabels(labels);
            cartesian.setData(datos);

            //Options
            LineChartOptions options = new LineChartOptions();
            CartesianScales cScales = new CartesianScales();
            CartesianLinearAxes linearAxes = new CartesianLinearAxes();
            linearAxes.setId("left-y-axis");
            linearAxes.setPosition("left");
            CartesianLinearAxes linearAxes2 = new CartesianLinearAxes();
            linearAxes2.setId("right-y-axis");
            linearAxes2.setPosition("right");

            cScales.addYAxesData(linearAxes);
            cScales.addYAxesData(linearAxes2);
            options.setScales(cScales);

            Title title = new Title();
            title.setDisplay(true);
            title.setText("Top Productos");
            options.setTitle(title);

            cartesian.setOptions(options);

        } catch (Exception e) {
            System.out.println("edu.app.controlador.OrdenView.crearCartesianLinerModel()" + e.getMessage());
        }
    }
    
    public void eliminarOrdenes(OrdenConfeccion ordenes) {
        try {
            ordenConfeccionFacadeLocal.remove(ordenes);
            listaOrdenConfeccion.remove(ordenes);
            cantidad.remove(ordenes);
        } catch (Exception e) {
            System.out.println("edu.app.controlador.ProduccionView.eliminarOrdenes()" + e.getMessage());
        }
    }

    public ProduccionView() {
    }

    public void crearOrdenConfeccion() {
        try {
            Producto codigo = productoFacadeLocal.find(idProducto);
            orden.setProductoCodigoProducto(codigo);
            orden.setUsuarioIdUsuario(usuarioSesion.getIngresar());
            ordenConfeccionFacadeLocal.create(orden);
            orden = new OrdenConfeccion();
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
            System.out.println(ordenes.getProductoCodigoProducto());
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

    public ArrayList<OrdenConfeccion> getListaProceso() {
        return listaProceso;
    }

    public void setListaProceso(ArrayList<OrdenConfeccion> listaProceso) {
        this.listaProceso = listaProceso;
    }

    public OrdenConfeccionFacadeLocal getOrdenConfeccionFacadeLocal() {
        return ordenConfeccionFacadeLocal;
    }

    public void setOrdenConfeccionFacadeLocal(OrdenConfeccionFacadeLocal ordenConfeccionFacadeLocal) {
        this.ordenConfeccionFacadeLocal = ordenConfeccionFacadeLocal;
    }

    public UsuarioFacadeLocal getUsuarioFacadeLocal() {
        return usuarioFacadeLocal;
    }

    public void setUsuarioFacadeLocal(UsuarioFacadeLocal usuarioFacadeLocal) {
        this.usuarioFacadeLocal = usuarioFacadeLocal;
    }

    public UsuarioSesion getUsuarioSesion() {
        return usuarioSesion;
    }

    public void setUsuarioSesion(UsuarioSesion usuarioSesion) {
        this.usuarioSesion = usuarioSesion;
    }

    public InsumosFacadeLocal getInsumosFacadeLocal() {
        return insumosFacadeLocal;
    }

    public void setInsumosFacadeLocal(InsumosFacadeLocal insumosFacadeLocal) {
        this.insumosFacadeLocal = insumosFacadeLocal;
    }

    public ColorFacadeLocal getColorFacadeLocal() {
        return colorFacadeLocal;
    }

    public void setColorFacadeLocal(ColorFacadeLocal colorFacadeLocal) {
        this.colorFacadeLocal = colorFacadeLocal;
    }

    public ProductoFacadeLocal getProductoFacadeLocal() {
        return productoFacadeLocal;
    }

    public void setProductoFacadeLocal(ProductoFacadeLocal productoFacadeLocal) {
        this.productoFacadeLocal = productoFacadeLocal;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
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

    public ArrayList<Producto> getCantidadProducots() {
        return cantidadProducots;
    }

    public void setCantidadProducots(ArrayList<Producto> cantidadProducots) {
        this.cantidadProducots = cantidadProducots;
    }

    public ArrayList<String> getPedidosUsuarioId() {
        return pedidosUsuarioId;
    }

    public void setPedidosUsuarioId(ArrayList<String> pedidosUsuarioId) {
        this.pedidosUsuarioId = pedidosUsuarioId;
    }

    public ArrayList<String> getListaProductoUsuarioId() {
        return listaProductoUsuarioId;
    }

    public void setListaProductoUsuarioId(ArrayList<String> listaProductoUsuarioId) {
        this.listaProductoUsuarioId = listaProductoUsuarioId;
    }

    public ArrayList<String> getProductosIdUsuario() {
        return productosIdUsuario;
    }

    public void setProductosIdUsuario(ArrayList<String> productosIdUsuario) {
        this.productosIdUsuario = productosIdUsuario;
    }

    public ArrayList<OrdenConfeccion> getListaOrdenConfeccion() {
        return listaOrdenConfeccion;
    }

    public void setListaOrdenConfeccion(ArrayList<OrdenConfeccion> listaOrdenConfeccion) {
        this.listaOrdenConfeccion = listaOrdenConfeccion;
    }

    public String getValidacionPedido() {
        return validacionPedido;
    }

    public void setValidacionPedido(String validacionPedido) {
        this.validacionPedido = validacionPedido;
    }

    public LineChartModel getCartesian() {
        return cartesian;
    }

    public void setCartesian(LineChartModel cartesian) {
        this.cartesian = cartesian;
    }

    public PieChartModel getGraficoVentas() {
        return graficoVentas;
    }

    public void setGraficoVentas(PieChartModel graficoVentas) {
        this.graficoVentas = graficoVentas;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
    
}
