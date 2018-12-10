extern crate regex;

use self::regex::Regex;
use std::collections::HashMap;

type Data = (HashMap<usize, usize>, HashMap<usize, Vec<(usize, usize)>>);

#[aoc_generator(day4)]
pub fn input_generator(input: &str) -> Vec<&str> {
    let mut lines: Vec<&str> = input.trim().lines().map(|l| l.trim()).collect::<Vec<_>>();
    lines.sort();
    lines
}

fn to_data(lines: Vec<&str>) -> Data{
    let regex_number = Regex::new(r"(\d+)").unwrap();
    let regex_start = Regex::new(r"begins shift").unwrap();
    let regex_sleep = Regex::new(r"falls asleep").unwrap();
    let regex_wake = Regex::new(r"wakes up").unwrap();

    // Track each guard's minutes asleep and the sleep ranges
    let mut time_asleep = HashMap::new();
    let mut sleep_ranges = HashMap::new();
    let mut current_guard = 0;
    let mut start_sleep = 0;
    for line in lines {
        let caps: Vec<_> = regex_number.captures_iter(&line).map(|cap| cap[1].parse::<usize>().unwrap()).collect();
        let time = ((caps[3] + 12) % 24) * 60 + caps[4];

        if regex_start.captures(&line).is_some() {
            current_guard = caps[5];
        } else if regex_sleep.captures(&line).is_some() {
            start_sleep = time;
        } else if regex_wake.captures(&line).is_some() {
            *time_asleep.entry(current_guard).or_insert(0) += time - start_sleep;
            sleep_ranges.entry(current_guard).or_insert(vec![]).push((start_sleep, time));
        } else {
            panic!("Unable to parse input");
        }
    }
    (time_asleep, sleep_ranges)
}

pub fn best_opportunity (input: Vec<&str> )  -> i32 {
    let (time_asleep, sleep_ranges) = to_data(input);
    // Find which guard slept the most
    let guard = time_asleep.iter().map(|(guard, minutes)| (minutes, guard)).max().unwrap().1;

    // Find the minute that the guard spent the most days asleep during
    let mut days_asleep = HashMap::new();
    for range in &sleep_ranges[guard] {
        for minute in range.0 .. range.1 {
            *days_asleep.entry(minute).or_insert(0) += 1;
        }
    }
    let minute = days_asleep.iter().map(|(m, d)| (d, m)).max().unwrap().1 % 60;
    (guard * minute) as i32
}

pub fn most_at_same_minute (input: Vec<&str> ) -> i32 {
    let (_, sleep_ranges) = to_data(input);
    let mut most = (0, 0, 0); // (days, minute, guard)
    for (guard, ranges) in &sleep_ranges {
        let mut days_asleep = HashMap::new();
        for range in ranges {
            for minute in range.0 .. range.1 {
                *days_asleep.entry(minute).or_insert(0) += 1;
            }
        }
        let (days, minute) = days_asleep.iter().map(|(m, d)| (d, m)).max().unwrap();
        let cand = (*days, *minute, *guard);
        if cand > most {
            most = cand;
        }
    }
    let guard = most.2;
    let minute = most.1 % 60;
    (guard * minute) as i32
}

#[cfg(test)]
mod tests {
    use super::{best_opportunity, most_at_same_minute, input_generator};

    fn test_input() -> &'static str {
        "[1518-11-01 00:00] Guard #10 begins shift
        [1518-11-01 00:05] falls asleep
        [1518-11-01 00:25] wakes up
        [1518-11-01 00:30] falls asleep
        [1518-11-01 00:55] wakes up
        [1518-11-01 23:58] Guard #99 begins shift
        [1518-11-02 00:40] falls asleep
        [1518-11-02 00:50] wakes up
        [1518-11-03 00:05] Guard #10 begins shift
        [1518-11-03 00:24] falls asleep
        [1518-11-03 00:29] wakes up
        [1518-11-04 00:02] Guard #99 begins shift
        [1518-11-04 00:36] falls asleep
        [1518-11-04 00:46] wakes up
        [1518-11-05 00:03] Guard #99 begins shift
        [1518-11-05 00:45] falls asleep
        [1518-11-05 00:55] wakes up"
    }

    #[test]
    fn sample1() {
        assert_eq!(
            240,
            best_opportunity(input_generator(test_input()))
        );
    }

    #[test]
    fn sample2() {
        assert_eq!(
            4455,
            most_at_same_minute(input_generator(test_input()))
        );
    }
}
