package com.vikki.chompfooddelivery.service.serviceimplementation;

import com.vikki.chompfooddelivery.config.paystack.InitializeTransactionRequestDTO;
import com.vikki.chompfooddelivery.config.paystack.InitializeTransactionResponseDTO;
import com.vikki.chompfooddelivery.service.InitializeTransactionService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class InitializeTransactionServiceImpl implements InitializeTransactionService {

    RestTemplate restTemplate;

    @Override
    public InitializeTransactionResponseDTO InitializeTransaction(InitializeTransactionRequestDTO initializeTransactionRequestDTO) {

        String url = "https://api.paystack.co/transaction/initialize";
        HttpHeaders headers = new org.springframework.http.HttpHeaders();

        String key = "sk_test_fecaa97ba88ff3039da2991f506250518d0c0307";

        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + key);

        HttpEntity<InitializeTransactionRequestDTO> entity = new HttpEntity<>
                (initializeTransactionRequestDTO, headers);

        ResponseEntity<InitializeTransactionResponseDTO> response = restTemplate.postForEntity(url, entity, InitializeTransactionResponseDTO.class);

        return response.getBody();
    }



}
