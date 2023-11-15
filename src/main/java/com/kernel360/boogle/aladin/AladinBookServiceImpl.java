package com.kernel360.boogle.aladin;

import com.kernel360.boogle.book.db.BookEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import java.net.URI;
import java.util.List;

@Service
@Slf4j
@Transactional
public class AladinBookServiceImpl implements AladinBookService {

    private final AladinBookRepository aladinBookRepository;

    public AladinBookServiceImpl(AladinBookRepository aladinBookRepository) {
        this.aladinBookRepository = aladinBookRepository;
    }

    @Value("${aladin.api.ttb-key}")
    private String aladinApiKey;

    @Value("${aladin.api.output}")
    private String aladinApiOutput;

    @Value("${aladin.api.version}")
    private String aladinApiVersion;

    @Value("${aladin.api.book-list.url}")
    private String aladinBookListUrl;

    @Value("${aladin.api.book-detail.url}")
    private String aladinBookDetailUrl;

    @Override
    public void getAladinBook(AladinBookRequest request) {
        MultiValueMap<String, String> mvMap = getRequestMultiValueMap(request);

        URI uri = UriComponentsBuilder
                    .fromHttpUrl(aladinBookListUrl)
                    .queryParams(mvMap)
                    .build()
                    .encode()
                    .toUri();

        RestTemplate restTemplate = new RestTemplate();
        AladinBookResponse response = restTemplate.getForObject(uri.toString(), AladinBookResponse.class);

        List<BookEntity> books = response.toBookEntityList();
        System.out.println(books);

        aladinBookRepository.saveAll(books);
    }

    private MultiValueMap<String, String> getRequestMultiValueMap(AladinBookRequest request) {
        MultiValueMap<String, String> mvMap = new LinkedMultiValueMap<>();

        mvMap.add("TTBKey", aladinApiKey);
        mvMap.add("Output", aladinApiOutput);
        mvMap.add("Version", aladinApiVersion);
        mvMap.add("QueryType", request.getQueryType());
        mvMap.add("SearchTarget", request.getSearchTarget()); // Book, eBook
        mvMap.add("MaxResults", request.getMaxResults());
        mvMap.add("Start", request.getStart());
        mvMap.add("CategoryId", request.getCategoryId()); // 351, 38401

        return mvMap;
    }
}
