package org.comtravo.travel.repository.base;

import com.mysema.query.hql.hibernate.HibernateQuery;

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

    /** 
     * @summary creates  new HibernateQuery which can be used with QueryDSL
     * @see     http://querydsl.com/
     * @see     http://querydsl.com/static/querydsl/1.1.0/reference/html/ch02s02.html
    */
    protected HibernateQuery CreateQuery(Session session) {
        return new HibernateQuery(session);
    }

        /**
     * @summary                     Creates a db connection (session)
     * @param dbTransactionSession  Lambda expression which passes the open db 'session'
     * @return                      Returns T - based on output of the method
     * @example                     
     * UsingDb(db -> { 
     *      ...
     *      // some db query logic
     *      ...
     * });
     */
    protected void UsingDb(IUseDbSession dbSession) {

        Session session = null;

        try  {
            session = CreateSession();
            dbSession.Use(session);
        }
        catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw e;
        }
        finally
        {
            CloseSession(session);
        }
    } 
    
    /**
     * @summary                     Creates a db connection (session)
     * @param dbTransactionSession  Lambda expression which passes the open db 'session'
     * @return                      Returns T - based on output of the method
     * @example                     
     * UsingDbWithResult<SomeEntity>(db -> { 
     *      ...
     *      // some code to get some records from Database
     *      var results = db.get(SomeEntity.class); // a db query
     *      ... 
     *      return results;
     * });
     */
    protected <T> T UsingDbWithResult(IUseDbSessionWithResult<T> dbSession) {

        Session session = null;
        T result = null;

        try  {
            session = CreateSession();
            result = dbSession.Use(session);
        }
        catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw e;
        }
        finally
        {
            CloseSession(session);
        }

        return result;
    }     

    /**
     * @summary                     Creates a db connection with an active transaction and simple error handling
     * @param dbTransactionSession  Lambda expression which passes the open db 'session'
     * @example                     
     * UsingDbWithTransaction(db -> { 
     *      ... 
     *      // some db query logic
     *      ... 
     * });
     */
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
