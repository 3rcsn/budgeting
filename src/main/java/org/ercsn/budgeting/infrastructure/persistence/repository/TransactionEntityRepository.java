package org.ercsn.budgeting.infrastructure.persistence.repository;

import org.ercsn.budgeting.domain.Category;
import org.ercsn.budgeting.domain.Transaction;
import org.ercsn.budgeting.infrastructure.persistence.entity.TransactionEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

public interface TransactionEntityRepository extends CrudRepository<TransactionEntity, UUID> {
    List<TransactionEntity> findAllByCategory(Category category);
}
