package org.ercsn.budgeting.application.input;

import org.ercsn.budgeting.domain.Category;

public record PersistTransactionInput(String description, long amount, Category category) {

}
