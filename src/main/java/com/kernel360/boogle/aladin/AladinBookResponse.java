package com.kernel360.boogle.aladin;

import com.kernel360.boogle.book.db.BookEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AladinBookResponse {
    private String version;
    private String logo;
    private String title;
    private String link;
    private int totalResults;
    private int startIndex;
    private int itemsPerPage;
    private String query;
    private int searchCategoryId;
    private String searchCategoryName;
    private List<AladinBookItem> item;

    public List<BookEntity> toBookEntityList() {
        List<BookEntity> bookEntities = new ArrayList<>();
        item.forEach(e -> {
            bookEntities.add(new BookEntity(
                      null
                    , e.getTitle()
                    , e.getCategoryId()
                    , e.getCover()
                    , e.getAuthor()
                    , e.getPublisher()
                    , e.getIsbn13()
                    , e.getDescription()
                    , LocalDate.parse(e.getPubDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd"))
                    , e.getPriceStandard()
                    , "TEST_ADMIN"
                    , null
                    , null
                    , LocalDateTime.now()
                    , "N"
                    , null
            ));
        });

        return bookEntities;
    }
}
