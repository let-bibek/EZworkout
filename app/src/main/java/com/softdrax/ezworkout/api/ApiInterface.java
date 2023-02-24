package com.softdrax.ezworkout.api;

import com.softdrax.ezworkout.model.Exercise;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {
    String BASE_URL="https://jsonplaceholder.typicode.com/";
    @GET("posts")
    Call<Exercise> getExercise();

}
