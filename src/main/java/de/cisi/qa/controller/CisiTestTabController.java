package de.cisi.qa.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import de.cisi.qa.resource.CisiQuestion;
import de.cisi.qa.service.CisiQuestionService;
import de.felixroske.jfxsupport.FXMLController;
import javafx.collections.FXCollections;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;

@FXMLController
public class CisiTestTabController 
{
	private static final Logger LOG = LoggerFactory.getLogger(CisiTestTabController.class);
	
	@Autowired private CisiQuestionService service;
	@Autowired private CisiStatsTabController statsController;
	
	@FXML private ChoiceBox<Integer> chapterFilter;
	@FXML private ChoiceBox<Integer> sectionFilter;
	@FXML private ChoiceBox<Integer> subsectionFilter;
	
	@FXML private TextArea giveQuestionOutBox;

	@FXML
	public void onGiveQuestion(final Event event) {
		if(chapterFilter == null || sectionFilter == null || subsectionFilter == null)
		{
			giveQuestionOutBox.setText("You have to save a question first!");
			return;
		}
		
		CisiQuestion question = service.getRandomQuestion(chapterFilter.getValue(), sectionFilter.getValue(), subsectionFilter.getValue());
		giveQuestionOutBox.setText(question.getChapter() + "." + question.getSection() + "." + question.getSubSection() + ": " + question.getText());
		statsController.incrementNumTestQuestions();
	}

	public void addChapterToFilter(int chapter) {
		LOG.info("chapterFilter is null? {}", chapterFilter == null);
		if(chapterFilter.getItems() == null) { chapterFilter.setItems(FXCollections.observableArrayList()); }
		if(!chapterFilter.getItems().contains(chapter)) { chapterFilter.getItems().add(chapter); }
	}
	
	public void addSectionToFilter(int section) {
		if(sectionFilter.getItems() == null) { sectionFilter.setItems(FXCollections.observableArrayList()); }
		if(!sectionFilter.getItems().contains(section)) { sectionFilter.getItems().add(section); }
	}
	
	public void addSubsectionToFilter(int subsection) {
		if(subsectionFilter.getItems() == null) { subsectionFilter.setItems(FXCollections.observableArrayList()); }
		if(!subsectionFilter.getItems().contains(subsection)) { subsectionFilter.getItems().add(subsection); }
	}
}
