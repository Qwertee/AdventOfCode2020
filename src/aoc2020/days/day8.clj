(ns aoc2020.days.day8
  (:require [clojure.string :as str]))

(def input (->> "resources/day8/input.txt"
                slurp
                str/split-lines
                (mapv #(str/split % #" "))
                (map (fn [[instr num]] [(keyword instr) (Integer/parseInt num)]))
                vec))

(defn part-1 [input]
  (execute input))

(defn part-2 [input]
  (filter (fn [[result number]]
            (= true result))
          (for [i (range (count input))]
            (let [[instr num] (nth input i)]
              (case instr
                :nop (execute (assoc input i [:jmp num]))
                :jmp (execute (assoc input i [:nop num]))
                :acc [nil nil])))))

(defn execute [input]
  (loop [pc 0
         acc 0
         visited #{}]
    (if (= pc (count input))
      [true acc]
      (let [[instr num] (nth input pc)]
        (if (visited pc)
          [false acc]
          (case instr
            :nop (recur (inc pc)   acc         (conj visited pc))
            :acc (recur (inc pc)   (+ acc num) (conj visited pc))
            :jmp (recur (+ pc num) acc         (conj visited pc))))))))
