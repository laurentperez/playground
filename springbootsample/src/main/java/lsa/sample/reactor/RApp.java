package lsa.sample.reactor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.Exceptions;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.publisher.MonoProcessor;

import java.time.Duration;
import java.util.concurrent.atomic.LongAdder;
import java.util.function.Function;

public class RApp {

    static final Logger LOG = LoggerFactory.getLogger(RApp.class);

    public static void main(String[] args) throws InterruptedException {

        LongAdder failureStat = new LongAdder();

        MonoProcessor<String> texecute = MonoProcessor.create();
        Mono<String> result = texecute
                .delaySubscription(Duration.ofSeconds(2))
                .map(e -> parse2(e)).doOnError(e -> {
                    failureStat.increment();
                }).doOnSuccess(s -> {
                    LOG.info("success {}", s);
                })
                .onErrorReturn("ko")
                .subscribe();

        texecute.onNext("xx");

        LOG.info("...............;");
        String block = result.block();
        LOG.info("r={}", block);
        boolean sc = texecute.isSuccess();
        System.out.println("failureStat = " + failureStat);
        LOG.info(sc ? "success" : "error");
        texecute.dispose();

        Flux<String> flux =
                Flux.interval(Duration.ofMillis(250))
                        .map(input -> {
                            if (input < 3) return "tick " + input;
                            throw new RuntimeException("boom");
                        })
                        .onErrorReturn("Uh oh");

        flux.subscribe(System.out::println);
        Thread.sleep(2100);


    }

    public static String parse2(String s) {
        System.out.println("parse s = " + s);
        if (s.equals("xx")) {
            try {
                throw new Exception("no xx");
            } catch (Exception e) {
                throw Exceptions.propagate(e);
            }
        }
        return s;
    }

    public static Mono<String> parse(String s) {
        System.out.println("parse s = " + s);
        if (s.equals("xx")) {
            return Mono.error(new Exception("no xx"));
        }
        return Mono.just(s);
    }


}
