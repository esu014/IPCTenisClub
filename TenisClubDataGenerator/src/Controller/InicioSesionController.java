/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Member;
import model.Club;

/**
 * FXML Controller class
 *
 * @author Kike
 */
public class InicioSesionController extends TenisClubViewController implements Initializable {

    @FXML
    private Button botonAcep;
    @FXML
    private Button botonCancel;
    @FXML
    private TextField nickname;
    @FXML
    private PasswordField password;
    @FXML
    private Text mensajeError;
    
    private String nickName;
    private String passWord; 
    @FXML
    private GridPane gridPane;
    
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        botonAcep.setOnMouseEntered(ae -> botonAcep.setStyle("-fx-font-size: 15px"));
        botonAcep.setOnMouseExited(ae -> botonAcep.setStyle(""));
        botonCancel.setOnMouseEntered(ae -> botonCancel.setStyle("-fx-font-size: 15px"));
        botonCancel.setOnMouseExited(ae -> botonCancel.setStyle(""));
        gridPane.getStyleClass().add("gridPane");
        
        nickname.textProperty().addListener((observable, oldValue, newValue) -> {
            nickName = newValue;
            System.out.println(nickName);
        }); 
        password.textProperty().addListener((observable, oldValue, newValue) -> {
            passWord = newValue;
            System.out.println(passWord);
        }); 
        
        
    }    
    //MIRAR PRACTICA 3 A PARTIR DE LA DIAPOSITIVA 30
    @FXML
    private void acepIni(ActionEvent event) throws Exception 
    {
        Stage stage = (Stage) botonAcep.getScene().getWindow();
        try
        {
            //aqui viene la validacion de los datos que han introducido
            club = Club.getInstance(); // como no se puede crear otro club, obtenemos el metodo de getinstance para poder obtener sus metodos.
            miembro = club.getMemberByCredentials(nickName, passWord); //develve null si no hay ninguno con los parametros pasados
            USUARIO = miembro.getName();
            IMAGEN = miembro.getImage();
            //cierra la ventana de inicio sesion
            stage.close();
            //cierra la de TenisClubView

            //abre la pagina principal
            FXMLLoader pagPrincipal = new FXMLLoader(getClass().getResource("/View/PaginaPrincipal.fxml"));
            Parent root = pagPrincipal.load();
            Scene nuevaPestaña = new Scene(root);
            Stage nuevaVentana = new Stage();
            nuevaVentana.resizableProperty();
            nuevaVentana.setScene(nuevaPestaña);
            Image logo = new Image(getClass().getResourceAsStream("/resources/fotos/logo.png"));
            nuevaVentana.getIcons().add(logo);
            nuevaVentana.setTitle("Página Principal");
            nuevaVentana.show();
            
        }
        catch(NullPointerException e)
        {
            mensajeError.setText("Nickname y/o contraseña incorrectos");
            nickname.requestFocus();
            password.clear();
        }
        
    }

    @FXML
    private void cancelIni(ActionEvent event) throws IOException 
    {
        Stage stage = (Stage) botonCancel.getScene().getWindow();
        stage.close();
        FXMLLoader pagPrincipal = new FXMLLoader(getClass().getResource("/View/TenisClubView.fxml"));
        Parent root = pagPrincipal.load();
        Scene nuevaPestaña = new Scene(root);
        Stage nuevaVentana = new Stage();
        nuevaVentana.resizableProperty();
        nuevaVentana.setScene(nuevaPestaña);
        Image logo = new Image(getClass().getResourceAsStream("/resources/fotos/logo.png"));
        nuevaVentana.getIcons().add(logo);
        nuevaVentana.setTitle("GreenBall");
        nuevaVentana.show();
                
    }

    @FXML //Este método hace que cuando escribamos la contraseña es como si le dieramos al boton aceptar al presionar Enter
    private void dirBotAcep(ActionEvent event) throws Exception 
    {
        acepIni(event);
    }

}
