package org.comtravo.travel;

import org.comtravo.travel.repository.DbInitialiser;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.EnableAsync;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@EnableAsync
@Slf4j
public class TravelApplication {

    @SuppressWarnings("unused")
    public static void main(String[] args) {

        log.info("Comtravo Application Starting...");
        try {
            var applicationContext = SpringApplication.run(TravelApplication.class, args);

            //displayAllBeans(applicationContext);

            log.info("Comtravo Application Running");
        } catch (Exception ex) {
            log.error("Comtravo Application Failed...", ex);
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
        log.info("Application.displayAllBeans:");
        final String[] allBeanNames = applicationContext.getBeanDefinitionNames();
        for (final String beanName : allBeanNames) {
            log.info(beanName);
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
     * That - is what java is... a lazy teenager. The potential is there - it is just currently being a dick about it
    */

}
