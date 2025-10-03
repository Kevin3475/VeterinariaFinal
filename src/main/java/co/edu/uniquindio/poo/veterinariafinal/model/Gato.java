package co.edu.uniquindio.poo.veterinariafinal.model;

public class Gato extends Mascota {

    private TipoGato tipoGato;
    private int horasSueno;
    private String nivelIndependencia;

    public Gato(String nombre,String raza,int edad,double pesoKg,String id,Dueno dueno,TipoGato tipoGato,int horasSueno,String nivelIndependencia){
        super(nombre,raza,edad,pesoKg,id,dueno);

        this.tipoGato = tipoGato;
        this.horasSueno = horasSueno;
        this.nivelIndependencia = nivelIndependencia;

    }

    public TipoGato getTipoGato() {
        return tipoGato;
    }

    public void setTipoGato(TipoGato tipoGato) {
        this.tipoGato = tipoGato;
    }

    public int getHorasSueno() {
        return horasSueno;
    }

    public void setHorasSueno(int horasSueno) {
        this.horasSueno = horasSueno;
    }

    public String getNivelIndependencia() {
        return nivelIndependencia;
    }

    public void setNivelIndependencia(String nivelIndependencia) {
        this.nivelIndependencia = nivelIndependencia;
    }

    @Override
    public String toString() {
        return "Gato{" +
                "tipoGato=" + tipoGato +
                ", horasSueno=" + horasSueno +
                ", nivelIndependencia='" + nivelIndependencia + '\'' +
                '}';
    }
}
