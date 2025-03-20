package hello.core;

import hello.core.discount.FixDiscountPolicy;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;

// 앱에 대한 환경 구성 : 앱의 실제 동작에서 필요한 구현 객체를 생성하고
// 연결하는 책임을 가지는 별도의 클래스
// 객체의 연결과 생성
public class AppConfig {

    public MemberService memberService() { // 생성자 주입 (injection)
        return new MemberServiceImpl(new MemoryMemberRepository());
    }

    public OrderService orderService() {
        return new OrderServiceImpl(new MemoryMemberRepository(), new FixDiscountPolicy());
    }
}