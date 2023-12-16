package com.easy.market.infrastructure.db.springdata.dbo;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "categorias")
public class CategoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_categoria")
    private Long IdCategoria;
    private String descripcion;
    private Boolean estado;

    @OneToMany(mappedBy = "categoryEntity")
    private List<ProductEntity> productEntities;

    public List<ProductEntity> getProductos() {
        return productEntities;
    }

    public void setProductos(List<ProductEntity> productEntities) {
        this.productEntities = productEntities;
    }

    public Long getIdCategoria() {
        return IdCategoria;
    }

    public void setIdCategoria(Long idCategoria) {
        IdCategoria = idCategoria;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }


}
