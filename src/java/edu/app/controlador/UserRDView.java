package edu.app.controlador;

import javax.inject.Named;
import javax.faces.view.ViewScoped;
import edu.app.entity.Usuario;
import edu.app.facade.UsuarioFacadeLocal;
import java.io.Serializable;
import java.util.ArrayList;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;

@Named(value = "userRDView")
@ViewScoped
public class UserRDView implements Serializable {

    @EJB
    private UsuarioFacadeLocal usuarioFacadeLocal;
    private String verificar = "";
    private String correo = "";
    private String contrasenia = "";
    private Usuario ingresar = new Usuario();

    private Usuario objUser = new Usuario();
    private ArrayList<Usuario> usuariosRegistrados = new ArrayList<>();

    @PostConstruct
    public void usuariosCreados() {
        usuariosRegistrados.addAll(usuarioFacadeLocal.findAll());
    }

    public void eliminarUsuario(Usuario usuario) {
        try {
            usuarioFacadeLocal.remove(usuario);
            usuariosRegistrados.remove(usuario);
        } catch (Exception e) {
            System.out.println("edu.app.controlador.UserRDView.eliminarUsuario()" + e.getMessage());
        }
    }

    public UserRDView() {
    }

    public UsuarioFacadeLocal getUsuarioFacadeLocal() {
        return usuarioFacadeLocal;
    }

    public void setUsuarioFacadeLocal(UsuarioFacadeLocal usuarioFacadeLocal) {
        this.usuarioFacadeLocal = usuarioFacadeLocal;
    }

    public String getVerificar() {
        return verificar;
    }

    public void setVerificar(String verificar) {
        this.verificar = verificar;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public Usuario getIngresar() {
        return ingresar;
    }

    public void setIngresar(Usuario ingresar) {
        this.ingresar = ingresar;
    }

    public Usuario getObjUser() {
        return objUser;
    }

    public void setObjUser(Usuario objUser) {
        this.objUser = objUser;
    }

    public ArrayList<Usuario> getUsuariosRegistrados() {
        return usuariosRegistrados;
    }

    public void setUsuariosRegistrados(ArrayList<Usuario> usuariosRegistrados) {
        this.usuariosRegistrados = usuariosRegistrados;
    }

}
