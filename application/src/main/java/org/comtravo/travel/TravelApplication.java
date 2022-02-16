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

}
