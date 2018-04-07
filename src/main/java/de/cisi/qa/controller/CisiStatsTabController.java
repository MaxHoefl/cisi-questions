package de.cisi.qa.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import de.cisi.qa.service.CisiQuestionService;
import de.felixroske.jfxsupport.FXMLController;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.TextField;

@FXMLController
public class CisiStatsTabController 
{
	private static final Logger LOG = LoggerFactory.getLogger(CisiStatsTabController.class);
	
	@FXML private TextField numTrainQuestions;
	@FXML private TextField numTestQuestions;
	@FXML private PieChart chapterQuestionDistribution;
	
	@Autowired CisiQuestionService service;
	
	public void incrementNumTrainQuestions()
	{
		numTrainQuestions.setText(Integer.toString(Integer.parseInt(numTrainQuestions.getText())+1));
	}
	
	public void incrementNumTestQuestions()
	{
		numTrainQuestions.setText(Integer.toString(Integer.parseInt(numTestQuestions.getText())+1));
	}
	
	public void updateTrainQuestionDistribution()
	{
		chapterQuestionDistribution.setData(service.getChatperQuestionCount());
	}
}
