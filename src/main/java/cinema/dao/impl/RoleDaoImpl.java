package cinema.dao.impl;

import cinema.dao.RoleDao;
import cinema.exceptions.DataProcessingException;
import cinema.model.Role;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class RoleDaoImpl implements RoleDao {
    private final SessionFactory sessionFactory;

    public RoleDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void add(Role role) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(role);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't insert role Entity "
                    + role, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public Role getRoleByName(String roleName) {
        try (Session session = sessionFactory.openSession()) {
            Query<Role> findByEmailQuery = session.createQuery("FROM Role"
                    + " WHERE roleName = :name", Role.class);
            findByEmailQuery.setParameter("name", roleName == "ADMIN"
                    ? Role.RoleName.ADMIN : Role.RoleName.USER);
            return findByEmailQuery.getSingleResult();
        } catch (Exception e) {
            throw new DataProcessingException("Can't find available role with name = "
                    + roleName, e);
        }
    }
}
