package org.ercsn.budgeting.application;

import org.ercsn.budgeting.application.input.PersistTransactionInput;
import org.ercsn.budgeting.application.output.TransactionOutput;
import org.ercsn.budgeting.domain.Transaction;
import org.ercsn.budgeting.domain.TransactionRepository;
import org.springframework.stereotype.Service;

@Service
public class PersistTransactionUseCase {
    private final TransactionRepository transactionRepository;

    public PersistTransactionUseCase(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public TransactionOutput execute(PersistTransactionInput input) {
        var transaction = transactionRepository.save(
                new Transaction(input.description(), input.amount(), input.category())
        );

        return TransactionOutput.from(transaction);
    }
}
