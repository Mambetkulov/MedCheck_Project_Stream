package service;
import Models.Hospital;
import Models.Patient;
import dao.HospitalDaoImp;
import service.impl.HospitalService;
import java.util.List;
import java.util.Map;

public class HospitalServiceImp implements  HospitalService {
    public HospitalDaoImp hospitalDaoImp;

    public HospitalServiceImp(HospitalDaoImp hospitalDaoImp) {
        this.hospitalDaoImp = hospitalDaoImp;
    }

    @Override
    public String addHospital(Hospital hospital) {
        return hospitalDaoImp.addHospital(hospital);
    }

    @Override
    public Hospital findHospitalById(Long id) {
        return hospitalDaoImp.findHospitalById(id);
    }

    @Override
    public List<Hospital> getAllHospital() {
        return hospitalDaoImp.getAllHospital();
    }

    @Override
    public List<Patient> getAllPatientFromHospital(Long id) {
        return hospitalDaoImp.getAllPatientFromHospital(id);
    }

    @Override
    public String deleteHospitalById(Long id) {
        return hospitalDaoImp.deleteHospitalById(id);
    }

    @Override
    public Map<Integer, Hospital> getAllHospitalByAddress(String address) {
        return hospitalDaoImp.getAllHospitalByAddress(address);
    }
}
