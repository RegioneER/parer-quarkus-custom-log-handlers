/*
 * Engineering Ingegneria Informatica S.p.A.
 *
 * Copyright (C) 2023 Regione Emilia-Romagna <p/> This program is free software: you can
 * redistribute it and/or modify it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the License, or (at your option)
 * any later version. <p/> This program is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A
 * PARTICULAR PURPOSE. See the GNU Affero General Public License for more details. <p/> You should
 * have received a copy of the GNU Affero General Public License along with this program. If not,
 * see <https://www.gnu.org/licenses/>.
 */

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
