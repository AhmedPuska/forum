package dao;

import java.util.List;

public interface InterfaceDao<E> {

    public void save(E e);

    public void update(E e);

    public E getById(int id);

    public List<E> getList();

    public void delete(E e);
}
