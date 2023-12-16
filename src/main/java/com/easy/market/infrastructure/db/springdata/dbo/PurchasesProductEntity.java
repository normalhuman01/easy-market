package com.easy.market.infrastructure.db.springdata.dbo;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "compras_productos")
public class PurchasesProductEntity {

    @EmbeddedId
    private PurchasesProductPKEntity id;

    private Long cantidad;
    private BigDecimal total;
    private Boolean estado;

    @ManyToOne
    @MapsId("idCompra")
    @JoinColumn(name = "id_compra", updatable = false, insertable = false)
    private PurchaseEntity purchaseEntity;

    @ManyToOne
    @JoinColumn(name = "id_producto", updatable = false, insertable = false)
    private ProductEntity productEntity;

    public PurchaseEntity getCompra() {
        return purchaseEntity;
    }

    public void setCompra(PurchaseEntity purchaseEntity) {
        this.purchaseEntity = purchaseEntity;
    }

    public ProductEntity getProducto() {
        return productEntity;
    }

    public void setProducto(ProductEntity productEntity) {
        this.productEntity = productEntity;
    }

    public PurchasesProductPKEntity getId() {
        return id;
    }

    public void setId(PurchasesProductPKEntity id) {
        this.id = id;
    }

    public Long getCantidad() {
        return cantidad;
    }

    public void setCantidad(Long cantidad) {
        this.cantidad = cantidad;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }


}
