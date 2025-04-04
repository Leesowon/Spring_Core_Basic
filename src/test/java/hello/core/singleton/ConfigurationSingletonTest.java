package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.MemberRepository;
import hello.core.member.MemberServiceImpl;
import hello.core.order.OrderServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ConfigurationSingletonTest {

    @Test
    void configurationSingletonTest() {
        ApplicationContext ac= new AnnotationConfigApplicationContext(AppConfig.class);

        MemberServiceImpl memberService = ac.getBean("memberService", MemberServiceImpl.class);
        OrderServiceImpl orderService = ac.getBean("orderService", OrderServiceImpl.class);

        MemberRepository memberRepository1 = memberService.getMemberRepository();
        MemberRepository memberRepository2 = orderService.getMemberRepository();
        MemberRepository memberRepository = ac.getBean("memberRepository", MemberRepository.class);

        System.out.println("memberService -> memberRepository = " + memberRepository1);
        System.out.println("orderService -> memberRepository = " + memberRepository2);
        System.out.println("memberRepository = " + memberRepository);

        Assertions.assertThat(memberRepository1).isEqualTo(memberRepository2);
        Assertions.assertThat(memberRepository1).isEqualTo(memberRepository);

    }

    @Test
    void configurationDeep(){
        ApplicationContext ac= new AnnotationConfigApplicationContext(AppConfig.class);
        Object bean = ac.getBean(AppConfig.class);

        // 클래스 타입 출력 : bean = class hello.core.AppConfig$$SpringCGLIB$$0 / $$~ : spring이 만든 클래스 정보
        System.out.println("bean = " + bean.getClass());

    }
}
