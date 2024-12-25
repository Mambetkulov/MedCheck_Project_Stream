package dao;
import Models.Department;
import Models.Doctor;
import Models.Hospital;
import dao.imp.DoctorDao;
import database.DataBase;
import service.impl.GenericService;

import java.util.List;
public class DoctorDaoImp implements GenericService<Doctor>,DoctorDao {


    @Override
    public Doctor findDoctorById(Long id) {
        return DataBase.hospitals.stream()
                .flatMap(hospital -> hospital.getDoctors().stream()
                        .filter(doctor -> doctor.getId().equals(id))
                        .map(doctor -> hospital.getDoctors().get(hospital.getDoctors().indexOf(doctor))))
                .findFirst()
                .orElse(null);

    }

    @Override
    public String assignDoctorToDepartment(Long departmentId, List<Doctor> doctorsId) {

        return DataBase.hospitals.stream()
                .flatMap(hospital -> hospital.getDepartments().stream()
                        .filter(department -> department.getId().equals(departmentId))
                        .map(department -> {department.getDoctors().addAll(doctorsId);
                              return "successfully assigned";}))
                .findFirst()
                .orElse("Departament with this id not found");

    }

    @Override
    public List<Doctor> getAllDoctorsByHospitalId(Long id) {
        return DataBase.hospitals.stream()
                .filter(hospital -> hospital.getId().equals(id))
                .findFirst()
                .map(Hospital::getDoctors)
                .orElse(null);
    }

    @Override
    public List<Doctor> getAllDoctorsByDepartmentId(Long id) {
        return DataBase.hospitals.stream()
                .flatMap(hospital -> hospital.getDepartments().stream()
                        .filter(department -> department.getId().equals(id))
                        .map(Department::getDoctors))
                .findFirst()
                .orElse(null);
    }

    @Override
    public String add(Long hospitalId, Doctor doctor) {
     return DataBase.hospitals.stream()
             .filter(hospital -> hospital.getId().equals(hospitalId))
             .findFirst()
             .map(hospital -> {hospital.getDoctors().add(doctor);
                   return "successfully added";})
             .orElse("Hospital with this id not found");
    }

    @Override
    public void removeById(Long id) {
        try{
            boolean Deleted = DataBase.hospitals.stream()
                    .flatMap(hospital -> hospital.getDoctors().stream()
                            .filter(doctor -> doctor.getId().equals(id))
                            .map(doctor -> {
                                hospital.getDoctors().remove(doctor);
                                System.out.println("successfully deleted");
                                return true;
                            }))
                    .findFirst()
                    .orElse(false);
            if (!Deleted) {
                System.out.println("Doctor with this id not found");
            }
        }catch (Exception e){
            System.out.println(" ");
        }

    }

    @Override
    public String updateById(Long id, Doctor doctor) {
        return DataBase.hospitals.stream()
                .flatMap(hospital -> hospital.getDoctors().stream()
                        .filter(doctor1 -> doctor1.getId().equals(id))
                        .map(doctor1 -> {
                            hospital.getDoctors().set(hospital.getDoctors().indexOf(doctor1),doctor);
                            return "successfully updated";
                        }))
                .findFirst()
                .orElse("Doctor with this id not found");
    }
}
