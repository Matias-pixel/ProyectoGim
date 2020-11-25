
package BD;

import MODEL.Entrenador;
import MODEL.Equipamiento;
import MODEL.TipoEquipamiento;
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
    
       public void InserTrainer(Entrenador oEntrenador) throws SQLException{
    
       sql="INSERT INTO entrenador VALUES (null,'"+oEntrenador.getRut()+"', '"+oEntrenador.getNombre()+"','"+oEntrenador.getApellido()+"', SHA2('"+oEntrenador.getPass()+"',0),'"+oEntrenador.getCorreo()+"' )";
       oConexion.ejecutar(sql);
       System.out.println(sql);     
       
    } 
    
     
     
     

    
    
    
 
}
