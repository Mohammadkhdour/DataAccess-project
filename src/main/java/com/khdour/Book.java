package com.khdour;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@NoArgsConstructor
@Data
@AllArgsConstructor
public class Book {
    private String title;
    private int isbn;
    private String language;
    private String author;
}
