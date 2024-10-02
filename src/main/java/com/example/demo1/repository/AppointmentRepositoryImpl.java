package com.first_project.clinicman.repository;

import com.first_project.clinicman.model.Appointment;
import com.first_project.clinicman.model.Doctor;
import com.first_project.clinicman.model.Patient;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Repository
public class AppointmentRepositoryImpl implements AppointmentRepository {

    private final JdbcTemplate jdbcTemplate;

    public AppointmentRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // RowMapper to map database rows to Appointment objects
    private static final class AppointmentRowMapper implements RowMapper<Appointment> {
        @Override
        public Appointment mapRow(ResultSet rs, int rowNum) throws SQLException {
            Appointment appointment = new Appointment();
            appointment.setAppointmentID(rs.getInt("appointmentID"));

            Patient patient = new Patient();
            patient.setPatientID(rs.getInt("patientID")); // Assuming Patient has a setPatientID method
            appointment.setPatient(patient);

            Doctor doctor = new Doctor();
            doctor.setDoctorID(rs.getInt("doctorID")); // Assuming Doctor has a setDoctorID method
            appointment.setDoctor(doctor);

            appointment.setAppointmentDate(rs.getObject("appointmentDate", LocalDate.class));
            appointment.setTime(rs.getObject("time", LocalTime.class));
            appointment.setStatus(Appointment.Status.valueOf(rs.getString("status"))); // Assuming status is stored as a string

            return appointment;
        }
    }

    @Override
    public List<Appointment> findAll() {
        String sql = "SELECT * FROM Appointments";
        return jdbcTemplate.query(sql, new AppointmentRowMapper());
    }

    @Override
    public Optional<Appointment> findById(int appointmentID) {
        String sql = "SELECT * FROM Appointments WHERE appointmentID = ?";
        return jdbcTemplate.query(sql, new AppointmentRowMapper(), appointmentID)
                .stream()
                .findFirst();
    }

    @Override
    public int save(Appointment appointment) {
        String sql = "INSERT INTO Appointments (patientID, doctorID, appointmentDate, time, status) VALUES (?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql,
                appointment.getPatient().getPatientID(),  // Assuming Patient has getPatientID()
                appointment.getDoctor().getDoctorID(),    // Assuming Doctor has getDoctorID()
                appointment.getAppointmentDate(),
                appointment.getTime(),
                appointment.getStatus().name());
    }

    @Override
    public int update(Appointment appointment) {
        String sql = "UPDATE Appointments SET patientID = ?, doctorID = ?, appointmentDate = ?, time = ?, status = ? WHERE appointmentID = ?";
        return jdbcTemplate.update(sql,
                appointment.getPatient().getPatientID(),
                appointment.getDoctor().getDoctorID(),
                appointment.getAppointmentDate(),
                appointment.getTime(),
                appointment.getStatus().name(),
                appointment.getAppointmentID());
    }

    @Override
    public int deleteById(int appointmentID) {
        String sql = "DELETE FROM Appointments WHERE appointmentID = ?";
        return jdbcTemplate.update(sql, appointmentID);
    }
}
