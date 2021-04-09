package edu.app.controlador;

import com.csvreader.CsvReader;
import edu.app.facade.OrdenConfeccionFacadeLocal;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import edu.app.entity.OrdenConfeccion;
import java.io.InputStreamReader;
import org.primefaces.model.UploadedFile;
import edu.app.entity.Usuario;

@ManagedBean
@RequestScoped

public class CargarArchivoView implements Serializable {

    @EJB
    private OrdenConfeccionFacadeLocal ordenConfeccionFacadeLocal;
    private List<OrdenConfeccion> listaOrdenes;
    private OrdenConfeccion ordenes;
    private UploadedFile file;

    private int mensaje;

    public List<OrdenConfeccion> getListaOrdenes() {
        return listaOrdenes;
    }

    public void setListaOrdenes(List<OrdenConfeccion> listaOrdenes) {
        this.listaOrdenes = listaOrdenes;
    }

    @PostConstruct
    public void init() {
        ordenes = new OrdenConfeccion();
    }

    public OrdenConfeccion getOrdenes() {
        return ordenes;
    }

    public void setOrdenes(OrdenConfeccion ordenes) {
        this.ordenes = ordenes;
    }

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    public int getMensaje() {
        return mensaje;
    }

    public void setMensaje(int mensaje) {
        this.mensaje = mensaje;
    }

    public void upload() {
        Usuario usuarioAdmin = new Usuario(49);
        System.out.println(usuarioAdmin.getIdUsuario() + " este es el id");
        if (file != null) {
            try {
                CsvReader leerOrdenes;
                leerOrdenes = new CsvReader(new InputStreamReader(file.getInputstream()));

                leerOrdenes.readHeaders();
                while (leerOrdenes.readRecord()) {
                    ordenes = new OrdenConfeccion();

                    ordenes.setProducto(leerOrdenes.get(0));
                    ordenes.setEstadoPedido(leerOrdenes.get(1));
                    ordenes.setCantidad(leerOrdenes.get(2));
                    ordenes.setUsuarioIdUsuario(usuarioAdmin);

                    ordenConfeccionFacadeLocal.create(ordenes);
                }
                mensaje = 1;
            } catch (IOException e) {
                e.printStackTrace();
                mensaje = 2;
            }
        }
    }

}
