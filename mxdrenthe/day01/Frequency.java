package day01;

import java.math.BigInteger;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import util.FilesUtil;

public class Frequency {
    public static void main(String[] args) {
        part1();
        part2();
    }

    static void part1() {
        System.out.println(FilesUtil.read("mxdrenthe/day01/input.txt").stream()
                .map(Integer::parseInt)
                .reduce(0, Integer::sum));
    }

    static void part2() {
        List<Integer> input = FilesUtil.read("mxdrenthe/day01/input.txt").stream()
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        Set<Integer> frequencies = new HashSet<>();
        int index = 0;
        int sum = 0;

        while (true) {
            frequencies.add(sum);
            sum += input.get(index);
            if(frequencies.contains(sum)){
                System.out.println(sum);
                break;
            }
            index++;
            index = index % input.size();
        }
    }
}