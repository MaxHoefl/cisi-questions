package de.cisi.qa.resource;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Document(collection="cisiquestioncounters")
@NoArgsConstructor
public class CisiQuestionCounter {
	@Id @Setter private String id;
	@Setter private int seq;
	
	public String getId() {
		return id;
	}
	public int getSeq() {
		return seq;
	}
}
