package com.softdrax.ezworkout;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.softdrax.ezworkout.model.Exercise;

import java.util.ArrayList;

public class MainActivityAdapter extends RecyclerView.Adapter<MainActivityAdapter.ViewHolder> {

    Context context;
    ArrayList<Exercise> modelArrayList;

    public MainActivityAdapter(Context context, ArrayList<Exercise> modelArrayList) {
        this.context = context;
        this.modelArrayList = modelArrayList;
    }

    @NonNull
    @Override
    public MainActivityAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(context).inflate(R.layout.exercise_rv,null,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MainActivityAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {


        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context,ExerciseDetails.class);
                intent.putExtra("name",modelArrayList.get(position).getTitle());
                context.startActivity(intent);
            }
        });

        holder.tvExerciseTitle.setText(modelArrayList.get(position).getTitle());
        holder.tvExerciseCategory.setText(modelArrayList.get(position).getBody());

    }

    @Override
    public int getItemCount() {
        return modelArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvExerciseTitle,tvExerciseCategory;
        CardView cardView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvExerciseTitle=itemView.findViewById(R.id.tvMainExerciseListTitle);
            tvExerciseCategory=itemView.findViewById(R.id.tvMainExerciseListCategory);
            cardView=itemView.findViewById(R.id.cvIndividualExercise);
        }
    }
}
