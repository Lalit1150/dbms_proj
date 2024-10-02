package com.first_project.clinicman.repository;

import com.first_project.clinicman.model.Department;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
public class DepartmentRepositoryImpl implements DepartmentRepository {

    private final JdbcTemplate jdbcTemplate;

    public DepartmentRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // RowMapper to map database rows to Department objects
    private static final class DepartmentRowMapper implements RowMapper<Department> {
        @Override
        public Department mapRow(ResultSet rs, int rowNum) throws SQLException {
            Department department = new Department();
            department.setDepartmentID(rs.getInt("departmentID"));
            department.setDepartmentName(rs.getString("departmentName"));
            department.setDescription(rs.getString("description"));
            return department;
        }
    }

    @Override
    public List<Department> findAll() {
        String sql = "SELECT * FROM Departments";
        return jdbcTemplate.query(sql, new DepartmentRowMapper());
    }

    @Override
    public Optional<Department> findById(int departmentID) {
        String sql = "SELECT * FROM Departments WHERE departmentID = ?";
        return jdbcTemplate.query(sql, new DepartmentRowMapper(), departmentID)
                .stream()
                .findFirst();
    }

    @Override
    public int save(Department department) {
        String sql = "INSERT INTO Departments (departmentName, description) VALUES (?, ?)";
        return jdbcTemplate.update(sql,
                department.getDepartmentName(),
                department.getDescription());
    }

    @Override
    public int update(Department department) {
        String sql = "UPDATE Departments SET departmentName = ?, description = ? WHERE departmentID = ?";
        return jdbcTemplate.update(sql,
                department.getDepartmentName(),
                department.getDescription(),
                department.getDepartmentID());
    }

    @Override
    public int deleteById(int departmentID) {
        String sql = "DELETE FROM Departments WHERE departmentID = ?";
        return jdbcTemplate.update(sql, departmentID);
    }
}
