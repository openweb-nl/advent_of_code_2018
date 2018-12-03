package com.gklijs.adventofcode;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Objects;
import java.util.Set;

import com.gklijs.adventofcode.utils.Pair;
import io.reactivex.Flowable;

public class Utils {

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

    public static <T> Pair<Set<T>, T> firstDuplicate(Pair<Set<T>, T> pair, T value){
        if(pair.getFirst().contains(value)){
            pair.setSecond(value);
        }else{
            pair.getFirst().add(value);
        }
        return pair;
    }
}
