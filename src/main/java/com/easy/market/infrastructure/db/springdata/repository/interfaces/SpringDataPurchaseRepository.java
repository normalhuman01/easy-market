package com.easy.market.infrastructure.db.springdata.repository.interfaces;

import com.easy.market.infrastructure.db.springdata.dbo.PurchaseEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface SpringDataPurchaseRepository extends CrudRepository<PurchaseEntity, Long> {

    public Optional<List<PurchaseEntity>> findByIdCliente(String idCliente);
}
