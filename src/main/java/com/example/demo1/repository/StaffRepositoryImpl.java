package com.first_project.clinicman.repository;

import com.first_project.clinicman.model.Staff;
import com.first_project.clinicman.model.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
public class StaffRepositoryImpl implements StaffRepository {

    private final JdbcTemplate jdbcTemplate;

    public StaffRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // RowMapper to map database rows to Staff objects
    private static final class StaffRowMapper implements RowMapper<Staff> {
        @Override
        public Staff mapRow(ResultSet rs, int rowNum) throws SQLException {
            Staff staff = new Staff();
            staff.setStaffID(rs.getInt("staffID"));

            User user = new User();
            user.setUserID(rs.getInt("userID")); // Assuming User has userID field
            staff.setUser(user);

            staff.setDesignation(rs.getString("designation"));
            staff.setSalary(rs.getDouble("salary"));
            return staff;
        }
    }

    @Override
    public List<Staff> findAll() {
        String sql = "SELECT * FROM Staff";
        return jdbcTemplate.query(sql, new StaffRowMapper());
    }

    @Override
    public Optional<Staff> findById(int staffID) {
        String sql = "SELECT * FROM Staff WHERE staffID = ?";
        return jdbcTemplate.query(sql, new StaffRowMapper(), staffID)
                .stream()
                .findFirst();
    }

    @Override
    public int save(Staff staff) {
        String sql = "INSERT INTO Staff (userID, designation, salary) VALUES (?, ?, ?)";
        return jdbcTemplate.update(sql,
                staff.getUser().getUserID(),  // Assuming User has getUserID()
                staff.getDesignation(),
                staff.getSalary());
    }

    @Override
    public int update(Staff staff) {
        String sql = "UPDATE Staff SET userID = ?, designation = ?, salary = ? WHERE staffID = ?";
        return jdbcTemplate.update(sql,
                staff.getUser().getUserID(),
                staff.getDesignation(),
                staff.getSalary(),
                staff.getStaffID());
    }

    @Override
    public int deleteById(int staffID) {
        String sql = "DELETE FROM Staff WHERE staffID = ?";
        return jdbcTemplate.update(sql, staffID);
    }
}
