package com.easy.market.infrastructure.db.springdata.dbo;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "clientes")
public class ClientEntity {

    @Id
    private String id;
    private String nombre;
    private String apellidos;
    private String celular;
    private String direccion;

    @Column(name = "correo_electronico")
    private String correoElectronico;

    @OneToMany(mappedBy = "clientEntity")
    private List<PurchaseEntity> purchaseEntities;

    public List<PurchaseEntity> getCompras() {
        return purchaseEntities;
    }

    public void setCompras(List<PurchaseEntity> purchaseEntities) {
        this.purchaseEntities = purchaseEntities;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }
}
