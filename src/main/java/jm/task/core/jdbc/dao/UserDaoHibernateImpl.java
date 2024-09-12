package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    public UserDaoHibernateImpl() {

    }

    @Override
    public void createUsersTable() {
        Transaction transaction=null;
        Session session=null;
        try {
            session=Util.getSessionFactory().openSession();
            transaction= session.beginTransaction();
            session
                    .createSQLQuery(
                            " CREATE TABLE IF NOT EXISTS userTable" +
                                    "(id SERIAL PRIMARY KEY NOT NULL ," + " name VARCHAR(50)," +
                                    "lastname VARCHAR(50),"+ "age INT)")
                    .executeUpdate();
            transaction.commit();
            System.out.println("Таблица успешно создана!");
        }
        catch(HibernateException e){
            e.printStackTrace();
        }
        finally {
            if( transaction!=null && transaction.isActive()){
                transaction.rollback();
            }
            session.close();
        }
    }

    @Override
    public void dropUsersTable() {
        Transaction transaction=null;
        Session session=null;
        try {
            session=Util.getSessionFactory().openSession();
            transaction= session.beginTransaction();
            session
                    .createSQLQuery(
                            "DROP TABLE IF EXISTS userTable" )
                    .executeUpdate();
            transaction.commit();
            System.out.println("Таблица успешно удалена!");
        }
        catch(HibernateException e){
            e.printStackTrace();
        }
        finally {
            if( transaction!=null && transaction.isActive()){
                transaction.rollback();
            }
            session.close();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Transaction transaction=null;
        Session session=null;
        try{
            session=Util.getSessionFactory().openSession();
            transaction= session.beginTransaction();
            session
                    .save(new User(name,lastName,age));
            transaction.commit();
            System.out.println("User с имененем " + name + " добавлен в базу данных");
        }
        catch(HibernateException e){
            e.printStackTrace();
        }
        finally {
            if( transaction!=null && transaction.isActive()){
                transaction.rollback();
            }
            session.close();
        }
    }

    @Override
    public void removeUserById(long id) {
        Transaction transaction=null;
        Session session=null;
        try {
            session=Util.getSessionFactory().openSession();
            transaction= session.beginTransaction();
            session
                    .delete(session.get(User.class, id));
            transaction.commit();
            System.out.println("Пользователь с Id " + id + " успешно удален");
        }
        catch(HibernateException e){
            e.printStackTrace();
        }
        finally {
            if( transaction!=null && transaction.isActive()){
                transaction.rollback();
            }
            session.close();
        }
    }

    @Override
    public List<User> getAllUsers() {
        Transaction transaction=null;
        Session session=null;
        List<User> allUser=new ArrayList<>();
        try {
            session = Util.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            allUser=session.createQuery(" from User").getResultList();
            transaction.commit();
            System.out.println("Успешно достали юзеров");
        }
        catch(HibernateException e){
            e.printStackTrace();
        }
        finally {
            if( transaction!=null && transaction.isActive()){
                transaction.rollback();
            }
            session.close();
        }
        return  allUser;
    }

    @Override
    public void cleanUsersTable() {
        Transaction transaction=null;
        Session session=null;
        try {
            session=Util.getSessionFactory().openSession();
            transaction= session.beginTransaction();
            session
                    .createSQLQuery(
                            "DELETE FROM userTable")
                    .executeUpdate();
            transaction.commit();
            System.out.println("Таблица успешно очищена!");
        }
        catch(HibernateException e){
            e.printStackTrace();
        }
        finally {
            if( transaction!=null && transaction.isActive()){
                transaction.rollback();
            }
            session.close();
        }
    }
}
