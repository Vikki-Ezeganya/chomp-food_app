package com.vikki.chompfooddelivery.config.paystack;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InitializeTransactionResponseDTO {

    private String status;
    private String message;
    private String Data;

}
