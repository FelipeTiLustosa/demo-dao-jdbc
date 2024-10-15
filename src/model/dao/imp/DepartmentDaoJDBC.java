package model.dao.imp;

import db.DB;
import db.DbException;
import db.DbIntegrityException;
import model.dao.DepartmentDao;
import model.entities.Department;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DepartmentDaoJDBC implements DepartmentDao {

    private Connection conn;

    public DepartmentDaoJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(Department obj) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement(
                    "INSERT INTO department "
                    + "(Name) "
                    + "VALUES (?)" , PreparedStatement.RETURN_GENERATED_KEYS);
                    ps.setString(1, obj.getName());

                    int rows = ps.executeUpdate();
                    if (rows > 0) {
                        rs = ps.getGeneratedKeys();
                        if (rs.next()) {
                            obj.setId(rs.getInt(1));
                        }
                    }
                    else {
                        throw new DbException("Unexpected erro! No rows affected!");
                    }

        }
        catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
        finally {
            DB.closeStatement(ps);
            DB.closeResultSet(rs);
        }
    }

    @Override
    public void update(Department obj) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement(
                    " UPDATE department "
                    + "SET Name=? "
                    + "WHERE Id=?");
            ps.setString(1, obj.getName());
            ps.setInt(2, obj.getId());
            int rows = ps.executeUpdate();
            if (rows > 0) {
                System.out.println("Update completed");
            }
            else {
                throw new DbException("Unexpected erro! No rows affected!");
            }
        }
        catch (SQLException e){
            throw new DbException(e.getMessage());
        }
        finally {
            DB.closeStatement(ps);
            DB.closeResultSet(rs);
        }

    }

    @Override
    public void deleteById(Integer id) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement(
                    "DELETE FROM department WHERE Id= ? ");
            ps.setInt(1, id);
            int rows = ps.executeUpdate();

            if (rows > 0) {
                System.out.println("Delete completed rows affected: " + rows);
            }
            else {
                throw new DbException("Unexpected erro! No rows affected!");
            }
        }
        catch (SQLException e) {
            throw new DbIntegrityException(e.getMessage());
        }
        finally {
            DB.closeStatement(ps);
            DB.closeResultSet(rs);
        }
    }

    @Override
    public Department findById(int id) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement("select * from department where id = ? ");
            ps.setInt(1,id);
            rs = ps.executeQuery();

            if (rs.next()) {
                Department obj = instantiationDepartment(rs);
                return obj;
            }
            return null;
        }
        catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
        finally {
            DB.closeStatement(ps);
            DB.closeResultSet(rs);
        }
    }

    @Override
    public List<Department> findAll() {
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = conn.prepareStatement("select * from department");
            rs = ps.executeQuery();

            List<Department> list = new ArrayList<>();
            while (rs.next()) {
                Department obj = instantiationDepartment(rs);
                list.add(obj);
            }
            return list;
        }
        catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }
    private Department instantiationDepartment(ResultSet rs) throws SQLException {
        Department dep = new Department();
        dep.setId(rs.getInt("Id"));
        dep.setName(rs.getString("Name"));

        return dep;
    }
}
