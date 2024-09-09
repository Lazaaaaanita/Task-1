package jm.task.core.jdbc.dao;

import com.mysql.cj.protocol.Resultset;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

public class UserDaoJDBCImpl implements UserDao {

    public UserDaoJDBCImpl() {

    }
    public void createUsersTable() {
        try (
                Connection connection=Util.getConection();
                Statement statement = connection.createStatement()
        ) {
            statement.executeUpdate(" CREATE TABLE IF NOT EXISTS userTable" +
                    "(id SERIAL PRIMARY KEY NOT NULL ," + " name VARCHAR(50)," + "lastname VARCHAR(50)," + "age INT)");
            System.out.println("Таблица успешно создана");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        try (
                Connection connection=Util.getConection();
                Statement statement = connection.createStatement()
        ) {
            statement.executeUpdate("DROP TABLE IF EXISTS userTable");
            System.out.println("Таблица успешно удалена");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try (
                Connection connection=Util.getConection();
                PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO userTable(name, lastname, age) VALUES (?,?,?)")
            ){
    preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setInt(3, age);
            preparedStatement.executeUpdate();
            System.out.println("User с имененем " + name + " добавлен в базу данных");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        try (
                Connection connection=Util.getConection();
                Statement statement = connection.createStatement()
        ) {
            statement.executeUpdate("DELETE FROM userTable WHERE id = " + id);
            System.out.println("Пользователь с Id " + id + " успешно удален");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> allUser = new ArrayList<>();
        try (
                Connection connection=Util.getConection();
                Statement statement = connection.createStatement();
                ResultSet resultset = statement.executeQuery(" SELECT * FROM userTable")
        ){
            while (resultset.next()) {
                User user = new User();
                user.setId(resultset.getLong("id"));
                user.setName(resultset.getString("name"));
                user.setLastName(resultset.getString("lastname"));
                user.setAge(resultset.getByte("age"));
                allUser.add(user);
            }
            for (User user : allUser) {
                System.out.println(user.toString() + "\n");
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return allUser;
    }

    public void cleanUsersTable() {
        try (
                Connection connection=Util.getConection();
                Statement statement = connection.createStatement();
              ) {
            statement.executeUpdate("DELETE FROM userTable");
            System.out.println("Таблица успешно очищена");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}