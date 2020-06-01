package com.shubhcode.nmcourse;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.NonNull;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.ms.square.android.expandabletextview.ExpandableTextView;

import java.util.List;

import es.dmoral.toasty.Toasty;

import static androidx.constraintlayout.widget.Constraints.TAG;


public class RecyclerViewCourseDetails extends RecyclerView.Adapter<RecyclerViewCourseDetails.RecyclerViewCourseDetailsHolder> {
    Context mContext;
    List<ClassChapterDetails> mClassChapterDetails;
    onItemCLickListenerRecycle mListener;
    boolean isShimmer=true;
    int shimmerNumber=5;

    public interface onItemCLickListenerRecycle {
        void onItemClicked(int position);
    }
    public void setOnItemClickListener(onItemCLickListenerRecycle listener) {
        mListener = listener;
    }

    public RecyclerViewCourseDetails(Context mContext, List<ClassChapterDetails> mClassChapterDetails) {
        this.mContext = mContext;
        this.mClassChapterDetails = mClassChapterDetails;

    }











    public class RecyclerViewCourseDetailsHolder extends RecyclerView.ViewHolder {
        public TextView mChapterTitle;
        public Button  joinButton;
        public ExpandableTextView mChapterDescription;
        public ImageView mChapterImage;
public ShimmerFrameLayout shimmerFrameLayout;

        public RecyclerViewCourseDetailsHolder(@NonNull View itemView) {
            super(itemView);
shimmerFrameLayout=itemView.findViewById(R.id.shimmerframelayout);
            mChapterTitle = itemView.findViewById(R.id.courseTitleChapter);
            mChapterDescription = itemView.findViewById(R.id.expand_text_view);
            mChapterImage = itemView.findViewById(R.id.chapterImage);
            joinButton = itemView.findViewById(R.id.joinButton);


            joinButton.setText("Join");

            joinButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mListener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            mListener.onItemClicked(position);
                            joinButton.setText("joined");

                                




                        }
                    }
                }
            });


        }


    }

    @NonNull
    @Override
    public RecyclerViewCourseDetailsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.coursecard, parent, false);
        return new RecyclerViewCourseDetailsHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerViewCourseDetailsHolder holder, int position) {


            final ClassChapterDetails newClassChapterDetails = mClassChapterDetails.get(position);
            holder.mChapterTitle.setText(newClassChapterDetails.getmTitleOfChapter());
            holder.mChapterDescription.setText(newClassChapterDetails.getmFullDescriptionOfChapter());
            holder.joinButton.setText(newClassChapterDetails.getmJoin());
            Glide.with(mContext).load(newClassChapterDetails
                    .getmThumbnailofChapter()).placeholder(R.drawable.logo).fitCenter().centerCrop()
                    .into(holder.mChapterImage);







//

    }

    @Override
    public int getItemCount() {
        return isShimmer?shimmerNumber:mClassChapterDetails.size();
    }




}
