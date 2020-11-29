
package BD;

import MODEL.Usuario;
import MODEL.Equipamiento;
import MODEL.TipoActividad;
import MODEL.TipoEquipamiento;
import java.sql.ResultSet;
import java.sql.SQLException;// import para manejar excepciones SQL
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;

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
                "gimnasio",//nombre de la base de datos       
                "root",//usuario
                ""//contraseña
        );
        
    }

    public void InsertTipoEquipamiento(TipoEquipamiento oTipoEquipamiento) throws SQLException{
    
       sql="INSERT INTO tipoequipamiento VALUES (null,'"+oTipoEquipamiento.getNombre()+"')";
       oConexion.ejecutar(sql);
       System.out.println(sql);     
       
    }
    
    public DefaultTableModel show_tipo_equipamiento() throws SQLException{
    
    sql="Select * from tipoequipamiento";
    oConexion.ejecutarSelect(sql);
    System.out.println(sql);

    DefaultTableModel modelo = new DefaultTableModel();
    modelo.setColumnIdentifiers(new Object[]{"ID","Nombre"});
    try {
        while (oConexion.rs.next()) {
        modelo.addRow(new Object[]{oConexion.rs.getString("ID"),oConexion.rs.getString("NOMBRE")});
        }
        return modelo;
    } catch (Exception e) {
        System.out.println(e);
    }
        return null;
    
}
    
    public void InsertEquipamiento(Equipamiento oEquipamiento) throws SQLException{
    
       sql="INSERT INTO equipamiento VALUES (null,'"+oEquipamiento.getNombre()+"','"+oEquipamiento.getDescripción()+"',"+oEquipamiento.getTipo_equipamiento_ID()+")";
       oConexion.ejecutar(sql);
       System.out.println(sql);     
       
    }
    
public DefaultComboBoxModel llenar_combobox () throws SQLException{
  
    sql="Select id from tipoequipamiento ";
    oConexion.ejecutarSelect(sql);
    System.out.println(sql);
      
    DefaultComboBoxModel cbo_modelo = new DefaultComboBoxModel();
    cbo_modelo.addElement("Selecciona el ID de una Categoría: ");
    
    try {
            while (oConexion.rs.next()) {
                cbo_modelo.addElement(oConexion.rs.getString("ID"));
            }
            oConexion.rs.close();
    } catch (Exception e) {
          System.out.println(e);
    }
    
    return cbo_modelo;
  
  }

    public DefaultTableModel show_equipamiento() throws SQLException{
    
    sql="Select ID, NOMBRE, DESCRIPCION from equipamiento";
    oConexion.ejecutarSelect(sql);
    System.out.println(sql);

    DefaultTableModel modelo = new DefaultTableModel();
    modelo.setColumnIdentifiers(new Object[]{"ID","Nombre","Descripción"});
    try {
        while (oConexion.rs.next()) {
        modelo.addRow(new Object[]{oConexion.rs.getString("ID"),oConexion.rs.getString("NOMBRE"),oConexion.rs.getString("DESCRIPCION")});
        }
        return modelo;
    } catch (Exception e) {
        System.out.println(e);
    }
        return null;
    
}
    
       public void InserTrainer(Usuario oEntrenador) throws SQLException{
    
       sql="INSERT INTO usuario VALUES (null,'"+oEntrenador.getRut()+"', '"+oEntrenador.getNombre()+"','"+oEntrenador.getApellido()+"', SHA2('"+oEntrenador.getPass()+"',0),'"+oEntrenador.getCorreo()+"',2 )";
       oConexion.ejecutar(sql);
       System.out.println(sql);     
       
    } 
       
       public void InsertTipoActividad(TipoActividad oTipoActividad ) throws SQLException{
           String sql = "INSERT INTO tipoactividad values (null,'"+oTipoActividad.getNombre()+"')";
           oConexion.ejecutar(sql);
           System.out.println(sql);
           
       
       }
       
       public DefaultComboBoxModel llenar_comboboxTipoActividad () throws SQLException{
  
        sql="Select id from tipoActividad ";
        oConexion.ejecutarSelect(sql);
         System.out.println(sql);
      
        DefaultComboBoxModel cbo_modelo = new DefaultComboBoxModel();
        cbo_modelo.addElement("Selecciona el ID del Tipo de Actividad: ");
    
        try {
            while (oConexion.rs.next()) {
                cbo_modelo.addElement(oConexion.rs.getString("ID"));
            }
            oConexion.rs.close();
        } catch (Exception e) {
            System.out.println(e);
        }
         return cbo_modelo;
  
  }
       public boolean isEntrenadorisValid(Usuario oUsuario){
           String sql = "SELECT COUNT(*) AS 'existe' FROM usuario WHERE nombre = '"+oUsuario.getNombre()+"' AND contrasena = SHA2('"+oUsuario.getPass()+"',0); ";
           try {
               ResultSet resultado = oConexion.ejecutarSelect(sql);
               if(resultado.next()){
                   return resultado.getInt("existe") == 1;
               }
               
           } catch (SQLException e) {
                System.out.println(e);
           }
           return false;
       }
       public int obtenerUsuarioId(String nombre){
           String sql ="SELECT id FROM usuario WHERE nombre = '"+nombre+"'";
           try {
               ResultSet resultado = oConexion.ejecutarSelect(sql);
               if (resultado.next()){
                   return resultado.getInt("id");
               }
           } catch (SQLException e) {
               System.out.println(e);
           }
        return 0;  
       }
       
       public int isAdmin (Usuario oUsuario){
            sql = "SELECT tipoUsuario_id_fk FROM usuario WHERE id = '"+oUsuario.getTipoUsuario_id_fk()+"'";
            try {
               ResultSet resultado = oConexion.ejecutarSelect(sql);
               if(resultado.next()){
                   return resultado.getInt("tipoUsuario_id_fk");
               }
           } catch (Exception e) {
                System.out.println(e);
           }
        return 0;
       }

 
