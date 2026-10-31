package org.ercsn.budgeting.application;

import org.ercsn.budgeting.application.output.TransactionOutput;
import org.ercsn.budgeting.domain.Category;
import org.ercsn.budgeting.domain.TransactionRepository;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListTransactionByCategoryUseCase {
    private final TransactionRepository transactionRepository;

    public ListTransactionByCategoryUseCase(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Tool(name = "list-transactions-by-category", description = "Lista transacoes financeiras por categoria")
    public List<TransactionOutput> execute(@ToolParam(description = "categoria de transacao") Category category) {
        return transactionRepository.findAllByCategory(category).stream().map(TransactionOutput::from).toList();
    }
}
