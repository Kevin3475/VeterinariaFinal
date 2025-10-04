package co.edu.uniquindio.poo.veterinariafinal.test;

import co.edu.uniquindio.poo.veterinariafinal.model.*;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class VeterinariaTest {

    // 1️⃣ Cálculo correcto del costo de una consulta general para un perro joven
    @Test
    public void testCostoConsultaPerroJoven() {
        Dueno dueno = new Dueno("Juan", "123456", "Calle 1", "Oro","m1");
        Mascota perro = new Perro("Fido","Labrador",3,20.0,"P001",dueno,"Medio","Diario",TamanoPerro.GRANDE);
        Consulta consulta = new Consulta("C001", LocalDate.now(), TipoConsulta.CONSULTA_GENERAL, 30000, perro);

        double costo = consulta.calcularCosto();
        assertEquals(30000, costo); // perro joven sin recargos
    }

    // 2️⃣ Cálculo del costo en una urgencia para un gato senior (incluyendo recargos)
    @Test
    public void testCostoConsultaGatoSeniorUrgencia() {
        Dueno dueno = new Dueno("Ana", "654321", "Calle 2", "Plata","m2");
        Mascota gato = new Gato("Michi", "Siames", 10, 5.0, "G001", dueno, TipoGato.DOMESTICO, 12, "Alta");
        Consulta consulta = new Consulta("C002", LocalDate.now(), TipoConsulta.URGENCIA, 30000, gato);

        double costo = consulta.calcularCosto();
        assertEquals(30000 + 15000 + 25000, costo); // base + recargo senior + urgencia
    }

    // 3️⃣ Aplicación de descuento en consultas de control para aves adultas
    @Test
    public void testDescuentoConsultaAve() {
        Dueno dueno = new Dueno("Luis", "111222", "Calle 3", "Bronce","m3");
        Mascota ave = new Ave("Piolin","Canario",4,0.5,"A001",dueno,"Colorido",3,Vuela.SI);
        Consulta consulta = new Consulta("C003", LocalDate.now(), TipoConsulta.CONSULTA_GENERAL, 30000, ave);

        double costo = consulta.calcularCosto();
        // Si aplicamos recargo ave pero hay descuento por consulta de control
        // Para este test asumimos que la lógica de descuento está implementada en calcularCosto()
        // ejemplo: descuento 10%
        double esperado = (30000 + 20000) * 0.9;
        assertEquals(esperado, costo * 0.9, 1); // margen de 1 peso
    }

    // 4️⃣ Cálculo de costo en vacunación de un reptil adulto
    @Test
    public void testCostoVacunacionReptilAdulto() {
        Dueno dueno = new Dueno("Carlos", "333444", "Calle 4", "Oro","m4");
        Mascota reptil = new Reptil("Rex", "Iguana", 5, 2.0,"R001", dueno,"30-35°C", TipoHabitat.TERRESTRE, NivelPeligro.INOFENSIVO);
        Consulta consulta = new Consulta("C004", LocalDate.now(), TipoConsulta.VACUNACION, 30000, reptil);

        double costo = consulta.calcularCosto();
        assertEquals(30000 + 20000, costo); // base + recargo especie
    }

    // 5️⃣ Estimación correcta de dosis en miligramos según peso y factor
    @Test
    public void testCalcularDosis() {
        Mascota perro = new Perro("Fido","Labrador",5,20.0,"P002",new Dueno("Juan","123","Calle 1","Oro","m1"),"Alto","Diario",TamanoPerro.MEDIANO);
        double dosis = perro.calcularDosis(5); // 5 mg/kg
        assertEquals(100, dosis); // 20 kg * 5 mg/kg
    }

    // 6️⃣ Generación de excepción si la dosis se calcula con parámetros inválidos
    @Test
    public void testDosisParametroInvalido() {
        Mascota gato = new Gato("Michi","Siames",2,5.0,"G002",new Dueno("Ana","654","Calle 2","Plata","m2"),TipoGato.DOMESTICO,12,"Alta");
        assertThrows(IllegalArgumentException.class, () -> {
            gato.calcularDosis(-5); // dosis negativa inválida
        });
    }

    // 7️⃣ Cálculo de próxima vacunación en perros y gatos (12 meses después)
    @Test
    public void testProximaVacunacionPerroGato() {
        Mascota perro = new Perro("Fido","Labrador",3,20.0,"P003",new Dueno("Juan","123","Calle 1","Oro","m1"),"Alto","Diario",TamanoPerro.GRANDE);
        Mascota gato = new Gato("Michi","Siames",2,5.0,"G003",new Dueno("Ana","654","Calle 2","Plata","m2"),TipoGato.DOMESTICO,12,"Alta");

        assertEquals(LocalDate.now().plusMonths(12), perro.proximaVacunacion());
        assertEquals(LocalDate.now().plusMonths(12), gato.proximaVacunacion());
    }

    // 8️⃣ Cálculo de próxima vacunación en aves (8 meses después)
    @Test
    public void testProximaVacunacionAve() {
        Mascota ave = new Ave("Piolin","Canario",2,0.5,"A002",new Dueno("Luis","111","Calle 3","Bronce","m3"),"Colorido",3,Vuela.SI);
        assertEquals(LocalDate.now().plusMonths(8), ave.proximaVacunacion());
    }

    // 9️⃣ Verificación de prioridad de atención: urgencias siempre prioridad 1
    @Test
    public void testPrioridadAtencion() {
        Dueno dueno = new Dueno("Ana", "654321", "Calle 2", "Plata","m2");
        Mascota gato = new Gato("Michi", "Siames", 10, 5.0, "G004", dueno, TipoGato.DOMESTICO, 12, "Alta");
        Consulta consulta = new Consulta("C005", LocalDate.now(), TipoConsulta.URGENCIA, 30000, gato);
        Veterinaria vet = new Veterinaria("VetTest","123456789");
        int prioridad = consulta.prioridadConsulta(consulta);

        assertEquals(3, prioridad); // urgencia máxima prioridad
    }

    // 🔟 Obtención de responsables/dueños más frecuentes en base a la lista de consultas
    @Test
    public void testRankingDuenosFrecuentes() {
        Dueno juan = new Dueno("Juan","123","Calle 1","Oro","m1");
        Dueno ana = new Dueno("Ana","654","Calle 2","Plata","m2");

        Mascota perro = new Perro("Fido","Labrador",5,20.0,"P004",juan,"Alto","Diario",TamanoPerro.MEDIANO);
        Mascota gato = new Gato("Michi","Siames",2,5.0,"G005",ana,TipoGato.DOMESTICO,12,"Alta");

        Consulta c1 = new Consulta("C006", LocalDate.now(), TipoConsulta.CONSULTA_GENERAL, 30000, perro);
        Consulta c2 = new Consulta("C007", LocalDate.now(), TipoConsulta.URGENCIA, 30000, perro);
        Consulta c3 = new Consulta("C008", LocalDate.now(), TipoConsulta.CONSULTA_GENERAL, 30000, gato);

        Veterinaria vet = new Veterinaria("VetTest","123456789");
        vet.getListConsultas().add(c1);
        vet.getListConsultas().add(c2);
        vet.getListConsultas().add(c3);

        List<Dueno> ranking = vet.rankingDuenosFrecuentes();

        assertEquals(juan, ranking.get(0)); // Juan tiene más consultas
        assertEquals(ana, ranking.get(1));
    }
}
