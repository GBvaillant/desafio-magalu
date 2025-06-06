package com.example.magalums.infra;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sqs.SqsAsyncClient;
import software.amazon.awssdk.services.sqs.SqsClient;

import java.net.URI;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;

@Configuration
public class SqsConfig {

    @Value("${aws.endpoint.localstack}")
    private String endpointLocalstack;

//    @Value("${spring.cloud.aws.credentials.access-key}")
//    private String accessKey;
//
//    @Value("${spring.cloud.aws.credentials.secret-key}")
//    private String secretKey;

    @Bean
    public SqsAsyncClient sqsClient() {
       return SqsAsyncClient.builder()
                .endpointOverride(URI.create(endpointLocalstack))
                .region(Region.US_EAST_1)
                .build();
    }
}

