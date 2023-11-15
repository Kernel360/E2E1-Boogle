package com.kernel360.boogle.aladin;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AladinBookItem {
    private String title;
    private Long categoryId;
    private String cover;
    private String author;
    private String publisher;
    private Long isbn13;
    private String description;
    private String pubDate;
    private Integer priceStandard;
}
