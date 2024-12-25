package dao.imp;

import Models.Patient;

import java.util.List;
import java.util.Map;

public interface PatientDao {

    String addPatientsToHospital(Long id, List<Patient> patients);

    Patient getPatientById(Long id);

    Map<Integer, List<Patient>> getPatientByAge();

    List<Patient> sortPatientsByAge(String ascOrDesc);
}
