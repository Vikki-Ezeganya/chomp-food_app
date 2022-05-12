package com.vikki.chompfooddelivery.controller;


import com.vikki.chompfooddelivery.dto.AddressDto;
import com.vikki.chompfooddelivery.dto.request.AddressDetailsRequest;
import com.vikki.chompfooddelivery.dto.response.AddressDetailsResponse;
import com.vikki.chompfooddelivery.dto.response.OperationStatusModel;
import com.vikki.chompfooddelivery.dto.response.RequestOperationName;
import com.vikki.chompfooddelivery.dto.response.RequestOperationStatus;
import com.vikki.chompfooddelivery.service.AddressDetailsService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("address")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class AddressController {

    AddressDetailsService addressDetailsService;

    @PostMapping
    public AddressDetailsResponse createAddress(@RequestBody AddressDetailsRequest addressDetailsRequest) {
        ModelMapper modelMapper = new ModelMapper();
        AddressDto addressDto = modelMapper.map(addressDetailsRequest, AddressDto.class);

        AddressDto savedAddress = addressDetailsService.createAddress(addressDto);

        return modelMapper.map(savedAddress, AddressDetailsResponse.class);
    }

    @GetMapping(path = "/{userId}")
    public ResponseEntity<AddressDetailsResponse> getAddress(@PathVariable Long userId) {
        var address = addressDetailsService.getAddress(userId);

        ModelMapper modelMapper = new ModelMapper();
        AddressDetailsResponse response = modelMapper.map(address, AddressDetailsResponse.class);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping(path="/{addressId}")
    public AddressDetailsResponse changeAddress(@PathVariable String addressId,
                                                @RequestBody AddressDetailsRequest addressDetailsRequest) {
        ModelMapper modelMapper = new ModelMapper();
        AddressDto addressDto = modelMapper.map(addressDetailsRequest, AddressDto.class);

        AddressDto editedAddress = addressDetailsService.editAddress(addressId, addressDto);

        return modelMapper.map(editedAddress, AddressDetailsResponse.class);
    }

    @DeleteMapping(path="/{addressId}")
    public OperationStatusModel deleteAddress(@PathVariable String addressId) {
        OperationStatusModel operationStatusModel = new OperationStatusModel();
        operationStatusModel.setOperationName(RequestOperationName.DELETE.name());
        operationStatusModel.setOperationResult(RequestOperationStatus.SUCCESS.name());

        addressDetailsService.deleteAddress(addressId);

        return operationStatusModel;
    }


}
