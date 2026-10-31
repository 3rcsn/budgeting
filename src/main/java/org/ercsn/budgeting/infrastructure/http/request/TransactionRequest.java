package org.ercsn.budgeting.infrastructure.http.request;

import org.ercsn.budgeting.application.input.PersistTransactionInput;
import org.ercsn.budgeting.domain.Category;

public record TransactionRequest(String description, Category category, long amount) {
    public PersistTransactionInput toInput() {
        return new PersistTransactionInput(description(), amount, category);
    }
}
