package com.learnreactivespring.fluxandmonoplayground;

import org.junit.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.time.Duration;

public class FluxAndMonoWithTimeTest {

    @Test
    public void infiniteSequenceTest() throws InterruptedException {

        Flux<Long> infiniteFlux = Flux.interval(Duration.ofMillis(200))
                .log(); //starts from 0 --> ......

        infiniteFlux
                .subscribe((element) -> System.out.println("Value is: " + element));


        Thread.sleep(3000);
    }

    @Test
    public void finiteSequenceTest() throws InterruptedException {

        Flux<Long> finiteFlux = Flux.interval(Duration.ofMillis(200))
                .take(3)
                .log(); //starts from 0 --> ......

        StepVerifier.create(finiteFlux)
                .expectSubscription()
                .expectNext(0L,1L,2L)
                .verifyComplete();
    }

    @Test
    public void finiteSequenceMapTest() throws InterruptedException {

        Flux<Integer> finiteFlux = Flux.interval(Duration.ofMillis(200))
                .map(l-> (l.intValue()))
                .take(3)
                .log(); //starts from 0 --> ......

        StepVerifier.create(finiteFlux)
                .expectSubscription()
                .expectNext(0, 1, 2)
                .verifyComplete();
    }

    @Test
    public void finiteSequenceMapWithDelayTest() throws InterruptedException {

        Flux<Integer> finiteFlux = Flux.interval(Duration.ofMillis(200))
                .delayElements(Duration.ofSeconds(1))
                .map(l-> (l.intValue()))
                .take(3)
                .log(); //starts from 0 --> ......

        StepVerifier.create(finiteFlux)
                .expectSubscription()
                .expectNext(0, 1, 2)
                .verifyComplete();
    }
}
