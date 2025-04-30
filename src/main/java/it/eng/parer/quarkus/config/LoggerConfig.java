/**
 * 
 */
package it.eng.parer.quarkus.config;

import static io.quarkus.bootstrap.logging.InitialConfigurator.DELAYED_HANDLER;

import java.util.logging.Handler;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import io.quarkus.runtime.Startup;
import it.eng.parer.quarkus.handlers.StackTraceSingleLineMsgConsoleHandler;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;

/*
 * 
 * https://stackoverflow.com/a/73832653/20111624
 */
@ApplicationScoped
@Startup(1)
public class LoggerConfig {

    @ConfigProperty(name = "parer.quarkus.config.singleline-message.enabled", defaultValue = "false")
    boolean singleLineMessageEnabled;

    @PostConstruct
    public void initLogger() {
        Handler[] oldHandlers = DELAYED_HANDLER.clearHandlers();
        if (singleLineMessageEnabled) {
            DELAYED_HANDLER.addHandler(new StackTraceSingleLineMsgConsoleHandler());
        }
        for (Handler oldHandler : oldHandlers) {
            DELAYED_HANDLER.addHandler(oldHandler);
        }
    }

}
