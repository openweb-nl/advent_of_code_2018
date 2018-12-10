use std::collections::HashMap;
use std::collections::LinkedList;
use std::fmt::Debug;
use std::iter::FromIterator;

#[aoc_generator(day2)]
pub fn input_generator(input: &str) -> Vec<Vec<char>> {
    input.lines().map(|l| l.chars().collect()).collect()
}

fn does_contain<T, U>(map: &HashMap<T, U>, value: &U) -> bool
where
    T: Eq + std::hash::Hash,
    U: PartialEq,
{
    map.values().any(|val| val == value)
}

fn to_frequency_map(id: &[char]) -> HashMap<char, i32> {
    let mut result = HashMap::new();
    for c in id {
        result.entry(c.clone()).and_modify(|e| *e += 1).or_insert(1);
    }
    result
}

#[aoc(day2, part1)]
pub fn checksum(input: &[Vec<char>]) -> i32 {
    let counts =
        input
            .iter()
            .map(|id| to_frequency_map(id))
            .fold((0, 0), |counts, frequency_map| {
                let new_left = if does_contain(&frequency_map, &2) {
                    counts.0 + 1
                } else {
                    counts.0
                };
                let new_right = if does_contain(&frequency_map, &3) {
                    counts.1 + 1
                } else {
                    counts.1
                };
                (new_left, new_right)
            });
    counts.0 * counts.1
}

fn only_one_diff<T>(first: &[T], second: &[T]) -> Option<usize>
where
    T: Eq + Debug,
{
    if first.len() != second.len() {
        return None;
    }
    let mut result = None;
    for i in 0..first.len() {
        if first[i] != second[i] {
            match result {
                None => result = Some(i),
                Some(_) => return None,
            }
        }
    }
    result
}

fn contains_only_one_diff(item: &[char], list: &LinkedList<&Vec<char>>) -> Option<usize> {
    for second_item in list {
        let diff = only_one_diff(item, second_item);
        if diff != None {
            return diff;
        }
    }
    None
}

#[aoc(day2, part2)]
pub fn common_letters(input: &[Vec<char>]) -> String {
    let mut list = LinkedList::new();
    for item in input {
        let diff = contains_only_one_diff(item, &list);
        match diff {
            None => list.push_back(item),
            Some(place) => {
                let mut result = item.clone();
                result.remove(place);
                return String::from_iter(result);
            }
        }
    }
    String::from("Not found")
}

#[cfg(test)]
mod tests {
    use super::{checksum, common_letters};

    fn to_char_vec(values: Vec<&str>) -> Vec<Vec<char>> {
        values.iter().map(|l| l.chars().collect()).collect()
    }

    #[test]
    fn sample1() {
        assert_eq!(
            12,
            checksum(&to_char_vec(vec!(
                "abcdef", "bababc", "abbcde", "abcccd", "aabcdd", "abcdee", "ababab"
            )))
        );
    }

    #[test]
    fn sample2() {
        assert_eq!(
            "fgij",
            common_letters(&to_char_vec(vec!(
                "abcde", "fghij", "klmno", "pqrst", "fguij", "axcye", "wvxyz"
            )))
        );
    }
}
