(ns aoc2020.days.day6
  (:require [aoc2020.common :as common]
            [clojure.set :as set]
            [clojure.string :as str]))

(defn part1 []
  (->> "resources/day6/input.txt"
        slurp
        (common/split #"\n\n")
        (mapv #(str/split % #"\s+"))
        (map #(reduce concat %))
        (map #(into #{} %))))

(defn part2 []
  (->> "resources/day6/input.txt"
        slurp
        (common/split #"\n\n")                                     ; group inputs
        (mapv #(str/split % #"\s+"))                               ; combine lines
        (map (fn [items] (map #(into #{} %) items)))                ; each person's votes to a set
        (map (fn [items] (reduce #(set/intersection %1 %2) items))) ; intersections of each groups votes
        (map count)
        (reduce +)))
