package org.comtravo.travel.repository.base;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import org.comtravo.travel.repository.implementation.UserRepository;
import org.comtravo.travel.repository.implementation.interfaces.IUseDbSession;
import org.comtravo.travel.repository.implementation.interfaces.IUseDbSessionWithResult;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BaseDbRepository {

    private static final Logger logger = LoggerFactory.getLogger(UserRepository.class);

    private SessionFactory sessionFactory;

    private void CloseFactory() {
        if (sessionFactory != null) {
            sessionFactory.close();
            sessionFactory = null;
        }
    }   

    protected void CloseSession(Session session) {
        session.close();
        CloseFactory();
    }

    protected Session CreateSession() {
        CloseFactory();
		sessionFactory = new Configuration()
                                            .configure()
                            				.buildSessionFactory();

        return sessionFactory.openSession();
    }

    protected <T> List<T> findAll(Class<T> type, Session session, int pageIndex, int pageSize) {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<T> criteria = builder.createQuery(type);
        criteria.from(type);
        var query = session.createQuery(criteria);
        query.setFirstResult(pageIndex);
        query.setMaxResults(pageSize);
        List<T> data = query.getResultList();
        return data;
      }      

    protected void UsingDb(IUseDbSession dbSession) {

        Session session = null;

        try  {
            session = CreateSession();
            dbSession.Use(session);
        }
        catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        finally
        {
            CloseSession(session);
        }
    } 
    
    protected <T> T UsingDbWithResult(IUseDbSessionWithResult<T> dbSession) {

        Session session = null;
        T result = null;

        try  {
            session = CreateSession();
            result = dbSession.Use(session);
        }
        catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        finally
        {
            CloseSession(session);
        }

        return result;
    }     

    protected void UsingDbWithTransaction(IUseDbSession dbTransactionSession) {

        UsingDb(dbSession -> {
            try {
                dbSession.beginTransaction();
        
                dbTransactionSession.Use(dbSession);

                dbSession.getTransaction().commit();
            }
            catch (Exception e)
            {
                dbSession.getTransaction().rollback();
                throw e;
            }
        });                
    }

}
