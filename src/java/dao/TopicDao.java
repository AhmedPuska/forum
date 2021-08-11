package dao;

import java.util.List;
import model.HibernateUtil;
import model.Topic;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class TopicDao implements InterfaceDao<Topic> {

    @Override
    public void save(Topic topic) {
        Session session = HibernateUtil.createSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(topic);
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
    public void update(Topic topic) {
        Session session = HibernateUtil.createSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.update(topic);
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
    public Topic getById(int id) {
        Topic topic = null;
        Session session = HibernateUtil.createSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            topic = (Topic) session.get(Topic.class, id);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
        } 
        return topic;
    }

    @Override
    public List<Topic> getList() {
        Session session = HibernateUtil.createSessionFactory().getCurrentSession();
        Transaction tx = null;
        List<Topic> allTopics = null;
        try {
            tx = session.beginTransaction();

            Criteria criteria = session.createCriteria(Topic.class);

            allTopics = criteria.list();

            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            System.out.println(e);
        } finally {
            HibernateUtil.close();
        }
        return allTopics;
    }

    @Override
    public void delete(Topic topic) {
        Session session = HibernateUtil.createSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.delete(topic);
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
