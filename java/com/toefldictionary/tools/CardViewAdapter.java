package com.toefldictionary.tools;

/**
 * Created by Gor on 04-May-16.
 */

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import com.toefldictionary.DB.executors.objects.Word;
import com.toefldictionary.R;
import com.toefldictionary.activities.WordPageActivity;

import java.util.ArrayList;

/**
 * Created by Gor on 04-May-16.
 */
public class CardViewAdapter extends RecyclerView.Adapter<CardViewAdapter.ViewHolder> {

    private static ArrayList<Word> words;
    private Context c;
    public CardViewAdapter(ArrayList<Word> words, Context context) {
        this.c = context;
        this.words = words;
    }


    @Override
    public CardViewAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemLayoutView = LayoutInflater.from(viewGroup.getContext()).inflate(
                R.layout.card_view, null);
        ViewHolder viewHolder = new ViewHolder(itemLayoutView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CardViewAdapter.ViewHolder viewHolder, int i) {
        final TTSManager ttsManager = new TTSManager();
        ttsManager.init(c);
        final Word w = words.get(i);

        viewHolder.nameText.setText(w.getName());
        viewHolder.transText.setText(w.getTranslation());
        viewHolder.speech.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ttsManager.initQueue(w.getName());
            }
        });
        viewHolder.w = w;
    }

    @Override
    public int getItemCount() {
        return words.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView nameText;
        public TextView transText;
        public ImageButton speech;
        public Word w;
        public ViewHolder(View itemLayoutView) {
            super(itemLayoutView);
            nameText = (TextView) itemLayoutView
                    .findViewById(R.id.nameText);
            transText = (TextView) itemLayoutView
                    .findViewById(R.id.transText);
            speech = (ImageButton)itemLayoutView.findViewById(R.id.speech);
            itemLayoutView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), WordPageActivity.class);
                    intent.putExtra("word", w);
                    v.getContext().startActivity(intent);
                }
            });
        }
    }
}
