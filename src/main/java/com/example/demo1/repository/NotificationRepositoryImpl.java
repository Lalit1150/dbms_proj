package com.first_project.clinicman.repository;

import com.first_project.clinicman.model.Notification;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
public class NotificationRepositoryImpl implements NotificationRepository {

    private final JdbcTemplate jdbcTemplate;

    public NotificationRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // RowMapper to map database rows to Notification objects
    private static final class NotificationRowMapper implements RowMapper<Notification> {
        @Override
        public Notification mapRow(ResultSet rs, int rowNum) throws SQLException {
            Notification notification = new Notification();
            notification.setNotificationID(rs.getInt("notificationID"));
            notification.setMessage(rs.getString("message"));
            notification.setDateSent(rs.getDate("dateSent").toLocalDate());
            // Assuming User and Appointment can be fetched from their respective IDs.
            // For simplicity, these can be set later using separate queries if needed.
            return notification;
        }
    }

    @Override
    public List<Notification> findAll() {
        String sql = "SELECT * FROM Notifications";
        return jdbcTemplate.query(sql, new NotificationRowMapper());
    }

    @Override
    public Optional<Notification> findById(int notificationID) {
        String sql = "SELECT * FROM Notifications WHERE notificationID = ?";
        return jdbcTemplate.query(sql, new NotificationRowMapper(), notificationID)
                .stream()
                .findFirst();
    }

    @Override
    public int save(Notification notification) {
        String sql = "INSERT INTO Notifications (userID, appointmentID, message, dateSent) VALUES (?, ?, ?, ?)";
        return jdbcTemplate.update(sql,
                notification.getUser().getUserID(), // Assuming User has a method to get the user ID
                notification.getAppointment().getAppointmentID(), // Assuming Appointment has a method to get appointment ID
                notification.getMessage(),
                notification.getDateSent());
    }

    @Override
    public int update(Notification notification) {
        String sql = "UPDATE Notifications SET userID = ?, appointmentID = ?, message = ?, dateSent = ? WHERE notificationID = ?";
        return jdbcTemplate.update(sql,
                notification.getUser().getUserID(),
                notification.getAppointment().getAppointmentID(),
                notification.getMessage(),
                notification.getDateSent(),
                notification.getNotificationID());
    }

    @Override
    public int deleteById(int notificationID) {
        String sql = "DELETE FROM Notifications WHERE notificationID = ?";
        return jdbcTemplate.update(sql, notificationID);
    }
}
