/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author Kike
 */
public class PaginaPrincipalController extends TenisClubViewController  implements Initializable {

    @FXML
    private Menu usuarioMenu;
    @FXML
    private RadioMenuItem datos;
    @FXML
    private ToggleGroup USER;
    @FXML
    private RadioMenuItem misreservas;
    @FXML
    private RadioMenuItem cerrSes;
    @FXML
    private ImageView imagenPerfil;
    @FXML
    private VBox vbox;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        /**
         * Aqui pondremos el nombre de usuario y la foto que haya elegido.
         * Si no ha elegido ninguna pondremos la de por defecto.
         * Para poner el nombre:
         * - Una vez comprobamos en la base de datos que el nickName corresponde
         * con la contraseña, nos guardamos el nickname y lo ponemos ahi. 
         * Al ejecutar los menus del usuario, deberan estar asociados a un metodo
         * de la clase member, y que tendran que estar guardados LOS DATOS en el
         * member, y cuando se pulse el menu mis reservas, que
         * muestre todos los datos en la ventana emergente.
         * 
         * TODO ESO DEBE SER EN CUANTO SE HABRA LA PESTAÑA POR LO QUE LO MAS 
         * PROBABLE ES QUE SE PONGA LO QUE HAY QUE HACER EN EL INITIALIZE
         **/

        usuarioMenu.setText(USUARIO);
        imagenPerfil.setImage(IMAGEN);
    }    

    @FXML
    private void infDatos(ActionEvent event) {
    }

    @FXML
    private void misReservas(ActionEvent event) {
    }

    @FXML
    private void cerrarSesion(ActionEvent event) 
    { 
    }
}
