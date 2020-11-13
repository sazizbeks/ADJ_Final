package kz.edu.astanait.repositories.implementations;

import kz.edu.astanait.databases.Postgres;
import kz.edu.astanait.exceptions.NotFoundException;
import kz.edu.astanait.models.Event;
import kz.edu.astanait.models.Student;
import kz.edu.astanait.repositories.interfaces.IEventRepository;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class EventRepository implements IEventRepository {

    @Override
    public Event queryOne(String sql) throws NotFoundException {
        try {
            Statement statement = Postgres.getConnection().createStatement();
            ResultSet rs = statement.executeQuery(sql);
            if (rs.next()) {
                return new Event.Builder()
                        .setId(rs.getInt("event_id"))
                        .setName(rs.getString("event_name"))
                        .setStart_date(rs.getDate("event_start_date"))
                        .setEnd_date(rs.getDate("event_end_date"))
                        .setCreator_id(rs.getInt("event_creator_id"))
                        .build();
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        throw new NotFoundException("Not Found");
    }

    @Override
    public List<Event> findSeveral(String sql) {
        List<Event> list = new LinkedList<>();
        try {
            Statement statement = Postgres.getConnection().createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                list.add(
                        new Event.Builder()
                                .setId(rs.getInt("event_id"))
                                .setName(rs.getString("event_name"))
                                .setStart_date(rs.getDate("event_start_date"))
                                .setEnd_date(rs.getDate("event_end_date"))
                                .setCreator_id(rs.getInt("event_creator_id"))
                                .build()
                );
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return list;
    }

    @Override
    public List<Event> getAll() {
        String sql = "SELECT * FROM events";
        return findSeveral(sql);
    }

    @Override
    public void add(Event entity) {
        String sql = "INSERT INTO events(EVENT_NAME, EVENT_START_DATE, EVENT_END_DATE, EVENT_CREATOR_ID) VALUES (?,?,?,?)";
        try {
            PreparedStatement ps = Postgres.getConnection().prepareStatement(sql);
            ps.setString(1, entity.getName());
            ps.setDate(2, (Date) entity.getStart_date());
            ps.setDate(3, (Date) entity.getEnd_date());
            ps.setInt(4, entity.getCreator_id());
            ps.execute();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

    @Override
    public void update(Event entity) {

    }

    @Override
    public void delete(Event entity) {

    }
}
