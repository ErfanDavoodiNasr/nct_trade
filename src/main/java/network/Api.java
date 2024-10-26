package network;


import exception.SymbolNotFoundException;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Api {
    public static double getForexPrice(String currencyPair) throws Exception {
        String url = "https://scanner.tradingview.com/symbol?symbol=FX_IDC:" + currencyPair + "&fields=close|60&no_404=true";
        HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
        conn.setRequestMethod("GET");

        try (BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
            StringBuilder content = new StringBuilder();
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }

            try {
                JSONObject jsonObject = new JSONObject(content.toString());
                if (!jsonObject.has("close|60")) {
                    throw new SymbolNotFoundException(currencyPair +": Invalid currency symbol or price data not available.");
                }
                return jsonObject.getDouble("close|60");
            } catch (JSONException e) {
                throw new SymbolNotFoundException(currencyPair +": Invalid currency symbol or price data not available.");
            }
        } catch (IOException e) {
            throw new SymbolNotFoundException(currencyPair +": Invalid currency symbol or price data not available.");
        } finally {
            conn.disconnect();
        }
    }
}

