package com.AI.ML.AIandML.util;

import java.util.Random;

public class OtpUtil {

    public static String generateOtp() {

        Random random = new Random();

        int otp = 100000 + random.nextInt(900000);

        return String.valueOf(otp);
    }
}