package com.victor.communication_system.conf;

import com.twilio.Twilio;
import org.springframework.context.annotation.Configuration;

import java.util.logging.Logger;

@Configuration
public class SenderConfiguration {
    public static Logger logger = Logger.getLogger(SenderConfiguration.class.getName());

    public SenderConfiguration(TwilioConfiguration senderConfiguration) {
        Twilio.init(senderConfiguration.getAccountSid(), senderConfiguration.getAuthToken());

        logger.info("Account Sid: " + senderConfiguration.getAccountSid() +
                " Auth token: " + senderConfiguration.getAuthToken() +
                " Trial number: " + senderConfiguration.getTrialNumber());
    }
}
