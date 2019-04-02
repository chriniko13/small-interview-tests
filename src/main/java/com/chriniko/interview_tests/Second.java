package com.chriniko.interview_tests;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.Iterator;

public class Second {

    public static void main(String[] args) {
        getMovieTitles("Spiderman");

        System.out.println("~~~~~~~~~~~~~~~~~~~~~");

        getMovieTitles("Batman");
    }

    /*
     * Complete the function below.
     * Base url: https://jsonmock.hackerrank.com/api/movies/search/?Title=
     */
    static String[] getMovieTitles(String substr) {

        String movieResource = "https://jsonmock.hackerrank.com/api/movies/search/?Title=" + substr;
        String[] results = null;
        int resultsIdx = -1;

        try {
            URL url = new URL(movieResource);
            HttpURLConnection request = (HttpURLConnection) url.openConnection();
            request.setDoOutput(true);
            request.setRequestMethod("GET");

            request.connect();
            JsonParser jp = new JsonParser();
            JsonElement element = jp.parse(new InputStreamReader((InputStream) request.getContent()));
            JsonObject obj = element.getAsJsonObject();

            if (request.getResponseCode() == HttpURLConnection.HTTP_OK) {

                System.out.println(obj);

                int totalResults = obj.get("total").getAsInt();

                results = new String[totalResults];
                resultsIdx = 0;

                int totalPages = obj.get("total_pages").getAsInt();
                if (totalPages < 1) {
                    return new String[]{};
                }

                // Note: read first page...
                JsonArray data = obj.get("data").getAsJsonArray();
                Iterator<JsonElement> dataIterator = data.iterator();
                while (dataIterator.hasNext()) {

                    JsonObject movieEntry = dataIterator.next().getAsJsonObject();
                    String title = movieEntry.get("Title").deepCopy().getAsString();

                    //System.out.println(title);

                    results[resultsIdx++] = title;
                }

                // Note: read any additional page(s) if exist(s)
                String movieResourcePageUrl = movieResource + "&page=";
                for (int page = 2; page <= totalPages; page++) {

                    String urlToHit = movieResourcePageUrl + page;

                    url = new URL(urlToHit);
                    request = (HttpURLConnection) url.openConnection();
                    request.setDoOutput(true);
                    request.setRequestMethod("GET");

                    request.connect();
                    jp = new JsonParser();
                    element = jp.parse(new InputStreamReader((InputStream) request.getContent()));
                    obj = element.getAsJsonObject();


                    data = obj.get("data").getAsJsonArray();
                    dataIterator = data.iterator();
                    while (dataIterator.hasNext()) {

                        JsonObject movieEntry = dataIterator.next().getAsJsonObject();
                        String title = movieEntry.get("Title").deepCopy().getAsString();

                        //System.out.println(title);

                        results[resultsIdx++] = title;
                    }
                }

                System.out.println("Total records: " + results.length);
                System.out.println("Before sorting: " + Arrays.toString(results));

                Arrays.sort(results);

                System.out.println("After sorting: " + Arrays.toString(results));

                return results;
            }
            return new String[]{};
        } catch (Exception error) {
            error.printStackTrace();
            return new String[]{};
        }
    }

}
