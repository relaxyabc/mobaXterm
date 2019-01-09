package com.wanna.keygen;

import com.wanna.keygen.controller.MainController;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * 启动类
 *
 * @author wanna
 * @since 2019-01-03
 */
public class App extends Application {

    private static Application application = null;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/main.fxml"));
        primaryStage.setTitle("mobaxterm");
        primaryStage.getIcons().addAll(new Image("/static/logo.png"));
        primaryStage.setScene(new Scene(root));
        primaryStage.setResizable(false);
        primaryStage.setOnCloseRequest(event -> {
            MainController.executor.shutdownNow();
            Platform.exit();
            System.exit(-1);
        });
        primaryStage.show();
        application = this;
    }

    public static Application getInstance() {
        return application;
    }
}
