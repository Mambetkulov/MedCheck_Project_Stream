package dao;
import Models.Hospital;
import Models.Patient;
import dao.imp.HospitalDao;
import database.DataBase;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class HospitalDaoImp implements  HospitalDao {

    @Override
    public String addHospital(Hospital hospital) {
        boolean exists = DataBase.hospitals.stream()
                .anyMatch(hospital1 -> hospital1.getHospitalName().equals(hospital.getHospitalName()));
        if (exists) {
            return "Hospital with this name already exists";
        }
        DataBase.hospitals.add(hospital);
        return "successfully added";
    }

    @Override
    public Hospital findHospitalById(Long id) {
        return DataBase.hospitals.stream()
                .filter(hospital -> hospital.getId().equals(id))
                .findFirst()
                .map(hospital ->  DataBase.hospitals.get(DataBase.hospitals.indexOf(hospital)))
                .orElse(null);
    }

    @Override
    public List<Hospital> getAllHospital() {
        return DataBase.hospitals;
    }

    @Override
    public List<Patient> getAllPatientFromHospital(Long id) {
        return DataBase.hospitals.stream()
                .filter(hospital -> hospital.getId().equals(id))
                .findFirst()
                .map(hospital -> DataBase.hospitals.get(DataBase.hospitals.indexOf(hospital)).getPatients())
                .orElse(null);
    }

    @Override
    public String deleteHospitalById(Long id) {
        boolean b = DataBase.hospitals.stream()
                .filter(hospital -> hospital.getId().equals(id))
                .findFirst()
                .map(hospital ->
                    DataBase.hospitals.remove(hospital)
                )
                .orElse(false);

        if(b){
            return "successfully deleted";
        }

        return "Hospital with this id not found";
    }

    @Override
    public Map<Integer, Hospital> getAllHospitalByAddress(String address) {
        int[] key = {0};
        return DataBase.hospitals.stream()
                .filter(hospital -> hospital.getAddress().equals(address))
                .collect(Collectors.toMap(
                        _ -> ++key[0],
                        hospital -> hospital
                ));
    }
}
