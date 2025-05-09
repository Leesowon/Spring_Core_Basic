package hello.core.scope;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import static org.assertj.core.api.Assertions.assertThat;

public class PrototypeTest {

    @Test
    void prototypeBeanFind() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);
        // 파라미터에 PrototypeBean.class 을 해주면 해당 클래스가 자동으로 컴포넌트 대상처럼 동작 -> 스프링 빈으로 자동 등록

        // 프로토타입 빈은 조회하기 직전에 생성
        System.out.println("find prototypeBean1");
        PrototypeBean prototypeBean1 = ac.getBean(PrototypeBean.class);

        System.out.println("find prototypeBean2");
        PrototypeBean prototypeBean2 = ac.getBean(PrototypeBean.class);

        System.out.println("prototypeBean1 = " + prototypeBean1);
        System.out.println("prototypeBean2 = " + prototypeBean2);

        assertThat(prototypeBean1).isNotSameAs(prototypeBean2);

        prototypeBean1.destroy();
        prototypeBean2.destroy();
        ac.close();
    }

    @Scope("prototype")

    static class PrototypeBean {
        @PostConstruct
        public void init(){
            System.out.println("PrototypeBean init");
        } // init

        @PreDestroy
        public void destroy(){
            System.out.println("PrototypeBean destroy");
        } // destroy
    }
}
