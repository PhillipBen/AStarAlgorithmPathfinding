//I certify, that this computer program submitted by me is all of my own work. Signed: Phillip Benson

import java.io.Serializable;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.*;
import java.io.Serializable;
import java.io.*;

import javafx.application.Application;

import javafx.geometry.Pos;
import javafx.geometry.Insets;

import javafx.stage.Stage;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import javafx.collections.FXCollections;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.FontPosture;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;

public class BensonOOPFinal extends Application{
	//Initialization
	protected Text text = new Text(50, 50, "JavaFX");
	
	public Grid grid = new Grid(25, 25);
	public SaveLoadManager SLM = new SaveLoadManager();
	public Scanner scnr = new Scanner(System.in);
	boolean debugModeTF = false;
	
	public static void main(String[] args) {
		launch(args);
	}
	
	public void ClearGrid() {
		grid = new Grid(25, 25);
	}
	
	@Override // Override the start method in the Application class
	public void start(Stage primaryStage) {
		//Initialization
		HBox pane = new HBox(150);
		
		//##### Beg of Points Buttons #####
		VBox pointsBox = new VBox(50);
		TextField obsX;
		TextField obsY;
		HBox line1 = new HBox(10);
		
		Text obsXText = new Text(100, 100, "Obstacle X: ");
		obsX = new TextField();
		obsX.setOnAction(e -> text.setText(obsX.getText()));
		Text obsYText = new Text(100, 100, "Obstacle Y: ");
		obsY = new TextField();
		obsY.setOnAction(e -> text.setText(obsY.getText()));
		line1.getChildren().addAll(obsXText, obsX, obsYText, obsY);
		
		HBox buttonsLine1 = new HBox(10);
	
		Button btAdd = new Button("Add Obstacle");
		btAdd.setOnAction(new EventHandler<ActionEvent>() {
			@Override 
			public void handle(ActionEvent e) {
				try {
					grid.AddObstacle(Integer.parseInt(obsX.getText()), Integer.parseInt(obsY.getText()));
					grid.PrintGrid();
				}catch(NullPointerException f) {
						System.out.println("Error! The grid has been set to null, probably due to an incorrect load error. Please press the 'Clear Current Grid' Button to reset the grid.");
				}catch(Exception f) {
					System.out.println("Error: Not all boxes have numbers in them.");
				}
			}
		});
		
		Button btDelete = new Button("Delete Obstacle");
		btDelete.setOnAction(new EventHandler<ActionEvent>() {
			@Override 
			public void handle(ActionEvent e) {
				try {
					grid.RemoveObstacle(Integer.parseInt(obsX.getText()), Integer.parseInt(obsY.getText()));
					grid.PrintGrid();
				}catch(NullPointerException f) {
						System.out.println("Error! The grid has been set to null, probably due to an incorrect load error. Please press the 'Clear Current Grid' Button to reset the grid.");
				}catch(Exception f) {
					System.out.println("Error: Not all boxes have numbers in them.");
				}
			}
		});
		
		Button btSSP = new Button("Set Start Point");
		btSSP.setOnAction(new EventHandler<ActionEvent>() {
			@Override 
			public void handle(ActionEvent e) {
				try {
					grid.AddStartPoint(Integer.parseInt(obsX.getText()), Integer.parseInt(obsY.getText()));
					grid.PrintGrid();
				}catch(NullPointerException f) {
						System.out.println("Error! The grid has been set to null, probably due to an incorrect load error. Please press the 'Clear Current Grid' Button to reset the grid.");
				}catch(Exception f) {
					System.out.println("Error: Not all boxes have numbers in them.");
				}
			}
		});
		
		Button btSEP = new Button("Set End Point");
		btSEP.setOnAction(new EventHandler<ActionEvent>() {
			@Override 
			public void handle(ActionEvent e) {
				try {
					grid.AddEndPoint(Integer.parseInt(obsX.getText()), Integer.parseInt(obsY.getText()));
					grid.PrintGrid();
				}catch(NullPointerException f) {
						System.out.println("Error! The grid has been set to null, probably due to an incorrect load error. Please press the 'Clear Current Grid' Button to reset the grid.");
				}catch(Exception f) {
					System.out.println("Error: Not all boxes have numbers in them.");
				}
			}
		});
		
		buttonsLine1.getChildren().addAll(btAdd, btDelete, btSSP, btSEP);
		pointsBox.getChildren().addAll(line1, buttonsLine1);
		//##### End of Points Buttons #####
		
		
		//##### Beg of Save/Load Buttons #####
		VBox saveLoadButtons = new VBox(50);

		HBox fileNameTextField = new HBox(50);
		TextField fileNameStr;
		Text fileNameText = new Text(100, 100, "File Name: ");
		fileNameStr = new TextField();
		fileNameStr.setOnAction(e -> text.setText(fileNameStr.getText()));
		fileNameTextField.getChildren().addAll(fileNameText, fileNameStr);
		
		Button btSave = new Button("Save Grid");
		btSave.setOnAction(new EventHandler<ActionEvent>() {
			@Override 
			public void handle(ActionEvent e) {;
				if (!fileNameStr.getText().equals("")) {
					SLM.save(grid, fileNameStr.getText());
					System.out.println("File Successfully Saved!");
				} else
					System.out.println("File Name cannot be left blank!");
			}
		});
		
		Button btLoad = new Button("Load Grid");
		btLoad.setOnAction(new EventHandler<ActionEvent>() {
			@Override 
			public void handle(ActionEvent e) {
				if (!fileNameStr.getText().equals("")) 
					try {
						grid = SLM.load(fileNameStr.getText());
						grid.PrintGrid();
						System.out.println("File Successfully Loaded!");
					}catch(NullPointerException f) {
						System.out.println("Error: Corrupted Save OR File Does not exist! Sorry, you will need to recreate the save.");
					}catch(Exception f) {
						f.getMessage();
					}
				else
					System.out.println("File Name cannot be left blank!");
			}
		});
		saveLoadButtons.getChildren().addAll(fileNameTextField, btSave, btLoad);
		//##### End of Save/Load Buttons #####
		
		
		//##### Beg of Single Commands Buttons #####
		VBox singleCommandButtons = new VBox(50);
		
		Button btCalcPath = new Button("Calculate Path");
		btCalcPath.setOnAction(new EventHandler<ActionEvent>() {
			@Override 
			public void handle(ActionEvent e) {
				ArrayList<AStarNode> targetNodePath = grid.AStarSearch(debugModeTF);
				//print the path
				if (grid != null) {
					if (targetNodePath == null)
						grid.PrintGrid();
					else
						grid.PrintGrid(targetNodePath);
				} else 
					System.out.println("Error! The grid has been set to null, probably due to an incorrect load error. Please press the 'Clear Current Grid' Button to reset the grid.");
			}
		});
		
		Button btClearGrid = new Button("Clear Current Grid");
		btClearGrid.setOnAction(new EventHandler<ActionEvent>() {
			@Override 
			public void handle(ActionEvent e) {
				ClearGrid();
				grid.PrintGrid();
			}
		});
		
		Button btToggleDBGMode = new Button("Toggle Debug Mode");
		btToggleDBGMode.setOnAction(new EventHandler<ActionEvent>() {
			@Override 
			public void handle(ActionEvent e) {
				if (debugModeTF) {
					debugModeTF = false;
					System.out.println("Debug mode: OFF");
				} else {
					debugModeTF = true;
					System.out.println("Debug mode: ON");
				}
			}
		});
		
		Button btPrintGrid = new Button("Print Current Grid");
		btPrintGrid.setOnAction(new EventHandler<ActionEvent>() {
			@Override 
			public void handle(ActionEvent e) {
				if (grid != null) {
					grid.PrintGrid();
				} else 
					System.out.println("Error! The grid has been set to null, probably due to an incorrect load error. Please press the 'Clear Current Grid' Button to reset the grid.");
			}
		});
		singleCommandButtons.getChildren().addAll(btCalcPath, btClearGrid, btToggleDBGMode, btPrintGrid);
		//##### End of Single Commands Buttons #####
		
		//Final Box Grouping
		pane.getChildren().addAll(pointsBox, saveLoadButtons, singleCommandButtons);
		
		// Create a scene and place it in the stage
		Scene scene = new Scene(pane);
		primaryStage.setTitle("A* Alg"); // Set the stage title
		primaryStage.setScene(scene); // Place the scene in the stage
		primaryStage.show(); // Display the stage
	}
}