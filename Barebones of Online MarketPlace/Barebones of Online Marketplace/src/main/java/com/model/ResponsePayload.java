package com.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.ArrayList;
import java.util.List;

public class ResponsePayload {

    public enum RESPONSE_STATUS {SUCCESS, FAILURE};
    private RESPONSE_STATUS response_status;
    private String responseMesg;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<Object> responseDetails = new ArrayList<>();

    public ResponsePayload(){

    }

    public ResponsePayload(RESPONSE_STATUS response_status, String responseMesg){
        this.response_status = response_status;
        this.responseMesg = responseMesg;
    }

    public RESPONSE_STATUS getResponse_status() {
        return response_status;
    }

    public void setResponse_status(RESPONSE_STATUS response_status) {
        this.response_status = response_status;
    }

    public String getResponseMesg() {
        return responseMesg;
    }

    public void setResponseMesg(String responseMesg) {
        this.responseMesg = responseMesg;
    }

    public List<Object> getResponseDetails() {
        return responseDetails;
    }

    public void setResponseDetails(List<Object> responseDetails) {
        this.responseDetails = responseDetails;
    }
}
