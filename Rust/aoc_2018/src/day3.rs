extern crate regex;

use self::regex::Regex;
use std::collections::HashSet;

pub struct Patch {
    pub id: i32,
    pub from_left: usize,
    pub from_top: usize,
    pub width: usize,
    pub height: usize,
}

impl Patch {
    fn new (patch: &str) -> Patch{
        lazy_static! {
            static ref RE: Regex = Regex::new(r"^#(\d+) @ (\d+),(\d+): (\d+)x(\d+)$").unwrap();
        }
        let cap = RE.captures(patch).unwrap();
        Patch{
            id: cap[1].parse::<i32>().unwrap(),
            from_left: cap[2].parse::<usize>().unwrap(),
            from_top: cap[3].parse::<usize>().unwrap(),
            width: cap[4].parse::<usize>().unwrap(),
            height: cap[5].parse::<usize>().unwrap()
        }
    }
}

#[aoc_generator(day3)]
pub fn input_generator(input: &str) -> Vec<Patch> {
    input
        .lines()
        .map(|l| Patch::new(l))
        .collect()
}

#[aoc(day3, part1)]
pub fn multiple_claims(input: &[Patch]) -> i32 {
    let mut fabric: Vec<[i32; 1000]> = vec![[0; 1000]; 1000];
    for patch in input{
        for row in fabric.iter_mut().skip(patch.from_left).take(patch.width){
            for mut inch in row.iter_mut().skip(patch.from_top).take(patch.height){
                *inch += &1;
            }
        }
    };
    let mut counter = 0;
    for row in fabric.iter(){
        for inch in row.iter(){
            if *inch > 1 {counter += 1 }
        }
    }
    counter
}

fn set_id(mut fabric: Vec<[i32; 1000]>, patch: &Patch) -> Vec<[i32; 1000]>{
    for row in fabric.iter_mut().skip(patch.from_left).take(patch.width){
        for mut inch in row.iter_mut().skip(patch.from_top).take(patch.height){
            *inch = patch.id;
        }
    }
    fabric
}

fn current_ids(fabric: &[[i32; 1000]], patch: &Patch) -> HashSet<i32>{
    let mut set = HashSet::new();
    for row in fabric.iter().skip(patch.from_left).take(patch.width){
        for inch  in row.iter().skip(patch.from_top).take(patch.height){
            set.insert(*inch);
        }
    }
    set
}

#[aoc(day3, part2)]
pub fn no_claims(input: &[Patch]) -> i32 {
    let mut fabric: Vec<[i32; 1000]> = vec![[0; 1000]; 1000];
    let mut ids = HashSet::with_capacity(1000);
    for patch in input{
        let current_claims = current_ids(&fabric, patch);
        fabric = set_id(fabric, patch);
        if current_claims.len() == 1 && current_claims.contains(&0){
            ids.insert(patch.id);
        }else{
            for id in current_claims{
                ids.remove(&id);
            }
        }
    }
    *ids.iter().next().unwrap()
}

#[cfg(test)]
mod tests {
    use super::{multiple_claims, no_claims, Patch};

    fn to_patch_vec(values: Vec<&str>) -> Vec<Patch>{
        values
            .iter()
            .map(|l| Patch::new(l))
            .collect()
    }

    #[test]
    fn sample1() {
        assert_eq!(4, multiple_claims(&to_patch_vec(vec!("#1 @ 1,3: 4x4", "#2 @ 3,1: 4x4", "#3 @ 5,5: 2x2"))));
    }

    #[test]
    fn sample2() {
        assert_eq!(3, no_claims(&to_patch_vec(vec!("#1 @ 1,3: 4x4", "#2 @ 3,1: 4x4", "#3 @ 5,5: 2x2"))));
    }
}