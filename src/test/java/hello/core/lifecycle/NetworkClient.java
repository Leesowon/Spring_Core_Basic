package hello.core.lifecycle;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

public class NetworkClient {

    private String url;

    public NetworkClient() {
        System.out.println("생성자 호출 url = " + url); // 접속 Url 출력
        connect();
        call("초기화 연결 메세지");
    }

    public void setUrl(String url) {
        this.url = url;
    }

    // 서비스를 시작할 때 호출하는 메소드
    public void connect() {
        System.out.println("connect : " + url);
    }

    public void call(String message) {
        System.out.println("call : " + url + ", message = " + message);
    }

    // 서비스 종료시 호출
    public void disconnect() {
        System.out.println("close : " + url);
    }

    @PostConstruct
//    @Override, afterPropertiesSet
    public void init() { // 의존성 주입이 끝나면 호출
        connect();
        call("초기화 연결 메세지");

    }

    @PreDestroy
//    @Override, destory
    public void close() { // 빈이 종료될 때 호출
        disconnect();
    }
}