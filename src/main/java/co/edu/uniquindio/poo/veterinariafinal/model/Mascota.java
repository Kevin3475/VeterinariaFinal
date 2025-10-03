package co.edu.uniquindio.poo.veterinariafinal.model;

import java.time.LocalDate;

public abstract class Mascota {

     private String nombre;
     private String raza;
     private int edad;
     private double pesoKg;
     private String id;
     private Dueno dueno;

     public Mascota(String nombre,String raza,int edad,double pesoKg,String id,Dueno dueno){

         this.nombre = nombre;
         this.raza = raza;
         this.edad = edad;
         this.pesoKg = pesoKg;
         this.id = id;
         this.dueno = dueno;
     }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRaza() {
        return raza;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public double getPesoKg() {
        return pesoKg;
    }

    public void setPesoKg(double pesoKg) {
        this.pesoKg = pesoKg;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Dueno getDueno() {
        return dueno;
    }

    public void setDueno(Dueno dueno) {
        this.dueno = dueno;
    }


    @Override
    public String toString() {
        return "Mascota{" +
                "nombre='" + nombre + '\'' +
                ", raza='" + raza + '\'' +
                ", edad=" + edad +
                ", pesoKg=" + pesoKg +
                ", id='" + id + '\'' +
                ", dueno=" + dueno +
                '}';
    }

    public double calcularDosis(double mgPorKg) {
        if (mgPorKg <= 0) {
            throw new IllegalArgumentException("El factor de dosis debe ser mayor a 0");
        }
        return pesoKg * mgPorKg;
    }



    public LocalDate proximaVacunacion() {
        int meses;
        if (this instanceof Perro || this instanceof Gato) {
            meses = 12;
        } else if (this instanceof Ave) {
            meses = 8;
        } else if (this instanceof Reptil) {
            meses = 18;
        } else {
            meses = 12; // valor por defecto
        }
        return LocalDate.now().plusMonths(meses);
    }



}
