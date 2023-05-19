/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.*;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import model.Club;
import model.Member;

/**
 * FXML Controller class
 *
 * @author Kike
 */
public class RegistroController extends TenisClubViewController implements Initializable {

    @FXML
    private Button cancReg;
    @FXML
    private Button acepReg;
    @FXML
    private TextField nombre;
    private String Nombre;
    @FXML
    private TextField apellido;
    private String Apellido;
    @FXML
    private TextField nickName;
    private String NickName;
    @FXML
    private TextField numero;
    private String Numero;
    @FXML
    private TextField tarjetaCred;
    private String TarjetaCred;
    @FXML
    private Button botonImage;
    @FXML
    private PasswordField contraseña;
    private String Contraseña;
    @FXML
    private TextField linkImagen; //practica 4, diapositiva 25
    private Image imagen;
    
    private Member persona;
    @FXML
    private Text textoErrorNombre;
    @FXML
    private Text textoErrorApellido;
    @FXML
    private Text textoErrorNickname;
    @FXML
    private Text textoErrorContraseña;
    @FXML
    private Text textoErrorTelf;
    @FXML
    private Text textoErrorTarjeta;
    @FXML
    private Text textoErrorSVC;
    @FXML
    private TextField svc;
    private int sVc;
    @FXML
    private VBox vBox;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            initClub();
        } catch (Exception ex) {
            Logger.getLogger(RegistroController.class.getName()).log(Level.SEVERE, null, ex);
        }
        /**INICIALIZAR CSS**/
        acepReg.getStyleClass().add("button");
        vBox.getStyleClass().add("vbox");
        textoErrorNombre.getStyleClass().add("textError");
        textoErrorApellido.getStyleClass().add("textError");
        textoErrorContraseña.getStyleClass().add("textError");
        textoErrorNickname.getStyleClass().add("textError");
        nombre.getStyleClass().add("text");
        apellido.getStyleClass().add("text");
        nickName.getStyleClass().add("text");
        contraseña.getStyleClass().add("text");
        
        cancReg.setOnMouseEntered(ae -> cancReg.setStyle("-fx-font-size: 15px"));
        cancReg.setOnMouseExited(ae -> cancReg.setStyle(""));
        acepReg.setOnMouseEntered(ae -> acepReg.setStyle("-fx-font-size: 15px"));
        acepReg.setOnMouseExited(ae -> acepReg.setStyle(""));
        
        //desabilita si no estan las cosas minimas
        acepReg.disableProperty().bind(nombre.textProperty().isEmpty().or(apellido.textProperty().isEmpty().or(contraseña.textProperty().isEmpty().or(nickName.textProperty().isEmpty()))));
        
        /**METODO PARA CRERAR LISTENERS
         *  NOMBRE *
         * * * * * **/
        ChangeListener<String> nombreListener = (observable, oldValue, newValue) -> {
            if (newValue.length() > 0 && nomValido(newValue)) {
                Nombre = newValue;
                textoErrorNombre.setText("");
                System.out.println("Nombre Puesto");
            } else if (nomValido(newValue) && newValue.length() > 1) {
                textoErrorNombre.setText("Introduzca un nombre válido");
                nombre.requestFocus();
            } else {
                textoErrorNombre.setText("Campo obligatorio");
                nombre.requestFocus();
            }
        };
        nombre.textProperty().addListener(nombreListener);
        
         /**METODO PARA CRERAR LISTENERS
         *  APELLIDO *
         * * * * * * **/
        ChangeListener<String> apellidoListener = (observable, oldValue, newValue) -> {
            if (newValue.length() > 0 && nomValido(newValue)) {
                Apellido = newValue;
                textoErrorApellido.setText("");
                System.out.println("Apellido Puesto");
            } else if (nomValido(newValue) && newValue.length() > 1) {
                textoErrorApellido.setText("Introduzca un nombre válido");
                apellido.requestFocus();
            } else {
                textoErrorApellido.setText("Campo obligatorio");
                apellido.requestFocus();
            }
        };
        apellido.textProperty().addListener(apellidoListener);
        
        /**METODO PARA CRERAR LISTENERS
         *  CONTRASEÑA *
         * * * * * * * */
        ChangeListener<String> contraseñaListener = (observable, oldValue, newValue) -> {
                Contraseña = newValue;
        };
        contraseña.textProperty().addListener(contraseñaListener);
        
        /**METODO PARA CRERAR LISTENERS
         *  NUMERO *
         * * * * * * **/
        numero.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.isEmpty()) {
                Numero = "600000000";
                textoErrorTelf.setText("");
                System.out.println("Telefono por defecto puesto");
            } else if (soloNum(newValue) && newValue.length() == 9) {
                Numero = newValue;
                textoErrorTelf.setText("");
                System.out.println("Telefono propio puesto");
            } else {
                textoErrorTelf.setText("Introduzca un número válido");
                numero.requestFocus();
            }
        });
        
        /**METODO PARA CRERAR LISTENERS
         *  Tarjeta  *
         * * * * * * **/
        tarjetaCred.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.isEmpty()) {
                TarjetaCred = "0000000000000000";
                textoErrorTarjeta.setText("");
            } else if (soloNum(newValue) && newValue.length() == 16) {
                TarjetaCred = newValue;
                textoErrorTarjeta.setText("");
            } else {
                textoErrorTarjeta.setText("Introduzca una tarjeta válida");
                tarjetaCred.requestFocus();
            }
        });
        
        /**METODO PARA CRERAR LISTENERS
         *  svc  *
         * * * * **/
        svc.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.equals("")) {
                sVc = 0;
                textoErrorSVC.setText("");
            } else if (soloNum(newValue) && newValue.length() == 3) {
                sVc = Integer.parseInt(newValue);
                textoErrorSVC.setText("");
            } else {
                textoErrorSVC.setText("Introduzca un número válido");
                svc.requestFocus();
            }
        }); 
        nickName.textProperty().addListener(nickNameChangeListener);
    }   

    @FXML
    private void cancelRegistro(ActionEvent event) throws IOException
    {
        
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Registro");
        alert.setHeaderText("Cancelar Registro");
        alert.setContentText("¿Seguro que quieres cancelar el registro?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK)
        {
            System.out.println("OK");
            Stage stage = (Stage) cancReg.getScene().getWindow();
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
        else 
        {
            System.out.println("CANCEL");
            
        }
    }

    @FXML
    private void acepRegistro(ActionEvent event) throws Exception 
    {
        if(linkImagen.getText().equals("")) //comprueba que no hemos subido imagen
        {
            linkImagen.setText("/lib/tenisClub/avatars/default.png");
        }
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Registro");
        alert.setHeaderText("Aceptar Registro");
        alert.setContentText("¿Seguro que quieres registrarte?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK)
        {
            Member member = club.registerMember(Nombre, Apellido, Numero, NickName, Contraseña, TarjetaCred, sVc, imagen);
            System.out.println("OK");
            Stage stage = (Stage) acepReg.getScene().getWindow();
            //Tiene que iniciar sesion, una vez añadido a la base de datos
            USUARIO = member.getName();
            IMAGEN = member.getImage();
            stage.close();

            //abre la pagina principal
            FXMLLoader pagPrincipal = new FXMLLoader(getClass().getResource("/View/PaginaPrincipal.fxml"));
            Parent root = pagPrincipal.load();
            Scene nuevaPestaña = new Scene(root);
            Stage nuevaVentana = new Stage();
            nuevaVentana.setScene(nuevaPestaña);
            nuevaVentana.resizableProperty();
            nuevaVentana.setTitle("Página Principal");
            Image logo = new Image(getClass().getResourceAsStream("/resources/fotos/logo.png"));
            nuevaVentana.getIcons().add(logo);
            nuevaVentana.show(); 
        } 
        else 
        {
            System.out.println("CANCEL");
            
        }
    }
    private boolean nomValido(String s)
    {
        Pattern patron = Pattern.compile(".*\\d.*");
        Matcher matcher = patron.matcher(s);
        return !matcher.matches();
    }
    private boolean nickVal(String s)
    {
        return !s.contains(" ");
    }
    private boolean soloNum(String s)
    {
        return s.matches("\\d+");
    }
    
    // Método para inicializar el Club y manejar la excepción CLUBDAOException
    private void initClub() throws Exception {
        club = Club.getInstance();
    }
    
    // Listener para el cambio de texto en el TextField nickName
    private ChangeListener<String> nickNameChangeListener = new ChangeListener<String>() {
        @Override
        public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
            if (club == null) {
                try {
                    // Si el Club no está inicializado, intenta inicializarlo
                    initClub();
                } catch (Exception ex) {
                    Logger.getLogger(RegistroController.class.getName()).log(Level.SEVERE, null, ex);
                    System.err.println("initclubException");
                }
            }

            if (!club.existsLogin(newValue) && nickVal(newValue)) {
                NickName = newValue;
                textoErrorNickname.setText("");
                System.out.println("nickname");
            } else if (club.existsLogin(newValue)) {
                textoErrorNickname.setText("Nombre de usuario no disponible");
                nickName.requestFocus();
                System.out.println("nickname no disponible");
            } else {
                textoErrorNickname.setText("El nombre no puede contener espacios");
                nickName.requestFocus();
                System.out.println("nickname con espacios");
            }
        }
    };

    @FXML
    private void subirArchivos(ActionEvent event) 
    {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Abrir fichero");
        fileChooser.getExtensionFilters().addAll(
            new ExtensionFilter("Imágenes", "*.png", "*.jpg", "*.gif"));
        File selectedFile = fileChooser.showOpenDialog(
        ((Node)event.getSource()).getScene().getWindow());
        if (selectedFile != null) 
        {
            imagen = new Image(selectedFile.toURI().toString());
            linkImagen.setText(selectedFile.getAbsolutePath());
            System.out.println(selectedFile.getAbsolutePath());
        }
    }
    
}