public void sett_campos_trainer(Usuario oUsuario) throws SQLException{
  
    sql="Select correo, nombre, apellido,contrasena from usuario where rut='"+oUsuario.getRut()+"' ";
    oConexion.ejecutarSelect(sql);
    
    while (oConexion.rs.next()) {
         oUsuario.setCorreo(oConexion.rs.getString("Correo"));
         oUsuario.setNombre(oConexion.rs.getString("nombre"));
         oUsuario.setApellido(oConexion.rs.getString("apellido"));
         oUsuario.setPass(oConexion.rs.getString("Contrasena"));
    }
    
  
  }

    public void updateTrainer(Usuario oUsuario) throws SQLException{
  
         
       sql="UPDATE usuario SET nombre='"+oUsuario.getNombre()+"',apellido='"+oUsuario.getApellido()+"', contrasena=SHA2('"+oUsuario.getPass()+"',0), correo='"+oUsuario.getCorreo()+"' WHERE RUT='"+oUsuario.getRut()+"' ";
       oConexion.ejecutar(sql);
       System.out.println(sql);
  
  
  
    }
    
    
    public void DeleteTrainer(Usuario oUsuario) throws SQLException{
  
         
       sql="DELETE FROM usuario WHERE RUT='"+oUsuario.getRut()+"' ";
       oConexion.ejecutar(sql);
       System.out.println(sql);
  
  
  
    }
  
    
    public DefaultComboBoxModel llenar_combobox_trainer () throws SQLException{
  
    sql="Select rut from usuario where tipoUsuario_id_fk = 2";
    oConexion.ejecutarSelect(sql);
    System.out.println(sql);
      
    DefaultComboBoxModel cbo_modelo = new DefaultComboBoxModel();
    cbo_modelo.addElement("Selecciona el RUT a eliminar: ");
    
    try {
            while (oConexion.rs.next()) {
                cbo_modelo.addElement(oConexion.rs.getString("RUT"));
            }
            oConexion.rs.close();
    } catch (Exception e) {
          System.out.println(e);
    }
    
    return cbo_modelo;
  
  }
 
}



