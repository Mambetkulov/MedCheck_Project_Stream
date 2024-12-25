package service;

import Models.Department;
import dao.DepartamentDaoImp;
import service.impl.DepartmentService;
import service.impl.GenericService;

import java.util.List;

public class DepartamentServiceImp implements GenericService<Department>, DepartmentService {
    private final DepartamentDaoImp departamentDaoImp;

    public DepartamentServiceImp(DepartamentDaoImp departamentDaoImp) {
        this.departamentDaoImp = departamentDaoImp;
    }

    @Override
    public List<Department> getAllDepartmentByHospital(Long id) {
        return departamentDaoImp.getAllDepartmentByHospital(id);
    }

    @Override
    public Department findDepartmentByName(String name) {
        return departamentDaoImp.findDepartmentByName(name);
    }

    @Override
    public String add(Long hospitalId, Department departament) {
        return departamentDaoImp.add(hospitalId,departament);
    }

    @Override
    public void removeById(Long id) {
        departamentDaoImp.removeById(id);
    }

    @Override
    public String updateById(Long id, Department departament) {
        return departamentDaoImp.updateById(id,departament);
    }
}
