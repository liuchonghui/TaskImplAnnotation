package tools.android.taskimpl.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.PACKAGE})
@Retention(RetentionPolicy.CLASS)
public @interface CreateService {
    String name() default "TaskImplService";
    String taskimpl();
    String tag() default "TaskImplService";
}
