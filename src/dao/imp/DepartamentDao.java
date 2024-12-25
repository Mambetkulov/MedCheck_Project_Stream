package dao.imp;

import Models.Department;

import java.util.List;

public interface DepartamentDao {

    List<Department> getAllDepartmentByHospital(Long id);

    Department findDepartmentByName(String name);
}
