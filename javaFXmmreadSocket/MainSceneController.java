package javaFXmmreadSocket;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;


import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;

public class MainSceneController {
	
	
	Logger logger = Logger.getLogger("mainSceneController");
	mm_socketListener mm_socketListener = new mm_socketListener();
	Timer timer = new Timer();
	static Integer i = 0;
	static Integer lastCount=0;

	@FXML
	public  Button mainButton;
	@FXML
	public  Label label1;
	@FXML
	private Slider slider;
	@FXML
	private TextArea outputTextArea;
	@FXML
	private Button button;
	
	
	
	/**
	 * Initializes the controller class. This method is automatically called
	 * after the fxml file has been loaded.
	 */
	@SuppressWarnings("unchecked")
	@FXML
	private void initialize() {
		BasicConfigurator.configure();
		mm_socketListener.distance_mmProperty().addListener(new ChangeListener(){
	        @Override public void changed(ObservableValue o,Object oldVal, 
	                 Object newVal){
	        	logger.debug(newVal.toString()+"new value just came in");
	             outputTextArea.appendText("Slider Value Changed (newValue: " + newVal.toString() + ")\n");
	        }
	      });
		// Listen for Slider value changes
		slider.valueProperty().addListener(new ChangeListener<Number>() {
			@Override
		    public void changed(ObservableValue<? extends Number> observable,
		            Number oldValue, Number newValue) {
		        
		        outputTextArea.appendText("Slider Value Changed (newValue: " + newValue.intValue() + ")\n");
		    }
		});
	}//close constructor


	public void buttonClickedMainButton() {
		mm_socketListener.start(); //initialise the socket listener module
		
		
		logger.debug("Open Socket Button clicked- inside method- buttonClickedMainButton()");
		
	}// close button clicked event


	public  void setLabel(int mm) {	
	Integer mm4= Integer.valueOf(mm);
	String mm5 = mm4.toString();
	
		try {
			;//label1.setText(mm5);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}//clodse set label
	@FXML
	private void handleButtonAction() {
	  outputTextArea.appendText("Button Action\n");
	}

}// close class
