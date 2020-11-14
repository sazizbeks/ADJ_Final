package kz.edu.astanait.repositories.implementations;

import kz.edu.astanait.databases.Postgres;
import kz.edu.astanait.exceptions.NotFoundException;
import kz.edu.astanait.models.Event;
import kz.edu.astanait.models.News;
import kz.edu.astanait.repositories.interfaces.INewsRepository;

import javax.ws.rs.BadRequestException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

public class NewsRepository implements INewsRepository {
    @Override
    public void add(News entity) throws BadRequestException {

        String sql = "INSERT INTO news(NEWS_TITLE, NEWS_DESCRIPTION, MODERATOR_ID) VALUES (?,?,?)";
        try {
            PreparedStatement ps = Postgres.getConnection().prepareStatement(sql);
            ps.setString(1, entity.getTitle());
            ps.setString(2, entity.getDescription());
            ps.setInt(3, entity.getModerator_id());
            ps.execute();
        } catch (SQLException throwable) {
            throw new BadRequestException();
        }
    }

    /**
     * Example of usage:
     * INewsRepository rep = new NewsRepository();
     * rep.add(new News.Builder().
     * setTitle("Testing News add")
     * .setDescription("News successfully added")
     * .setEnd_date(new Date(2020-1900, Calendar.DECEMBER,14))
     * .setModerator_id(1)
     * .build());
     *
     * @param entity - object of News
     */
    @Override
    public void update(News entity) throws BadRequestException {
        StringBuilder sql = new StringBuilder("UPDATE NEWS SET");
        if (entity.getTitle() != null) sql.append(" news_title=?,");
        if (entity.getDescription() != null) sql.append(" news_description=?,");
        if (entity.getModerator_id() != null) sql.append(" moderator_id=?,");
        sql.deleteCharAt(sql.length() - 1);
        sql.append(" WHERE news_id=?");

        try {
            PreparedStatement ps = Postgres.getConnection().prepareStatement(sql.toString());
            int i = 1;
            if (entity.getTitle() != null) ps.setString(i++, entity.getTitle());
            if (entity.getDescription() != null) ps.setString(i++, entity.getDescription());
            if (entity.getModerator_id() != null) ps.setInt(i++, entity.getModerator_id());
            ps.setInt(i, entity.getId());
            ps.execute();
        } catch (SQLException throwable) {
            throw new BadRequestException();
        }
    }
    /**
     * Example of usage:
     * INewsRepository rep = new NewsRepository();
     *         rep.update(new News.Builder()
     *                 .setId(1)
     *                 .setTitle("Testing News")
     *                 .setDescription("Updated version of news")
     *                 .setModerator_id(2)
     *                 .build());
     *
     * @param entity - object of News
     */
    @Override
    public void delete(News entity) throws BadRequestException {
        String sql = "DELETE FROM news WHERE news_id=" + entity.getId();
        try {
            Postgres.getConnection().createStatement().execute(sql);
        } catch (SQLException throwable) {
            throw new BadRequestException();
        }
    }
    /**
     * Example of usage:
     * INewsRepository rep = new NewsRepository();
     *         rep.delete(new News.Builder()
     *                 .setId(1)
     *                 .build());
     *
     */
    @Override
    public News queryOne(String sql) throws NotFoundException {
        try {
            Statement statement = Postgres.getConnection().createStatement();
            ResultSet rs = statement.executeQuery(sql);
            if (rs.next()) {
                return new News.Builder()
                        .setId(rs.getInt("news_id"))
                        .setTitle(rs.getString("news_title"))
                        .setDescription(rs.getString("news_description"))
                        .setModerator_id(rs.getInt("moderator_id"))
                        .build();
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        throw new NotFoundException("Not Found");
    }

    @Override
    public List<News> getAll() {
        String sql = "SELECT * FROM news";
        return findSeveral(sql);
    }

    @Override
    public List<News> findSeveral(String sql) throws BadRequestException {
        List<News> list = new LinkedList<>();
        try {
            Statement statement = Postgres.getConnection().createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                list.add(
                        new News.Builder()
                                .setId(rs.getInt("news_id"))
                                .setTitle(rs.getString("news_title"))
                                .setDescription(rs.getString("news_description"))
                                .setModerator_id(rs.getInt("moderator_id"))
                                .build()
                );
            }
        } catch (SQLException throwable) {
            throw new BadRequestException();
        }
        return list;
    }
}
