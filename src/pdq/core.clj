(ns pdq.core)

;; List words -> PDQ -> List words
;;
;; [\p, \d, \q]

(defn pdq->str
  [[p d q]]
  (str "^" p ".*" d ".*" q ".*"))

(defn pdq->regex
  [pdq]
  (re-pattern (pdq->str pdq)))

(defn matches?
  [regex word]
  (re-find regex word))

(defn solve
  [words pdq]
  (let [regex (pdq->regex pdq)]
    (filter (partial matches? regex) words)))

(defn lines
  [file-name fun]
  (with-open [rdr (clojure.java.io/reader file-name)]
    (vec (fun (line-seq rdr)))))

(defn solve-with-dict
  [pdq]
  (lines "/usr/share/dict/words"
         (fn [l] (solve l pdq))))
