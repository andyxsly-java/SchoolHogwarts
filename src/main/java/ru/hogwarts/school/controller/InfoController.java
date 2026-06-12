package ru.hogwarts.school.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Stream;

@RestController
public class InfoController {

    @Value("${server.port}")
    private int port;

    @GetMapping("/port")
    public ResponseEntity<Integer> getPort() {
        return ResponseEntity.ok(port);
    }

    @GetMapping("/optimization")
    public long getSum() {
        long mod = System.currentTimeMillis();
        long sum = Stream.iterate(1L, a -> a + 1L)
                .limit(1_000_000)
                .parallel()
                .reduce(0L, (a, b) -> a + b);
        return System.currentTimeMillis() - mod;


    }
}
