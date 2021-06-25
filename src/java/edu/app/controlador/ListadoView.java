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
import static java.lang.Integer.parseInt;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import org.primefaces.model.charts.ChartData;
import org.primefaces.model.charts.axes.cartesian.CartesianScales;
import org.primefaces.model.charts.axes.cartesian.linear.CartesianLinearAxes;
import org.primefaces.model.charts.line.LineChartDataSet;
import org.primefaces.model.charts.line.LineChartModel;
import org.primefaces.model.charts.line.LineChartOptions;
import org.primefaces.model.charts.optionconfig.title.Title;
import org.primefaces.model.charts.pie.PieChartDataSet;
import org.primefaces.model.charts.pie.PieChartModel;

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
    private ArrayList<String> pedidosUsuarioId = new ArrayList<>();
    private ArrayList<String> listaProductoUsuarioId = new ArrayList<>();
    private ArrayList<String> productosIdUsuario = new ArrayList<>();

    private String validacionPedido = "";
    private LineChartModel cartesian = new LineChartModel();
    private PieChartModel graficoVentas = new PieChartModel();
    private Color color = new Color();

    @PostConstruct
    public void listarInsumos() {
        try {
            cantidadProducots.addAll(productoFacadeLocal.findAll());
            listaPedido.addAll(pedidoFacadeLocal.findAll());
            //Inicio de mis pruebas(Juan)
            String verificadorid = usuarioSesion.getIngresar().toString();
            for (int i = 0; i <= listaPedido.size(); i++) {
                if (listaPedido.get(i).getUsuarioIdUsuario().toString().equalsIgnoreCase(verificadorid)) {
                    System.out.println("Cantidad:" + listaPedido.get(i).getCantidad());
                    pedidosUsuarioId.add(listaPedido.get(i).getCantidad());
                    String valorOriginal = listaPedido.get(i).getProductoCodigoProducto().toString();
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

    public void editarPedido(Pedido pedidos) {
        try {
            pedidoFacadeLocal.edit(pedidos);
            System.out.println(pedidos.getProductoCodigoProducto());
        } catch (Exception e) {
            System.out.println("edu.app.controlador.ListadoView.editarPedido() " + e.getMessage());
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

    public void crearGraficoVentas() {
        try {
            graficoVentas = new PieChartModel();
            ChartData informacion = new ChartData();
            PieChartDataSet dataSet = new PieChartDataSet();
            List<Number> valores = new ArrayList<>();
            ArrayList<Pedido> listadePedidos = new ArrayList<>();
            listadePedidos.addAll(pedidoFacadeLocal.findAll());
            for (int i = 0; i < listadePedidos.size(); i++) {
                System.out.println("VALOR: " + listadePedidos.get(i));
                valores.add(parseInt(listadePedidos.get(i).getCantidad()));
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
            for (int i = 0; i < listadePedidos.size(); i++) {
                System.out.println("NOMBRE:" + listadePedidos.get(i).getNombres());
                labels.add(listadePedidos.get(i).getNombres());
            }
            informacion.setLabels(labels);
            graficoVentas.setData(informacion);
        } catch (Exception e) {
            System.out.println("ERROR durante la impresion: " + e.getMessage());
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

    public PieChartModel getGraficoVentas() {
        return graficoVentas;
    }

    public void setGraficoVentas(PieChartModel graficoVentas) {
        this.graficoVentas = graficoVentas;
    }

    public LineChartModel getCartesian() {
        return cartesian;
    }

    public void setCartesian(LineChartModel cartesian) {
        this.cartesian = cartesian;
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

}
