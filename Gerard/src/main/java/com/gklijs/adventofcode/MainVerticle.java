package com.gklijs.adventofcode;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import io.reactivex.Single;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServerRequest;
import static com.gklijs.adventofcode.Answers.ANS;
import static io.reactivex.Observable.fromIterable;

public class MainVerticle extends AbstractVerticle {

    private static final Logger LOGGER = Logger.getLogger(MainVerticle.class.getName());

    private static final String FORM = "<link rel=\"stylesheet\" href=\"https://cdnjs.cloudflare.com/ajax/libs/bulma/0.7.2/css/bulma.min.css\">" +
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
        "                           <option value=\"14\">Day 14</option>\n" +
        "                           <option value=\"15\">Day 15</option>\n" +
        "                           <option value=\"16\">Day 16</option>\n" +
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
        "       </div>\n" +
        "  </section>";

    private static final String RESULT = "<link rel=\"stylesheet\" href=\"https://cdnjs.cloudflare.com/ajax/libs/bulma/0.7.2/css/bulma.min.css\">" +
        "   <section class=\"section\">\n" +
        "       <div class=\"container\">" +
        "           <div class=\"notification %s\">" +
        "               <p>Result for day: %d part %s</p>" +
        "               <p>%s</p>" +
        "           </div>\n" +
        "           <a href=\"/\">\n" +
        "               <button class=\"button is-link\">Return</button>\n" +
        "           </a>\n" +
        "       </div>\n" +
        "  </section>";

    @Override
    public void start(Future<Void> startFuture) {
        //Route route = router.route()
        vertx.createHttpServer()
            .requestHandler(getRequestHandler()).listen(8080, http -> {
            if (http.succeeded()) {
                startFuture.complete();
                LOGGER.info("HTTP server started on http://localhost:8080");
            } else {
                startFuture.fail(http.cause());
            }
        });
    }

    private Handler<HttpServerRequest> getRequestHandler() {
        return req -> {
            if (req.method().equals(HttpMethod.GET)) {
                req.response()
                    .putHeader("content-type", "text/html")
                    .end(FORM);
            } else {
                req.setExpectMultipart(true);
                req.endHandler(v -> {
                    List<String> input = Arrays.asList(req.getFormAttribute("input").split("\n"));
                    int day = Integer.parseInt(req.getFormAttribute("day"));
                    String part = req.getFormAttribute("part");
                    Single<String> s = "1".equals(part) ? ANS.get(day).getFirst().apply(fromIterable(input)) : ANS.get(day).getSecond().apply(fromIterable(input));
                    String result;
                    String type;
                    try {
                        result = s.blockingGet();
                        type = "is-success";
                    } catch (RuntimeException e) {
                        result = "Some error occurred, please make sure you selected the right day for the input. Error was: " + e.toString();
                        type = "is-danger";
                    }
                    req.response()
                        .putHeader("content-type", "text/html")
                        .end(String.format(RESULT, type, day, part, result));
                });
            }
        };
    }
}
