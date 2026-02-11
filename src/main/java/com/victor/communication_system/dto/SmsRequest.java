package com.victor.communication_system.dto;

import com.fasterxml.jackson.annotation.JsonProperty;


public class SmsRequest {
     String sender;
     int ddd;
     String phoneNumber;
     String message;

    public SmsRequest(@JsonProperty("sender") String sender,
                      @JsonProperty("ddd") int ddd,
                      @JsonProperty("phoneNumber") String phoneNumber,
                      @JsonProperty("message") String message) {
        this.sender = sender;
        this.ddd = ddd;
        this.phoneNumber = phoneNumber;
        this.message = message;
    }

    public String getSender() {
        return sender;
    }

    public int getDdd() {
        return ddd;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getMessage() {
        return message;
    }
}
