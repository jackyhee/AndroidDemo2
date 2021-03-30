package com.shgbit.android.demo.http.tool;

import android.util.Base64;

import java.nio.charset.StandardCharsets;

public class GBUE1 {

    public static String encode(String data, String key) {
        String result = "";

        try {

            byte [] bData = data.getBytes("UTF-8");
            byte [] bKey = key.getBytes("UTF-8");

            for (int i = 0; i < bData.length; i++) {

                byte m = bData[i];

                for (int j = 0; j < bKey.length; j++) {

                    m ^= bKey[j];
                }

                bData[i] = m;

            }
            result = android.util.Base64.encodeToString(bData, android.util.Base64.DEFAULT);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public static String decode(String data, String key) {

        String result = "";

        try {

            byte [] bData = android.util.Base64.decode(data.getBytes("UTF-8"), Base64.DEFAULT);
            byte [] bKey = key.getBytes("UTF-8");

            for (int i = 0; i < bData.length; i++) {

                byte m = bData[i];

                for (int j = 0; j < bKey.length; j++) {

                    m ^= bKey[j];
                }

                bData[i] = m;

            }
            result = new String(bData, StandardCharsets.UTF_8);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }
}
