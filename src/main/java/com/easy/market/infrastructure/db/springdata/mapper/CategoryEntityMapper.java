package com.easy.market.infrastructure.db.springdata.mapper;

import com.easy.market.domain.Category;
import com.easy.market.infrastructure.db.springdata.dbo.CategoryEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface CategoryEntityMapper {
    @Mappings({
            @Mapping(source = "idCategoria", target = "categoryId"),
            @Mapping(source = "descripcion", target = "category"),
            @Mapping(source = "estado", target = "active")
    })
    Category toCategory(CategoryEntity categoryEntity);

    @InheritInverseConfiguration
    @Mapping(target = "productos", ignore = true)
    CategoryEntity toCategoria(Category category);
}
