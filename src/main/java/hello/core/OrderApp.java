package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.order.Order;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;

public class OrderApp {

    public static void main(String[] args) {

        AppConfig appConfig = new AppConfig();

        MemberService memberService = appConfig.memberService();
        OrderService orderService = appConfig.orderService();

        Long memberId = 1L;
        Member memeber = new Member(memberId, "memberA", Grade.VIP);
        memberService.join(memeber); // 메모리 객체에 넣어야 함 -> 그래야 주문에서 찾아 쓸 수 있음

        Order order = orderService.createOrder(memberId, "itemA", 10000);
        System.out.println("order" + order);

    } // main
} // class