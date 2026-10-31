package org.ercsn.budgeting.infrastructure.persistence.repository;

import org.ercsn.budgeting.domain.Category;
import org.ercsn.budgeting.domain.Transaction;
import org.ercsn.budgeting.domain.TransactionRepository;
import org.ercsn.budgeting.infrastructure.persistence.entity.TransactionEntity;

import java.util.List;

public class JpaTransactionRepository implements TransactionRepository {

    private final TransactionEntityRepository transactionEntityRepository;

    public JpaTransactionRepository(TransactionEntityRepository transactionEntityRepository) {
        this.transactionEntityRepository = transactionEntityRepository;
    }

    @Override
    public Transaction save(Transaction transaction) {
        var entity = TransactionEntity.from(transaction);
        return transactionEntityRepository.save(entity).toDomain();
    }

    @Override
    public List<Transaction> findAllByCategory(Category category) {
        return List.of();
    }
}
