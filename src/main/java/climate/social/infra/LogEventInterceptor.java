package climate.social.infra;


import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;


@LogEvent
@Interceptor
public class LogEventInterceptor {
//    private static final Logger log = LoggerFactory.getLogger(LogEventInterceptor.class);

    @AroundInvoke
    public Object logEvent(InvocationContext ctx) throws Exception {
//        log.info("###### Interceptor ....");
//        log.info(ctx.getMethod().getName());
//        log.info(Arrays.deepToString(ctx.getParameters()));
        return ctx.proceed();
    }
}
