(defproject om-inputs "0.3.7-SNAPSHOT"
  :description "Generate Web Input Form for Om/React.js, validation included."
  :url "https://github.com/hiram-madelaine/om-inputs"

  :dependencies [[org.clojure/clojure "1.6.0"]
                 [org.clojure/clojurescript "0.0-2371" :scope "provided"]
                 [org.clojure/core.async "0.1.346.0-17112a-alpha"]
                 [om "0.8.0-alpha1" :scope "provided"]
                 [prismatic/dommy "0.1.2"]
                 [prismatic/schema "0.3.3"]
                 [com.facebook/react "0.11.1"]
                 [jkkramer/verily "0.6.0"]
                 [figwheel "0.1.5-SNAPSHOT"]
                 [com.cemerick/piggieback "0.1.3"]
                 [weasel "0.4.2"]
                 [sablono "0.2.22"]
                 [clj-vat "0.1.2" :scope "provided"]]

  :plugins [[lein-cljsbuild "1.0.4-SNAPSHOT"]
            [lein-figwheel "0.1.5-SNAPSHOT"]
            [codox "0.8.10"]]

  :min-lein-version "2.5.0"

  :uberjar-name "om-inputs.jar"

  :jvm-opts ["-Xmx1g" "-server"]

  :codox {:language :clojurescript
          :include [om-inputs.date-utils om-inputs.core]}
  :resource-paths ["examples"]

  :repl-options {:nrepl-middleware [cemerick.piggieback/wrap-cljs-repl]}

  :cljsbuild {
              :builds [{:id "dev"
                        :source-paths ["src" "examples/contact/src"]
                        :compiler {:output-to "examples/contact/out/om_inputs.js"
                                   :output-dir "examples/contact/out"
                                   :optimizations :none
                                   :source-map true}}
                       {:id "simple"
                        :source-paths ["src" "examples/contact/src"]
                        :compiler {:output-to "examples/contact/out/main.js"
                                   :optimizations :simple
                                   :pretty-print true
                                   :preamble ["react/react.min.js"]
                                   :externs ["react/externs/react.js"]}}
                       {:id "release"
                        :source-paths ["src" "examples/contact/src"]
                        :compiler {:output-to "examples/contact/out/main.js"
                                   :optimizations :advanced
                                   ;:closure-warnings {:check-useless-code :on}
                                   :pretty-print false
                                   :pseudo-names false
                                   :preamble ["react/react.min.js"]
                                   :externs ["react/externs/react.js"]}}]}


  :figwheel {:http-server-root "contact"
             :server-port 3449
             :css-dirs ["examples/contact/css"]}


  #_(:profiles {:dev     {:repl-options {:init-ns          try-chestnut.server
                                       :nrepl-middleware [cemerick.piggieback/wrap-cljs-repl]}
                        :plugins      [[lein-figwheel "0.1.4-SNAPSHOT"]]
                        :figwheel     {:http-server-root "public"
                                       :port             3449
                                       :css-dirs         ["resources/public/css"]}
                        :env          {:is-dev true}
                        :cljsbuild    {:builds {:app {:source-paths ["env/dev/cljs"]}}}}

              :uberjar {:hooks       [leiningen.cljsbuild]
                        :env         {:production true}
                        :omit-source true
                        :aot         :all
                        :cljsbuild   {:builds {:app
                                                {:source-paths ["env/prod/cljs"]
                                                 :compiler
                                                               {:optimizations :advanced
                                                                :pretty-print  false}}}}}}))
