package com.ghostdovahkiin.librapi.purchaseservice.feign;

import com.ghostdovahkiin.librapi.purchaseservice.purchase.BookDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(contextId = "GetBook", url = "http://localhost:3332/v1/api/books", name = "book-service")
public interface GetBook {

    @GetMapping(value = "/id/{id}")
    BookDTO findBySpecificID(@PathVariable String id);
}
