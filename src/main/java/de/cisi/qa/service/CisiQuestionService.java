package de.cisi.qa.service;

import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import de.cisi.qa.dao.CisiQuestionDao;
import de.cisi.qa.resource.CisiQuestion;
import de.cisi.qa.resource.CisiQuestionCounter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart.Data;
import lombok.NonNull;

@Service
public class CisiQuestionService 
{
	private static final Logger LOG = LoggerFactory.getLogger(CisiQuestionService.class);
	
	@Autowired private CisiQuestionDao dao;
	
	@Autowired private MongoOperations mongo;
	
	@Value("${collection.name.cisi-train-question-collection}") private String cisiTrainQuestionCollection;
	
	public CisiQuestion onCisiQuestionSave(@NonNull final String questionText, @NonNull final String chapterSection)
	{
		LOG.info("chapterSection={}, chapterSection={}",questionText, chapterSection);
		String[] chapterSectionSplit = chapterSection.split("\\.");
		if(chapterSectionSplit.length < 2) 
		{throw new IllegalArgumentException("Pass chapter and section as <chapter>.<section>[.<subsection>]");}
		
		try
		{
			CisiQuestion cisiQuestion;
			try
			{
				int chapter = Integer.parseInt(chapterSectionSplit[0]);
				int section = Integer.parseInt(chapterSectionSplit[1]);
				int subSection = chapterSectionSplit.length > 2? Integer.parseInt(chapterSectionSplit[2]) : 0;
				
				cisiQuestion = new CisiQuestion(getNextQuestionId(), questionText, chapter, section, subSection);
			}
			catch(Exception e1)
			{
				LOG.error("Cannot instantiate cisi question from {},{}", questionText, chapterSectionSplit);
				throw e1;
			}
			
			return dao.save(cisiQuestion);
		}
		catch(Exception e)
		{
			LOG.error("Error in onCisiQuestionSave({}, {})", questionText, chapterSection);
			throw e;
		}
	}
	
	public void save(final CisiQuestion question)
	{
		try
		{
			dao.save(question);
		}
		catch(Exception e)
		{
			LOG.error("Cannot save cisi question {}", question);
			throw e;
		}
	}
	
	public List<CisiQuestion> getAll()
	{
		List<CisiQuestion> res = new ArrayList<>();
		try
		{
			dao.findAll().forEach(res::add);
			return res;
		}
		catch(Exception e)
		{
			LOG.error("Cannot retrieve questions");
			throw e;
		}
	}
	
	private int getNextQuestionId()
	{
		CisiQuestionCounter counter = mongo.findAndModify(
            query(where("_id").is(cisiTrainQuestionCollection)),
	            new Update().inc("seq",1),
	            options().returnNew(true).upsert(true),
	            CisiQuestionCounter.class);
	        return counter.getSeq();
	}

	public ObservableList<Data> getChatperQuestionCount() {
		List<CisiQuestion> questions = new ArrayList<>();
		dao.findAll().forEach(questions::add);
		
		ObservableList<Data> res = FXCollections.observableArrayList();
		Map<Integer, Integer> chapterQuestionCounts = new HashMap<>();
		questions.forEach(q -> {
			chapterQuestionCounts.merge(q.getChapter(), 1, (v,o) -> v.intValue()+1);
		});
		for(Map.Entry<Integer, Integer> e : chapterQuestionCounts.entrySet())
		{
			res.add(new Data(e.getKey().toString(), e.getValue()));
		}
		return res;
	}

	public CisiQuestion getRandomQuestion(Integer chapter, Integer section, Integer subsection) {
		List<CisiQuestion> questions = dao.findByChapterAndSectionAndSubSection(chapter, section, subsection);
		if(questions.isEmpty()) { return null; }
		
		Random rdmIdx = new Random();
		return questions.get(rdmIdx.nextInt(questions.size()));
	}
}
