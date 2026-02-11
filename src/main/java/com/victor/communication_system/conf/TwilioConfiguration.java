package com.victor.communication_system.conf;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TwilioConfiguration {
    private final String accountSid;
    private final String authToken;
    private final String trialNumber;

    public TwilioConfiguration(@Value("${twilio.account_sid}") String accountSid,
                               @Value("${twilio.auth_token}") String authToken,
                               @Value("${twilio.trial_number}") String trialNumber) {
        this.accountSid = accountSid;
        this.authToken = authToken;
        this.trialNumber = trialNumber;
    }

    public String getAccountSid() {
        return accountSid;
    }

    public String getAuthToken() {
        return authToken;
    }

    public String getTrialNumber() {
        return trialNumber;
    }
}
