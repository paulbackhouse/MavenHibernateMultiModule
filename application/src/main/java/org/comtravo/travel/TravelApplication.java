package org.comtravo.travel;

import org.comtravo.travel.repository.DbInitialiser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class TravelApplication {

    private static final Logger logger = LoggerFactory.getLogger(TravelApplication.class);

    @SuppressWarnings("unused")
    public static void main(String[] args) {

        logger.info("Comtravo Application Starting...");
        try {
            var applicationContext = SpringApplication.run(TravelApplication.class, args);
            
            //displayAllBeans(applicationContext);

            logger.info("Comtravo Application Running");
        } catch (Exception ex) {
            logger.error("Comtravo Application Failed...", ex);
        }
        finally {
            DbInitialiser.OnInitialise(args);
        }
    }

    /** Displays the names of all Spring beans in the given application context.
     * @param applicationContext
     *            object to access many Spring services
     */
    @SuppressWarnings("unused")
    private static void displayAllBeans(final ApplicationContext applicationContext) {
        logger.info("Application.displayAllBeans:");
        final String[] allBeanNames = applicationContext.getBeanDefinitionNames();
        for (final String beanName : allBeanNames) {
            logger.info(beanName);
        }
    }

    /**
     * Thoughts on Java 
     * 
     * Imagine a teenager, perhaps your own child. 
     * 
     * You ask said teenager to cut the lawn for you. It shouldn't take long and it would be a great help to you
     * The teenager does not respond well - what follows is general moaning, complaints etc...
     * 
     * So you say, ok, fine, I will pay you to cut the lawn. Said teenager begrudgingly agrees - they need the money
     * 
     * Some time later, you checkin with the progress to find the teenager has done the BARE minimum...
     * so you end up cutting the lawn yourself
     * 
     * That - is what java is... a lazy teenager
    */

}
