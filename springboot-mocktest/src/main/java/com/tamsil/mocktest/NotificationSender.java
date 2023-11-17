package com.tamsil.mocktest;

import com.tamsil.mocktest.entity.Reservation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class NotificationSender {

    public <T> String sendMessage(String queueName, T message) {
        String result;
        Reservation reservationMessage = (Reservation) message;
        if (message != null) {
            log.info("{} - {} 회원님의 {} 레슨예약이 완료되었습니다.", queueName, reservationMessage.getMember().getName(), reservationMessage.getReservationNumber());
            result = "SUCCESS";
        } else {
            result = "FAIL";
        }
        return result;
    }
}
