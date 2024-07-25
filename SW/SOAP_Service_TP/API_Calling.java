/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package org.tp.calculator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import org.json.JSONObject;

/**
 *
 * @author amyr
 */
public class API_Calling {

    /**
     * @param args the command line arguments
     */
    public static double Convert_Currency(double value, String currency1, String currency2) {
        String apiKey = "YOUR-API-KEY-HERE";
        try {
            URL url = new URL("https://api.currencybeacon.com/v1/latest?api_key=" + apiKey + "&base=" + currency1 + "&symbols=" + currency2);

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();
            if (responseCode == 200) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                JSONObject jsonResponse = new JSONObject(response.toString());
                JSONObject rates = jsonResponse.getJSONObject("response").getJSONObject("rates");
                double targetRate = rates.getDouble(currency2);

                return value * targetRate;
            } else {
                System.out.println("Failed to fetch data. Response code: " + responseCode);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1.0; // Return a negative value to indicate failure
    }


    public static void main(String[] args) throws ProtocolException, IOException {
        double result = Convert_Currency(55,"EUR","DZD");
        System.out.println("Converted value: " + result);
    }
    
}
