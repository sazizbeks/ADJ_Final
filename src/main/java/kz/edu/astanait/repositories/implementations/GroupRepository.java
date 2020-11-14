package kz.edu.astanait.repositories.implementations;

import kz.edu.astanait.databases.Postgres;
import kz.edu.astanait.exceptions.NotFoundException;
import kz.edu.astanait.models.Group;
import kz.edu.astanait.repositories.interfaces.IGroupRepository;

import javax.ws.rs.BadRequestException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

public class GroupRepository implements IGroupRepository {
    @Override
    public void add(Group entity) throws BadRequestException {
        String sql = "INSERT INTO groups (MAJOR_ID, GROUP_NUMBER) VALUES (?,?)";
        try {
            PreparedStatement ps = Postgres.getConnection().prepareStatement(sql);
            ps.setString(1, entity.getMajor_id());
            ps.setInt(2, entity.getGroup_number());
            ps.execute();
        } catch (SQLException throwable) {
            throw new BadRequestException();
        }
    }

    @Override
    public void update(Group oldGroup, Group newGroup) throws BadRequestException {
        StringBuilder sql = new StringBuilder("UPDATE GROUPS SET");
        if (newGroup.getMajor_id() != null) sql.append(" major_id=?,");
        if (newGroup.getGroup_number() != null) sql.append(" group_number=?,");
        sql.deleteCharAt(sql.length() - 1);
        sql.append(" WHERE major_id=? AND group_number=?");

        try {
            PreparedStatement ps = Postgres.getConnection().prepareStatement(sql.toString());
            int i = 1;
            if (newGroup.getMajor_id() != null) ps.setString(i++, newGroup.getMajor_id());
            if (newGroup.getGroup_number() != null) ps.setInt(i++, newGroup.getGroup_number());
            ps.setString(i++, oldGroup.getMajor_id());
            ps.setInt(i, oldGroup.getGroup_number());
            ps.execute();
        } catch (SQLException throwable) {
            throw new BadRequestException();
        }
    }

    @Override
    public void delete(Group entity) throws BadRequestException {
        String sql = "DELETE FROM groups WHERE major_id=? AND group_number=?";
        try {
            PreparedStatement ps = Postgres.getConnection().prepareStatement(sql);
            ps.setString(1, entity.getMajor_id());
            ps.setInt(2, entity.getGroup_number());
            ps.execute();
        } catch (SQLException throwable) {
            throw new BadRequestException();
        }
    }

    @Override
    public Group queryOne(String sql) throws NotFoundException {
        try {
            Statement statement = Postgres.getConnection().createStatement();
            ResultSet rs = statement.executeQuery(sql);
            if (rs.next()) {
                return new Group.Builder()
                        .setMajor_id(rs.getString("major_id"))
                        .setGroup_number(rs.getInt("group_number"))
                        .build();
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        throw new NotFoundException("Not Found");
    }

    @Override
    public List<Group> getAll() {
        String sql = "SELECT * FROM groups";
        return findSeveral(sql);
    }

    @Override
    public List<Group> findSeveral(String sql) throws BadRequestException {
        List<Group> list = new LinkedList<>();
        try {
            Statement statement = Postgres.getConnection().createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                list.add(
                        new Group.Builder()
                                .setMajor_id(rs.getString("major_id"))
                                .setGroup_number(rs.getInt("group_number"))
                                .build()
                );
            }
        } catch (SQLException throwable) {
            throw new BadRequestException();
        }
        return list;
    }


}
