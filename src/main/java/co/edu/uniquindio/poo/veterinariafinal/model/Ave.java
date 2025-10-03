package co.edu.uniquindio.poo.veterinariafinal.model;

public class Ave extends Mascota{

    private String tipoPlumaje;
    private int sonidosImitados;
    private Vuela vuela;

    public Ave(String nombre,String raza,int edad,double pesoKg,String id,Dueno dueno,String tipoPlumaje,int sonidoImitados,Vuela vuela){
        super(nombre,raza,edad,pesoKg,id,dueno);

        this.tipoPlumaje = tipoPlumaje;
        this.sonidosImitados = sonidosImitados;
        this.vuela = vuela;
    }

    public String getTipoPlumaje() {
        return tipoPlumaje;
    }

    public void setTipoPlumaje(String tipoPlumaje) {
        this.tipoPlumaje = tipoPlumaje;
    }

    public int getSonidosImitados() {
        return sonidosImitados;
    }

    public void setSonidosImitados(int sonidosImitados) {
        this.sonidosImitados = sonidosImitados;
    }

    public Vuela getVuela() {
        return vuela;
    }

    public void setVuela(Vuela vuela) {
        this.vuela = vuela;
    }
}
