package dao;

import java.util.List;
import model.HibernateUtil;
import model.User;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class UserDao implements InterfaceDao<User> {

    @Override
    public void save(User user) {
        Session session = HibernateUtil.createSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(user);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            HibernateUtil.close();
        }
    }

    @Override
    public void update(User user) {
        Session session = HibernateUtil.createSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.update(user);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            HibernateUtil.close();
        }
    }

    @Override
    public User getById(int id) {
        User user = null;
        Session session = HibernateUtil.createSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            user = (User) session.get(User.class, id);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            HibernateUtil.close();
        }
        return user;
    }

    @Override
    public List<User> getList() {
        Session session = HibernateUtil.createSessionFactory().getCurrentSession();
        Transaction tx = null;
        List<User> allUsers = null;
        try {
            tx = session.beginTransaction();

            Criteria criteria = session.createCriteria(User.class);

            allUsers = criteria.list();

            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            System.out.println(e);
        } finally {
            HibernateUtil.close();
        }
        return allUsers;
    }

    @Override
    public void delete(User user) {
        Session session = HibernateUtil.createSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.delete(user);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            HibernateUtil.close();
        }
    }

}
