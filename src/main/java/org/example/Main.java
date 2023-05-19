package org.example;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.core.util.JsonParserDelegate;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;

import java.io.IOException;
import java.util.*;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) throws IOException {
        OkHttpClient client=new OkHttpClient();

        ObjectMapper objectMapper=new ObjectMapper();

        String json="[\n" +
                "\n" +
                " {\n" +
                "\n" +
                "  \"country\":\"Kyrgyzstan\",\n" +
                "\n" +
                "  \"city\":\"Naryn\"\n" +
                "\n" +
                " },\n" +
                " {\n" +
                "\n" +
                "  \"country\":\"Italy\",\n" +
                "\n" +
                "  \"city\":\"Madrid\"\n" +
                "\n" +
                " },\n" +
                " {\n" +
                "\n" +
                "  \"country\":\"Kyrgyzstan\",\n" +
                "\n" +
                "  \"city\":\"Bishkek\"\n" +
                "\n" +
                " },\n" +
                "\n" +
                " {\n" +
                "\n" +
                "  \"country\":\"Kyrgyzstan\",\n" +
                "\n" +
                "  \"city\":\"Osh\"\n" +
                "\n" +
                " },\n" +
                "\n" +
                " {\n" +
                "\n" +
                "  \"country\":\"Italy\",\n" +
                "\n" +
                "  \"city\":\"MIlan\"\n" +
                "\n" +
                " }\n" +
                "\n" +
                "]";
        List<Country> countryList= objectMapper.readValue(json, new TypeReference<List<Country>>(){});
        countryList.sort(new Comparator<Country>() {
            @Override
            public int compare(Country o1, Country o2) {
                return o1.getCountry().compareTo(o2.getCountry());
            }
        });
        Response response=new Response();
        Student student=new Student("Samat","07053417868","https://github.com/TheInfinityS?tab=repositories");

        response.setStudent(student);
        List<Result> result=new ArrayList<>();
        System.out.println(countryList.get(1).equals(countryList.get(2)));
        for(int i=0;i<countryList.size();i++){
            Result r=new Result();
            r.setCountry(countryList.get(i).getCountry());
            List<String> cities=new ArrayList<>();
            int j=i;
            do{
                cities.add(countryList.get(j).getCity());
                j++;
            }
            while (j<=countryList.lastIndexOf(countryList.get(i)));
            r.setCities(cities);
            r.setCities_count(cities.size());
            result.add(r);
            i=j-1;
        }
        response.setResult(result);
        String body=objectMapper.writeValueAsString(response);
        System.out.println(body);

        MediaType JSON = MediaType.get("application/json; charset=utf-8");

        RequestBody requestBody = RequestBody.create(body, JSON);
        Request.Builder requestBuilder = new Request.Builder().url("https://procodeday-01.herokuapp.com/meet-up/post-request").post(requestBody);
        Request request = requestBuilder.build();
        System.out.println(request.body().toString());

        try (okhttp3.Response response1 = client.newCall(request).execute()) {
            if (!response1.isSuccessful()) {
                throw new IOException("Запрос к серверу не был успешен: " +
                        response1.code() + " " + response1.message());
            }
            System.out.println(response1.body().string());
        } catch (IOException e) {
            System.out.println("Ошибка подключения: " + e);
        }
    }
}