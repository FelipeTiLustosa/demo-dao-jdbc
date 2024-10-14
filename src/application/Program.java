package application;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

import java.time.LocalDate;
import java.util.List;

public class Program {
    public static void main(String[] args) {

        System.out.println("=== TEST 1: seller findyId ===");
        SellerDao sellerDao = DaoFactory.createSellerDao();
        Seller seller = sellerDao.findById(3);
        System.out.println(seller);

        System.out.println("\n=== TEST 2: seller findyIdDapartmentDao ===");
        Department dep = new Department(2, null);
        List<Seller> list = sellerDao.findByDepartment(dep);
        list.forEach(System.out::println);

        System.out.println("\n=== TEST 3: seller findAll ===");
        list = sellerDao.findAll();
        list.forEach(System.out::println);

        System.out.println("\n=== TEST 4: seller Insert ===");
        Seller newSeller = new Seller(null,"Greg","Greg@gmail.com",LocalDate.now(), 4000.0,dep);
        sellerDao.insert(newSeller);
        System.out.println("Inserted! new id = " + newSeller.getId());
    }
}
