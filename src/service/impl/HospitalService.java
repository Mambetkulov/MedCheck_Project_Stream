package service.impl;

import Models.Hospital;
import Models.Patient;

import java.util.List;
import java.util.Map;

public interface HospitalService {
    String addHospital(Hospital hospital);

    Hospital findHospitalById(Long id);

    List<Hospital> getAllHospital();

    List<Patient> getAllPatientFromHospital(Long id);

    String deleteHospitalById(Long id);

    Map<Integer,Hospital> getAllHospitalByAddress(String address);
}
