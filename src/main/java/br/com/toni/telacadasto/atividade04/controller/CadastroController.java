package br.com.toni.telacadasto.atividade04.controller;


import br.com.toni.telacadasto.atividade04.model.Contato;
import br.com.toni.telacadasto.atividade04.service.ContatoService;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CadastroController {

    @Autowired
    private ContatoService contatoService;

    @FXML
    private Button BtApagar;

    @FXML
    private Button BtLimpar;

    @FXML
    private Button BtSair;

    @FXML
    private Button BtSalvar;

    @FXML
    private Button BtMostrarOcultar;

    @FXML
    private TextField EmailV1;

    @FXML
    private TextField NameV1;

    @FXML
    private TextField TelefoneV1;

    @FXML
    private TableColumn<Contato, String> colEmail;

    @FXML
    private TableColumn<Contato, Long> colId;

    @FXML
    private TableColumn<Contato, String> colNome;

    @FXML
    private TableColumn<Contato, String> colTelefone;

    @FXML
    private Label lblMensagem;

    @FXML
    private Pane paneMensagem;

    @FXML
    private TableView<Contato> tableContatos;

    private ObservableList<Contato> contatosList = FXCollections.observableArrayList();

    private boolean tabelaVisivel = true; // Estado da tabela

    @FXML
    public void initialize() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colTelefone.setCellValueFactory(new PropertyValueFactory<>("telefone"));

        carregarContatos();
    }

    @FXML
    void salvarContatos(ActionEvent event) {
        String mensagem = "";

        if (NameV1.getText().isEmpty()) {
            mensagem = "O campo Nome é obrigatório.";
        } else if (EmailV1.getText().isEmpty()) {
            mensagem = "O campo E-mail é obrigatório.";
        } else if (TelefoneV1.getText().isEmpty()) {
            mensagem = "O campo Telefone é obrigatório.";
        } else {
            Contato contato = Contato.builder()
                    .nome(NameV1.getText())
                    .email(EmailV1.getText())
                    .telefone(TelefoneV1.getText())
                    .build();

            contatoService.salvarContato(contato);
            mensagem = "Contato salvo com sucesso!";
            carregarContatos();
        }
        mostrarMensagem(mensagem);
    }

    @FXML
    void apagarContatos(ActionEvent event) {
        Contato contatoSelecionado = tableContatos.getSelectionModel().getSelectedItem();
        String mensagem = "";

        if (contatoSelecionado != null) {
            contatoService.deletarContato(contatoSelecionado.getId());
            mensagem = "Contato " + contatoSelecionado.getNome() + " removido com sucesso!";
            carregarContatos();
        } else {
            mensagem = "Nenhum contato selecionado para excluir.";
        }
        mostrarMensagem(mensagem);
    }

    @FXML
    void limparCampos(ActionEvent event) {
        NameV1.setText("");
        EmailV1.setText("");
        TelefoneV1.setText("");
    }

    @FXML
    void sair(ActionEvent event) {
        Platform.exit();
    }

    @FXML
    void mostrarOcultarTabela(ActionEvent event) {
        tabelaVisivel = !tabelaVisivel; // Alternar estado da tabela
        tableContatos.setVisible(tabelaVisivel);

        String mensagem = tabelaVisivel ? "Tabela exibida." : "Tabela oculta.";
        mostrarMensagem(mensagem);

        // Atualizar texto do botão
        BtMostrarOcultar.setText(tabelaVisivel ? "Ocultar Dados" : "Mostrar Dados");
    }

    private void carregarContatos() {
        contatosList.clear();
        contatosList.addAll(contatoService.listarContatos());
        tableContatos.setItems(contatosList);
    }

    private void mostrarMensagem(String mensagem) {
        lblMensagem.setText(mensagem);
        paneMensagem.setVisible(true);

        PauseTransition pauseTransition = new PauseTransition(Duration.seconds(3));
        pauseTransition.setOnFinished(e -> paneMensagem.setVisible(false));
        pauseTransition.play();
    }
}
