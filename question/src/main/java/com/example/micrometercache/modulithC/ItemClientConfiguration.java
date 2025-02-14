package com.example.micrometercache.modulithC;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.JdkClientHttpRequestFactory;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
class ItemClientConfiguration {

    @Bean
    RestClient itemRestClient(RestClient.Builder restClientBuilder) {
        JdkClientHttpRequestFactory jdkClientHttpRequestFactory = new JdkClientHttpRequestFactory();
        return restClientBuilder.requestFactory(jdkClientHttpRequestFactory).baseUrl("http://localhost:8082").build();
    }

    @Bean
    ItemExchangeInterface itemExchangeInterface(RestClient itemRestClient) {
        final RestClientAdapter adapter = RestClientAdapter.create(itemRestClient);
        return HttpServiceProxyFactory.builderFor(adapter).build().createClient(ItemExchangeInterface.class);
    }

}
