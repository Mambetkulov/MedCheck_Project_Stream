package dao;

import Models.Department;
import Models.Hospital;
import dao.imp.DepartamentDao;
import database.DataBase;
import service.impl.GenericService;

import java.util.List;

public class DepartamentDaoImp implements GenericService<Department>, DepartamentDao {


    @Override
    public List<Department> getAllDepartmentByHospital(Long id) {
        return DataBase.hospitals.stream()
                .filter(hospital -> hospital.getId().equals(id))
                .findFirst()
                .map(Hospital::getDepartments)
                .orElse(null);
    }

    @Override
    public Department findDepartmentByName(String name) {
        return DataBase.hospitals.stream()
                .flatMap(hospital -> hospital.getDepartments().stream()
                        .filter(department -> department.getDepartmentName().equals(name))
                        .map(department -> hospital.getDepartments().get(hospital.getDepartments().indexOf(department)))
                )
                .findFirst()
                .orElse(null);

    }

    @Override
    public String add(Long hospitalId, Department departament) {
        return DataBase.hospitals.stream()
                .filter(hospital -> hospital.getId().equals(hospitalId))
                .findFirst()
                .map(hospital -> {hospital.getDepartments().add(departament);
                    return "successfully added";})
                .orElse("Hospital with this id not found");
    }

    @Override
    public void removeById(Long id) {
        try{
            boolean Deleted = DataBase.hospitals.stream()
                    .flatMap(hospital -> hospital.getDepartments().stream()
                            .filter(department -> department.getId().equals(id))
                            .map(department -> {
                                hospital.getDepartments().remove(department);
                                System.out.println("successfully deleted");
                                return true;
                            }))
                    .findFirst()
                    .orElse(false);
            if (!Deleted) {
                System.out.println("Department with this id not found");
            }

        }catch (Exception e){
            System.out.println(" ");
        }

    }

    @Override
    public String updateById(Long id, Department departament) {
        return DataBase.hospitals.stream()
                .flatMap(hospital -> hospital.getDepartments().stream()
                        .filter(department -> department.getId().equals(id))
                        .map(department -> {
                            hospital.getDepartments().set(hospital.getDepartments().indexOf(department),departament);
                            return "successfully updated";
                        }))
                .findFirst()
                .orElse("Department with this id not found");
    }
}
