package application;

import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.dao.SellerDao;
import model.entities.Department;

import java.util.List;

public class Program2 {
    public static void main(String[] args) {

        System.out.println("=== TEST 1: department findyId ===");
        DepartmentDao departmentDao = DaoFactory.createDepartmentDao();
        Department dep = departmentDao.findById(2);
        System.out.println(dep);

        System.out.println("\n=== TEST 2: department findyIdAll ===");
        List<Department> departments = departmentDao.findAll();
        departments.forEach(System.out::println);

    }
}
