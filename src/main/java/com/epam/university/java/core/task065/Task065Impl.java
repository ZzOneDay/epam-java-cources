package com.epam.university.java.core.task065;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.LinkedList;

public class Task065Impl implements Task065 {

    /**
     * WayTable.
     * This table has some params from HTML File.
     *
     * @param filepath path to HTML file.
     * @return loaded wayTable by HTML file.
     */
    public WayTable getWayTable(String filepath) {
        Document document = getDocumentFromFile(filepath);
        Elements table = document.select("#user_void > table > tbody:nth-child(3)");
        Elements elements = table.select("tr");

        int countWays = 0;
        LinkedList<Way> listWays = new LinkedList<>();
        for (Element element : elements) {
            String indexString = element.select("td").get(0).text();
            if (indexString.length() != 0) {
                countWays++;
                int index = Integer.parseInt(element.select("td").get(0).text());
                String date = element.select("td").get(2).text();
                double dist = Double.parseDouble(element.select("td").get(5).text());
                String comment = element.select("td").get(7).text();

                LocalDate localDate = getLocalDataFromString(date);
                listWays.add(new Way(index, dist, comment, localDate));
            }
        }


        double dist = 0.0;
        HashMap<LocalDate, Double> map = new HashMap<>();
        for (Way way : listWays) {
            dist += way.getDist();
            if (map.containsKey(way.getData())) {
                double sum = map.get(way.getData()) + way.getDist();
                map.put(way.getData(), sum);
            } else {
                map.put(way.getData(), way.getDist());
            }
        }

        int allDistance = (int) Math.round(dist);
        return new WayTableImpl(map, countWays, allDistance);
    }

    private Document getDocumentFromFile(String filepath) {
        try {
            File file = new File(getClass().getResource(filepath).toURI());
            System.out.println(file.getPath());
            return Jsoup.parse(file, "UTF-8", "http://example.com/");
        } catch (Exception e) {
            throw new IllegalArgumentException();
        }
    }

    private LocalDate getLocalDataFromString(String string) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yy");
        return LocalDate.parse(string, formatter);
    }
}
