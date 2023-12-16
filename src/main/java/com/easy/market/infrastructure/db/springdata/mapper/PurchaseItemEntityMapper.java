package com.easy.market.infrastructure.db.springdata.mapper;

import com.easy.market.domain.PurchaseItem;
import com.easy.market.infrastructure.db.springdata.dbo.PurchasesProductEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring",uses ={ProductEntityMapper.class})
public interface PurchaseItemEntityMapper {

    @Mappings({
            @Mapping(source = "id.idProducto", target = "productId"),
            @Mapping(source = "cantidad", target = "quantity"),
            @Mapping(source = "estado", target = "active")
    })
    PurchaseItem toPurchaseItem(PurchasesProductEntity producto);

    @InheritInverseConfiguration
    @Mappings({
            @Mapping(target = "compra", ignore = true),
            @Mapping(target = "producto", ignore = true),
            @Mapping(target = "id.idCompra", ignore = true)
    })
    PurchasesProductEntity toComprasProducto(PurchaseItem item);
}
