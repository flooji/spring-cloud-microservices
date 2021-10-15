package com.training.microservices.notification;

import com.training.microservices.notification.models.BirthdayEvent;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Logger;

@RestController
public class NotificationController {
    private static final Logger LOG = Logger.getLogger(NotificationController.class.getName());

    @Value("${service.instance.name}")
    private String serviceInstance;

    @PostMapping(path = "/", consumes = "application/json", produces = "application/json")
    public String createNotification(@RequestBody BirthdayEvent event) {
        LOG.info("Birthday notification for: " + event.toString());

        return "Birthday notification created from " + serviceInstance;
    }
}
