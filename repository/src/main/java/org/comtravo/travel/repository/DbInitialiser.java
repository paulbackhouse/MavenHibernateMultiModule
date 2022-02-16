package org.comtravo.travel.repository;

import java.math.BigInteger;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Random;

import org.comtravo.travel.domain.entities.BookingEntity;
import org.comtravo.travel.domain.entities.UserEntity;
import org.comtravo.travel.repository.implementation.BaseRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DbInitialiser extends BaseRepository {

    // java doesn't do lambda expression as method paramr
    // this is how you define it - because the java compiler - basically is lazy
    interface IFor {
        void Each(Integer i);
    }
    
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
            logger.error("Failed to initialise database", e);
        }
        finally {
            session.close();
            sessionFactory.close();
        }
    }

    private static boolean HasImportedRows(Session session, String tableName) {

        // hibernate uses its own query language, hql - which is basically dog shite
        // why would you not just use pure sql? 
        // the better question is WHY SHOULD YOU BE WRITING TEXT AT ALL WITH AN ORM!!!???
        // oh yeh, because hibernate is crap as well, wait until you see the "criteria" nonsense to query with conditions!
        // is hands down, the better ORM - System.Linq makes it easy to query - java/hibernate - its 2022!
        var sql = String.format("select count(*) FROM %s tbl", tableName);
        var query = session.createSQLQuery(sql);
        var count = ((BigInteger)query.uniqueResult()).longValue();

        logger.info(String.format("Table '%s' has %d records", tableName, count));

        return count > 0;
    }

    private static void LoadData(Session session) {

        ImportUsers(session);
        ImportBookings(session);

    }

    private static void For(Integer amount, IFor iterator) {
        for (var i = 0; i < amount; i++) {
            iterator.Each(i);
        }
    }

    private static Timestamp GetSqlTimestamp() {
        java.util.Date date = new java.util.Date();
        return new Timestamp(date.getTime()); 
    }

    private static void ImportUsers(Session session) {

        if (!HasImportedRows(session, "users")) {
            logger.info("Importing rows in 'users' table");

            String[] givenNames = { "John", "Paul", "Jane", "Camila", "Clare", "Karl", "George", "Daisy", "Phillip", "Victoria", "Matthew", "Milli", "Remy", "Bethany", "Nina", "Sarah", "Sam" };
            String[] lastNames = { "Smith", "Schmitt", "Jameson", "Donald", "McTommin", "Windsor", "Conner" };
            var createdUsernames = new ArrayList<String>();

            var givenNameLength = givenNames.length;
            var lastNameLength = lastNames.length;
            var now = GetSqlTimestamp();
            var rdm = new Random();

            session.beginTransaction();

            For(50, i -> {
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
            });

            session.getTransaction().commit();
        }
    }

    private static void ImportBookings(Session session) {

        if (!HasImportedRows(session, "bookings")) {
            logger.info("Importing rows in 'bookings' table");

            var query = session.createSQLQuery("select tbl.id FROM users tbl");
            var userIds = query.getResultList();

            String[] departures = { "Paris", "Berlin", "LA", "Singapore", "Sidney", "Madrid", "London", "Rome"};
            String[] destinations = { "Nice", "Hamburg", "New York", "Moscow", "Glasgow", "Barcelona", "Turin", "Lisbon"};
            String[] years = {"2022", "2023", "2024" };
            String[] months = {"03", "04", "05", "06", "07", "08", "09", "10", "11"};
            String[] days = { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13",  "16", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27" };

            var rdm = new Random();
            var now = GetSqlTimestamp();

            session.beginTransaction();

            For(userIds.size(), i -> {
                
                var userId = userIds.get(i);
                var bookingAmount = rdm.nextInt(10 + 0) + 1;

                For(bookingAmount, idx -> {
                    var departureIdx = rdm.nextInt(departures.length + 0);
                    var destinationIdx = rdm.nextInt(destinations.length + 0);
                    var departure = departures[departureIdx];
                    var destination = destinations[destinationIdx];
                    var dayIdx = rdm.nextInt(days.length + 0);
                    var monthIdx = rdm.nextInt(months.length + 0);
                    var yearIdx = rdm.nextInt(years.length + 0);
                    var departOn = String.format("%1$s-%2$s-%3$s", years[yearIdx], months[monthIdx], days[dayIdx]);
                    var travellers = rdm.nextInt(6 + 1) + 1;
                    var booking = new BookingEntity();

                    booking.setUserId((int)userId);
                    booking.setCreated(now);
                    booking.setModified(now);
                    booking.setDeparture(departure);
                    booking.setDestination(destination);
                    booking.setDepart(Date.valueOf(departOn));
                    booking.setTravellers((short)travellers);

                    session.save(booking);

                });

            });

            session.getTransaction().commit();
        }

    }

}
