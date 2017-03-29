package sk.mimacom.neo4j.tutorial.javaclient;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@ComponentScan("sk.mimacom.neo4j.tutorial.javaclient")
@EnableNeo4jRepositories("sk.mimacom.neo4j.tutorial.javaclient.repository")
public class Application {

	@Value("${spring.data.neo4j.uri}")
	private String neo4jUri;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
