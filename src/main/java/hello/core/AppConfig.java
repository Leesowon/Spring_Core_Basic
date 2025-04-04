package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// 앱에 대한 환경 구성 : 앱의 실제 동작에서 필요한 구현 객체를 생성하고
// 연결하는 책임을 가지는 별도의 클래스
// 객체의 연결과 생성

// @Bean MemberService -> new MemoryMemberRepository()
// @Bean OrderService -> new MemoryMemberRepository()
// 이러면 싱글톤이 깨지는가..? -> test

@Configuration // APP 설정정보, 구성정보
public class AppConfig {

    @Bean // spring container에 등록됨
    public MemberService memberService() { // 생성자 주입 (injection)
        System.out.println("AppConfig.memberService");
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        System.out.println("AppConfig.memberRepository");
        return new MemoryMemberRepository();
    }

    @Bean
    public OrderService orderService() {
        System.out.println("AppConfig.orderService");
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    @Bean
    public DiscountPolicy discountPolicy() {
        return new RateDiscountPolicy();
    }
}