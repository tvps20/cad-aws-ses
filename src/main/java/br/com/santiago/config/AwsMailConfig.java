package br.com.santiago.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.aws.mail.simplemail.SimpleEmailServiceJavaMailSender;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClientBuilder;

@Configuration
public class AwsMailConfig {

	@Autowired
	private CustomPropertyConfig customPropertyConfig;

	public AwsMailConfig() {

	}

	@Bean
	public AmazonSimpleEmailService amazonSimpleEmailService() {

		return AmazonSimpleEmailServiceClientBuilder.standard()
				.withCredentials(new AWSStaticCredentialsProvider(
						new BasicAWSCredentials(customPropertyConfig.awsAccessKey, customPropertyConfig.awsSecretKey)))
				.withRegion(Regions.SA_EAST_1).build();
	}

	@Bean
	public JavaMailSender javaMailSender(AmazonSimpleEmailService amazonSimpleEmailService) {
		return new SimpleEmailServiceJavaMailSender(amazonSimpleEmailService);
	}
}
