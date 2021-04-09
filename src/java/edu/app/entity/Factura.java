/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.app.entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Andrés
 */
@Entity
@Table(name = "factura")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Factura.findAll", query = "SELECT f FROM Factura f")
    , @NamedQuery(name = "Factura.findByCodigoFactura", query = "SELECT f FROM Factura f WHERE f.codigoFactura = :codigoFactura")
    , @NamedQuery(name = "Factura.findByNombreEmpresa", query = "SELECT f FROM Factura f WHERE f.nombreEmpresa = :nombreEmpresa")
    , @NamedQuery(name = "Factura.findByNombreCliente", query = "SELECT f FROM Factura f WHERE f.nombreCliente = :nombreCliente")
    , @NamedQuery(name = "Factura.findByDireccionEmpresa", query = "SELECT f FROM Factura f WHERE f.direccionEmpresa = :direccionEmpresa")
    , @NamedQuery(name = "Factura.findByFecha", query = "SELECT f FROM Factura f WHERE f.fecha = :fecha")
    , @NamedQuery(name = "Factura.findByTotal", query = "SELECT f FROM Factura f WHERE f.total = :total")
    , @NamedQuery(name = "Factura.findByIva", query = "SELECT f FROM Factura f WHERE f.iva = :iva")})
public class Factura implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigo_factura")
    private Integer codigoFactura;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "nombre_empresa")
    private String nombreEmpresa;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "nombre_cliente")
    private String nombreCliente;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "direccion_empresa")
    private String direccionEmpresa;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Basic(optional = false)
    @NotNull
    @Column(name = "total")
    private int total;
    @Column(name = "iva")
    private Integer iva;
    @JoinTable(name = "producto_has_factura", joinColumns = {
        @JoinColumn(name = "factura_codigo_factura", referencedColumnName = "codigo_factura")}, inverseJoinColumns = {
        @JoinColumn(name = "producto_codigo_producto", referencedColumnName = "codigo_producto")})
    @ManyToMany(fetch = FetchType.LAZY)
    private Collection<Producto> productoCollection;

    public Factura() {
    }

    public Factura(Integer codigoFactura) {
        this.codigoFactura = codigoFactura;
    }

    public Factura(Integer codigoFactura, String nombreEmpresa, String nombreCliente, String direccionEmpresa, Date fecha, int total) {
        this.codigoFactura = codigoFactura;
        this.nombreEmpresa = nombreEmpresa;
        this.nombreCliente = nombreCliente;
        this.direccionEmpresa = direccionEmpresa;
        this.fecha = fecha;
        this.total = total;
    }

    public Integer getCodigoFactura() {
        return codigoFactura;
    }

    public void setCodigoFactura(Integer codigoFactura) {
        this.codigoFactura = codigoFactura;
    }

    public String getNombreEmpresa() {
        return nombreEmpresa;
    }

    public void setNombreEmpresa(String nombreEmpresa) {
        this.nombreEmpresa = nombreEmpresa;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public String getDireccionEmpresa() {
        return direccionEmpresa;
    }

    public void setDireccionEmpresa(String direccionEmpresa) {
        this.direccionEmpresa = direccionEmpresa;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public Integer getIva() {
        return iva;
    }

    public void setIva(Integer iva) {
        this.iva = iva;
    }

    @XmlTransient
    public Collection<Producto> getProductoCollection() {
        return productoCollection;
    }

    public void setProductoCollection(Collection<Producto> productoCollection) {
        this.productoCollection = productoCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigoFactura != null ? codigoFactura.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Factura)) {
            return false;
        }
        Factura other = (Factura) object;
        if ((this.codigoFactura == null && other.codigoFactura != null) || (this.codigoFactura != null && !this.codigoFactura.equals(other.codigoFactura))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.app.entity.Factura[ codigoFactura=" + codigoFactura + " ]";
    }
    
}
