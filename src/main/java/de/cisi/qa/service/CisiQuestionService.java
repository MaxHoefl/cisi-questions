package de.cisi.qa.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.cisi.qa.dao.CisiQuestionDao;
import de.cisi.qa.resource.CisiQuestion;

@Service
public class CisiQuestionService 
{
	private static final Logger LOG = LoggerFactory.getLogger(CisiQuestionService.class);
	
	@Autowired private CisiQuestionDao dao;
	
	public void save(final CisiQuestion question)
	{
		try
		{
			dao.save(question);
		}
		catch(Exception e)
		{
			LOG.error("Cannot save question {}", question);
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
}
