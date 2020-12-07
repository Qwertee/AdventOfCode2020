(ns aoc2020.days.day5
  (:require [clojure.string :as str]))

(def lines
  (-> "resources/day5/input.txt"
      slurp
      (str/split-lines)))

(defn part1 []
  (->> (for [line lines]
         (map #(if (or (= % \F)
                       (= % \L))
                 :lower
                 :higher) line))

       (map #(split-at 7 %))
       (map (fn [[row seat]]
              [(get-row row) (get-row seat)]))
       (map (fn [[row seat]]
              (+ seat (* 8 row))))
       (reduce max)
       ))

(defn part2 []
  (->> lines
       (map #(split-at 7 %))
       (map (fn [[row seat]]
              [(get-row row) (get-row seat)]))
       (map (fn [[row seat]]
              (+ seat (* 8 row))))
       (sort)
       (reduce #(if (not= %2 (inc %1))
                  (reduced (inc %1))
                  %2))))

(defn exp [x n]
  (reduce * (repeat n x)))

(defn get-row [code]
  (loop [c code
         rows (range (exp 2 (count code)))]
    (if (empty? c)
      (first rows)
      (case (first c)
        :lower (recur (rest c) (take (/ (count rows) 2) rows))
        :higher (recur (rest c) (drop (/ (count rows) 2) rows))))))
