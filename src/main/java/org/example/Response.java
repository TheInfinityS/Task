package org.example;

import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(of = {"student","result"})
public class Response {
    private Student student;
    private List<Result> result;
}
