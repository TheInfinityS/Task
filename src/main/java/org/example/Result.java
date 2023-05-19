package org.example;

import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(of = {"country","cities","cities_count"})
public class Result {
    private String country;
    private List<String> cities;
    private Integer cities_count;
}
