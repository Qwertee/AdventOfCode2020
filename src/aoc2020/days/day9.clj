(ns aoc2020.days.day9
  (:require [clojure.string :as str]))

(def input (->> "resources/day9/input.txt"
                slurp
               str/split-lines
               (map #(bigint %))
               (partition 26 1)
               (map (fn [grp] [(butlast grp)(last grp)]))
               ))

(def target 14144619N)

(defn find-first
         [f coll]
         (first (filter f coll)))

(defn part-1 [input]
  (->> (map valid-group? input)
       (remove #(= nil %))
       first)
  )

(defn part-2 []
  (let [nums (->> "resources/day9/input.txt"
                  slurp
                  str/split-lines
                  (map bigint))]
    (for [start (range (count nums))]
      (if (contains-sequence? [start tgt nums])
        [true ()]))))

(defn valid-group? [[numbers target]]
  (when (not ((set
                (for [i numbers
                      j numbers]
                  (when (not= i j)
                    (+ i j)
                    ))) target))
    target))


(defn part-11 [input]
  (-> (partition 26 1 input)
      ))

(valid-group? ['(8123576148N
   8839359343N
   8304047925N
   9218672931N
   9582551976N
   9623610480N
   16709892023N
   12782346972N
   12114203405N
   14578686689N
   15872339349N
   9777575136N
   11084122139N
   11216699421N
   19486080618N
   17241317822N
   13283925521N
   13603333646N
   30313225669N
   14761660292N
   17620631410N
   16168912872N
   16884696067N
   16349384649N
   25568057580N)
  20666674115N])


(defn max-array [arr]
  (reduce #'max arr))
(max)
