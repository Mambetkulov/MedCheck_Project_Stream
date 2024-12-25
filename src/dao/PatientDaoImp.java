package dao;
import Models.Patient;
import dao.imp.PatientDao;
import database.DataBase;
import service.impl.GenericService;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PatientDaoImp implements GenericService<Patient>, PatientDao {


    @Override
    public String addPatientsToHospital(Long id, List<Patient> patients) {
          boolean isTrue = DataBase.hospitals.stream()
                .filter(hospital -> hospital.getId().equals(id))
                .findFirst()
                  .map(hospital -> hospital.getPatients().addAll(patients))
                  .orElse(false);

          if(isTrue){
              return "successfully added";
          }
          return "Hospital with this id not found";
    }

    @Override
    public Patient getPatientById(Long id) {
        return DataBase.hospitals.stream()
                .flatMap(hospital -> hospital.getPatients().stream()
                        .filter(patient -> patient.getId().equals(id))
                        .map(patient -> hospital.getPatients().get(hospital.getPatients().indexOf(patient))))
                .findFirst()
                .orElse(null);

    }

    @Override
    public Map<Integer, List<Patient>> getPatientByAge() {
        Map<Integer, List<Patient>> patientsByAge = new HashMap<>();

        List<Patient> patients = DataBase.hospitals.stream()
                .flatMap(hospital -> hospital.getPatients().stream())
                .sorted(Comparator.comparingInt(Patient::getAge))
                .collect(Collectors.toList());

        if (!patients.isEmpty()) {
            patientsByAge.put(1, patients);
        }

        return patientsByAge;
    }

    @Override
    public List<Patient> sortPatientsByAge(String ascOrDesc) {
        List<Patient> patients = DataBase.hospitals.stream()
                .flatMap(hospital -> hospital.getPatients().stream())
                .collect(Collectors.toList());

        if (ascOrDesc.equalsIgnoreCase("A")) {
            patients.sort(Comparator.comparingInt(Patient::getAge));
        } else if (ascOrDesc.equalsIgnoreCase("D")) {
            patients.sort(Comparator.comparingInt(Patient::getAge).reversed());
        } else {
            System.out.println("Invalid sorting order");
        }

        return patients;
    }

    @Override
    public String add(Long hospitalId, Patient patient) {
        return DataBase.hospitals.stream()
                .filter(hospital -> hospital.getId().equals(hospitalId))
                .findFirst()
                .map(hospital -> {hospital.getPatients().add(patient);
                    return "successfully added";})
                .orElse("Hospital with this id not found");

    }

    @Override
    public void removeById(Long id) {
        try{
            boolean Deleted = DataBase.hospitals.stream()
                    .flatMap(hospital -> hospital.getPatients().stream()
                            .filter(patient -> patient.getId().equals(id))
                            .map(patient -> {
                                hospital.getPatients().remove(patient);
                                System.out.println("successfully deleted");
                                return true;
                            }))
                    .findFirst()
                    .orElse(false);
            if (!Deleted) {
                System.out.println("Patient with this id not found");
            }
        }catch (Exception e){
            System.out.println(" ");
        }


    }

    @Override
    public String updateById(Long id, Patient patient) {
          return DataBase.hospitals.stream()
                  .flatMap(hospital -> hospital.getPatients().stream()
                        .filter(patient1 -> patient1.getId().equals(id))
                        .map(patient1 -> {
                            hospital.getPatients().set(hospital.getPatients().indexOf(patient1),patient);
                            return "successfully updated";
                        }))
                .findFirst()
                .orElse("Patient with this id not found");
    }
}
