package com.khdour;

import lombok.*;

@NoArgsConstructor
@Data
@AllArgsConstructor
public class Book {
    private String title;
    @NonNull
    private int isbn;
    private String language;
    private String author;
    private String description;
}
