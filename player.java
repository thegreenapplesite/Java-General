package mymedia;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.geometry.Insets;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.HBox;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.util.Duration;

public class myplayer extends Application {
	private static final String MEDIA_URL = "file:/home/pie/Downloads/a.mp4";
	
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		Media m = new Media(MEDIA_URL);
		MediaPlayer myplayer = new MediaPlayer(m);
		MediaView myplayerview = new MediaView(myplayer);
		
		Button play = new Button("Play");
		play.setOnAction(e -> {
			if (play.getText().equals("Play")) {
				myplayer.play();
				play.setText("Pause");
			} else {
				myplayer.pause();
				play.setText("Play");	
			}
		});
		
		
		
		Button rewind = new Button("Rewind");
		rewind.setOnAction(e->myplayer.seek(Duration.ZERO));
		
		
		Slider vol = new Slider();
		vol.setPrefWidth(150);
		vol.setMaxWidth(Region.USE_PREF_SIZE);
		vol.setMinWidth(30);
		vol.setValue(50);
		myplayer.volumeProperty().bind(vol.valueProperty().divide(100));
		
		
		
		//HBox mybox = new HBox(10);
		//mybox.setAlignment(Pos.CENTER);
		//mybox.getChildren().addAll(play,rewind, new Label("Volume"), vol);
		
		
		//Top part of the Player
		Button refresh = new Button("Refresh");
		refresh.setOnAction(e-> {
			System.out.println("Pressed the Refresh Button");
		});
		
		Label myl = new Label("Source Media");
		TextField mystring = new TextField();
		mystring.setText(MEDIA_URL);
		
		TextField duration = new TextField();
		Label dur = new Label("Duration Min");
		
		
		myplayer.setOnReady(new Runnable() {
			public void run() {
				double m = myplayer.getMedia().getDuration().toMinutes();
				duration.setText(String.format("%.2f", m));
			}			
			
		});
		
		
		GridPane gp = new GridPane();
		gp.setPadding(new Insets(11.5, 12.5, 13.5, 14.5));
		gp.setHgap(5.5);
		gp.setVgap(5.5);
		ColumnConstraints col1 = new ColumnConstraints();
		ColumnConstraints col2 = new ColumnConstraints();
		col1.setPercentWidth(5);
		col2.setPercentWidth(95);
		gp.getColumnConstraints().addAll(col1,col2);
		gp.setAlignment(Pos.CENTER);
		gp.add(myl, 0, 0);
		gp.add(mystring, 1, 0);
		gp.add(dur, 0, 1);
		gp.add(duration, 1, 1);
		//gp.add(refresh, 1, 2);
		
		
		GridPane bt = new GridPane();
		bt.setPadding(new Insets(11.5, 12.5, 13.5, 14.5));
		bt.setHgap(5.5);
		bt.setVgap(5.5);
		bt.setAlignment(Pos.CENTER);
		bt.add(play, 0, 0);
		bt.add(rewind, 1, 0);
		bt.add(vol,2,0);
		bt.add(new Label("Vol"), 3, 0);

		
		BorderPane p = new BorderPane();
		p.setTop(gp);
		p.setCenter(myplayerview);
		p.setBottom(bt);
		
		Scene s = new Scene(p,1800,1000);
		primaryStage.setTitle("My Player App");
		primaryStage.setScene(s);
		
		primaryStage.show();
		
		
	}
	
}
