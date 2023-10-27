package com.kernel360.boogle.book.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookRequest {
    private String ttbkey;
    private String queryType;
    private String searchTarget;
    private String subSearchTarget;
    private int start;
    private int maxResults;
    private int categoryId;
    private String output;
}
