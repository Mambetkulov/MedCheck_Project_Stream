package service;

import Models.Doctor;
import dao.DoctorDaoImp;
import database.DataBase;
import service.impl.DoctorService;
import service.impl.GenericService;

import java.util.List;
import java.util.stream.Collectors;

public class DoctorServiceImp implements GenericService<Doctor>, DoctorService {
    public DoctorDaoImp doctorDaoImp;

    public DoctorServiceImp(DoctorDaoImp doctorDaoImp) {
        this.doctorDaoImp = doctorDaoImp;
    }

    @Override
    public Doctor findDoctorById(Long id) {
        return doctorDaoImp.findDoctorById(id);
    }

    @Override
    public String assignDoctorToDepartment(Long departmentId, List<Long> doctorsId) {
        List<Doctor> doctors = DataBase.hospitals.stream()
                .flatMap(hospital -> hospital.getDepartments().stream()
                        .filter(department -> department.getId().equals(departmentId))
                        .flatMap(department -> hospital.getDoctors().stream()
                                .filter(doctor -> doctorsId.contains(doctor.getId()))))
                .collect(Collectors.toList());

        if (!doctors.isEmpty()) {
            return doctorDaoImp.assignDoctorToDepartment(departmentId, doctors);
        }

        return "Department with this id or Doctors with ids you entered not found";
    }

    @Override
    public List<Doctor> getAllDoctorsByHospitalId(Long id) {
        return doctorDaoImp.getAllDoctorsByHospitalId(id);
    }

    @Override
    public List<Doctor> getAllDoctorsByDepartmentId(Long id) {
        return doctorDaoImp.getAllDoctorsByDepartmentId(id);
    }

    @Override
    public String add(Long hospitalId, Doctor doctor) {
        return doctorDaoImp.add(hospitalId,doctor);
    }

    @Override
    public void removeById(Long id) {
     doctorDaoImp.removeById(id);
    }

    @Override
    public String updateById(Long id, Doctor doctor) {
        return doctorDaoImp.updateById(id,doctor);
    }
}
