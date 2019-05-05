package com.learnreactivespring.fluxandmonoplayground;

import org.junit.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.util.Arrays;
import java.util.List;

import static reactor.core.publisher.Flux.fromIterable;

public class FluxAndMonoFilterTest {

    List<String> names = Arrays.asList("adam", "anna", "jack", "jenny");

    @Test
    public void filterTest(){

        Flux<String> stringFlux = Flux.fromIterable(names) //adam, anna, jack, jenny
                .filter(s -> s.startsWith("a")) // adam, anna
                .log();

        StepVerifier.create(stringFlux)
                .expectNext("adam", "anna")
                .verifyComplete();

    }

    @Test
    public void filterTestLength(){

        Flux<String> stringFlux = Flux.fromIterable(names) //adam, anna, jack, jenny
                .filter(s -> s.length() > 4) // jenny
                .log();

        StepVerifier.create(stringFlux)
                .expectNext("jenny")
                .verifyComplete();

    }
}
