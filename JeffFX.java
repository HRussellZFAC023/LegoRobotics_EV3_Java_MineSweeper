import java.io.IOException;
import java.net.UnknownHostException;

import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class JeffFX extends Application {
	// Create the Canvas
	private final static int CANVAS_WIDTH = 1200;
	private final static int CANVAS_HIGHT = 600;
	private final static Canvas CANVAS = new Canvas(CANVAS_WIDTH, CANVAS_HIGHT);
	private final static Point2D CANVAS_CENTER = new Point2D(CANVAS_WIDTH/2,CANVAS_HIGHT/2);
	// Get the graphics context of the canvas
	private static GraphicsContext gc = CANVAS.getGraphicsContext2D();
	
	public static void main(String[] args) throws UnknownHostException, IOException {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		Thread outputThread = new OutputThread();
		outputThread.setDaemon(true);
		outputThread.start();
		// Set line width
		gc.setLineWidth(2.0);
		// Set the Color
		gc.setStroke(Color.GREEN);

		// Create the Pane
		Pane root = new Pane();
		// Set the Style-properties of the Pane
		root.setStyle("-fx-padding: 10;" + "-fx-border-style: solid inside;" + "-fx-border-width: 2;"
				+ "-fx-border-insets: 5;" + "-fx-border-radius: 5;" + "-fx-border-color: blue;");	
		// Add the Canvas to the Pane
		root.getChildren().add(CANVAS);
		// Create the Scene
		Scene scene = new Scene(root);
		// Add the Scene to the Stage
		stage.setScene(scene);
		// Set the Title of the Stage
		stage.setTitle("Drawing Paths on a Canvas");
		// Display the Stage
		stage.show();
	}

	public static void addPoint(String x, String y) {
		System.out.println(x);
		System.out.println(y);
		Float xi = Float.valueOf(x)/10;
		Float yi = Float.valueOf(y)/10;
		gc.lineTo(xi + CANVAS_CENTER.getX(),yi+CANVAS_CENTER.getY());
		gc.stroke();
		
	}

}