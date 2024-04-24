package Service;

import java.util.List;

public interface IService<T> {
    public void add(T t);
    public void update(T t);
    public void delete(T t);
    List<T> getAll();
    T getById(int id);
}
