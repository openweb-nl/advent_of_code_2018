package com.gklijs.adventofcode;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Objects;

import io.reactivex.Flowable;

class Utils {

    private Utils(){
        //prevent instantiation
    }

    static Flowable<String> readLines(String fileName){
        return Flowable.generate(
            () -> new BufferedReader(new InputStreamReader(Objects.requireNonNull(Utils.class.getClassLoader().getResourceAsStream(fileName)))),
            (reader, emitter) -> {
                final String line = reader.readLine();
                if (line != null) {
                    emitter.onNext(line);
                } else {
                    emitter.onComplete();
                }
            },
            BufferedReader::close);
    }
}
