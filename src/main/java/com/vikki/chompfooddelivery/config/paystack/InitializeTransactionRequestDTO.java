package com.vikki.chompfooddelivery.config.paystack;

import com.vikki.chompfooddelivery.enums.Channels;
import com.vikki.chompfooddelivery.enums.PaystackBearer;
import lombok.*;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InitializeTransactionRequestDTO {
    private String amount;
    private String email;
    private String reference;
    private String callback_Url;
    private Integer invoice_limit;
    private List<Channels> channels;
    private String subAccount;
    private Integer transaction_charge;
    private PaystackBearer paystackBearer = PaystackBearer.ACOUNT;

}
