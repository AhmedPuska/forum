/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.HibernateUtil;
import model.Post;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author KUCA
 */
public class PostDao implements InterfaceDao<Post> {

    @Override
    public void save(Post post) {
        Session session = HibernateUtil.createSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(post);
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
    public void update(Post post) {
        Session session = HibernateUtil.createSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.update(post);
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
    public Post getById(int id) {
        Post post = null;
        Session session = HibernateUtil.createSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            post = (Post) session.get(Post.class, id);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            HibernateUtil.close();
        }
        return post;
    }

    @Override
    public void delete(Post post) {
        Session session = HibernateUtil.createSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.delete(post);
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
    public List<Post> getList() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
