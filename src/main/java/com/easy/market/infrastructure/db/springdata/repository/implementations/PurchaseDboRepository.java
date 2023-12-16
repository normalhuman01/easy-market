package com.easy.market.infrastructure.db.springdata.repository.implementations;

import com.easy.market.infrastructure.db.springdata.repository.interfaces.SpringDataPurchaseRepository;
import com.easy.market.domain.Purchase;
import com.easy.market.application.repository.PurchaseRepository;
import com.easy.market.infrastructure.db.springdata.dbo.PurchaseEntity;
import com.easy.market.infrastructure.db.springdata.mapper.PurchaseEntityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class PurchaseDboRepository implements PurchaseRepository {
    @Autowired
    private SpringDataPurchaseRepository springDataPurchaseRepository;

    @Autowired
    private PurchaseEntityMapper mapper;

    @Override
    public List<Purchase> getAll() {
        return mapper.toPurchases((List<PurchaseEntity>) springDataPurchaseRepository.findAll());
    }

    @Override
    public Optional<Purchase> getPurchase(Long purchaseId) {
        return springDataPurchaseRepository.findById(purchaseId).map(compra -> mapper.toPurchase(compra));
    }

    @Override
    public Optional<List<Purchase>> getByClient(String clientId) {
        return springDataPurchaseRepository.findByIdCliente(clientId)
                .map(compras -> mapper.toPurchases(compras));
    }

    @Override
    public Purchase save(Purchase purchase) {
        PurchaseEntity purchaseEntity = mapper.toCompra(purchase);
        purchaseEntity.getProductos().forEach(producto -> producto.setCompra(purchaseEntity));
        return mapper.toPurchase(springDataPurchaseRepository.save(purchaseEntity));
    }

    @Override
    public void delete(Long purchaseId) {
        springDataPurchaseRepository.deleteById(purchaseId);
    }
}
