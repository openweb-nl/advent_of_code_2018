package nl.openweb.adventofcode2018.ivor.day3;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Ivor
 */
public class Day3Question2 extends Day3Question1 {

    public static void main(String... args) {
        long time = System.currentTimeMillis();
        Set<Claim> notOverlappingClaims = new Day3Question2().getNotOverlappingClaims();
        System.out.println("Answer: " + notOverlappingClaims.stream()
                .map(Claim::getId)
                .collect(Collectors.toSet()));
        System.out.println("Calculation took " + (System.currentTimeMillis() - time) + "ms");
    }


    public Set<Claim> getNotOverlappingClaims() {
        Set<Claim> claims = getLines().collect(Collectors.toSet());
        return claims.stream()
                .filter(c -> !isIntersecting(claims, c))
                .collect(Collectors.toSet());
    }

    private boolean isIntersecting(Set<Claim> claims, Claim c) {
        return claims.stream()
                .filter(item -> item.getId() != c.getId())
                .anyMatch(item -> item.isOverlapping(c));
    }
}
