package com.tamsil.mocktest.util;

import java.util.Optional;
import java.util.UUID;

public class ApplicationUtil {

    public static String generateId() {
        return UUID.randomUUID().toString();
    }

    public static String getReservationId(String studentName, String teacherName) {
        return String.join("_", studentName, teacherName);
    }

    public static String getProfile() {
        String activeProfile = System.getProperty("spring.profiles.active");
        return Optional.ofNullable(activeProfile).orElse("default");
    }

    public static String getName() {
        return "tamsil";
    }
}
