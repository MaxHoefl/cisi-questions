package de.cisi.qa.dao;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import de.cisi.qa.resource.CisiQuestion;

public interface CisiQuestionDao extends MongoRepository<CisiQuestion, Long>
{
	public List<CisiQuestion> findAll();
	public List<CisiQuestion> findByChapter(int chapter);
	public List<CisiQuestion> findBySection(int section);
	public List<CisiQuestion> findByChapterAndSectionAndSubSection(Integer chapter, Integer section, Integer subsection);
}
