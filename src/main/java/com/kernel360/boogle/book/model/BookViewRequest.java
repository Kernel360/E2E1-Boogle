package com.kernel360.boogle.book.model;

import com.sun.istack.NotNull;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookViewRequest {

    @NotNull
    private Long id;
}
