package com.vikki.chompfooddelivery.service;

import com.vikki.chompfooddelivery.config.paystack.InitializeTransactionRequestDTO;
import com.vikki.chompfooddelivery.config.paystack.InitializeTransactionResponseDTO;


public interface InitializeTransactionService {
    InitializeTransactionResponseDTO InitializeTransaction(InitializeTransactionRequestDTO initializeTransactionRequest);
}
