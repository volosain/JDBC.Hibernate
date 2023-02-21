package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class UserDaoHibernateImpl implements UserDao {
    private static final SessionFactory sessionFactory = Util.getHBconnection();
    public UserDaoHibernateImpl() {

    }

    @Override
    public void createUsersTable() {
       try (Session session = sessionFactory.openSession()) {
           session.beginTransaction();
           String createTableSQL = """
                    create table if not exists Users(
                    id serial primary key,
                    name varchar(100),
                    lastName varchar(100),
                    age smallint
                    )""";
           Query query = session.createNativeQuery(createTableSQL);
           query.executeUpdate();
           session.getTransaction().commit();
       } catch (Exception e) {
           e.printStackTrace();
       }
    }

    @Override
    public void dropUsersTable() {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            String dropTableSQL = """
                    DROP TABLE IF EXISTS users""";
            Query query = session.createNativeQuery(dropTableSQL);
            query.executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            User user = new User(name, lastName, age);
            session.persist(user);
            session.getTransaction().commit();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeUserById(long id) {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            User user = session.load(User.class, id);
            session.delete(user);
            session.flush();
            session.getTransaction().commit();
            //дописать
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = null;
        try (Session session = sessionFactory.openSession();){
            session.beginTransaction();
            users = session.createQuery("from User").getResultList();
            session.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public void cleanUsersTable() {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            String cleanTable = """
                    TRUNCATE TABLE users""";
            Query query = session.createNativeQuery(cleanTable);
            query.executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
