(ns aoc2020.days.day2
  (:require [clojure.string :refer [split-lines split]]))

(defn parse-password [s]
  (let [[policy password] (split s #": ")
        [range letter] (split policy #" ")
        [lower upper] (split range #"-")]
    {
     :lower (Integer/parseInt lower)
     :upper (Integer/parseInt upper)
     :letter (first letter)
     :password password
     :occurrances (get (frequencies password) (first letter) 0)
     }))

(defn part-1 []
  (->> (slurp "resources/day2/input.txt")
      (split-lines)
      (map parse-password)
      (filter (fn [{:keys [occurrances upper lower]}]
                (<= lower occurrances upper)))
      (count)))


(defn check-index [password index value]
  (if (= value (nth password (dec index)))
    true
    nil))

(defn part-2 []
  (->> (slurp "resources/day2/input.txt")
      (split-lines)
      (map parse-password)
      (filter (fn [{:keys [password upper lower letter]}]
                   (not= (check-index password upper letter)
                         (check-index password lower letter))))
      (count)))
