package interceptors;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoggingInterceptor {

    private Logger logger = Logger.getLogger(this.getClass().getName());

    @AroundInvoke
    private Object doLog(InvocationContext ic) {
        Object obj = null;

        try {
            logger.entering(ic.getTarget().toString(),
                    ic.getMethod().getName());

            Object[] params = ic.getParameters();
            String className = ic.getMethod().getDeclaringClass().getName();
            String method = ic.getMethod().getName();
            logger.log(Level.INFO,
                    "Executing method : {0} in class : {1} with first parameter : {2}",
                    new String[]{ method, className, params[0].toString() });

            obj = ic.proceed();
        } catch (Exception ignored) {
        } finally {
            logger.exiting(ic.getTarget().toString(),
                    ic.getMethod().getName());
        }
        return obj;
    }

}
