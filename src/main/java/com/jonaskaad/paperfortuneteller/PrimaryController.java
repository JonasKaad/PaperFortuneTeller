package com.jonaskaad.paperfortuneteller;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.media.MediaView;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.concurrent.atomic.AtomicInteger;

public class PrimaryController {
    @FXML
    private ImageView imageView;
    @FXML
    private Label winLabel;
    @FXML
    private AnchorPane resultAnchorPane;
    @FXML
    private Button blueButton;
    @FXML
    private Button redButton;
    @FXML
    private Button greenButton;
    @FXML
    private Button yellowButton;
    @FXML
    private Label topLabel;
    @FXML
    private Button topLeftButton;
    @FXML
    private Button topRightButton;
    @FXML
    private Button botLeftButton;
    @FXML
    private Button botRightButton;
    @FXML
    private MediaView mediaView;
    @FXML
    private MediaPlayer mediaPlayer;

    static boolean outPut1;

    int amountOfTimesFlipped = 0;


    @FXML
    public void initialize(){
        if(topLeftButton != null){
            if(outPut1){
                setButtonOutput1();
            }
            else{
                setButtonOutput2();
            }
            resultAnchorPane.setDisable(true);
            resultAnchorPane.setOpacity(0);
            resultAnchorPane.setVisible(false);
        }
    }

    public void colorButtonHandler(ActionEvent event) throws IOException {

        if(event.getSource() == blueButton || event.getSource() == greenButton){
            outPut1 = true;
        }
        if(event.getSource() == redButton || event.getSource() == yellowButton){
            outPut1 = false;
        }

        FXMLLoader loader = new FXMLLoader(App.class.getResource("fortuneteller.fxml"));
        loader.load();
        Stage stage = (Stage) blueButton.getScene().getWindow();
        Scene scene = new Scene(loader.getRoot());
        stage.setScene(scene);
    }
    int numberPicked;
    public void buttonHandler(ActionEvent event) {
        if (event.getSource() == topLeftButton) {
            numberPicked = Integer.parseInt(topLeftButton.getText());
        }
        if (event.getSource() == topRightButton) {
            numberPicked = Integer.parseInt(topRightButton.getText());
        }
        if (event.getSource() == botLeftButton) {
            numberPicked = Integer.parseInt(botLeftButton.getText());
        }
        if (event.getSource() == botRightButton) {
            numberPicked = Integer.parseInt(botRightButton.getText());
        }

        if(amountOfTimesFlipped >= 2){
            topRightButton.setDisable(true);
            topLeftButton.setDisable(true);
            botRightButton.setDisable(true);
            botLeftButton.setDisable(true);
            showResult(numberPicked);
        }

        else {
            topRightButton.setDisable(true);
            topLeftButton.setDisable(true);
            botRightButton.setDisable(true);
            botLeftButton.setDisable(true);

            AtomicInteger i = new AtomicInteger();
            int cloneNumberPicked = numberPicked;
            AtomicInteger numb = new AtomicInteger(cloneNumberPicked);
            AtomicInteger currentOutput = new AtomicInteger(1);
            double duration = 0.5;
            if (cloneNumberPicked == 1) {
                duration = 0.8;
                topLabel.setText("Flip Flapping " + cloneNumberPicked + " time");
            } else {
                topLabel.setText("Flip Flapping " + cloneNumberPicked + " times");
            }
            Timeline timeline = new Timeline(
                    new KeyFrame(Duration.seconds(duration), e -> {

                        if (i.get() == 0) {
                            if (numb.get() == 7 || numb.get() == 8 || numb.get() == 3 || numb.get() == 4) {
                                setButtonOutput2();
                                currentOutput.getAndIncrement();
                            }
                            if (numb.get() == 1 || numb.get() == 2 || numb.get() == 5 || numb.get() == 6) {
                                setButtonOutput1();
                            }
                        } else {
                            switch (currentOutput.getAcquire()) {
                                case 1 -> {
                                    setOutput(currentOutput.getAcquire());
                                    currentOutput.getAndIncrement();
                                }
                                case 2 -> {
                                    setOutput(currentOutput.getAcquire());
                                    currentOutput.getAndDecrement();
                                }
                            }
                        }
                        i.getAndIncrement();
                    })
            );
            timeline.setCycleCount(numberPicked);
            timeline.play();
            timeline.setOnFinished(event1 -> {
                topLabel.setText("Pick another number:");
                amountOfTimesFlipped++;
                if(amountOfTimesFlipped >= 2){
                    topLabel.setText("Pick a number to see your fortune:");
                }
                topRightButton.setDisable(false);
                topLeftButton.setDisable(false);
                botRightButton.setDisable(false);
                botLeftButton.setDisable(false);

            });
        }
    }

    public void showResult(int numberPicked){
        resultAnchorPane.setDisable(false);
        resultAnchorPane.setOpacity(1);
        resultAnchorPane.setVisible(true);
        System.out.println(numberPicked);
        switch (numberPicked) {
            case 1 -> {
                winLabel.setText("An Aglet");
                String filename = "videos/aglet.mp4";
                try {
                    File f = new File(getClass().getResource(filename).toURI().toString());
                    System.out.println(f);
                    String str = f.toString().replace("\\", "/");
                    System.out.println(str);
                    Media media = new Media(str);
                    mediaPlayer = new MediaPlayer(media);
                    mediaView.setMediaPlayer(mediaPlayer);
                    mediaPlayer.play();
                } catch (URISyntaxException e) {
                    e.printStackTrace();
                }
            }
            case 2 -> {
                winLabel.setText("Some Peanuts");
                getImage("peanuts");
            }
            case 3 -> {
                winLabel.setText("An Apple");
                getImage("apple");
            }
            case 4 -> {
                winLabel.setText("A Cup of Coffee");
                getImage("coffee");
            }
            case 5 -> {
                winLabel.setText("A Piece of Cake");
                getImage("cake");
            }
            case 6 -> {
                winLabel.setText("A Bottle of Water");
                getImage("water");
            }
            case 7 -> {
                winLabel.setText("A Sandwich");
                getImage("sandwich");
            }
            case 8 -> {
                winLabel.setText("A Bottle of Soda");
                getImage("cola");
            }
        }
    }

    public void getImage(String imageName){
        try {
            imageView.setImage(new Image((getClass().getResource("images/" + imageName + ".png").toURI().toString())));
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }


    public void setOutput(int value){
        if(value == 1){
            setButtonOutput2();
        }
        else if(value == 2){
            setButtonOutput1();
        }
    }

    public void setButtonOutput1(){
        topLeftButton.setText("7");
        topRightButton.setText("8");
        botLeftButton.setText("3");
        botRightButton.setText("4");
    }
    public void setButtonOutput2(){
        topLeftButton.setText("5");
        topRightButton.setText("1");
        botLeftButton.setText("6");
        botRightButton.setText("2");
    }
}