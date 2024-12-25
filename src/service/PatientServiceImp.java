package service;

import Models.Patient;
import dao.PatientDaoImp;
import service.impl.GenericService;
import service.impl.PatientService;

import java.util.List;
import java.util.Map;

public class PatientServiceImp implements GenericService<Patient>, PatientService {
    private final PatientDaoImp patientDaoImp;

    public PatientServiceImp(PatientDaoImp patientDaoImp) {
        this.patientDaoImp = patientDaoImp;
    }

    @Override
    public String add(Long hospitalId, Patient patient) {
        return patientDaoImp.add(hospitalId,patient);
    }

    @Override
    public void removeById(Long id) {
     patientDaoImp.removeById(id);
    }

    @Override
    public String updateById(Long id, Patient patient) {
        return patientDaoImp.updateById(id,patient) ;
    }

    @Override
    public String addPatientsToHospital(Long id, List<Patient> patients) {
        return patientDaoImp.addPatientsToHospital(id,patients);
    }

    @Override
    public Patient getPatientById(Long id) {
        return patientDaoImp.getPatientById(id);
    }

    @Override
    public Map<Integer, List<Patient>> getPatientByAge() {
        return patientDaoImp.getPatientByAge();
    }

    @Override
    public List<Patient> sortPatientsByAge(String ascOrDesc) {
        return patientDaoImp.sortPatientsByAge(ascOrDesc);
    }
}
