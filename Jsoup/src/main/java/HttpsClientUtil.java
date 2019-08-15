import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

/**
 * <b>Get the Html source from the secure url </b>
 */
public class HttpsClientUtil {
    public static void main(String[] args) throws Exception {
        String httpsURL = "https://www.gismeteo.ru/weather-sankt-peterburg-4079/10-days/";
        String FILENAME = "login.html";
        BufferedWriter bw = new BufferedWriter(new FileWriter(FILENAME));
        URL myurl = new URL(httpsURL);
        HttpsURLConnection con = (HttpsURLConnection) myurl.openConnection();
        con.setRequestProperty ( "User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:63.0) Gecko/20100101 Firefox/63.0" );
        InputStream ins = con.getInputStream();
        InputStreamReader isr = new InputStreamReader(ins, "Windows-1252");
        BufferedReader in = new BufferedReader(isr);
        String inputLine;

        // Write each line into the file
        while ((inputLine = in.readLine()) != null) {
            System.out.println(inputLine);
            bw.write(inputLine);
        }
        in.close();
        bw.close();
    }
}