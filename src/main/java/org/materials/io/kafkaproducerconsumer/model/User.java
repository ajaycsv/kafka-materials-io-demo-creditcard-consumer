package org.materials.io.kafkaproducerconsumer.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString
public class User {
    private String name;
    private String department;
    private long salary;
}