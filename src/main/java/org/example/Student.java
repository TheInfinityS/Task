package org.example;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(of = {"name","phone","github_url"})
public class Student {
    private  String name;
    private String phone;
    private String github_url;
}
