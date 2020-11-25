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
public class Equipamiento {
    private String Nombre, Descripción;
    private int id, Tipo_equipamiento_ID;

    public Equipamiento() {
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getDescripción() {
        return Descripción;
    }

    public void setDescripción(String Descripción) {
        this.Descripción = Descripción;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTipo_equipamiento_ID() {
        return Tipo_equipamiento_ID;
    }

    public void setTipo_equipamiento_ID(int Tipo_equipamiento_ID) {
        this.Tipo_equipamiento_ID = Tipo_equipamiento_ID;
    }

    
    
    
}
