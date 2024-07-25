/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/EjbWebService.java to edit this template
 */
package org.tp.calculator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.ejb.Stateless;
import javax.net.ssl.HttpsURLConnection;
import org.json.JSONObject;
import static org.tp.calculator.API_Calling.Convert_Currency;
/**
 *
 * @author amyr
 */
@WebService(serviceName = "Calculator_WebService")
@Stateless()
public class Calculator_WebService {

    /// Data and Static variables
    
    static Object[][] exchangeData = {
            {"USD", 133.703, "DZD"},
            {"USD", 3.07433, "TND"},
            {"USD", 9.81594, "MAD"},
            {"USD", 3.07433, "TND"},
            {"USD", 0.78407, "GBP"},
            {"USD", 142.346, "JPY"},
            {"USD", 1.46239, "AUD"},
            {"USD", 1.32000, "CAD"},
            {"USD", 0.84962, "CHF"},
            {"USD", 0.90327, "EUR"},
            {"USD", 37.4303, "UAH"},
            {"USD", 15396.3, "IDR"},
            {"DZD", 0.02300, "TND"},
            {"DZD", 0.07300, "MAD"},
            {"DZD", 1.05000, "JPY"},
            {"DZD", 0.00590, "GBP"},
            {"DZD", 0.01100, "AUD"},
            {"DZD", 0.00990, "CAD"},
            {"DZD", 0.00630, "CHF"},
            {"DZD", 0.00670, "EUR"},
            {"DZD", 0.28000, "UAH"},
            {"DZD", 115.170, "IDR"},
            };
    
    static List<String> Currencies = Arrays.asList("AED","AFN","ALL","AMD","ANG","AOA","ARS","AUD","AWG","AZN","BAM",
                                            "BBD","BDT","BGN","BHD","BIF","BMD","BND","BOB","BOV","BRL","BSD","BTN",
                                            "BWP","BYN","BZD","CAD","CDF","CHF","CLF","CLP","CNY","COP","CRC","CUC",
                                            "CUP","CVE","CZK","DJF","DKK","DOP","DZD","EGP","ERN","ETB","EUR","FJD",
                                            "FKP","GBP","GEL","GHS","GIP","GMD","GNF","GTQ","GYD","HKD","HNL","HRK",
                                            "HTG","HUF","IDR","ILS","INR","IQD","IRR","ISK","JMD","JOD","JPY","KES",
                                            "KGS","KHR","KMF","KPW","KRW","KWD","KYD","KZT","LAK","LBP","LKR","LRD",
                                            "LSL","LTL","LVL","LYD","MAD","MDL","MGA","MKD","MMK","MNT","MOP","MRO",
                                            "MUR","MVR","MWK","MXN","MYR","MZN","NAD","NGN","NIO","NOK","NPR","NZD",
                                            "OMR","PAB","PEN","PGK","PHP","PKR","PLN","PYG","QAR","RON","RSD","RUB",
                                            "RWF","SAR","SBD","SCR","SDG","SEK","SGD","SHP","SLL","SOS","SRD","SSP",
                                            "STD","SVC","SYP","SZL","THB","TJS","TMT","TND","TOP","TRY","TTD","TWD",
                                            "TZS","UAH","UGX","USD","UYU","UZS","VEF","VND","VUV","WST","XAF","XCD",
                                            "XOF","XPF","YER","ZAR","ZMW","ZWL");

    static boolean isPrime(int num)
    {
        if(num<=1)
        {
            return false;
        }
        for(int i=2;i<=num/2;i++)
        {
            if((num%i)==0)
                return  false;
        }
        return true;
    }
    
    public static double[] solveQuadraticEquation(double a, double b, double c) {
        double discriminant = b * b - 4 * a * c;
        double[] roots;
        if (a == 0) {
            System.out.println("This is not a quadratic equation.");
            return null;
        } else if (discriminant > 0) {
            roots = new double[2];
            roots[0] = (-b + Math.sqrt(discriminant)) / (2 * a);
            roots[1] = (-b - Math.sqrt(discriminant)) / (2 * a);
        } else if (discriminant == 0) {
            roots = new double[1];
            roots[0] = -b / (2 * a);
        } else {
            roots = new double[2];
            roots[0] = (-b) / (2 * a);
            roots[1] = Math.sqrt(-discriminant) / (2 * a);
        }
        return roots;
    }
    
    public static double Convert_Currency(double Value1, String Currency1, String Currency2) {
        double exchangeRate = 0.0;
        // https://api.currencybeacon.com/v1/latest?api_key=hb29jMWeouxO8f10sTDeD7ssA1gnXhKz&base=DZD&symbols=EUR
        // Iterate through the array to find the exchange rate
        for (Object[] row : exchangeData) {
            String sourceCurrency = (String) row[0];
            double rate = (double) row[1];
            String targetCurrency = (String) row[2];
            if ((sourceCurrency.equals(Currency1) && targetCurrency.equals(Currency2)) ||
                (sourceCurrency.equals(Currency2) && targetCurrency.equals(Currency1))) {
                exchangeRate = (sourceCurrency.equals(Currency1)) ? rate : 1 / rate;
                break;
            }
        }
        // Perform the conversion
        //System.out.println(Currency1+ "  -"+exchangeRate+"->  " +Currency2);
        return (exchangeRate == 0.0) ? 0.0 : Value1 * exchangeRate;
    }
    
    public static List<String> Currency_List() {
        /*
        List<String> C_list = new ArrayList<>();
        for (Object[] row : exchangeData) {
            String sourceCurrency = (String) row[0];
            String targetCurrency = (String) row[2];
            
            if (!C_list.contains(sourceCurrency)) {
                C_list.add(sourceCurrency);
            } else if (!C_list.contains(targetCurrency)) {
                C_list.add(targetCurrency);
            }
        }
        */
        return Currencies;
    }
    
    /// Web Methods
    @WebMethod(operationName = "hello")
    public String hello(@WebParam(name = "name") String txt) {
        return "Hello " + txt + " !";
    }

    /**
     * 1. Sum of Two numbers
     */
    @WebMethod(operationName = "Sum")
    public int Sum(@WebParam(name = "Num_1") int Num_1, @WebParam(name = "Num_2") int Num_2) {
        return Num_1+Num_2;
    }

    /**
     * 2. Check if a number is Prime
     */
    @WebMethod(operationName = "Prime_Check")
    public boolean Prime_Check(@WebParam(name = "Num") int Num) {
        
        System.out.println("GET REQ "+Num);
        return isPrime(Num);
    }

    /**
     * 3. Second degree equation solver
     */
    @WebMethod(operationName = "SecondDegreEquation")
    public double[] SecondDegreEquation(@WebParam(name = "a") double a, @WebParam(name = "b") double b, @WebParam(name = "c") double c) {
        
        double[] roots = solveQuadraticEquation(a, b, c);
        return roots;
    }

    /**
     * 4. Currency Converter
     */
    @WebMethod(operationName = "CurrencyConverter")
    public double CurrencyConverter(@WebParam(name = "Value1") double Value1, 
                                    @WebParam(name = "Currency1") String Currency1, 
                                    @WebParam(name = "Currency2") String Currency2) throws IOException {
        System.out.println("in convertion method");
        double Converted_value = Convert_Currency(Value1,Currency1,Currency2);
        System.out.println(Converted_value);
        
        if (Converted_value == -1) {
            return -1.0;
        }
        
        return Convert_Currency(Value1, Currency1, Currency2);
    }

    /**
     * 4.1 Currency List
     */
    @WebMethod(operationName = "Currencies")
    public List<String> Currencies() {
        return Currencies;
    }

}
