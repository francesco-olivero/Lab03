package it.polito.tdp.spellchecker;

import java.net.URL;
import java.util.*;
import java.util.ResourceBundle;

import it.polito.tdp.spellchecker.model.Dictionary;
import it.polito.tdp.spellchecker.model.RichWord;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class FXMLController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> comboBox;

    @FXML
    private TextArea txtInput;

    @FXML
    private Button btnSpellCheck;

    @FXML
    private TextArea txtOutput;

    @FXML
    private Label txtErrors;

    @FXML
    private Button btnClearText;

    @FXML
    private Label txtTime;
    
    private Dictionary dizionario;
    private String language;

    @FXML
    void chooseLanguage(ActionEvent event) {
    	language = comboBox.getValue();
    	dizionario.loadDictionary(language);
    }

    @FXML
    void doClearText(ActionEvent event) {
    	this.txtInput.clear();
    	this.txtOutput.clear();

    }

    @FXML
    void doSpellCheck(ActionEvent event) {
    	long da = System.nanoTime();
    	String input = this.txtInput.getText();
    	input=input.replaceAll("[^a-zA-Z \n]", ""); // ^ means not, cioé sostituisci tutto 
    										// quello che non è lettere, spazio o \n con ""
    	
    	ArrayList<String> parole = new ArrayList<String>();
    	List<RichWord> richwords;
    	String[] array = input.split("[ \n]"); // split se spazio o \n
    	for (String ss: array) {
    		parole.add(ss);
    	}
    	richwords = dizionario.spellCheckText(parole);
    	
    	for (RichWord rw: richwords) {
    		if (!rw.correct) {
    			this.txtOutput.appendText(rw.inputWord+"\n");
    		}
    	}
    	long a = System.nanoTime();
    	double time = (double)(a-da)/(1e9);
    	this.txtTime.setText("tempo di esecuzione: "+time);
    	this.txtErrors.setText("# errors: "+this.dizionario.countWrongWords);
    }
    
    public void setModel (Dictionary d) {
    	this.dizionario = d;
    	this.comboBox.getItems().addAll("English", "Italian");
    }

    @FXML
    void initialize() {
        assert comboBox != null : "fx:id=\"comboBox\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtInput != null : "fx:id=\"txtInput\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnSpellCheck != null : "fx:id=\"btnSpellCheck\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtOutput != null : "fx:id=\"txtOutput\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtErrors != null : "fx:id=\"txtErrors\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnClearText != null : "fx:id=\"btnClearText\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtTime != null : "fx:id=\"txtTime\" was not injected: check your FXML file 'Scene.fxml'.";

    }
}




