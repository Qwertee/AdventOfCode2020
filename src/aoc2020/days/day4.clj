(ns aoc2020.days.day4
  (:require [clojure.string :as str]))

(defn check-height [height-str]
  (let [[height unit] (rest (re-matches #"(\d+)(cm|in)" height-str))]
    (case unit
      "cm" (<= 150 (Integer/parseInt height) 193)
      "in" (<= 59 (Integer/parseInt height) 76)
      false)))

(def rules
  {"byr" (fn [x] (<= 1920 (Integer/parseInt x) 2002))
   "iyr" (fn [x] (<= 2010 (Integer/parseInt x) 2020))
   "eyr" (fn [x] (<= 2020 (Integer/parseInt x) 2030))
   "hgt" (fn [x] (check-height x))
   "hcl" (fn [x] (some? (re-matches #"#[a-f0-9]{6}" x)))
   "ecl" (fn [x] (some? (#{"amb" "blu" "brn" "gry" "grn" "hzl" "oth"} x)))
   "pid" (fn [x] (some? (re-matches #"\d{9}" x)))
   "cid" (constantly true)})

(defn group-passports [lines]
  (->>
   (partition-by #(= "" %) lines)
   (filter #(not= % '("")))
   (map #(str/join " " %))))

(defn parse-passport [passport]
  (str/join " " passport))

(defn required-fields? [passport]
  (reduce #(and %1 %2) (map #(str/includes? passport %) ["byr:" "iyr:" "eyr:" "hgt:" "hcl:" "ecl:" "pid:"]))
  )

(defn check-constraints [passport]
  (-> passport
      (str/split #" ")))

(defn parse-fields [passport]
  (let [fields (str/split passport #" ")]
    (->> (map #(str/split % #":") fields)
         (into {})
         (seq))))

(defn validate-fields [fields]
  (reduce #(and %1 %2)
          (map (fn [[field value]]
                 ((get rules field) value)) fields)))
(defn part-1 []
  (->> (slurp "resources/day4/input.txt")
       (str/split-lines)
       (group-passports)
       (filter required-fields?)
                                        ; (map #(count (str/split % #" ")))
       (count)
       ))
(defn part-2 []
  (->> (slurp "resources/day4/input.txt")
       (str/split-lines)
       (group-passports)
       (filter required-fields?)
       (map parse-fields)
       (filter validate-fields)
       (count)
       ))
