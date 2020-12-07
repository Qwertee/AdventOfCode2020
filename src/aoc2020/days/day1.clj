(ns aoc2020.days.day1
  (:require [clojure.string :refer [split-lines]]))

(def numbers
  (->> (slurp "resources/day1/part1.txt")
       split-lines
       (map #(Integer/parseInt %))))

(defn part-1 []
  (for [i numbers
        j numbers
        :when (= 2020 (+ i j))]
    (* i j)))

(defn part-2 []
  (for [i numbers
        j numbers
        k numbers
        :when (= 2020 (+ i j k))]
    (* i j k)))


(println (first (part-1)))
(println (first (part-2)))
