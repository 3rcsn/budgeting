package org.ercsn.budgeting.infrastructure.persistence.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.ercsn.budgeting.domain.Category;

import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
public class TransactionEntity {
    @Id
    private UUID id;
    private String description;
    private long amount;
    private Category category;
}
