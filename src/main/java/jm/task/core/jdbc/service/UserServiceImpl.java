package jm.task.core.jdbc.service;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;

import java.util.List;

public class UserServiceImpl implements UserService {
    public void createUsersTable(){
        jm.task.core.jdbc.dao.UserDaoJDBCImpl UserDaoJDBCImpl=new UserDaoJDBCImpl();
        UserDaoJDBCImpl.createUsersTable();
    }

    public void dropUsersTable() {
        jm.task.core.jdbc.dao.UserDaoJDBCImpl UserDaoJDBCImpl=new UserDaoJDBCImpl();
        UserDaoJDBCImpl.dropUsersTable();
    }

    public void saveUser(String name, String lastName, byte age) {
        jm.task.core.jdbc.dao.UserDaoJDBCImpl UserDaoJDBCImpl=new UserDaoJDBCImpl();
        UserDaoJDBCImpl.saveUser(name,lastName, age);
    }

    public void removeUserById(long id) {
        jm.task.core.jdbc.dao.UserDaoJDBCImpl UserDaoJDBCImpl=new UserDaoJDBCImpl();
        UserDaoJDBCImpl.removeUserById(id);
    }

    public List<User> getAllUsers() {
        jm.task.core.jdbc.dao.UserDaoJDBCImpl UserDaoJDBCImpl=new UserDaoJDBCImpl();
        return  UserDaoJDBCImpl.getAllUsers();
    }

    public void cleanUsersTable() {
        jm.task.core.jdbc.dao.UserDaoJDBCImpl UserDaoJDBCImpl=new UserDaoJDBCImpl();
        UserDaoJDBCImpl.cleanUsersTable();
    }
}
