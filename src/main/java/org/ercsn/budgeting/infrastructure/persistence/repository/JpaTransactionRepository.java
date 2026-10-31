package org.ercsn.budgeting.infrastructure.persistence.repository;

import org.ercsn.budgeting.domain.Category;
import org.ercsn.budgeting.domain.Transaction;
import org.ercsn.budgeting.domain.TransactionRepository;
import org.ercsn.budgeting.infrastructure.persistence.entity.TransactionEntity;

import java.util.List;

public class JpaTransactionRepository implements TransactionRepository {

    @Override
    public Transaction save(Transaction transaction) {
        return null;
    }

    @Override
    public List<Transaction> findAllByCategory(Category category) {
        return List.of();
    }
}
