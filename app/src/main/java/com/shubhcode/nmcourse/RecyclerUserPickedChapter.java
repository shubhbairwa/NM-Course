package com.shubhcode.nmcourse;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import static androidx.constraintlayout.widget.Constraints.TAG;


public class RecyclerUserPickedChapter extends RecyclerView.Adapter<RecyclerUserPickedChapter.ViewHolderUserPicked> {
    public Context mContext;
    public ArrayList<ClassUserPickedChapter> mClassUserPickedChapterList;
    onItemClickUserPickedRrecycle mPickedListener;

    public interface onItemClickUserPickedRrecycle {
        void onPickedClicked(int position);
    }

    public void setOnItemClickListener(onItemClickUserPickedRrecycle listener) {
        mPickedListener = listener;
    }


    public RecyclerUserPickedChapter(Context mContext, ArrayList<ClassUserPickedChapter> mClassUserPickedChapterList) {
        this.mContext = mContext;
        this.mClassUserPickedChapterList = mClassUserPickedChapterList;
    }

    @NonNull
    @Override
    public ViewHolderUserPicked onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.recycler_home_fragment, parent, false);
        return new ViewHolderUserPicked(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderUserPicked holder, int position) {
        final ClassUserPickedChapter classUserPickedChapter = mClassUserPickedChapterList.get(position);
        holder.textOFtitle.setText(classUserPickedChapter.getTitleUser());
        Glide.with(mContext).load(classUserPickedChapter
                .getPicUser()).placeholder(R.drawable.logo).fitCenter().centerCrop()
                .into(holder.imageofchapter);

    }

    @Override
    public int getItemCount() {
        return mClassUserPickedChapterList.size();
    }


    public class ViewHolderUserPicked extends RecyclerView.ViewHolder {
        public TextView textOFtitle;
        public ImageView imageofchapter;

        public ViewHolderUserPicked(@NonNull View itemView) {
            super(itemView);
            textOFtitle = itemView.findViewById(R.id.pickedtitle);
            imageofchapter = itemView.findViewById(R.id.pickedImage);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d(TAG, "onClick: homeClicked");
                    if (mPickedListener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            mPickedListener.onPickedClicked(position);
                        }
                    }
                }
            });


        }
    }


}
