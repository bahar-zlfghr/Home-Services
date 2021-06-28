package ir.maktab.dtos.factory;

import ir.maktab.dtos.AddressDto;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

/**
 * @author : Bahar Zolfaghari
 **/
public interface AddressFactory {

    static AddressDto getAddress(String latitude, String longitude) throws IOException, ParseException {
        StringBuilder address = new StringBuilder();
        URL url = new URL("https://api.neshan.org/v2/reverse?lat=" + latitude + "&lng=" + longitude);
        HttpURLConnection conn = (HttpURLConnection)url.openConnection();
        conn.setRequestMethod("GET");
        conn.addRequestProperty("api-key", "service.cPDC4Tri7dDZsDZhoqg2VfUFK4oJ8AsuYYLM1DOd");
        conn.connect();
        int responseCode = conn.getResponseCode();
        if(responseCode != 200) {
            throw new RuntimeException("HttpResponseCode: " + responseCode);
        }
        else {
            Scanner sc = new Scanner(conn.getInputStream());
            while(sc.hasNext()) {
                address.append(sc.nextLine());
            }
            sc.close();
        }
        JSONParser parse = new JSONParser();
        JSONObject jobj = (JSONObject)parse.parse(address.toString());
        String state = (String) jobj.get("state");
        String city = (String) jobj.get("city");
        String formattedAddress = (String) jobj.get("formatted_address");
        conn.disconnect();
        return new AddressDto()
                .setState(state)
                .setCity(city)
                .setFormattedAddress(formattedAddress);
    }
}
