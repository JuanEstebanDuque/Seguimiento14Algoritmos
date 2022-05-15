package main;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.chart.PieChart.Data;
import javafx.scene.paint.Color;

public class ControllerMainWindow implements Initializable{

    @FXML
    private Canvas canvas;
    
    private GraphicsContext gc;
    
    private double[] getMinimumMaximun(ArrayList<Double> arr) {
    	ArrayList<Double> aux = new ArrayList<>();
    	aux.addAll(arr);
    	Collections.sort(aux);
    	double min = aux.get(0);
    	double max = aux.get(aux.size()-1);
		return new double[] {min,max};
	}
    
    private double convertion(double m,double b,double x) {
    	return m*x+b;
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		double[] xMinMax = getMinimumMaximun(DataArrays.ejeX);
		double minX = xMinMax[0];
		double maxX = xMinMax[1];
		
		double[] yMinMax = getMinimumMaximun(DataArrays.ejeY);
		double minY = yMinMax[0];
		double maxY = yMinMax[1];
		
		double deltaPXx = canvas.getWidth();
		double deltaDias = maxX - minX;
		double pendienteX = deltaPXx/deltaDias;
		double interceptoX = -pendienteX*minX;
		
		double deltaPXy = -canvas.getHeight();
		double deltaAccidentes = maxY - minY;
		double pendienteY = deltaPXy/deltaAccidentes;
		double interceptoY = -pendienteY*maxY;
		
		gc = canvas.getGraphicsContext2D();
		
		gc.setFill(Color.rgb(240, 240, 240));
    	gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
    	
    	gc.setStroke(Color.GREY);
    	gc.setLineWidth(2);
    	
    	
    	gc.setFill(Color.RED);
    	for(int i=0;i<DataArrays.ejeX.size();i++) {
    		gc.fillOval(convertion(pendienteX, interceptoX, DataArrays.ejeX.get(i))-3, convertion(pendienteY, interceptoY, DataArrays.ejeY.get(i))-3, 6, 6);
    		gc.lineTo(convertion(pendienteX, interceptoX, DataArrays.ejeX.get(i)-3),convertion(pendienteY, interceptoY, DataArrays.ejeY.get(i)-3));
    	}
    	
    	gc.stroke();
    	
	}





}