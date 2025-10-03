package co.edu.uniquindio.poo.veterinariafinal.model;

public class Perro extends Mascota{

    private String nivelAdiestramiento;
    private String necesidadPaseo;
    private TamanoPerro tamanoPerro;

    public Perro(String nombre,String raza,int edad,double pesoKg,String id,Dueno dueno,String nivelAdiestramiento,String necesidadPaseo,TamanoPerro tamanoPerro){
        super(nombre,raza,edad,pesoKg,id,dueno);

        this.nivelAdiestramiento = nivelAdiestramiento;
        this.necesidadPaseo = necesidadPaseo;
        this.tamanoPerro = tamanoPerro;


    }


    public String getNivelAdiestramiento() {
        return nivelAdiestramiento;
    }

    public void setNivelAdiestramiento(String nivelAdiestramiento) {
        this.nivelAdiestramiento = nivelAdiestramiento;
    }

    public String getNecesidadPaseo() {
        return necesidadPaseo;
    }

    public void setNecesidadPaseo(String necesidadPaseo) {
        this.necesidadPaseo = necesidadPaseo;
    }

    public TamanoPerro getTamanoPerro() {
        return tamanoPerro;
    }

    public void setTamanoPerro(TamanoPerro tamanoPerro) {
        this.tamanoPerro = tamanoPerro;
    }

    @Override
    public String toString() {
        return "Perro{" +
                "nivelAdiestramiento='" + nivelAdiestramiento + '\'' +
                ", necesidadPaseo='" + necesidadPaseo + '\'' +
                ", tamanoPerro=" + tamanoPerro +
                '}';
    }
}
