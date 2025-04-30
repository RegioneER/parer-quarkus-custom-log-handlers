/**
 * 
 */
package it.eng.parer.quarkus.handlers;

import java.util.Objects;
import java.util.logging.LogRecord;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.jboss.logmanager.handlers.ConsoleHandler;

public class StackTraceSingleLineMsgConsoleHandler extends ConsoleHandler {

    @Override
    public void publish(LogRecord myRecord) {
        super.publish(cropMessage(myRecord));
    }

    private LogRecord cropMessage(LogRecord myRecord) {
        // record.getThrown() not null
        if (!Objects.isNull(myRecord.getThrown())) {
            // crop message on single line
            String singlelinstacktrace = ExceptionUtils.getStackTrace(myRecord.getThrown()).replaceAll("[\r\n]+",
                    "\\\\n");
            myRecord.setMessage(ExceptionUtils.getRootCauseMessage(myRecord.getThrown()) + " : " + singlelinstacktrace);
            myRecord.setThrown(null);
        }
        return myRecord;
    }

}
