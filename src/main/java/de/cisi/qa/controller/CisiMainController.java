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
public class CisiMainController 
{
	private static final Logger LOG = LoggerFactory.getLogger(CisiMainController.class);
	
	@Autowired private CisiQuestionService questionService;
	@Autowired private CisiTrainTabController trainController;
	@Autowired private CisiTestTabController testController;
	
	
}
