package com.example.hogwarts.service;

import org.springframework.stereotype.Service;

@Service
public class GeneralService {

    public long calculateSum() {
        return Stream.iterate(1, a -> a + 1)
                .limit(1_000_000)
                .parallel()
                .reduce(0, Integer::sum);
    }
}