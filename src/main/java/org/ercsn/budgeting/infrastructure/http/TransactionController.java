package org.ercsn.budgeting.infrastructure.http;

import org.ercsn.budgeting.application.PersistTransactionUseCase;
import org.ercsn.budgeting.infrastructure.http.request.TransactionRequest;
import org.ercsn.budgeting.infrastructure.http.response.TransactionResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transactions")
public class TransactionController {
    private final PersistTransactionUseCase persistTransactionUseCase;

    public TransactionController(PersistTransactionUseCase persistTransactionUseCase) {
        this.persistTransactionUseCase = persistTransactionUseCase;
    }

    @PostMapping
    public TransactionResponse createTransaction(@RequestBody TransactionRequest request) {
        var transaction = persistTransactionUseCase.execute(request.toInput());
        return persistTransactionUseCase.from(transaction);
    }
}
