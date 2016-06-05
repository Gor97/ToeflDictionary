package com.toefldictionary.tools;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.toefldictionary.DB.TOEFL_DB;
import com.toefldictionary.DB.executors.objects.Word;
import com.toefldictionary.R;

import java.util.ArrayList;

/**
 * Created by Gor on 21-May-16.
 */
public class CardViewAdapter2 extends RecyclerView.Adapter<CardViewAdapter2.ViewHolder> {

    private static ArrayList<Word> words;
    private TTSManager ttsManager;
    private Context c;
    public CardViewAdapter2(ArrayList<Word> words, Context context) {
        this.c = context;
        this.words = words;
        ttsManager = new TTSManager();
        ttsManager.init(context);
    }


    @Override
    public CardViewAdapter2.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        /*View itemLayoutView = LayoutInflater.from(viewGroup.getContext()).inflate(
                R.layout.card_view, null);*/
        View itemLayoutView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_view, null, true);
        WindowManager windowManager = (WindowManager)c.getSystemService(Context.WINDOW_SERVICE);
        int width = windowManager.getDefaultDisplay().getWidth();
        itemLayoutView.setLayoutParams(new RecyclerView.LayoutParams(width, RecyclerView.LayoutParams.MATCH_PARENT));
        ViewHolder viewHolder = new ViewHolder(itemLayoutView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CardViewAdapter2.ViewHolder viewHolder, int i) {
        final Word w = words.get(i);

        viewHolder.nameText.setText(w.getName());
        viewHolder.transText.setText(w.getTranslation());
        ArrayAdapter<Word> s = new ArrayAdapter<>(c, R.layout.list_item, TOEFL_DB.getInstance(c).getAllSynonymsByWord(w.getId()));
        viewHolder.synonyms.setAdapter(s);
        ArrayAdapter<Word> a = new ArrayAdapter<>(c, R.layout.list_item, TOEFL_DB.getInstance(c).getAllAntonymsByWord(w.getId()));
        viewHolder.antonyms.setAdapter(a);

        viewHolder.speech.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ttsManager.initQueue(w.getName());
            }
        });
        viewHolder.wiki.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://en.wiktionary.org/wiki/" + w.getName()));
                c.startActivity(browserIntent);
            }
        });
        viewHolder.oxford.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.oxfordlearnersdictionaries.com/definition/english/" + w.getName() + "?q=" + w.getName()));
                c.startActivity(browserIntent);
            }
        });
        viewHolder.cambridge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://dictionary.cambridge.org/dictionary/english/" + w.getName()));
                c.startActivity(browserIntent);
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
        public Word w;
        public ListView synonyms, antonyms;
        public ImageButton speech;
        public TextView wiki, oxford, cambridge;
        public ViewHolder(View itemLayoutView) {
            super(itemLayoutView);
            nameText = (TextView) itemLayoutView
                    .findViewById(R.id.nameText);
            transText = (TextView) itemLayoutView
                    .findViewById(R.id.transText);
            synonyms = (ListView)itemLayoutView.
                    findViewById(R.id.synonymList);
            antonyms = (ListView)itemLayoutView.
                    findViewById(R.id.antonymList);
            wiki = (TextView) itemLayoutView.findViewById(R.id.wikiButton);
            oxford = (TextView) itemLayoutView.findViewById(R.id.oxfordButton);
            cambridge = (TextView) itemLayoutView.findViewById(R.id.cambridgeButton);

            speech = (ImageButton)itemLayoutView.findViewById(R.id.speech);
        }
    }
}
