package hello.core.scan.filter;

import java.lang.annotation.*;

@Target(ElementType.TYPE) // type : class level에 붙음
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyIncludeComponent {
}
