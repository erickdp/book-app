package com.programacion.dto;

import io.helidon.common.http.Http;
import io.helidon.common.reactive.Multi;
import io.helidon.common.reactive.Single;
import io.helidon.dbclient.DbClient;
import io.helidon.dbclient.DbRow;
import io.helidon.webserver.*;
import jakarta.json.JsonObject;

import java.util.concurrent.CompletionException;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class AbstractPokemonService implements Service {
    private static final Logger LOGGER = Logger.getLogger(AbstractPokemonService.class.getName());

    private final DbClient dbClient;

    /**
     * Create a new pokemon service with a DB client.
     *
     * @param dbClient DB client to use for database operations
     */
    protected AbstractPokemonService(DbClient dbClient) {
        this.dbClient = dbClient;
    }


    /**
     * Get a single pokemon by name.
     *
     * @param request  server request
     * @param response server response
     */
    private void getPokemon(ServerRequest request, ServerResponse response) {
        String pokemonName = request.path().param("name");

        dbClient.execute(exec -> exec.namedGet("select-one", pokemonName))
                .thenAccept(opt -> opt.ifPresentOrElse(it -> sendRow(it, response),
                        () -> sendNotFound(response, "Pokemon "
                                + pokemonName
                                + " not found")))
                .exceptionally(throwable -> sendError(throwable, response));
    }

    protected void sendNotFound(ServerResponse response, String message) {
        response.status(Http.Status.NOT_FOUND_404);
        response.send(message);
    }

    protected void sendRow(DbRow row, ServerResponse response) {
        response.send(row.as(JsonObject.class));
    }

    protected <T> T sendError(Throwable throwable, ServerResponse response) {
        Throwable realCause = throwable;
        if (throwable instanceof CompletionException) {
            realCause = throwable.getCause();
        }
        response.status(Http.Status.INTERNAL_SERVER_ERROR_500);
        response.send("Failed to process request: " + realCause.getClass().getName() + "(" + realCause.getMessage() + ")");
        LOGGER.log(Level.WARNING, "Failed to process request", throwable);
        return null;
    }
}
