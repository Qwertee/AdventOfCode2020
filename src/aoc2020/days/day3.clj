(ns aoc2020.days.day3
  (:require [clojure.string :refer [split-lines]]))

(defn find-path [[x y] [dx dy] trees slope]
  (if (>= x (count slope))
    trees
    (find-path [(+ x dx) (+ y dy)]
               [dx dy]
               (if (= \# (nth (nth slope x) y))
                 (inc trees)
                 trees) slope)))

(defn find-path-loop [[dx dy] slope]
  (loop [[x y] [0 0]
         trees 0]
    (if (>= x (count slope))
      trees
      (recur [(+ x dx) (+ y dy)]
             (if (= \# (nth (nth slope x) y))
                 (inc trees)
                 trees)))))

(defn find-path-for [[dx dy] slope]
  (reduce + (for [x (range 0 (count slope) dx)
                  :let [y (/ (* x dy) dx) ;; this is slightly confusing, but the key to this approach
                        z (-> slope (nth x) (nth y))
                        zz (get-in slope [x y]) ;; TODO: Why doesn't this work? Seems to always return zero
                        ]
                  :when (= z \#)]
              1)))

(defn find-path-reduce [[dx dy] slope]
  (reduce))

(defn part1 []
  (->> (slurp "resources/day3/input.txt")
       (split-lines)
       (map cycle)
       (find-path-for [1 3])))

(defn part2 []
  (let [m
        (->> (slurp "resources/day3/input.txt")
             (split-lines)
             (map cycle))]
    (reduce * (map #(find-path-for % m)
                   [[1 1] [1 3] [1 5] [1 7] [2 1]]))))
