package dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SmsRequest {
    private String sender;
    private int ddd;
    private String phoneNumber;
    private String message;

    public SmsRequest(@JsonProperty("sender") String sender,
                      @JsonProperty("ddd") int ddd,
                      @JsonProperty("phoneNumber") String phoneNumber,
                      @JsonProperty("message") String message) {
        this.sender = sender;
        this.ddd = ddd;
        this.phoneNumber = phoneNumber;
        this.message = message;
    }
}
