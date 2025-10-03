package co.edu.uniquindio.poo.veterinariafinal.model;

public class Reptil extends Mascota{

    private String temperaturaOptima;
    private TipoHabitat tipoHabitat;
    private NivelPeligro nivelPeligro;

    public Reptil(String nombre,String raza,int edad,double pesoKg,String id,Dueno dueno,String temperaturaOptima,TipoHabitat tipoHabitat,NivelPeligro nivelPeligro){
        super(nombre,raza,edad,pesoKg,id,dueno);

        this.temperaturaOptima = temperaturaOptima;
        this.tipoHabitat = tipoHabitat;
        this.nivelPeligro = nivelPeligro;


    }

    public String getTemperaturaOptima() {
        return temperaturaOptima;
    }

    public void setTemperaturaOptima(String temperaturaOptima) {
        this.temperaturaOptima = temperaturaOptima;
    }

    public TipoHabitat getTipoHabitat() {
        return tipoHabitat;
    }

    public void setTipoHabitat(TipoHabitat tipoHabitat) {
        this.tipoHabitat = tipoHabitat;
    }

    public NivelPeligro getNivelPeligro() {
        return nivelPeligro;
    }

    public void setNivelPeligro(NivelPeligro nivelPeligro) {
        this.nivelPeligro = nivelPeligro;
    }

    @Override
    public String toString() {
        return "Reptil{" +
                "temperaturaOptima='" + temperaturaOptima + '\'' +
                ", tipoHabitat=" + tipoHabitat +
                ", nivelPeligro=" + nivelPeligro +
                '}';
    }
}
