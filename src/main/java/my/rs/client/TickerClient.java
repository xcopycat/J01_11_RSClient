package my.rs.client;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class TickerClient {
    public static void main(String[] args) {
        String address = "http://yand.dyndns.org/api/tickers.aspx";
        try {
            URL url = new URL(address);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
    //        conn.getInputStream();
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(
                    conn.getInputStream()
                 )
            );
            String tickers_text = "";
            while (reader.ready()){
                String line = reader.readLine();
             //   System.out.println(line);
                tickers_text +=line;
            }

            reader.close();
            Gson  gson = new Gson();
            Ticker[] tickers =  gson.fromJson(tickers_text,Ticker[].class);
            for(Ticker t: tickers){
                System.out.println("Ценная бумага " + t.ticker + " " + "текущий курс " + t.value);
            }
            //System.out.println(tickers_text);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
