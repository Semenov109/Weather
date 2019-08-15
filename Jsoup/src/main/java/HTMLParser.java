import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;

public class HTMLParser{

    public static void main(String args[]) {

        String title;
        Document doc;
        try {
            doc = Jsoup.connect("https://www.gismeteo.ru/weather-sankt-peterburg-4079/10-days/").get();
            title = doc.title();
            System.out.println();
            System.out.println(title);

            //////
            ////// День недели, число
            Element widget__row_widget__row_date = doc.select("div[class=widget__row widget__row_date]").first();
            String date = widget__row_widget__row_date.text();
            System.out.println(date);
            //////
            ////// Погодное явление
            //Elements widget__row_widget__row_table_widget__row_icon = doc.select("div[class=widget__row widget__row_table widget__row_icon]");
            //for (Element row :  widget__row_widget__row_table_widget__row_icon ) {
            //    String rows = row.select("span[class=tooltip]").text();
             //   System.out.println(rows);
           // }
            //////
            ////// Температура
            Elements widget__chart_w_temperature_avg = doc.select("div[class=widget__chart w_temperature-avg]");
            for (Element temperature_c : widget__chart_w_temperature_avg ){
                String temperature = temperature_c.select("span[class=unit unit_temperature_c]").text();
                System.out.println(temperature);
            }
            //////
            ////// Ветер [м/c]
            Elements widget__row_widget__row_table_widget__row_wind = doc.select("div[class=widget__row widget__row_table widget__row_wind]");
            for (Element wind : widget__row_widget__row_table_widget__row_wind){
                String winds = wind.select("span[class=unit unit_wind_m_s]").text();
                String vector = wind.select("div[class=w_wind__direction gray]").text();
                System.out.println(winds);
                System.out.println(vector);
            }
            //////
            ////// Давление
            Elements js_pressure_pressureline_w_pressure = doc.select("div[class=maxt]");
            for (Element pressure : js_pressure_pressureline_w_pressure){
                String max_pressure = pressure.select("span[class=unit unit_pressure_mm_hg_atm]").text();
                System.out.print(max_pressure+" ");
            }
            System.out.println();
            //////
            //////
            Elements mint = doc.select("div[class=mint]");
            for (Element pressure : mint){
                String min_pressure = pressure.select("span[class=unit unit_pressure_mm_hg_atm]").text();
                System.out.print(min_pressure+" ");
            }
            //////
            //////
            System.out.println();
            //////
            ////// Влажность
            Elements row_humidity = doc.select("div[class=widget__row widget__row_table widget__row_humidity]");
            String vlaz = row_humidity.text();
            System.out.print(vlaz);
            //////
            //////
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}