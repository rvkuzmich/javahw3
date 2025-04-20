package kuzmich.dao;

import kuzmich.models.Author;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class AuthorDao {

    private final SessionFactory sessionFactory;

    @Autowired
    public AuthorDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional
    public List<Author> findAll() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("select a from Author a", Author.class).getResultList();
    }

    @Transactional
    public Author findById(long id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Author.class, id);
    }

    @Transactional
    public Author save(Author author) {
        Session session = sessionFactory.getCurrentSession();
        return (Author) session.save(author);
    }

    @Transactional
    public void update(Author author) {
        Session session = sessionFactory.getCurrentSession();
        Author old = session.get(Author.class, author.getId());
        old.setFirstName(author.getFirstName());
        old.setLastName(author.getLastName());
    }

    @Transactional
    public void delete(Author author) {
        Session session = sessionFactory.getCurrentSession();
        Author old = session.get(Author.class, author.getId());
        session.remove(old);
    }
}
