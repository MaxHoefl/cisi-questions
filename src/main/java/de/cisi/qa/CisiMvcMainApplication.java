package de.cisi.qa;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import de.felixroske.jfxsupport.AbstractJavaFxApplicationSupport;

@SpringBootApplication
@EnableMongoRepositories(basePackages = "de.cisi.qa")
@EnableJpaRepositories(basePackages = "de.cisi.qa")
public class CisiMvcMainApplication extends AbstractJavaFxApplicationSupport{

    public static void main(String[] args) {
	        launch(CisiMvcMainApplication.class, CisiMainView.class, args);
    }
}
