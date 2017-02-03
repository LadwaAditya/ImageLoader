package com.ladwa.aditya.image.data;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.ladwa.aditya.image.data.model.NamedResource;
import com.ladwa.aditya.image.data.model.Pokemon;
import com.ladwa.aditya.image.data.model.PokemonListResponse;
import com.ladwa.aditya.image.data.remote.MvpStarterService;
import rx.Single;
import rx.functions.Func1;

@Singleton
public class DataManager {

    private final MvpStarterService mMvpStarterService;

    @Inject
    DataManager(MvpStarterService mvpStarterService) {
        mMvpStarterService = mvpStarterService;
    }

    public Single<List<String>> getPokemonList(int limit) {
        return mMvpStarterService.getPokemonList(limit)
                .flatMap(new Func1<PokemonListResponse, Single<List<String>>>() {
                    @Override
                    public Single<List<String>> call(PokemonListResponse pokemonListResponse) {
                        List<String> pokemonNames = new ArrayList<>();
                        for (NamedResource pokemon : pokemonListResponse.results) {
                            pokemonNames.add(pokemon.name);
                        }
                        return Single.just(pokemonNames);
                    }
                });
    }

    public Single<Pokemon> getPokemon(String name) {
        return mMvpStarterService.getPokemon(name);
    }

}