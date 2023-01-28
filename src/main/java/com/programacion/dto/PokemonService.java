package com.programacion.dto;

import io.helidon.dbclient.DbClient;
import io.helidon.webserver.Routing;

public class PokemonService extends AbstractPokemonService {
    PokemonService(DbClient dbClient) {
        super(dbClient);

        // dirty hack to prepare database for our POC
        // MySQL init
        dbClient.execute(handle -> handle.namedDml("create-table"))
                .thenAccept(System.out::println)
                .exceptionally(throwable -> {
                    return null;
                });
    }

    @Override
    public void update(Routing.Rules rules) {

    }
}
