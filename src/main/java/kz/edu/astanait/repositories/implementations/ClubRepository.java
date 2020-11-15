package kz.edu.astanait.repositories.implementations;

import kz.edu.astanait.databases.Postgres;
import kz.edu.astanait.exceptions.NotFoundException;
import kz.edu.astanait.models.Club;
import kz.edu.astanait.repositories.interfaces.IClubRepository;

import javax.ws.rs.BadRequestException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

public class ClubRepository implements IClubRepository {
    @Override
    public void add(Club entity) throws BadRequestException {
        String sql = "INSERT INTO clubs(club_name) VALUES (?)";
        try {
            PreparedStatement ps = Postgres.getConnection().prepareStatement(sql);
            ps.setString(1, entity.getClub_name());
            ps.execute();
        } catch (SQLException throwable) {
            throw new BadRequestException();
        }
    }

    @Override
    public void update(Club entity) throws BadRequestException {
        StringBuilder sql = new StringBuilder("UPDATE clubs SET");
        if (entity.getClub_name() != null) sql.append(" club_name=?,");
        sql.deleteCharAt(sql.length() - 1);
        sql.append(" WHERE club_id=?");

        try {
            PreparedStatement ps = Postgres.getConnection().prepareStatement(sql.toString());
            int i = 1;
            if (entity.getClub_name() != null) ps.setString(i++, entity.getClub_name());
            ps.setInt(i, entity.getClub_id());
            ps.execute();
        } catch (SQLException throwable) {
            throw new BadRequestException();
        }
    }

    @Override
    public void delete(Club entity) throws BadRequestException {
        String sql = "DELETE FROM clubs WHERE club_id=" + entity.getClub_id();
        try {
            Postgres.getConnection().createStatement().execute(sql);
        } catch (SQLException throwable) {
            throw new BadRequestException();
        }
    }

    @Override
    public Club queryOne(String sql) throws NotFoundException {
        try {
            Statement statement = Postgres.getConnection().createStatement();
            ResultSet rs = statement.executeQuery(sql);
            if (rs.next()) {
                return new Club.Builder()
                        .setClub_id(rs.getInt("club_id"))
                        .setClub_name(rs.getString("club_name"))
                        .build();
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        throw new NotFoundException("Not Found");
    }

    @Override
    public List<Club> getAll() {
        String sql = "SELECT * FROM clubs";
        return findSeveral(sql);
    }

    @Override
    public List<Club> findSeveral(String sql) throws BadRequestException {
        List<Club> list = new LinkedList<>();
        try {
            Statement statement = Postgres.getConnection().createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                list.add(
                        new Club.Builder()
                                .setClub_id(rs.getInt("club_id"))
                                .setClub_name(rs.getString("club_name"))
                                .build()
                );
            }
        } catch (SQLException throwable) {
            throw new BadRequestException();
        }
        return list;
    }

    @Override
    public void addStudentToClub(Integer club_id, Integer student_id) {
        String sql = "INSERT INTO clubs_students(club_id, student_id) VALUES (?,?)";
        try {
            PreparedStatement ps = Postgres.getConnection().prepareStatement(sql);
            ps.setInt(1, club_id);
            ps.setInt(2, student_id);
            ps.execute();
        } catch (SQLException throwable) {
            throw new BadRequestException();
        }
    }
}
