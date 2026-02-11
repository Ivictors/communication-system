package com.victor.communication_system.service;

import com.victor.communication_system.dto.SmsRequest;
import org.springframework.stereotype.Component;


public interface ISmsSender {
    void sendSms (SmsRequest smsRequest);
}
