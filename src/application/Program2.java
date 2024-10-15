package application;

import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.dao.SellerDao;
import model.entities.Department;

import java.util.List;
import java.util.Scanner;

public class Program2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("=== TEST 1: department findyId ===");
        DepartmentDao departmentDao = DaoFactory.createDepartmentDao();
        Department dep = departmentDao.findById(2);
        System.out.println(dep);

        System.out.println("\n=== TEST 2: department findyIdAll ===");
        List<Department> departments = departmentDao.findAll();
        departments.forEach(System.out::println);

        System.out.println("\n=== TEST 3: department insert ===");
        Department dep1 =  new Department(null,"Financial");
        departmentDao.insert(dep1);
        System.out.println("Inserted! new id = "+dep1.getId());

        System.out.println("\n=== TEST 4: department update ===");
        dep1 = departmentDao.findById(dep1.getId());
        dep1.setName("Mercado");
        departmentDao.update(dep1);

        System.out.println("\n=== TEST 5: department delete ===");
        System.out.print("Enter id for delete test: ");
        int deleteById = sc.nextInt();
        departmentDao.deleteById(deleteById);



    }
}
