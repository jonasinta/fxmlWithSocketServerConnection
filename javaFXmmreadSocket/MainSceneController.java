package javaFXmmreadSocket;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;



import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;

public class MainSceneController {
	
	
	Logger logger = Logger.getLogger("mainSceneController");
	mm_socketListener   mm_socketListener = new mm_socketListener();

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
	@FXML
	public  Label	Label2;
	
	
	
	/**
	 * Initializes the controller class. This method is automatically called
	 * after the fxml file has been loaded.
	 */
	@FXML
	private void initialize() {
		BasicConfigurator.configure();
		mm_socketListener.distance_mmProperty().addListener(new ChangeListener<Object>(){
	        @Override public void changed(ObservableValue<?> o,Object oldVal, 
	                 Object newVal){
	        	logger.debug(newVal.toString()+"new value just came in");
	             outputTextArea.appendText("Slider Value Changed (newValue:  \n"+ newVal);
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


	public void buttonClicked() {
		logger.debug("line before L66 Open Socket Button clicked- inside method- buttonClickedMainButton()");
		mm_socketListener.start(); //initialise the socket listener module
		
		
		logger.debug("Open Socket Button clicked L70- inside method- buttonClickedMainButton()");
		
	}// close button clicked event


	
	@FXML
	private void handleButtonAction() {
	  outputTextArea.appendText("Button Action\n");
	}

}// close class
