import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.AudioClip;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * ChessGui shows a Graphical User Interface of each game in the Chess Database
 * @author adussa3
 * @version 1.0
 */

public class ChessGui extends Application {

    /**
     * [launched Chess Graphical User Interface]
     * @param args [description]
     */
    public static void main(String[] args) {
        AudioClip sound = new AudioClip("File:bensound-dubstep.wav");
        sound.play();

        launch(args);
    }

    /**
     * [start description]
     * @param stage [description]
     */
    @Override
    public void start(Stage stage) {

        // Table
        ChessDb chessDb = new ChessDb();
        ObservableList<ChessGame> games = FXCollections.observableArrayList(chessDb.getGames());

        TableView<ChessGame> table = createTable(games);
        table.getStyleClass().add("table");

        // View Button
        Button viewButton = new Button("View Message");
        viewButton.getStyleClass().add("button");

        viewButton.setOnAction(e -> {
                ChessGame msg = table.getSelectionModel().getSelectedItem();
                viewDialog(msg);
            }
        );

        viewButton.disableProperty().bind(Bindings.
            isNull(table.getSelectionModel().selectedItemProperty()));

        // Dismiss Button
        Button dismissButton = new Button("Dismiss");
        dismissButton.getStyleClass().add("button");

        dismissButton.setOnAction(e -> Platform.exit());

        // HBox
        final ImageView selectedImage = new ImageView();
        Image image1 = new Image("File:sound-on.png");

        selectedImage.setImage(image1);

        HBox buttonBox = new HBox();
        buttonBox.getChildren()
            .addAll(viewButton, dismissButton, selectedImage);

        // VBox
        VBox vbox = new VBox();
        vbox.getChildren().addAll(table, buttonBox);

        // Scene
        Scene scene = new Scene(vbox, 1920, 1080);
        scene.getStylesheets().add("ChessGui_StyleSheet.css");

        // Stage
        stage.setScene(scene);
        stage.setTitle("Chess Gui");
        stage.show();
    }

    /**
     * [createTable description]
     * @param  games [takes in a List of Chess Games]
     * @return       [the columns of the table]
     */
    private TableView<ChessGame> createTable(ObservableList<ChessGame> games) {

        TableView<ChessGame> table = new TableView<>();
        table.setItems(games);

        TableColumn<ChessGame, String> eventCol = new TableColumn<>("Event");
        eventCol.setCellValueFactory(new PropertyValueFactory("event"));

        TableColumn<ChessGame, String> siteCol = new TableColumn<>("Site");
        siteCol.setCellValueFactory(new PropertyValueFactory("site"));

        TableColumn<ChessGame, String> dateCol = new TableColumn<>("Date");
        dateCol.setCellValueFactory(new PropertyValueFactory("date"));

        TableColumn<ChessGame, String> whiteCol = new TableColumn<>("White");
        whiteCol.setCellValueFactory(new PropertyValueFactory("white"));

        TableColumn<ChessGame, String> blackCol = new TableColumn<>("Black");
        blackCol.setCellValueFactory(new PropertyValueFactory("black"));

        TableColumn<ChessGame, String> resultCol = new TableColumn<>("Result");
        resultCol.setCellValueFactory(new PropertyValueFactory("result"));

        TableColumn<ChessGame, String> openingCol = new TableColumn<>("Opening");
        openingCol.setCellValueFactory(new PropertyValueFactory("opening"));

        table.getColumns().setAll(eventCol, siteCol, dateCol, whiteCol, blackCol, resultCol, openingCol);
        return table;
    }

    /**
     * [Shows the metadata of each Chess Game (event, site, date, white player,
     * black player, result on a view dialog)]
     * @param msg [a Chess Game from the database]
     */
    private void viewDialog(ChessGame msg) {

        Alert alert = new Alert(Alert.AlertType.INFORMATION);

        alert.setTitle(msg.getEvent());

        alert.setHeaderText(
            String.format("Event: " + msg.getEvent()
                           + "Site: " + msg.getSite()
                           + "Date: " + msg.getDate()
                           + "White: " + msg.getWhite()
                           + "Black" + msg.getBlack()
                           + "Result: " + msg.getResult()
                           + "Opening: " + msg.getOpening()));

        alert.setContentText(msg.getMoves());
        alert.showAndWait();
    }

}