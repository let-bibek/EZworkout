package com.softdrax.ezworkout.model;

import java.util.ArrayList;

public class Exercise {
//    private String name;
//    private String type;
//    private String muscle;
//    private String equipment;
//    private String difficulty;
//    private String instructions;

    private String title;
    private String  body;

    public Exercise(String title, String body) {
        this.title = title;
        this.body = body;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public ArrayList<Exercise> getArrayExercise() {
        return arrayExercise;
    }

    public void setArrayExercise(ArrayList<Exercise> arrayExercise) {
        this.arrayExercise = arrayExercise;
    }

    ArrayList<Exercise> arrayExercise;

//    public Exercise(String name, String type, String muscle, String equipment, String difficulty, String instructions) {
//        this.name = name;
//        this.type = type;
//        this.muscle = muscle;
//        this.equipment = equipment;
//        this.difficulty = difficulty;
//        this.instructions = instructions;
//    }

//    public String getName() {
//        return name;
//    }

    public void setName(String name) {
//        this.name = name;
    }

//    public String getType() {
//        return type;
//    }

    public void setType(String type) {
//        this.type = type;
    }

//    public String getMuscle() {
//        return muscle;
//    }
//
//    public void setMuscle(String muscle) {
//        this.muscle = muscle;
//    }
//
//    public String getEquipment() {
//        return equipment;
//    }
//
//    public void setEquipment(String equipment) {
//        this.equipment = equipment;
//    }
//
//    public String getDifficulty() {
//        return difficulty;
//    }
//
//    public void setDifficulty(String difficulty) {
//        this.difficulty = difficulty;
//    }
//
//    public String getInstructions() {
//        return instructions;
//    }
//
//    public void setInstructions(String instructions) {
//        this.instructions = instructions;
//    }
}
