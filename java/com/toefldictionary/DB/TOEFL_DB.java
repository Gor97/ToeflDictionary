package com.toefldictionary.DB;

import android.content.Context;
import java.util.ArrayList;

import com.toefldictionary.DB.executors.functionality.BlacklistFunctionality;
import com.toefldictionary.DB.executors.functionality.WordAntonymFunctionality;
import com.toefldictionary.DB.executors.functionality.DescriptionFunctionality;
import com.toefldictionary.DB.executors.functionality.WordSynonymFunctionality;
import com.toefldictionary.DB.executors.functionality.TypeFunctionality;
import com.toefldictionary.DB.executors.functionality.WordDescriptionFunctionality;
import com.toefldictionary.DB.executors.functionality.WordFunctionality;
import com.toefldictionary.DB.executors.functionality.WordTypeFunctionality;
import com.toefldictionary.DB.executors.objects.Description;
import com.toefldictionary.DB.executors.objects.Type;
import com.toefldictionary.DB.executors.queries.BlacklistQueries;
import com.toefldictionary.DB.executors.queries.WordAntonymQueries;
import com.toefldictionary.DB.executors.queries.DescriptionQueries;
import com.toefldictionary.DB.executors.queries.WordSynonymQueries;
import com.toefldictionary.DB.executors.queries.TypeQueries;
import com.toefldictionary.DB.executors.queries.WordDescriptionQueries;
import com.toefldictionary.DB.executors.queries.WordQueries;
import com.toefldictionary.DB.executors.objects.Word;
import com.toefldictionary.DB.executors.queries.WordTypeQueries;

/**
 * Created by Gor on 26-Apr-16.
 */
public class TOEFL_DB implements WordFunctionality, WordSynonymFunctionality, WordAntonymFunctionality, DescriptionFunctionality,
        TypeFunctionality, WordTypeFunctionality, WordDescriptionFunctionality, BlacklistFunctionality{
    private static WordQueries w_q;
    private static WordSynonymQueries s_q;
    private static WordAntonymQueries a_q;
    private static DescriptionQueries d_q;
    private static TypeQueries t_q;
    private static WordTypeQueries wt_q;
    private static WordDescriptionQueries wd_q;
    private static BlacklistQueries b_q;

    private static TOEFL_DB db;

    private TOEFL_DB(Context context) {
        w_q = new WordQueries(context);
        s_q = new WordSynonymQueries(context);
        a_q = new WordAntonymQueries(context);
        d_q = new DescriptionQueries(context);
        t_q = new TypeQueries(context);
        wt_q = new WordTypeQueries(context);
        wd_q = new WordDescriptionQueries(context);
        b_q = new BlacklistQueries(context);
    }

    public static TOEFL_DB getInstance(Context context) {
        if(db == null) {
            db = new TOEFL_DB(context);
        }
        return db;
    }


    @Override
    public Word addWord(Word word) {
        w_q.open();
        Word w = w_q.addWord(word);
        w_q.close();
        return w;
    }

    @Override
    public Word editWord(Word word) {
        w_q.open();
        Word w = w_q.editWord(word);
        w_q.close();
        return w;
    }

    @Override
    public void deleteWord(int id) {
        w_q.open();
        w_q.deleteWord(id);
        w_q.close();
    }

    @Override
    public ArrayList<Word> getAllWords() {
        w_q.open();
        ArrayList<Word> words = w_q.getAllWords();
        w_q.close();
        return words;
    }

    @Override
    public void addWordSynonym(int id1, int id2) {
        s_q.open();
        s_q.addWordSynonym(id1, id2);
        s_q.close();
    }

    @Override
    public void deleteWordSynonym(int id) {
        s_q.open();
        s_q.deleteWordSynonym(id);
        s_q.close();
    }
    @Override
    public ArrayList<Word> getAllSynonymsByWord(int id) {
        s_q.open();
        ArrayList<Word> a = s_q.getAllSynonymsByWord(id);
        s_q.close();
        return a;
    }

    @Override
    public void addWordAntonym(int id1, int id2) {
        a_q.open();
        a_q.addWordAntonym(id1,id2);
        a_q.close();
    }

    @Override
    public void deleteWordAntonym(int id) {
        a_q.open();
        a_q.deleteWordAntonym(id);
        a_q.close();
    }
    @Override
    public ArrayList<Word> getAllAntonymsByWord(int id)
    {
        a_q.open();
        ArrayList<Word> a = a_q.getAllAntonymsByWord(id);
        a_q.close();
        return a;
    }

    @Override
    public Description addDescription(Description description) {
        d_q.open();
        Description d = d_q.addDescription(description);
        d_q.close();
        return d;
    }

    @Override
    public Description editDescription(Description description) {
        d_q.open();
        Description d = d_q.editDescription(description);
        d_q.close();
        return d;
    }

    @Override
    public void deleteDescription(int id) {
        d_q.open();
        d_q.deleteDescription(id);
        d_q.close();
    }

    @Override
    public Type addType(Type type) {
        t_q.open();
        Type t = t_q.addType(type);
        t_q.close();
        return t;
    }

    @Override
    public ArrayList<Type> getAllTypes() {
        t_q.open();
        ArrayList<Type> a = t_q.getAllTypes();
        t_q.close();
        return a;
    }

    @Override
    public void addWordType(int id1, int id2) {
        wt_q.open();
        wt_q.addWordType(id1, id2);
        wt_q.close();
    }

    @Override
    public void deleteWordTypeByWord(int id) {
        wt_q.open();
        wt_q.deleteWordTypeByWord(id);
        wt_q.close();
    }

    @Override
    public void deleteWordTypeByType(int id) {
        wt_q.open();
        wt_q.deleteWordTypeByType(id);
        wt_q.close();
    }

    @Override
    public ArrayList<Word> getAllWordByType(int id) {
        wt_q.open();
        ArrayList<Word> a =  wt_q.getAllWordByType(id);
        wt_q.close();
        return a;
    }

    @Override
    public void addWordDes(int id1, int id2) {
        wd_q.open();
        wd_q.addWordDes(id1,id2);
        wd_q.close();
    }

    @Override
    public void deleteWordDesByWord(int id) {
        wd_q.open();
        wd_q.deleteWordDesByWord(id);
        wd_q.close();
    }

    @Override
    public void deleteWordDesByDes(int id) {
        wd_q.open();
        wd_q.deleteWordDesByDes(id);
        wd_q.close();
    }

    @Override
    public ArrayList<Description> getAllDesByWord(int id) {
        wd_q.open();
        ArrayList<Description> a = wd_q.getAllDesByWord(id);
        wd_q.close();
        return a;
    }

    @Override
    public void addBlacklistWord(int id) {
        b_q.open();
        b_q.addBlacklistWord(id);
        b_q.close();
    }

    @Override
    public void deleteBlacklistWord(int id) {
        b_q.open();
        b_q.deleteBlacklistWord(id);
        b_q.close();
    }

    @Override
    public ArrayList<Word> getAllBlacklistWords() {
        b_q.open();
        ArrayList<Word> b = b_q.getAllBlacklistWords();
        b_q.close();
        return b;
    }
}
