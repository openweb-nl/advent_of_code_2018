use std::str::FromStr;
use std::collections::HashSet;

#[aoc_generator(day1)]
pub fn input_generator(input: &str) -> Vec<i32> {
    input
        .lines()
        .map(|l| {
            FromStr::from_str(l).unwrap()
        }).collect()
}

#[aoc(day1, part1)]
pub fn calculate_frequency(input: &Vec<i32>) -> i32 {
    input
        .iter()
        .fold(0, |frequency, frequency_change| frequency + frequency_change)
}

#[aoc(day1, part2)]
pub fn first_double_frequency(input: &Vec<i32>) -> i32 {
    let mut counter = 0;
    let mut frequency = 0;
    let mut past_frequencies = HashSet::new();
    past_frequencies.insert(0);
    loop {
        frequency = frequency + input[counter];
        if past_frequencies.contains(&frequency){
            return frequency
        }else{
            past_frequencies.insert(frequency);
            counter = if counter + 1 >= input.len() {0} else {counter + 1};
        }
    };
}

#[cfg(test)]
mod tests {
    use super::{calculate_frequency, first_double_frequency};

    #[test]
    fn sample1() {
        assert_eq!(3, calculate_frequency(&vec!(1, 1, 1)));
    }

    #[test]
    fn sample2() {
        assert_eq!(0, calculate_frequency(&vec!(1, 1, -2)));
    }

    #[test]
    fn sample3() {
        assert_eq!(-6, calculate_frequency(&vec!(-1, -2, -3)));
    }

    #[test]
    fn sample4() {
        assert_eq!(0, first_double_frequency(&vec!(1,-1)));
    }

    #[test]
    fn sample5() {
        assert_eq!(10, first_double_frequency(&vec!(3,3,4,-2,-4)));
    }

    #[test]
    fn sample6() {
        assert_eq!(5, first_double_frequency(&vec!(-6,3,8,5,-6)));
    }

    #[test]
    fn sample7() {
        assert_eq!(14, first_double_frequency(&vec!(7,7,-2,-7,-4)));
    }
}