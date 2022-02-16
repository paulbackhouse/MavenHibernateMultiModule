package org.comtravo.travel.repository;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Random;

import org.comtravo.travel.domain.entities.UserEntity;
import org.comtravo.travel.repository.implementation.BaseRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DbInitialiser extends BaseRepository {
    
    private static final Logger logger = LoggerFactory.getLogger(DbInitialiser.class);

    public static void OnInitialise(String[] args) {

        SessionFactory sessionFactory = null;
        Session session = null;

        try {

            logger.info("Database initialising");

            sessionFactory = new Configuration()
                                        .configure()
                                        .buildSessionFactory();

            session = sessionFactory.openSession();

            LoadData(session);

        }
        catch (Exception e) {
            logger.error("Failed to initialize database", e);
        }
        finally {
            session.close();
            sessionFactory.close();
        }
    }

    private static boolean HasImportedRows(Session session, String tableName) {

        var sql = String.format("select count(*) FROM %s tbl", tableName);
        var query = session.createSQLQuery(sql);
        var count = ((BigInteger)query.uniqueResult()).longValue();

        logger.info(String.format("Table '%s' has %d records", tableName, count));

        return count > 0;
    }


    private static void LoadData(Session session) {

        ImportUsers(session);

    }

    private static void ImportUsers(Session session) {

        if (!HasImportedRows(session, "users")) {
            logger.info("Importing rows in 'users' table");

            String[] givenNames = { "John", "Paul", "Jane", "Camila", "Clare", "Karl", "George", "Daisy", "Phillip", "Victoria", "Matthew", "Milli", "Remy", "Bethany", "Nina", "Sarah", "Sam" };
            String[] lastNames = { "Smith", "Schmitt", "Jameson", "Donald", "McTommin", "Windsor", "Conner" };
            var createdUsernames = new ArrayList<String>();

            var givenNameLength = givenNames.length;
            var lastNameLength = lastNames.length;
            var rdm = new Random();

            java.util.Date date = new java.util.Date();
            var now = new Timestamp(date.getTime()); 

            session.beginTransaction();

            for (var i = 0; i < 50; i++) {
                var rdmGivenNameIdx = rdm.nextInt(givenNameLength + 0);
                var rdmLastNameIdx = rdm.nextInt(lastNameLength + 0);
                var givenName = givenNames[rdmGivenNameIdx];
                var lastName = lastNames[rdmLastNameIdx];
                var username = String.format("%1$s%2$s", String.valueOf(givenName.charAt(0)).toLowerCase(), lastName.toLowerCase());
                
                if (!createdUsernames.contains(username)) {
                    var user = new UserEntity();
                    user.setGivenName(givenName);
                    user.setLastName(lastName);
                    user.setUsername(username);
                    user.setCreated(now);
                    user.setModified(now);
    
                    session.save(user);
                    createdUsernames.add(username);
                }    
           }

            session.getTransaction().commit();

        }
    }

    private static void ImportBookings(Session session) {

    }

}
