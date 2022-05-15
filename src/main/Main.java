package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application{

	public static void main(String[] args) {
		
		try {
			String path = "data//data.csv";
			File file = new File(path);
			FileInputStream fis = new FileInputStream(file);
			InputStreamReader isr = new InputStreamReader(fis);
			BufferedReader br = new BufferedReader(isr);
			String line = "";
			String data = "";
			line = br.readLine();
			while ((line = br.readLine()) != null) {
				data += line + ",";
			}
			String[] datos = data.split(",");
			for (int i = 0; i < datos.length; i++) {
				System.out.println(datos[i]);
				if(i % 2 == 0) {
					DataArrays.ejeX.add(Double.parseDouble(datos[i]));
				}else {
					String[] split2 = datos[i].split("\\;");
					System.out.println(split2[i]);
					//ControllerMainWindow.ejeY.add(Double.parseDouble(split2[i]));
				}
			}
			fis.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		FXMLLoader loader = new FXMLLoader(Main.class.getResource("MainWindow.fxml"));
		Parent parent = loader.load();
		Scene scene = new Scene(parent);
		Stage stage = new Stage();
		stage.setScene(scene);
		stage.show();
	}

}
