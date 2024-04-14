package com.example.codebase.client;

import com.example.codebase.configuration.FeignClientConfiguration;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * Codebase Client.
 */
@FeignClient(value = "${app.client.name}",
        url = "${app.client.url}",
        configuration = FeignClientConfiguration.class)
public interface CodebaseClient {
}
