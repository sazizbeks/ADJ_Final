package kz.edu.astanait.repositories.implementations;

import kz.edu.astanait.databases.Postgres;
import kz.edu.astanait.exceptions.NotFoundException;
import kz.edu.astanait.models.Admin;
import kz.edu.astanait.models.Club;
import kz.edu.astanait.models.Event;
import kz.edu.astanait.repositories.interfaces.IAdminRepository;
import kz.edu.astanait.repositories.interfaces.IRetrieve;

import javax.ws.rs.BadRequestException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

public class AdminRepository implements IAdminRepository {

    @Override
    public Admin queryOne(String sql) throws NotFoundException {
        try {
            Statement statement = Postgres.getConnection().createStatement();
            ResultSet rs = statement.executeQuery(sql);
            if (rs.next()) {
                return new Admin.Builder()
                        .setAdmin_id(rs.getInt("admin_id"))
                        .setUsername(rs.getString("username"))
                        .setPassword(rs.getString("password"))
                        .build();
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        throw new NotFoundException("Not Found");
    }

    @Override
    public List<Admin> getAll() {
        String sql = "SELECT * FROM admins";
        return findSeveral(sql);
    }

    @Override
    public List<Admin> findSeveral(String sql) throws BadRequestException {
        List<Admin> list = new LinkedList<>();
        try {
            Statement statement = Postgres.getConnection().createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                list.add(
                        new Admin.Builder()
                                .setAdmin_id(rs.getInt("admin_id"))
                                .setUsername(rs.getString("username"))
                                .setPassword(rs.getString("password"))
                                .build()
                );
            }
        } catch (SQLException throwable) {
            throw new BadRequestException();
        }
        return list;
    }

    @Override
    public Admin login(String username, String password) throws NotFoundException {
        String sql = "SELECT * FROM admins WHERE username=? AND password=?";
        try {
            PreparedStatement ps = Postgres.getConnection().prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);
            return queryOne(ps.toString());
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        throw new NotFoundException("No student has found with such last name.");
    }
}
