package org.example;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(of = {"country","city"})
@EqualsAndHashCode(of = {"country"})
public class Country {
    private String country;
    private String city;
}
