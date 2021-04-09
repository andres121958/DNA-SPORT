package edu.app.controlador;

import edu.app.entity.Usuario;
import edu.app.facade.UsuarioFacadeLocal;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;

@Named(value = "usuarioSesion")
@SessionScoped
public class UsuarioSesion implements Serializable {

    @EJB
    UsuarioFacadeLocal usuarioFacadeLocal;

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
            System.out.println("edu.app.controlador.LoginView.eliminarUsuario()" + e.getMessage());
        }
    }

    public UsuarioSesion() {
    }

    public void crearUsuario() {
        try {
            usuarioFacadeLocal.create(objUser);
            verificar = "Usuario registrado";
        } catch (Exception e) {
            System.out.println("edu.app.controlador.LoginView.crearUsuario()" + e.getMessage());
            verificar = "Error usuario";
        }
    }

    public void ingresarUsuario() {
        try {
            ingresar = usuarioFacadeLocal.verificarUsuario(correo, contrasenia);
            if (ingresar.getIdUsuario() != null && ingresar.getIdUsuario().equals(49)) {
                FacesContext rd = FacesContext.getCurrentInstance();
                rd.getExternalContext().redirect("Admin/usuarios.xhtml");
            }
            if (ingresar.getIdUsuario() != null && ingresar.getIdUsuario().equals(50)) {
                FacesContext rd = FacesContext.getCurrentInstance();
                rd.getExternalContext().redirect("Aux-Contab/pedidos.xhtml");
            }
            if (ingresar.getIdUsuario() != null) {
                FacesContext rd = FacesContext.getCurrentInstance();
                rd.getExternalContext().redirect("Cliente/estado_pedido.xhtml");
            } else {
                verificar = "Error validar información";
            }
        } catch (Exception e) {
            System.out.println("edu.app.controlador.LoginView.ingresarUsuario()" + e.getMessage());
        }
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
