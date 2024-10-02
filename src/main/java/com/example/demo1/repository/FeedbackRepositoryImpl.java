package com.first_project.clinicman.repository;

import com.first_project.clinicman.model.Feedback;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
public class FeedbackRepositoryImpl implements FeedbackRepository {

    private final JdbcTemplate jdbcTemplate;

    public FeedbackRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // RowMapper to map database rows to Feedback objects
    private static final class FeedbackRowMapper implements RowMapper<Feedback> {
        @Override
        public Feedback mapRow(ResultSet rs, int rowNum) throws SQLException {
            Feedback feedback = new Feedback();
            feedback.setFeedbackID(rs.getInt("feedbackID"));
            feedback.setRating(rs.getInt("rating"));
            feedback.setComments(rs.getString("comments"));
            feedback.setFeedbackDate(rs.getDate("feedbackDate").toLocalDate());
            // Assuming Appointment can be fetched from its ID later if needed.
            return feedback;
        }
    }

    @Override
    public List<Feedback> findAll() {
        String sql = "SELECT * FROM Feedbacks";
        return jdbcTemplate.query(sql, new FeedbackRowMapper());
    }

    @Override
    public Optional<Feedback> findById(int feedbackID) {
        String sql = "SELECT * FROM Feedbacks WHERE feedbackID = ?";
        return jdbcTemplate.query(sql, new FeedbackRowMapper(), feedbackID)
                .stream()
                .findFirst();
    }

    @Override
    public int save(Feedback feedback) {
        String sql = "INSERT INTO Feedbacks (appointmentID, rating, comments, feedbackDate) VALUES (?, ?, ?, ?)";
        return jdbcTemplate.update(sql,
                feedback.getAppointment().getAppointmentID(), // Assuming Appointment has a method to get the appointment ID
                feedback.getRating(),
                feedback.getComments(),
                feedback.getFeedbackDate());
    }

    @Override
    public int update(Feedback feedback) {
        String sql = "UPDATE Feedbacks SET appointmentID = ?, rating = ?, comments = ?, feedbackDate = ? WHERE feedbackID = ?";
        return jdbcTemplate.update(sql,
                feedback.getAppointment().getAppointmentID(),
                feedback.getRating(),
                feedback.getComments(),
                feedback.getFeedbackDate(),
                feedback.getFeedbackID());
    }

    @Override
    public int deleteById(int feedbackID) {
        String sql = "DELETE FROM Feedbacks WHERE feedbackID = ?";
        return jdbcTemplate.update(sql, feedbackID);
    }
}
