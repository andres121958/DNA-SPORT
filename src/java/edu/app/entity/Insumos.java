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
@Table(name = "insumos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Insumos.findAll", query = "SELECT i FROM Insumos i")
    , @NamedQuery(name = "Insumos.findByNumeroReferencia", query = "SELECT i FROM Insumos i WHERE i.numeroReferencia = :numeroReferencia")
    , @NamedQuery(name = "Insumos.findByCantidad", query = "SELECT i FROM Insumos i WHERE i.cantidad = :cantidad")
    , @NamedQuery(name = "Insumos.findByDescripcion", query = "SELECT i FROM Insumos i WHERE i.descripcion = :descripcion")
    , @NamedQuery(name = "Insumos.findByNombre", query = "SELECT i FROM Insumos i WHERE i.nombre = :nombre")})
public class Insumos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "numero_referencia")
    private Integer numeroReferencia;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "cantidad")
    private String cantidad;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "descripcion")
    private String descripcion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "nombre")
    private String nombre;
    @JoinColumn(name = "bodega_id_bodega", referencedColumnName = "id_bodega")
    @ManyToOne(fetch = FetchType.LAZY)
    private Bodega bodegaIdBodega;
    @JoinColumn(name = "proveedor_nit", referencedColumnName = "nit")
    @ManyToOne(fetch = FetchType.LAZY)
    private Proveedor proveedorNit;

    public Insumos() {
    }

    public Insumos(Integer numeroReferencia) {
        this.numeroReferencia = numeroReferencia;
    }

    public Insumos(Integer numeroReferencia, String cantidad, String descripcion, String nombre) {
        this.numeroReferencia = numeroReferencia;
        this.cantidad = cantidad;
        this.descripcion = descripcion;
        this.nombre = nombre;
    }

    public Integer getNumeroReferencia() {
        return numeroReferencia;
    }

    public void setNumeroReferencia(Integer numeroReferencia) {
        this.numeroReferencia = numeroReferencia;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Bodega getBodegaIdBodega() {
        return bodegaIdBodega;
    }

    public void setBodegaIdBodega(Bodega bodegaIdBodega) {
        this.bodegaIdBodega = bodegaIdBodega;
    }

    public Proveedor getProveedorNit() {
        return proveedorNit;
    }

    public void setProveedorNit(Proveedor proveedorNit) {
        this.proveedorNit = proveedorNit;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (numeroReferencia != null ? numeroReferencia.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Insumos)) {
            return false;
        }
        Insumos other = (Insumos) object;
        if ((this.numeroReferencia == null && other.numeroReferencia != null) || (this.numeroReferencia != null && !this.numeroReferencia.equals(other.numeroReferencia))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.app.entity.Insumos[ numeroReferencia=" + numeroReferencia + " ]";
    }
    
}
