package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderServiceImpl implements OrderService {

    // dip : 어떤 것을 참조할지 구체적인 정보 x
    private final MemberRepository memberRepository; // 회원을 찾아야 함
//    private final DiscountPolicy discountPolicy = new FixDiscountPolicy(); // 고정 할인 정책 - 남배우가 직접 상대 여배우를 캐스팅 하는꼴
//    private final DiscountPolicy discountPolicy = new RateDiscountPolicy(); // 고정 할인 정책
    private final DiscountPolicy discountPolicy;

    @Autowired
    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }


    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice); // Order를 만들어서 반환
    }

    // 테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}