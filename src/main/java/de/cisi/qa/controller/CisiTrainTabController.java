package de.cisi.qa.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import de.cisi.qa.resource.CisiQuestion;
import de.cisi.qa.service.CisiQuestionService;
import de.felixroske.jfxsupport.FXMLController;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

@FXMLController
public class CisiTrainTabController 
{
	private static final Logger LOG = LoggerFactory.getLogger(CisiTrainTabController.class);
	
	@Autowired private CisiQuestionService questionService;
	@Autowired private CisiStatsTabController statsController;
	@Autowired private CisiTestTabController testController;
	
	@FXML private TextArea trainQuestionInput;
	
	@FXML private TextField chapterSection;
	
	@FXML private ListView<String> latestTrainQuestions;

	@FXML
	public void onCisiQuestionSave(final Event event) 
	{
		LOG.info("trainQuestionInput.getText()={}, chapterSection.getText()={}",trainQuestionInput.getText(), chapterSection.getText());
		CisiQuestion question = questionService.onCisiQuestionSave(trainQuestionInput.getText(), chapterSection.getText());
		
		latestTrainQuestions.getItems().add(question.toString());
		
		statsController.incrementNumTrainQuestions();
		statsController.updateTrainQuestionDistribution();
		
		testController.addChapterToFilter(question.getChapter());
		testController.addSectionToFilter(question.getSection());
		testController.addSubsectionToFilter(question.getSubSection());
	}
}
