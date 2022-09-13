package org.dblab.auction_backend.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GradeDTO {
    
    private int grade_id;
    private int grade;
    private int cumulative_amount;
    private int reserve_amount;
}
