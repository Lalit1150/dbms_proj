package com.first_project.clinicman.repository;

import com.first_project.clinicman.model.Appointment;
import com.first_project.clinicman.model.Bill;
import com.first_project.clinicman.model.Patient;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
public class BillRepositoryImpl implements BillRepository {

    private final JdbcTemplate jdbcTemplate;

    public BillRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // RowMapper to map database rows to Bill objects
    private static final class BillRowMapper implements RowMapper<Bill> {
        @Override
        public Bill mapRow(ResultSet rs, int rowNum) throws SQLException {
            Bill bill = new Bill();
            bill.setBillID(rs.getInt("billID"));

            Patient patient = new Patient();
            patient.setPatientID(rs.getInt("patientID")); // Assuming Patient has setPatientID method
            bill.setPatient(patient);

            Appointment appointment = new Appointment();
            appointment.setAppointmentID(rs.getInt("appointmentID")); // Assuming Appointment has setAppointmentID method
            bill.setAppointment(appointment);

            bill.setAmount(rs.getDouble("amount"));
            bill.setDateIssued(rs.getObject("dateIssued", java.time.LocalDate.class));
            bill.setTransactionID(rs.getString("transactionID"));

            return bill;
        }
    }

    @Override
    public List<Bill> findAll() {
        String sql = "SELECT * FROM Bills";
        return jdbcTemplate.query(sql, new BillRowMapper());
    }

    @Override
    public Optional<Bill> findById(int billID) {
        String sql = "SELECT * FROM Bills WHERE billID = ?";
        return jdbcTemplate.query(sql, new BillRowMapper(), billID)
                .stream()
                .findFirst();
    }

    @Override
    public int save(Bill bill) {
        String sql = "INSERT INTO Bills (patientID, appointmentID, amount, dateIssued, transactionID) VALUES (?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql,
                bill.getPatient().getPatientID(),  // Assuming Patient has getPatientID()
                bill.getAppointment().getAppointmentID(), // Assuming Appointment has getAppointmentID()
                bill.getAmount(),
                bill.getDateIssued(),
                bill.getTransactionID());
    }

    @Override
    public int update(Bill bill) {
        String sql = "UPDATE Bills SET patientID = ?, appointmentID = ?, amount = ?, dateIssued = ?, transactionID = ? WHERE billID = ?";
        return jdbcTemplate.update(sql,
                bill.getPatient().getPatientID(),
                bill.getAppointment().getAppointmentID(),
                bill.getAmount(),
                bill.getDateIssued(),
                bill.getTransactionID(),
                bill.getBillID());
    }

    @Override
    public int deleteById(int billID) {
        String sql = "DELETE FROM Bills WHERE billID = ?";
        return jdbcTemplate.update(sql, billID);
    }
}
