/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.app.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Andrés
 */
@Entity
@Table(name = "orden_confeccion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "OrdenConfeccion.findAll", query = "SELECT o FROM OrdenConfeccion o")
    , @NamedQuery(name = "OrdenConfeccion.findByIdOrdenConfeccion", query = "SELECT o FROM OrdenConfeccion o WHERE o.idOrdenConfeccion = :idOrdenConfeccion")
    , @NamedQuery(name = "OrdenConfeccion.findByProducto", query = "SELECT o FROM OrdenConfeccion o WHERE o.producto = :producto")
    , @NamedQuery(name = "OrdenConfeccion.findByEstadoPedido", query = "SELECT o FROM OrdenConfeccion o WHERE o.estadoPedido = :estadoPedido")
    , @NamedQuery(name = "OrdenConfeccion.findByCantidad", query = "SELECT o FROM OrdenConfeccion o WHERE o.cantidad = :cantidad")})
public class OrdenConfeccion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_orden_confeccion")
    private Integer idOrdenConfeccion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "producto")
    private String producto;
    @Size(max = 30)
    @Column(name = "estado_pedido")
    private String estadoPedido;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "cantidad")
    private String cantidad;
    @JoinColumn(name = "usuario_id_usuario", referencedColumnName = "id_usuario")
    @ManyToOne(fetch = FetchType.LAZY)
    private Usuario usuarioIdUsuario;

    public OrdenConfeccion() {
    }

    public OrdenConfeccion(Integer idOrdenConfeccion) {
        this.idOrdenConfeccion = idOrdenConfeccion;
    }

    public OrdenConfeccion(Integer idOrdenConfeccion, String producto, String cantidad) {
        this.idOrdenConfeccion = idOrdenConfeccion;
        this.producto = producto;
        this.cantidad = cantidad;
    }

    public Integer getIdOrdenConfeccion() {
        return idOrdenConfeccion;
    }

    public void setIdOrdenConfeccion(Integer idOrdenConfeccion) {
        this.idOrdenConfeccion = idOrdenConfeccion;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public String getEstadoPedido() {
        return estadoPedido;
    }

    public void setEstadoPedido(String estadoPedido) {
        this.estadoPedido = estadoPedido;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public Usuario getUsuarioIdUsuario() {
        return usuarioIdUsuario;
    }

    public void setUsuarioIdUsuario(Usuario usuarioIdUsuario) {
        this.usuarioIdUsuario = usuarioIdUsuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idOrdenConfeccion != null ? idOrdenConfeccion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OrdenConfeccion)) {
            return false;
        }
        OrdenConfeccion other = (OrdenConfeccion) object;
        if ((this.idOrdenConfeccion == null && other.idOrdenConfeccion != null) || (this.idOrdenConfeccion != null && !this.idOrdenConfeccion.equals(other.idOrdenConfeccion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.app.entity.OrdenConfeccion[ idOrdenConfeccion=" + idOrdenConfeccion + " ]";
    }
    
}
