package co.edu.uniquindio.poo.veterinariafinal.model;



import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Veterinaria {

    private String nombre;
    private String nit;
    private List<Mascota> listMascotas;
    private List<Consulta> listConsultas;
    private ObservableList<Dueno> listDuenos;

    public Veterinaria(String nombre,String nit){

        this.nombre = nombre;
        this.nit = nit;
        this.listMascotas = new ArrayList<>();
        this.listConsultas = new ArrayList<>();
        this.listDuenos = FXCollections.observableArrayList();


    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public List<Consulta> getListConsultas() {
        return listConsultas;
    }

    public void setListConsultas(List<Consulta> listConsultas) {
        this.listConsultas = listConsultas;
    }

    public List<Mascota> getListMascotas() {
        return listMascotas;
    }

    public void setListMascotas(List<Mascota> listMascotas) {
        this.listMascotas = listMascotas;
    }


    public ObservableList<Dueno> getListDuenos() {
        return listDuenos;
    }

    public void setListDuenos(ObservableList<Dueno> listDuenos) {
        this.listDuenos = listDuenos;
    }

    public Veterinaria() {
        this.listDuenos = FXCollections.observableArrayList();
    }

    @Override
    public String toString() {
        return "Veterinaria{" +
                "nombre='" + nombre + '\'' +
                ", nit='" + nit + '\'' +
                ", listMascotas=" + listMascotas +
                ", listConsultas=" + listConsultas +
                ", listDuenos=" + listDuenos +
                '}';
    }


    public List<Dueno> rankingDuenosFrecuentes() {
        Map<Dueno, Long> contador = listConsultas.stream()
                .collect(Collectors.groupingBy(c -> c.getMascota().getDueno(), Collectors.counting()));

        // Ordenar descendente según número de visitas
        List<Dueno> ranking = contador.entrySet().stream()
                .sorted(Map.Entry.<Dueno, Long>comparingByValue().reversed())
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());

        return ranking;
    }



    public boolean agregarDueno(Dueno dueno) {
        if (buscarDuenoPorId(dueno.getId()) == null) {
            listDuenos.add(dueno);
            return true;
        }
        return false;
    }

    public Dueno buscarDuenoPorId(String id) {
        for (Dueno d : listDuenos) {
            if (d.getId().equals(id)) {
                return d;
            }
        }
        return null;
    }

    public boolean eliminarDueno(String id) {
        Dueno dueno = buscarDuenoPorId(id);
        if (dueno != null) {
            listDuenos.remove(dueno);
            return true;
        }
        return false;
    }

    public boolean actualizarDueno(Dueno duenoActualizado) {
        Dueno duenoExistente = buscarDuenoPorId(duenoActualizado.getId());
        if (duenoExistente != null) {
            duenoExistente.setNombre(duenoActualizado.getNombre());
            duenoExistente.setNumero(duenoActualizado.getNumero());
            duenoExistente.setPuntajeFidelidad(duenoActualizado.getPuntajeFidelidad());
            duenoExistente.setDireccion(duenoActualizado.getDireccion());
            return true;
        }
        return false;
    }


    public boolean agregarMascota(Mascota mascota) {
        if (buscarMascotaPorCodigo(mascota.getId()) == null) {
            listMascotas.add(mascota);
            return true;
        }
        return false;
    }


    public boolean registrarMascota(Mascota mascota, String idDueno) {
        for (Dueno dueno : listDuenos) {
            if (dueno.getId().equals(idDueno)) {
                mascota.setDueno(dueno);        // ✅ Asociar el dueño
                listMascotas.add(mascota);     // ✅ Agregar la mascota
                return true;
            }
        }
        return false; // ❌ Dueño no encontrado
    }




    public Mascota buscarMascotaPorCodigo(String id) {
        for (Mascota m : listMascotas) {
            if (m.getId().equals(id)) {
                return m;
            }
        }
        return null;
    }

    public boolean actualizarMascota(Mascota mascotaActualizada) {
        Mascota existente = buscarMascotaPorCodigo(mascotaActualizada.getId());
        if (existente != null) {
            existente.setNombre(mascotaActualizada.getNombre());
            existente.setRaza(mascotaActualizada.getRaza());
            existente.setEdad(mascotaActualizada.getEdad());
            existente.setPesoKg(mascotaActualizada.getPesoKg());
            existente.setId(mascotaActualizada.getId());
            existente.setDueno(mascotaActualizada.getDueno());

            // 👇 Actualiza los atributos específicos según el tipo
            if (existente instanceof Perro && mascotaActualizada instanceof Perro) {
                ((Perro) existente).setNivelAdiestramiento(((Perro) mascotaActualizada).getNivelAdiestramiento());
                ((Perro) existente).setNecesidadPaseo(((Perro) mascotaActualizada).getNecesidadPaseo());
                ((Perro) existente).setTamanoPerro(((Perro) mascotaActualizada).getTamanoPerro());
            } else if (existente instanceof Gato && mascotaActualizada instanceof Gato) {
                ((Gato) existente).setTipoGato(((Gato) mascotaActualizada).getTipoGato());
                ((Gato) existente).setHorasSueno(((Gato) mascotaActualizada).getHorasSueno());
                ((Gato) existente).setNivelIndependencia(((Gato) mascotaActualizada).getNivelIndependencia());
            } else if (existente instanceof Ave && mascotaActualizada instanceof Ave) {
                ((Ave) existente).setTipoPlumaje(((Ave) mascotaActualizada).getTipoPlumaje());
                ((Ave) existente).setSonidosImitados(((Ave) mascotaActualizada).getSonidosImitados());
                ((Ave) existente).setVuela(((Ave) mascotaActualizada).getVuela());
            } else if (existente instanceof Reptil && mascotaActualizada instanceof Reptil) {
                ((Reptil) existente).setTemperaturaOptima(((Reptil) mascotaActualizada).getTemperaturaOptima());
                ((Reptil) existente).setTipoHabitat(((Reptil) mascotaActualizada).getTipoHabitat());
                ((Reptil) existente).setNivelPeligro(((Reptil) mascotaActualizada).getNivelPeligro());
            }

            return true;
        }
        return false;
    }

    public boolean eliminarMascota(String codigo) {
        Mascota mascota = buscarMascotaPorCodigo(codigo);
        if (mascota != null) {
            listMascotas.remove(mascota);
            return true;
        }
        return false;
    }

    public List<Mascota> getListaMascotas() {
        return listMascotas;
    }

    // ===============================
    //      FILTROS POR TIPO
    // ===============================

    public List<Perro> getListaPerros() {
        List<Perro> perros = new ArrayList<>();
        for (Mascota m : listMascotas) {
            if (m instanceof Perro) {
                perros.add((Perro) m);
            }
        }
        return perros;
    }

    public List<Gato> getListaGatos() {
        List<Gato> gatos = new ArrayList<>();
        for (Mascota m : listMascotas) {
            if (m instanceof Gato) {
                gatos.add((Gato) m);
            }
        }
        return gatos;
    }

    public List<Ave> getListaAves() {
        List<Ave> aves = new ArrayList<>();
        for (Mascota m : listMascotas) {
            if (m instanceof Ave) {
                aves.add((Ave) m);
            }
        }
        return aves;
    }

    public List<Reptil> getListaReptiles() {
        List<Reptil> reptiles = new ArrayList<>();
        for (Mascota m : listMascotas) {
            if (m instanceof Reptil) {
                reptiles.add((Reptil) m);
            }
        }
        return reptiles;
    }
}



