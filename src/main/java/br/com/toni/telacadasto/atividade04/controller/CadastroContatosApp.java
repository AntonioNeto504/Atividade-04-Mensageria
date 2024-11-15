package br.com.toni.telacadasto.atividade04.controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lombok.Setter;
import org.springframework.context.ApplicationContext;

import java.io.IOException;

public class CadastroContatosApp extends Application {

    @Setter
    private static ApplicationContext springContext;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(CadastroContatosApp.class.getResource("/cadastrocontatos/tela-cadastro-contatos.fxml"));
        fxmlLoader.setControllerFactory(springContext::getBean);
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Cadastro de Contatos");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
