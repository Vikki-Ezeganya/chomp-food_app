package com.vikki.chompfooddelivery.dto.response;

public class OperationStatusModel {

    private String operationName;
    private String operationResult;


    public String getOperationName() {
        return operationName;
    }

    public void setOperationName(String operationName) {
        this.operationName = operationName;
    }

    public void setOperationResult(String operationResult) {
        this.operationResult = operationResult;
    }

    public String getOperationResult() {
        return operationResult;
    }

}
