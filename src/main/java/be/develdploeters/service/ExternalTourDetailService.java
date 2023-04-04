package be.develdploeters.service;

import be.develdploeters.service.dto.ActivityDTO;
import be.develdploeters.service.dto.TourDTO;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class ExternalTourDetailService {

    public List<ActivityDTO> getTourDataFromDate(String date) {
        List<String> urls = getUrlsForTours(date);
        List<List<String>> pages = readUrls(urls);

        return createTourDtos(pages);
    }

    private List<String> getUrlsForTours(String date) {
        List<String> urls = new ArrayList<>();

        String url = "https://www.mountainbike.be/toertochten-overzicht?provincie=Alles&datumVan=" + date + "&datumTot=" + date;

        try {
            Document doc = Jsoup.connect(url).get();
            Elements links = doc.select("a[href^=/toertochten/]");

            for (Element link : links) {
                urls.add("https://www.mountainbike.be" + link.attr("href").trim());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return urls;
    }

    private List<List<String>> readUrls(List<String> urls) {
        List<List<String>> pages = new ArrayList<>();
        urls.forEach(url -> pages.add(readUrl(url)));

        return pages;
    }

    private List<String> readUrl(String url) {
        List<String> page = new ArrayList<>();

        try {
            URL urlToertocht = new URL(url);
            URLConnection conn = urlToertocht.openConnection();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));

            String line;

            while ((line = bufferedReader.readLine()) != null) {
                page.add(line);
            }

            bufferedReader.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return page;
    }

    private List<ActivityDTO> createTourDtos(List<List<String>> htmlPages) {
        List<ActivityDTO> tourDtos = new ArrayList<>();
        htmlPages.forEach(page -> tourDtos.add(createTourDto(page)));

        return tourDtos;


    }

    private ActivityDTO createTourDto(List<String> htmlpage) {

        ActivityDTO dto = new ActivityDTO();

        String[] lines = new String[(htmlpage.size())];
        htmlpage.toArray(lines);

        for (int i = 0; i < htmlpage.size(); i++) {

            String line = lines[i];

            if (line.contains("<h1 class=\"font-primary-dark line-normal font-bigger\">")) {
                String title = stringBetweenTags(line);
                dto.setTitle(title);
            }

            if (line.contains("<div class=\"mrg-r-sml pad-t-tny border-solid border-top border-iron\">")) {
                if( lines[i+1].contains("Startlocatie")) {

                    String location = lines[i+4].trim().replaceAll("<br />", "");
                    String streetNumber = lines[i+5].trim().replaceAll("<br/>", "");
                    String[] streetNumberArray = streetNumber.split(" ");
                    String street = "";
                    String number = "";

                    for (int j = 0; j < streetNumberArray.length; j++) {
                        if (j == streetNumberArray.length - 1 && streetNumberArray.length > 1 && isNumber(streetNumberArray[j])) {

                            number = streetNumberArray[j];
                        } else {
                            street += " " + streetNumberArray[j];
                        }
                    }

                    String zipCity = lines[i+6].trim().replaceAll("<br />", "");
                    List<String> zipCity2 = Arrays.asList(zipCity.split(" "));

                    String zip = zipCity2.get(0);
                    String city = zipCity.replaceAll(zip, "");

                    dto.setLocation(location);
                    dto.setStreet(street.trim());
                    dto.setNumber(number);
                    dto.setZip(zip);
                    dto.setCity(city);
                }
            }

//            if (line.contains("<span class=\"uppercase font-base font-smler\"><strong>Organisatie</strong></span>")) {
//                String organisation = lines[i+3].trim().replaceAll("<br />", "");
//                String email = lines[i+4].trim().replaceAll("<a href=\"mailto:", "").replaceAll("\">", "");
//                String website = lines[i+7].trim().replaceAll("<a href=\"", "").replaceAll("\" target=\"_blank\">", "");
//
//                dto.setName(organisation);
//
//                if (!(email.equals("<br/>") || email.equals("<br />"))) {
//                    dto.setEmail(email);
//                }
//
//                dto.setWebsite(website);
//            }
        }

        return dto;
    }

    private String stringBetweenTags(String line) {

        Pattern p = Pattern.compile("\\>(.*?)\\<");
        Matcher m = p.matcher(line);
        if (m.find()) {
            return m.group(1);
        }

        return "";
    }

    private boolean isNumber(String number) {
        try {
            Integer.parseInt(number);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
