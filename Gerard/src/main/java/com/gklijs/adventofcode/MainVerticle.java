package com.gklijs.adventofcode;

import java.util.logging.Logger;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;

public class MainVerticle extends AbstractVerticle {

    private static final Logger LOGGER = Logger.getLogger(MainVerticle.class.getName());

    @Override
    public void start(Future<Void> startFuture) throws Exception {
        vertx.createHttpServer()
            .requestHandler(req -> req.response()
                .putHeader("content-type", "text/html")
                .end(
                    "<link rel=\"stylesheet\" href=\"https://cdnjs.cloudflare.com/ajax/libs/bulma/0.7.2/css/bulma.min.css\">" +
                        "   <section class=\"section\">\n" +
                        "       <div class=\"container\">" +
                        "       <div class=\"field is-horizontal\">" +
                        "           <form action=\"/\" method=\"post\">\n" +
                        "               <div class=\"field\">\n" +
                        "                   <label class=\"label\" for=\"input\">Enter the input:</label>\n" +
                        "                   <textarea rows=\"5\" cols=\"100\" id=\"input\" name=\"input\"></textarea>\n" +
                        "               </div>\n" +
                        "               <div class=\"control\">\n" +
                        "                   <div class=\"select\">\n" +
                        "                       <select id=\"day\" name=\"day\">\n" +
                        "                           <option value=\"1\">Day 1</option>\n" +
                        "                           <option value=\"2\">Day 2</option>\n" +
                        "                           <option value=\"3\">Day 3</option>\n" +
                        "                           <option value=\"4\">Day 4</option>\n" +
                        "                           <option value=\"5\">Day 5</option>\n" +
                        "                           <option value=\"6\">Day 6</option>\n" +
                        "                           <option value=\"7\">Day 7</option>\n" +
                        "                           <option value=\"8\">Day 8</option>\n" +
                        "                           <option value=\"9\">Day 9</option>\n" +
                        "                           <option value=\"10\">Day 10</option>\n" +
                        "                           <option value=\"11\">Day 11</option>\n" +
                        "                           <option value=\"12\">Day 12</option>\n" +
                        "                           <option value=\"13\">Day 13</option>\n" +
                        "                       </div>\n" +
                        "                   </select>\n" +
                        "               </div>\n" +
                        "               <div class=\"control\">\n" +
                        "                   <label class=\"radio\">\n" +
                        "                        <input type=\"radio\" name=\"part\" value=\"1\" checked>\n" +
                        "                           part 1\n" +
                        "                   </label>\n" +
                        "                   <label class=\"radio\">\n" +
                        "                       <input type=\"radio\" name=\"part\" value=\"2\">\n" +
                        "                           part 2\n" +
                        "                   </label>\n" +
                        "               </div>\n" +
                        "               <div class=\"control\">\n" +
                        "                   <button class=\"button is-primary\" type=\"submit\">Send</button>\n" +
                        "               </div>\n" +
                        "               </div>\n" +
                        "           </form>\n" +
                        "       </div>\n" +
                        "  </section>")).listen(8080, http -> {
            if (http.succeeded()) {
                startFuture.complete();
                LOGGER.info("HTTP server started on http://localhost:8080");
            } else {
                startFuture.fail(http.cause());
            }
        });
    }
}
