package com.victor.communication_system.service;

import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import com.victor.communication_system.conf.TwilioConfiguration;
import com.victor.communication_system.dto.SmsRequest;
import org.springframework.stereotype.Service;

@Service
public class TwilioSmsSenderService implements ISmsSender {

    private final TwilioConfiguration twilioConfiguration;

    public TwilioSmsSenderService(TwilioConfiguration twilioConfiguration) {
        this.twilioConfiguration = twilioConfiguration;
    }

    @Override
    public void sendSms(SmsRequest smsRequest) {
        String from = "From: " + smsRequest.getSender() + "\n";
        Message message = Message
                .creator(
                        new PhoneNumber("+55" + smsRequest.getDdd() + smsRequest.getPhoneNumber()),
                        new PhoneNumber(twilioConfiguration.getTrialNumber()),
                        from + smsRequest.getMessage()
                )
                .create();
    }
}
