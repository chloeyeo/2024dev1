package com.study.spring.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

@Configuration
public class AWSConfig {
	@Value("${cloud.aws.credentials.accessKey}") // @Value gets the iam access key from application.properties file
	private String iamAccessKey;

	@Value("${cloud.aws.credentials.secretKey}")
	private String iamSecretKey;

	@Value("${cloud.aws.region.static}")
	private String region;
	// can also write it as below
//	private String region = "cloud.aws.region.static";

	@Bean
	public AmazonS3Client amazonS3Client() {
		BasicAWSCredentials basicAWSCredentials = new BasicAWSCredentials(iamAccessKey, iamSecretKey);
		return (AmazonS3Client) AmazonS3ClientBuilder.standard().withRegion(region)
				.withCredentials(new AWSStaticCredentialsProvider(basicAWSCredentials)).build();
	}
}
