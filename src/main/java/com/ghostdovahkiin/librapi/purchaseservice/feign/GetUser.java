package com.ghostdovahkiin.librapi.purchaseservice.feign;

import com.ghostdovahkiin.librapi.purchaseservice.purchase.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(contextId = "GetUser", url = "http://localhost:3331/v1/api/users", name = "user-service")
public interface GetUser {

    @GetMapping(value = "/id/{id}")
    UserDTO findBySpecificID(@PathVariable String id);
}
