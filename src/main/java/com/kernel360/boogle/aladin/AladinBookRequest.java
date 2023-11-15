package com.kernel360.boogle.aladin;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AladinBookRequest {
    private String QueryType;
    private String SearchTarget;
    private String Start;
    private String MaxResults;
    private String CategoryId;
}
