package it.polito.tdp.metrodeparis;

import java.net.URL;
import java.util.ResourceBundle;

import it.polito.tdp.metrodeparis.model.Fermata;
import it.polito.tdp.metrodeparis.model.MetroDeParisModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;

public class MetroDeParisController {
	
	private MetroDeParisModel model;
	
	public void setModel(MetroDeParisModel mdp){
		model=mdp;
		model.caricaGrafo();
		cmbPartenza.getItems().addAll(model.getFermate());
        cmbArrivo.getItems().addAll(model.getFermate());
	}

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<Fermata> cmbPartenza;

    @FXML
    private ComboBox<Fermata> cmbArrivo;

    @FXML
    private Button btnCalcola;

    @FXML
    private TextArea txtOutput;

    @FXML
    void doCalcola(ActionEvent event) {
    	String s=model.camminoMinimo(cmbPartenza.getValue(), cmbArrivo.getValue());
    	txtOutput.setText(s);
    }

    @FXML
    void initialize() {
        assert cmbPartenza != null : "fx:id=\"cmbPartenza\" was not injected: check your FXML file 'MetroDeParis.fxml'.";
        assert cmbArrivo != null : "fx:id=\"cmbArrivo\" was not injected: check your FXML file 'MetroDeParis.fxml'.";
        assert btnCalcola != null : "fx:id=\"btnCalcola\" was not injected: check your FXML file 'MetroDeParis.fxml'.";
        assert txtOutput != null : "fx:id=\"txtOutput\" was not injected: check your FXML file 'MetroDeParis.fxml'.";
        
        

    }
}
