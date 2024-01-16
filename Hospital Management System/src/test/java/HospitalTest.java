import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class HospitalTest {

    private Hospital hospital;

    @Before
    public void setUp() {
        hospital = new Hospital();
    }

    @After
    public void tearDown() {
        hospital.closeConnection();
    }

    @Test
    public void testAddPatient() {

        Patient patient = new Patient("Dzuni Mukansi", 130);

        hospital.addPatient(patient);
        ArrayList<Patient> patients = hospital.getAllPatients();

        assertEquals(1, patients.size());
        assertEquals(patient.getName(), patients.get(0).getName());
        assertEquals(patient.getAge(), patients.get(0).getAge());
    }

    @Test
    public void testGetAllPatientsEmpty() {

        ArrayList<Patient> patients = hospital.getAllPatients();

        assertEquals(0, patients.size());
    }

}
