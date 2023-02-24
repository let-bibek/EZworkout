package com.softdrax.ezworkout.api;

import com.softdrax.ezworkout.model.Exercise;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {
    @GET("exercises/bodyPartList")
    Call<Exercise> getExercise();

}
