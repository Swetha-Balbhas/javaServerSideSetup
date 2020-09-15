package com.om.springboot.service.application;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Service
public class ExchangeRate {
    // private static final String USER_AGENT = "Mozilla/5.0";


    @Async
    public String getExchangeRate() {
        String value = sendGET();
        System.out.println("GET DONE");
        return value;
    }

    private static String sendGET() {
        try {
            final String GET_URL = "https://api.exchangeratesapi.io/latest?base=USD";
            URL obj = new URL(GET_URL);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("GET");
            //con.setRequestProperty("User-Agent", USER_AGENT);
            int responseCode = con.getResponseCode();
            System.out.println("GET Response Code :: " + responseCode);
            if (responseCode == HttpURLConnection.HTTP_OK) { // success
                BufferedReader in = new BufferedReader(new InputStreamReader(
                        con.getInputStream()));
                String inputLine;
                StringBuffer response = new StringBuffer();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();
                System.out.println(response.toString());

                return response.toString();
                // print result
            } else {
                System.out.println("GET request not worked");
            }

        } catch (IOException ioe) {
            System.err.println("Expection when getting Current Rate");
        }
        return "unavailable";
    }
}