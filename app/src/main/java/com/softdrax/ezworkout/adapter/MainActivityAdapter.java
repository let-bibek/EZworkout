package com.softdrax.ezworkout.adapter;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import com.softdrax.ezworkout.ExerciseDetails;
import com.softdrax.ezworkout.R;
import com.softdrax.ezworkout.model.ExerciseModel;
import java.util.ArrayList;


public class MainActivityAdapter extends RecyclerView.Adapter<MainActivityAdapter.ViewHolder> {
    Context context;
    ArrayList<ExerciseModel> exerciseModels;
    private int lastPos = -1;


    public void setFilteredList(ArrayList<ExerciseModel> filteredList){
        this.exerciseModels=filteredList;
        notifyDataSetChanged();

    }

    public MainActivityAdapter(Context context, ArrayList<ExerciseModel> exerciseModels) {
        this.context = context;
        this.exerciseModels = exerciseModels;
    }

    @NonNull
    @Override
    public MainActivityAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view= LayoutInflater.from(context).inflate(R.layout.exercise_rv,parent,false);
       return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MainActivityAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, ExerciseDetails.class);
                intent.putExtra("name",exerciseModels.get(position).getName());
                intent.putExtra("type",exerciseModels.get(position).getType());
                intent.putExtra("difficulty",exerciseModels.get(position).getDifficulty());
                intent.putExtra("muscle",exerciseModels.get(position).getMuscle());
                intent.putExtra("equipment",exerciseModels.get(position).getEquipment());
                intent.putExtra("instructions",exerciseModels.get(position).getInstructions());
                intent.putExtra("gif",exerciseModels.get(position).getGifUrl());

                context.startActivity(intent);
            }
        });

        holder.tvExerciseTitle.setText(exerciseModels.get(position).getName());
        holder.tvExerciseCategory.setText(exerciseModels.get(position).getType());

        initAnim(holder.itemView,position);



    }

    @Override
    public int getItemCount() {
        return exerciseModels.size();
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

    private void initAnim(View view,int position){

        if(position>lastPos){
            Animation slider= AnimationUtils.loadAnimation(context, R.anim.recycler_slide_animation);
            view.startAnimation(slider);
            lastPos=position;

        }

    }
}
