package com.company.lnctteachersschedule;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TeacherAdapter extends RecyclerView.Adapter<TeacherAdapter.ViewHolder> {

    private List<Teacher> teachers;

    public TeacherAdapter(List<Teacher> teachers) {
        this.teachers = teachers;
    }

    public void updateData(List<Teacher> newTeachers) {
        teachers.clear();
        teachers.addAll(newTeachers);
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_teacher, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Teacher teacher = teachers.get(position);
        holder.nameTextView.setText(teacher.getName());
        holder.enrollmentTextView.setText(teacher.getTeacherCode());
        holder.contactTextView.setText(teacher.getContactNo());
    }

    @Override
    public int getItemCount() {
        return teachers.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView nameTextView;
        public TextView enrollmentTextView;
        public TextView contactTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.nameTextView);
            enrollmentTextView = itemView.findViewById(R.id.enrollmentTextView);
            contactTextView = itemView.findViewById(R.id.contactno);
        }
    }
}
