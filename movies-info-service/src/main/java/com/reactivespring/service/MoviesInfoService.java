package com.reactivespring.service;

import com.reactivespring.domain.MovieInfo;
import com.reactivespring.repository.MovieInfoRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class MoviesInfoService {

    private MovieInfoRepository movieInfoRepository;

    public MoviesInfoService(MovieInfoRepository movieInfoRepository) {
        this.movieInfoRepository = movieInfoRepository;
    }

    public Mono<MovieInfo> addMovieInfo(MovieInfo movieInfo) {

        return movieInfoRepository.save(movieInfo);

    }

    public Flux<MovieInfo> getAllMovieInfos() {

        return movieInfoRepository.findAll();

    }

    public Mono<MovieInfo> getMovieInfoById(String id) {

        return movieInfoRepository.findById(id);

    }

    public Flux<MovieInfo> getMovieInfoByYear(Integer year) {

        return movieInfoRepository.findByYear(year);

    }

    public Mono<MovieInfo> getMovieInfoByName(String name) {

        return movieInfoRepository.findByName(name);

    }

    public Mono<MovieInfo> updateMovieInfo(MovieInfo movieInfo, String id) {

        return movieInfoRepository
                .findById(id)
                .flatMap(movieInfo1 -> {
                    movieInfo1.setMovieInfoId(movieInfo.getMovieInfoId());
                    movieInfo1.setYear(movieInfo.getYear());
                    movieInfo1.setCast(movieInfo.getCast());
                    movieInfo1.setRelease_date(movieInfo.getRelease_date());
                    movieInfo1.setName(movieInfo.getName());
                    return movieInfoRepository.save(movieInfo1);
                })
                ;

    }

    public Mono<Void> deleteMovieInfo(String id) {

        return movieInfoRepository
                .deleteById(id)
                ;

    }


}
