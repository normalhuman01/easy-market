package com.easy.market.infrastructure.db.springdata.repository.interfaces;

import com.easy.market.infrastructure.db.springdata.dbo.ProductEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface SpringDataProductRepository extends CrudRepository<ProductEntity, Long> {

    //Se pueden usar querys de SQL
    //@Query(value = "SELECT * FROM productos WHERE id_categoria= ?", nativeQuery = true)
    List<ProductEntity> findByIdCategoriaOrderByNombreAsc(long idCategoria);

    Optional<List<ProductEntity>> findByCantidadStockLessThanAndEstado(int cantidadStock, boolean estado);
}
