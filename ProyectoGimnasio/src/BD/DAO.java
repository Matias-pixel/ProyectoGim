package BD;


import MODEL.Actividad;


import MODEL.Usuario;
import MODEL.Equipamiento;
import MODEL.TipoEquipamiento;
import java.sql.ResultSet;
import java.sql.SQLException;// import para manejar excepciones SQL
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;

public class DAO {

    private final Conexion oConexion;//se crea sin inicializar el objeto como atributo de la Clase DAO
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

    public void insertTipoEquipamiento(TipoEquipamiento oTipoEquipamiento) throws SQLException{
    
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
    } catch (SQLException e) {
        System.out.println(e);
    }
        return null;
    
}
    
    public void insertEquipamiento(Equipamiento oEquipamiento) throws SQLException{
    
       sql="INSERT INTO equipamiento VALUES (null,'"+oEquipamiento.getNombre()+"','"+oEquipamiento.getDescripción()+"',"+oEquipamiento.getCantidad()+","+oEquipamiento.getTipo_equipamiento_ID()+")";
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
    } catch (SQLException e) {
          System.out.println(e);
    }
    
    return cbo_modelo;
  
  }

    public DefaultTableModel show_equipamiento() throws SQLException{
    
    sql="Select ID, NOMBRE, DESCRIPCION, cantidad from equipamiento";
    oConexion.ejecutarSelect(sql);
    System.out.println(sql);

    DefaultTableModel modelo = new DefaultTableModel();
    modelo.setColumnIdentifiers(new Object[]{"ID","Nombre","Descripción","Cantidad"});
    try {
        while (oConexion.rs.next()) {
        modelo.addRow(new Object[]{oConexion.rs.getString("ID"),oConexion.rs.getString("NOMBRE"),oConexion.rs.getString("DESCRIPCION"),oConexion.rs.getInt("cantidad")});
        }
        return modelo;
    } catch (SQLException e) {
        System.out.println(e);
    }
        return null;
    
}
    
       public void inserTrainer(Usuario oEntrenador) throws SQLException{
    
       sql="INSERT INTO usuario VALUES (null,'"+oEntrenador.getRut()+"', '"+oEntrenador.getNombre()+"','"+oEntrenador.getApellido()+"', SHA2('"+oEntrenador.getPass()+"',0),'"+oEntrenador.getCorreo()+"',2 )";
       oConexion.ejecutar(sql);
       System.out.println(sql);     
       
    } 
       
       public void insertTipoActividad(String text ) throws SQLException{
           sql = "INSERT INTO tipoactividad values (null,'"+text+"')";
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
                cbo_modelo.addElement(oConexion.rs.getString("id"));
            }
            oConexion.rs.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
         return cbo_modelo;
  
  }
       public boolean isUsuarioIsValid(Usuario oUsuario){
           sql = "SELECT COUNT(*) AS 'existe' FROM usuario WHERE correo = '"+oUsuario.getCorreo()+"' AND contrasena = SHA2('"+oUsuario.getPass()+"',0) and (tipoUsuario_id_fk = 1 or tipoUsuario_id_fk = 2) ";
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
       public String obtenerUsuarioId(String Correo){
           sql ="Select tipousuario.nombre as 'Tipo' \n" +
                "from usuario\n" +
                "inner join tipousuario on tipousuario.id = usuario.tipoUsuario_id_fk\n" +
                "where usuario.correo = '"+Correo+"'";
           try {
               ResultSet resultado = oConexion.ejecutarSelect(sql);
               if (resultado.next()){
                   return resultado.getString("Tipo");
               }
           } catch (SQLException e) {
               System.out.println(e);
           }
        return null;
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
    
    
    public void deleteTrainer(Usuario oUsuario) throws SQLException{
       sql="UPDATE usuario SET Tipousuario_id_fk='3' WHERE RUT='"+oUsuario.getRut()+"' ";
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
    } catch (SQLException e) {
          System.out.println(e);
    }
    return cbo_modelo;
  }
    
    public DefaultTableModel show_tipo_actividad() throws SQLException{
    
    sql="Select * from tipoactividad ORDER BY id, nombre ASC";
    oConexion.ejecutarSelect(sql);
    DefaultTableModel modelo = new DefaultTableModel();
    modelo.setColumnIdentifiers(new Object[]{"ID","Nombre"});
    try {
        while (oConexion.rs.next()) {
        modelo.addRow(new Object[]{oConexion.rs.getString("ID"),oConexion.rs.getString("nombre")});
        }
        return modelo;
    } catch (SQLException e) {
        System.out.println(e);
    }
        return null;
    
}
    public DefaultComboBoxModel llenar_combobox_equipamiento () throws SQLException{
  
    sql="Select id from equipamiento ";
    oConexion.ejecutarSelect(sql);
    
      
    DefaultComboBoxModel cbo_modelo = new DefaultComboBoxModel();
    cbo_modelo.addElement("Selecciona el nombre de un equipamiento: ");
    
    try {
            while (oConexion.rs.next()) {
                cbo_modelo.addElement(oConexion.rs.getString("id"));
            }
            oConexion.rs.close();
    } catch (SQLException e) {
          System.out.println(e);
    }
    return cbo_modelo;
  }
    
    public void insertActividad(Actividad oActividad) throws SQLException{
        sql = "Insert into actividad VALUES (null,'"+oActividad.getNombre()+"','"+oActividad.getDescripción()+"','"+oActividad.getCupos()+"','"+oActividad.getFecha()+"','"+oActividad.getEntrenador_ID()+"')";
        oConexion.ejecutar(sql);
    
    }
    
    
    public DefaultTableModel show_actividad() throws SQLException{
    
    sql="Select * from actividad";
    oConexion.ejecutarSelect(sql);
    System.out.println(sql);

    DefaultTableModel modelo = new DefaultTableModel();
    modelo.setColumnIdentifiers(new Object[]{"ID","Nombre","Descripción","Cupos","fecha","equipamiento","tipo","Entrenador"});
    
    try {
        while (oConexion.rs.next()){
        modelo.addRow(new Object[]{oConexion.rs.getString("ID"),oConexion.rs.getString("NOMBRE"),oConexion.rs.getString("DESCRIPCION"),oConexion.rs.getString("cupos"),oConexion.rs.getString("fecha"),oConexion.rs.getString("equipamiento_id_fk"),oConexion.rs.getString("tipoActividad_id_fk"),oConexion.rs.getString("usuario_id_fk")});
        }
        return modelo;
    } catch (SQLException e) {
        System.out.println(e);
    }
        return null;
}
   

public DefaultTableModel show_equip_orden(int param) throws SQLException{
    
    sql="CALL equipamiento_mas_usados("+param+")";
    oConexion.ejecutarSelect(sql);
    
    DefaultTableModel modelo = new DefaultTableModel();
    modelo.setColumnIdentifiers(new Object[]{"NOMBRE","VECES USADA"});
    try {
        while (oConexion.rs.next()) {
        modelo.addRow(new Object[]{oConexion.rs.getString("NOMBRE"),oConexion.rs.getString("VECES USADA")});
        }
        return modelo;
    } catch (SQLException e) {
        System.out.println(e);
    }
        return null;
    
}
    
    
public DefaultTableModel show_actividades_bituin(String fecha1, String fecha2) throws SQLException{
    
    sql="CALL calculos_fecha_entre('"+fecha1+"','"+fecha2+"')";
    oConexion.ejecutarSelect(sql);
    
    DefaultTableModel modelo = new DefaultTableModel();
    modelo.setColumnIdentifiers(new Object[]{"NOMBRE","DESCRIPCIÓN","FECHA","ENTRENADOR"});
    try {
        while (oConexion.rs.next()) {
        modelo.addRow(new Object[]{oConexion.rs.getString("NOMBRE"),oConexion.rs.getString("DESCRIPCION"),oConexion.rs.getString("FECHA"),oConexion.rs.getString("NOMBRE")});
        }
        return modelo;
    } catch (SQLException e) {
        System.out.println(e);
    }
        return null;
    
}

public DefaultTableModel show_trigger_2() throws SQLException{
    
    sql="SELECT ID, ANTIGUO_STOCK, NUEVO_STOCK FROM historialequipamiento";
    oConexion.ejecutarSelect(sql);
    
    DefaultTableModel modelo = new DefaultTableModel();
    modelo.setColumnIdentifiers(new Object[]{"ID","ANTIGUO STOCK","NUEVO STOCK"});
    try {
        while (oConexion.rs.next()) {
        modelo.addRow(new Object[]{oConexion.rs.getString("ID"),oConexion.rs.getString("ANTIGUO_STOCK"),oConexion.rs.getString("NUEVO_STOCK")});
        }
        return modelo;
    } catch (SQLException e) {
        System.out.println(e);
    }
        return null;
    
}
    public int idByName (String txt){
            sql = "SELECT id FROM usuario where nombre = '"+txt+"'";
            try {
               ResultSet resultado = oConexion.ejecutarSelect(sql);
               if(resultado.next()){
                   return resultado.getInt("tipo");
               }
           } catch (SQLException e) {
                System.out.println(e);
           }
        return 0;
       }
    
    public DefaultComboBoxModel llenar_comboboxEntrenador () throws SQLException{
  
        sql="Select id from usuario where tipoUsuario_id_fk = 2 ";
        oConexion.ejecutarSelect(sql);
        System.out.println(sql);
      
        DefaultComboBoxModel cbo_modelo = new DefaultComboBoxModel();
        cbo_modelo.addElement("Selecciona el ID del Entrenador: ");
        
        try {
            while (oConexion.rs.next()) {
                cbo_modelo.addElement(oConexion.rs.getString("id"));
            }
            oConexion.rs.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
         return cbo_modelo;
  
  }


 




 
}



