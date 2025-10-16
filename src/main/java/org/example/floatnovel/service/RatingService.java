package org.example.floatnovel.service;

import org.example.floatnovel.entity.Rating;
import org.example.floatnovel.entity.Result;

public interface RatingService {


    Result add(Rating rating);

    Result<Integer> getScore(Long novelId);
}
