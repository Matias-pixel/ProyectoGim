
package bd;

import java.sql.SQLException;// import para manejar excepciones SQL

public class DAO {

    private Conexion oConexion;//se crea sin inicializar el objeto como atributo de la Clase DAO
    private String sql;// se crea una variable String para almacenar la consulta temporalmente
    /*
     constructor de DAO
     Genera la conexion entregando los datos
     */
    public DAO() throws SQLException {
        oConexion = new Conexion( //se inicializa el objeto conexion
                "localhost",//se entrega servidor
                "",//nombre de la base de datos       
                "root",//usuario
                ""//contraseña
        );
        
    }
 //inicio de segmento para metos y funciones del CRUD
    

    
    
    
 
}
