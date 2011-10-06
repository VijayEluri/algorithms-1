(ns word-chains
  (:require [clojure.string :as s]))

(def dictionary
     (->> "/usr/share/dict/words"
          slurp
          s/split-lines
          (into #{})))

(def letters [\a \b \c \d \e \f \g \h \i \j \k \l \m \n \o \p \q \r \s \t \u \v \w \x \y \z])

(defn- add-a-letter-at
  [i word]
  (let [f (.substring word 0 i)
        l (.substring word i)]
    (map #(str f % l) letters)))

(defn- words-with-a-letter-added
  [word]
  (mapcat #(add-a-letter-at % word)
          (range (inc (count word)))))

(defn- remove-a-letter-at
  [i word]
  (str (.substring word 0 i)
       (.substring word (inc i))))

(defn- words-with-a-letter-removed
  [word]
  (map #(remove-a-letter-at % word)
       (range (count word))))

(defn- replace-a-letter-at
  [i word]
  (let [f (.substring word 0 i)
        l (.substring word (inc i))]
    (map #(str f % l) (remove #(= % (.charAt word i)) letters))))

(defn- words-with-a-letter-changed
  [word]
  (mapcat #(replace-a-letter-at % word)
          (range (count word))))

(defn- neighbors [word]
  (filter dictionary
          (concat (words-with-a-letter-changed word)
                  (words-with-a-letter-added word)
                  (words-with-a-letter-removed word))))

(defn search [start end]
  (loop [cache {start :root}
         queue (-> (clojure.lang.PersistentQueue/EMPTY) (conj start))]
    (when-let [node (peek queue)]
      (let [nghbrs (->> node neighbors (remove cache) seq)]
        (cond
         (some #{end} nghbrs)
         (take-while #(not= % :root)
                     (iterate (assoc cache end node) end))
         nghbrs
         (recur (reduce #(assoc %1 %2 node) cache nghbrs)
                (into (pop queue) nghbrs))
         :else
         (recur cache (pop queue)))))))
