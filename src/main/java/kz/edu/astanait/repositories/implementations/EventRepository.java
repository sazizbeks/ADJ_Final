package kz.edu.astanait.repositories.implementations;

import kz.edu.astanait.databases.Postgres;
import kz.edu.astanait.exceptions.NotFoundException;
import kz.edu.astanait.models.Event;
import kz.edu.astanait.repositories.interfaces.IEventRepository;

import java.sql.*;
import java.util.Date;
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


    /**
     * Example of usage:
     * IEventRepository rep = new EventRepository();
     * rep.add(new Event.Builder().
     * setName("Testing Event add")
     * .setStart_date(new Date())
     * .setEnd_date(new Date(2020-1900, Calendar.DECEMBER,14))
     * .setCreator_id(1)
     * .build());
     *
     * @param entity - object of Event
     */
    @Override
    public void add(Event entity) {
        String sql = "INSERT INTO events(EVENT_NAME, EVENT_START_DATE, EVENT_END_DATE, EVENT_CREATOR_ID) VALUES (?,?,?,?)";
        try {
            PreparedStatement ps = Postgres.getConnection().prepareStatement(sql);
            ps.setString(1, entity.getName());
            ps.setTimestamp(2, dateToTimestamp(entity.getStart_date()));
            ps.setTimestamp(3, dateToTimestamp(entity.getEnd_date()));
            ps.setInt(4, entity.getCreator_id());
            ps.execute();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

    /**
     * Example of usage:
     * IEventRepository rep = new EventRepository();
     *         rep.update(new Event.Builder()
     *                 .setId(1)
     *                 .setName("Testing Event")
     *                 .setStart_date(new Date())
     *                 .setEnd_date(new Date(2020-1900, Calendar.DECEMBER,14, 15,16,17))
     *                 .setCreator_id(2)
     *                 .build());
     *
     * @param entity - object of Event
     */
    @Override
    public void update(Event entity) {
        StringBuilder sql = new StringBuilder("UPDATE EVENTS SET");
        if (entity.getName() != null) sql.append(" event_name=?,");
        if (entity.getStart_date() != null) sql.append(" event_start_date=?,");
        if (entity.getEnd_date() != null) sql.append(" event_end_date=?,");
        if (entity.getCreator_id() != null) sql.append(" event_creator_id=?,");
        sql.deleteCharAt(sql.length() - 1);
        sql.append(" WHERE event_id=?");

        try {
            PreparedStatement ps = Postgres.getConnection().prepareStatement(sql.toString());
            int i = 1;
            if (entity.getName() != null) ps.setString(i++, entity.getName());
            if (entity.getStart_date() != null) ps.setTimestamp(i++, dateToTimestamp(entity.getStart_date()));
            if (entity.getEnd_date() != null) ps.setTimestamp(i++, dateToTimestamp(entity.getEnd_date()));
            if (entity.getCreator_id() != null) ps.setInt(i++, entity.getCreator_id());
            ps.setInt(i, entity.getId());
            ps.execute();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

    /**
     * Example of usage:
     * IEventRepository rep = new EventRepository();
     *         rep.delete(new Event.Builder()
     *                 .setId(1)
     *                 .build());
     *
     * @param entity - object of Event
     */
    @Override
    public void delete(Event entity) {
        String sql = "DELETE FROM events WHERE event_id=" + entity.getId();
        try {
            Postgres.getConnection().createStatement().execute(sql);
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

    // Method to convert java.util.Date to java.sql.Timestamp
    private Timestamp dateToTimestamp(Date date) {
        return new Timestamp(date.getTime());
    }
}
