package de.cisi.qa.resource;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

@Document(collection = "cisiquestions")
public class CisiQuestion {
	@Id @NonNull @Setter private Integer id;
	@NonNull @Setter private String text;
	@Setter  private int chapter;
	@Setter  private int section;
	@Setter  private int subSection;
	
	public CisiQuestion() {}
	
	public CisiQuestion(int id, String text, int chapter, int section, int subSection)
	{
		this.id = id;
		this.text = text;
		this.chapter = chapter;
		this.section = section;
		this.subSection = subSection;
	}

	public String getText() {
		return text;
	}

	public int getChapter() {
		return chapter;
	}

	public int getSection() {
		return section;
	}
	
	public int getSubSection() {
		return subSection;
	}

	@Override
	public String toString() {
		return chapter + "." + section + "." + subSection + ": " + text;
	}
	
	
}
