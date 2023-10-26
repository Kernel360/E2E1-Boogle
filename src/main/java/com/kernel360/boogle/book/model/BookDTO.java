package com.kernel360.boogle.book.model;

import com.kernel360.boogle.book.db.BookEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookDTO {

    private BookEntity bookEntity;


}
