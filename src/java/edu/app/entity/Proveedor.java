/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.app.entity;

import java.io.Serializable;
import java.util.Collection;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Andrés
 */
@Entity
@Table(name = "proveedor")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Proveedor.findAll", query = "SELECT p FROM Proveedor p")
    , @NamedQuery(name = "Proveedor.findByNit", query = "SELECT p FROM Proveedor p WHERE p.nit = :nit")
    , @NamedQuery(name = "Proveedor.findByRazonSocial", query = "SELECT p FROM Proveedor p WHERE p.razonSocial = :razonSocial")
    , @NamedQuery(name = "Proveedor.findByCiudad", query = "SELECT p FROM Proveedor p WHERE p.ciudad = :ciudad")
    , @NamedQuery(name = "Proveedor.findByCalle", query = "SELECT p FROM Proveedor p WHERE p.calle = :calle")
    , @NamedQuery(name = "Proveedor.findByZona", query = "SELECT p FROM Proveedor p WHERE p.zona = :zona")})
public class Proveedor implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "nit")
    private Integer nit;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "razon_social")
    private String razonSocial;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "ciudad")
    private String ciudad;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "calle")
    private String calle;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "zona")
    private String zona;
    @OneToMany(mappedBy = "proveedorNit", fetch = FetchType.LAZY)
    private Collection<Insumos> insumosCollection;
    @JoinColumn(name = "usuario_id_usuario", referencedColumnName = "id_usuario")
    @ManyToOne(fetch = FetchType.LAZY)
    private Usuario usuarioIdUsuario;

    public Proveedor() {
    }

    public Proveedor(Integer nit) {
        this.nit = nit;
    }

    public Proveedor(Integer nit, String razonSocial, String ciudad, String calle, String zona) {
        this.nit = nit;
        this.razonSocial = razonSocial;
        this.ciudad = ciudad;
        this.calle = calle;
        this.zona = zona;
    }

    public Integer getNit() {
        return nit;
    }

    public void setNit(Integer nit) {
        this.nit = nit;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getZona() {
        return zona;
    }

    public void setZona(String zona) {
        this.zona = zona;
    }

    @XmlTransient
    public Collection<Insumos> getInsumosCollection() {
        return insumosCollection;
    }

    public void setInsumosCollection(Collection<Insumos> insumosCollection) {
        this.insumosCollection = insumosCollection;
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
        hash += (nit != null ? nit.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Proveedor)) {
            return false;
        }
        Proveedor other = (Proveedor) object;
        if ((this.nit == null && other.nit != null) || (this.nit != null && !this.nit.equals(other.nit))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.app.entity.Proveedor[ nit=" + nit + " ]";
    }
    
}
