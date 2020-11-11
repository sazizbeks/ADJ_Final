package kz.edu.astanait.repositories.implementations;

import kz.edu.astanait.databases.IDB;
import kz.edu.astanait.databases.Postgres;
import kz.edu.astanait.models.Event;
import kz.edu.astanait.repositories.interfaces.IEventRepository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

public class EventRepository implements IEventRepository {

    private final IDB db = new Postgres();

    @Override
    public Event queryOne(String id) {
        return null;
    }

    @Override
    public List<Event> getAll() {
        List<Event> list = new LinkedList<>();

        try {
            String sql = "SELECT * FROM events";
            Statement statement = db.getConnection().createStatement();
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
    public void add(Event entity) {

    }

    @Override
    public void update(Event entity) {

    }

    @Override
    public void delete(Event entity) {

    }
}
