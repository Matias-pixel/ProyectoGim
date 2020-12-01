/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MODEL;

/**
 *
 * @author Esteban V
 */
public class Usuario {
    private String Rut,Nombre,Apellido,Pass,Correo;
    private int id , tipoUsuario_id_fk;

    public Usuario() {
    }

    
    public Usuario(String Nombre, String Pass) {
        this.Nombre = Nombre;
        this.Pass = Pass;
    }
    
    public Usuario(int id ,String Nombre, String Pass){
        this.id = id;
        this.Nombre = Nombre;
        this.Pass = Pass;
    }

    public int getTipoUsuario_id_fk() {
        return tipoUsuario_id_fk;
    }

    public void setTipoUsuario_id_fk(int tipoUsuario_id_fk) {
        this.tipoUsuario_id_fk = tipoUsuario_id_fk;
    }
    
    
    

    public String getRut() {
        return Rut;
    }

    public void setRut(String Rut) {
        this.Rut = Rut;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getApellido() {
        return Apellido;
    }

    public void setApellido(String Apellido) {
        this.Apellido = Apellido;
    }

    public String getPass() {
        return Pass;
    }

    public void setPass(String Pass) {
        this.Pass = Pass;
    }

    public String getCorreo() {
        return Correo;
    }

    public void setCorreo(String Correo) {
        this.Correo = Correo;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    
    
}
