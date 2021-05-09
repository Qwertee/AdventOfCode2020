(ns aoc2020.days.day7
  (:require [clojure.string :as str]))

(def test-str "light red bags contain 1 bright white bag, 2 muted yellow bags.")

(defn get-bag-lookup [bag-contents]
  (for [[container containees] bag-contents]
    {container
     (for [[number color] containees]
       {:count number :color color}
       )}
    ))

(defn to-dependency [[container & containees]]
  (for [[number color] containees]
    {color [{:count (Integer/parseInt number) :contains container}]}))

(defn parse-instr [s]
  (let [[_ bag contents] (re-matches #"(.+)\sbags contain (.+)\." s)
        seperated-contents (-> contents
                               (str/replace #" bags?" "")
                               (str/split #"\s*, ")
                               )
        parsed (map #(rest (re-matches #"(\d+) (.+)" %)) seperated-contents)
        ]
    [bag parsed]))

(defn part-1 []
  (->> "resources/day7/input.txt"
       slurp
       str/split-lines
       (map parse-instr)
       get-bag-lookup
       (into {})
       ))

(to-dependency (parse-instr test-str))


{
 "blue" [{:count 1 :color "green"} {:count 1 :color "red"}]
 }
