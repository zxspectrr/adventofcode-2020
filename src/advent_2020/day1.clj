(ns advent-2020.day1
  (:require [advent-2020.utils :as u]))

(def path "resources/day1/input.txt")
;(def path "resources/day1/small.txt")

(def numbers
  (->> (u/read-lines path)
       (map u/parse-int)))

(defn other-numbers [number numbers]
  (filter #(not (= % number)) numbers))

(defn check-two [number numbers goal]
  (->> (other-numbers number numbers)
       (map #(+ number %))
       (filter #(= goal %))
       first))

(defn check-three [number numbers]
  (->> (other-numbers number numbers)
       (filter #(check-two % numbers (- 2020 number)))
       first))

(defn process [filter-fn]
  (->> (filter filter-fn numbers)
       (reduce *)))

(defn part1 []
  (process #(check-two % numbers 2020)))

(defn part2 []
  (process #(check-three % numbers)))
