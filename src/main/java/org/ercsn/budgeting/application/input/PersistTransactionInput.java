package org.ercsn.budgeting.application.input;

import org.ercsn.budgeting.domain.Category;
import org.springframework.ai.tool.annotation.ToolParam;

public record PersistTransactionInput(@ToolParam(description = "descricao da transacao") String description,
                                      @ToolParam(description = "valor da transacao em centavos") long amount,
                                      @ToolParam(description = "categoria da transacao") Category category) {

}
