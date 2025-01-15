package com.trips.busservice.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.security.SecureRandom;
import java.util.UUID;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Util {
    public static String generateRandomId(int length) {
        final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        SecureRandom random = new SecureRandom();
        StringBuilder randomId = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(CHARACTERS.length());
            randomId.append(CHARACTERS.charAt(randomIndex));
        }
        return randomId.toString();
    }

    public static String generateUuid() {
        return UUID.randomUUID().toString();
    }

    public static String generateRouteId(String source, String destination) {
        return source.toLowerCase() + "-" + destination.toLowerCase();
    }

    public static String generateBusId(String travelsName, String busName) {
        return (travelsName.toLowerCase() + "-" + busName.toLowerCase()).replaceAll(" ","_");
    }
}
