package hello.core.singleton;

public class SingletonService {

    // 이렇게 생성하면 클래스 레벨에 생성되기 때문에 딱 1개만 생성됨 (static : 정적 변수, final : 재할당 불가)
    private static final SingletonService INSTANCE = new SingletonService();

    // 조회 : 인스턴스 참조를 꺼낼 수 있는 유일한 방법
    public static SingletonService getInstance() {
        return INSTANCE;
    }
    private SingletonService() {} // 다른 클래스에서 new하지 못하도록

    public void logic() {
        System.out.println("싱글톤 객체 로직 호출");
    }
}