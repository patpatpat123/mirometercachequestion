package com.example.micrometercache.modulithB;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.JdkClientHttpRequestFactory;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
class CustomerClientConfiguration {

    @Bean
    RestClient customerRestClient(RestClient.Builder restClientBuilder) {
        JdkClientHttpRequestFactory jdkClientHttpRequestFactory = new JdkClientHttpRequestFactory();
        return restClientBuilder.requestFactory(jdkClientHttpRequestFactory).baseUrl("http://localhost:8081").build();
    }

    @Bean
    CustomerExchangeInterface customerExchangeInterface(RestClient customerRestClient) {
        final RestClientAdapter adapter = RestClientAdapter.create(customerRestClient);
        return HttpServiceProxyFactory.builderFor(adapter).build().createClient(CustomerExchangeInterface.class);
    }

}
