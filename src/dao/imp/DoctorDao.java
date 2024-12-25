package dao.imp;

import Models.Doctor;

import java.util.List;

public interface DoctorDao {
    Doctor findDoctorById(Long id);

    String assignDoctorToDepartment(Long departmentId, List<Doctor> doctorsId);

    List<Doctor> getAllDoctorsByHospitalId(Long id);

    List<Doctor> getAllDoctorsByDepartmentId(Long id);
}
