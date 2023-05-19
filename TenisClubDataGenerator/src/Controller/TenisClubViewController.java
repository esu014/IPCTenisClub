/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Club;
import model.Member;

/**
 *
 * @author Kike
 */
public class TenisClubViewController implements Initializable {

    @FXML
    private Button botonIniSes;
    @FXML
    private Button botonReg;
    @FXML
    private VBox vbox;
    @FXML
    private GridPane gridPane;
    @FXML
    private Text tituloPagina;
    @FXML
    private Text mensajeInicial; 
    
    public static Member miembro;
    public static String USUARIO;
    public static Image IMAGEN;
    public static Club club;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        // TODO
        vbox.getStyleClass().add("vbox");
        botonReg.getStyleClass().add("button");
        botonIniSes.getStyleClass().add("button");
        mensajeInicial.getStyleClass().add("mensajeInicial");
        tituloPagina.getStyleClass().add("tituloPagina");
        botonReg.setOnMouseEntered(ae -> botonReg.setStyle("-fx-font-size: 20px"));
        botonReg.setOnMouseExited(ae -> botonReg.setStyle(""));
        botonIniSes.setOnMouseEntered(ae -> botonIniSes.setStyle("-fx-font-size: 20px"));
        botonIniSes.setOnMouseExited(ae -> botonIniSes.setStyle(""));
        if(USUARIO !=null)
        {
            Stage stage = (Stage) botonIniSes.getScene().getWindow();
            stage.close();
                    
        }
      
    } 

    @FXML
    private void iniciaSesion(ActionEvent event) throws Exception {
        FXMLLoader iniSes = new FXMLLoader(getClass().getResource("/View/InicioSesion.fxml"));
        Parent root = iniSes.load();
        Scene nuevaPestaña = new Scene(root);
        Stage nuevaVentana = new Stage();
        nuevaVentana.setScene(nuevaPestaña);
        nuevaVentana.setTitle("Iniciar Sesión");
        Image logo = new Image(getClass().getResourceAsStream("/resources/fotos/logo.png"));
        nuevaVentana.getIcons().add(logo);
        nuevaVentana.initModality(Modality.APPLICATION_MODAL);
        nuevaVentana.show();
        Stage close = (Stage)botonIniSes.getScene().getWindow();
        close.close();
    }

    @FXML
    private void registro(ActionEvent event) throws Exception 
    {
        FXMLLoader reg = new FXMLLoader(getClass().getResource("/View/Registro.fxml"));
        Parent root = reg.load();
        Scene nuevaPestaña = new Scene(root);
        Stage nuevaVentana = new Stage();
        nuevaVentana.setScene(nuevaPestaña);
        nuevaVentana.setTitle("Registro");
        Image logo = new Image(getClass().getResourceAsStream("/resources/fotos/logo.png"));
        nuevaVentana.getIcons().add(logo);
        nuevaVentana.initModality(Modality.APPLICATION_MODAL);
        nuevaVentana.show();
        Stage close = (Stage)botonIniSes.getScene().getWindow();
        close.close();
        
    }
    
}
