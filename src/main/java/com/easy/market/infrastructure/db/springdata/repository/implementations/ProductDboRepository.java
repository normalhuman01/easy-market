package com.easy.market.infrastructure.db.springdata.repository.implementations;

import com.easy.market.infrastructure.db.springdata.dbo.ProductEntity;
import com.easy.market.infrastructure.db.springdata.repository.interfaces.SpringDataProductRepository;
import com.easy.market.domain.Product;
import com.easy.market.application.repository.ProductRepository;
import com.easy.market.infrastructure.db.springdata.mapper.ProductEntityMapper;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Optional;

@Repository
public class ProductDboRepository implements ProductRepository {

    @Autowired
    private SpringDataProductRepository springDataProductRepository;

    @Autowired
    private ProductEntityMapper mapper;

    @Override
    public List<Product> getAll() {
        List<ProductEntity> productEntities = (List<ProductEntity>) springDataProductRepository.findAll();
        return mapper.toProducts(productEntities);
    }

    @Override
    public Optional<List<Product>> getByCategory(long categoryId) {
        List<ProductEntity> productEntities = springDataProductRepository.findByIdCategoriaOrderByNombreAsc(categoryId);
        return Optional.of(mapper.toProducts(productEntities));
    }

    @Override
    public Optional<List<Product>> getScarseProducts(int quantity) {
        Optional<List<ProductEntity>> productos = springDataProductRepository.findByCantidadStockLessThanAndEstado(quantity, true);
        return productos.map(prods -> mapper.toProducts(prods));
    }

    @Override
    public Optional<Product> getProduct(long productId) {
        return springDataProductRepository.findById(productId).map(producto -> mapper.toProduct(producto));
    }

    @Override
    public Product save(Product product) {
        ProductEntity productEntity = mapper.toProducto(product);
        return mapper.toProduct(springDataProductRepository.save(productEntity));
    }

    @Override
    public void delete(long productId){
        springDataProductRepository.deleteById(productId);
    }

    @Override
    public ByteArrayInputStream exportAllData() throws Exception {
        String[] columns = {"Id del ProductEntity","Nombre", "Id de la categoria", "Precio", "Stock", "Activo"};

        Workbook workbook = new XSSFWorkbook();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();

        Sheet sheet = workbook.createSheet("Productos");
        Row row = sheet.createRow(0);

        for (int i = 0; i < columns.length; i++){
            Cell cell = row.createCell(i);
            cell.setCellValue(columns[i]);
        }

        List<Product> products = this.getAll();
        int initRow = 1;
        for (Product product: products){
            row = sheet.createRow(initRow);
            row.createCell(0).setCellValue(product.getProductId());
            row.createCell(1).setCellValue(product.getName());
            row.createCell(2).setCellValue(product.getCategoryId());
            row.createCell(3).setCellValue(product.getPrice());
            row.createCell(4).setCellValue(product.getStock());
            row.createCell(5).setCellValue(Boolean.toString(product.isActive()));

            initRow++;
        }

        workbook.write(stream);
        workbook.close();
        return new ByteArrayInputStream(stream.toByteArray());
    }
}
