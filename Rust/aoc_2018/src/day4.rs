extern crate regex;

use self::regex::Regex;
use std::collections::HashMap;

type Data = (Vec<usize>, Vec<usize>, Vec<Vec<(usize, usize)>>);

#[aoc_generator(day4)]
pub fn input_generator(input: &str) -> Data {
    let mut lines: Vec<&str> = input.trim().lines().map(|l| l.trim()).collect::<Vec<_>>();
    lines.sort();
    to_data(lines)
}

fn to_data(lines: Vec<&str>) -> Data{
    let regex_number = Regex::new(r"(\d+)").unwrap();

    // Track each guard's minutes asleep and the sleep ranges
    let mut time_asleep = HashMap::new();
    let mut sleep_ranges = HashMap::new();
    let mut current_guard = 0;
    let mut start_sleep = 0;
    for line in lines {
        let caps: Vec<_> = regex_number.captures_iter(&line).map(|cap| cap[1].parse::<usize>().unwrap()).collect();
        let time = ((caps[3] + 12) % 24) * 60 + caps[4];

        if line.contains("begins shift") {
            current_guard = caps[5];
        } else if line.contains("falls asleep") {
            start_sleep = time;
        } else if line.contains("wakes up") {
            *time_asleep.entry(current_guard).or_insert(0) += time - start_sleep;
            sleep_ranges.entry(current_guard).or_insert(vec![]).push((start_sleep, time));
        } else {
            panic!("Unable to parse input");
        }
    }
    let mut guards = Vec::new();
    let mut sleep = Vec::new();
    let mut sleep_range = Vec::new();
    for kv in time_asleep.iter(){
        guards.push(*kv.0);
        sleep.push(*kv.1);
        sleep_range.push(sleep_ranges[kv.0].clone())
    };
    (guards, sleep, sleep_range)
}


pub fn best_opportunity (input: Data)  -> i32 {
    let (g,s,r) = input;
    // Find the id of the that guard slept the most
    let mut id = 0;
    let mut max_asleep = 0;
    for i in 0..s.len(){
        if s[i] > max_asleep {
            max_asleep = s[i];
            id = i;
        }
    }
    // Find the minute that the guard spent the most days asleep during
    let mut days_asleep = HashMap::new();
    for range in &r[id] {
        for minute in range.0 .. range.1 {
            *days_asleep.entry(minute).or_insert(0) += 1;
        }
    }
    let minute = days_asleep.iter().map(|(m, d)| (d, m)).max().unwrap().1 % 60;
    (g[id] * minute) as i32
}


pub fn most_at_same_minute (input: Data) -> i32 {
    let (g,_s,r) = input;
    let mut most = (0, 0, 0); // (days, minute, guard)
    for i in 0..g.len() {
        let mut days_asleep = HashMap::new();
        for range in &r[i] {
            for minute in range.0 .. range.1 {
                *days_asleep.entry(minute).or_insert(0) += 1;
            }
        }
        let (days, minute) = days_asleep.iter().map(|(m, d)| (d, m)).max().unwrap();
        let cand = (*days, *minute, g[i]);
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
