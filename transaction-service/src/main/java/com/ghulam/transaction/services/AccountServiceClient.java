package com.ghulam.transaction.services;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Map;

@FeignClient(name = "account-service", url = "${account-service.url}")
public interface AccountServiceClient {

    String BASE_PATH = "/account/api/v1";

    @PostMapping(path = BASE_PATH + "/deductBalance")
    Map<String, String> deductBalance(Map<String, String> request);
}
