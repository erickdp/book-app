package com.programacion.dto;

import io.helidon.config.Config;
import io.helidon.dbclient.DbClient;
import io.helidon.health.HealthSupport;
import io.helidon.media.jsonp.JsonpSupport;
import io.helidon.tracing.TracerBuilder;
import io.helidon.webserver.Routing;
import io.helidon.webserver.WebServer;

import java.io.IOException;

public class Jdbc {

    public static void main(String[] args) throws IOException {
        startServer();
    }
    static WebServer startServer() throws IOException {

        // By default this will pick up application.yaml from the classpath
        Config config = Config.create();

        // Prepare routing for the server
        WebServer server = WebServer.builder().addRouting(createRouting(config))
                // Get webserver config from the "server" section of application.yaml
                .config(config.get("server"))
                .tracer(TracerBuilder.create(config.get("tracing")))
                .addMediaSupport(JsonpSupport.create())
//                .addMediaSupport(JsonbSupport.create())
                .build();

        // Start the server and print some info.
        server.start().thenAccept(ws -> {
            System.out.println(
                    "WEB server is up! http://localhost:" + ws.port() + "/");
        });

        // Server threads are not daemon. NO need to block. Just react.
        server.whenShutdown().thenRun(() -> System.out.println("WEB server is DOWN. Good bye!"));

        return server;
    }

    /**
     * Creates new {@link io.helidon.webserver.Routing}.
     *
     * @param config configuration of this server
     * @return routing configured with JSON support, a health check, and a service
     */
    private static Routing createRouting(Config config) {
        Config dbConfig = config.get("db");

        // Client services are added through a service loader - see mongoDB example for explicit services
        DbClient dbClient = DbClient.builder(dbConfig)
                .build();

        return Routing.builder()
                .register("/db", new PokemonService(dbClient))
                .build();
    }
}
