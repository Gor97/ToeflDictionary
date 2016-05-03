package com.toefldictionary.DB;

import android.content.Context;
import java.util.ArrayList;
import com.toefldictionary.DB.executors.functionality.AntonymFunctionality;
import com.toefldictionary.DB.executors.functionality.DescriptionFunctionality;
import com.toefldictionary.DB.executors.functionality.SynonymFunctionality;
import com.toefldictionary.DB.executors.functionality.TypeFunctionality;
import com.toefldictionary.DB.executors.functionality.WordDescriptionFunctionality;
import com.toefldictionary.DB.executors.functionality.WordFunctionality;
import com.toefldictionary.DB.executors.functionality.WordTypeFunctionality;
import com.toefldictionary.DB.executors.objects.Description;
import com.toefldictionary.DB.executors.objects.Type;
import com.toefldictionary.DB.executors.queries.AntonymQueries;
import com.toefldictionary.DB.executors.queries.DescriptionQueries;
import com.toefldictionary.DB.executors.queries.SynonymQueries;
import com.toefldictionary.DB.executors.queries.TypeQueries;
import com.toefldictionary.DB.executors.queries.WordDescriptionQueries;
import com.toefldictionary.DB.executors.queries.WordQueries;
import com.toefldictionary.DB.executors.objects.Word;
import com.toefldictionary.DB.executors.queries.WordTypeQueries;

import java.util.ArrayList;

/**
 * Created by Gor on 26-Apr-16.
 */
public class TOEFL_DB implements WordFunctionality, SynonymFunctionality, AntonymFunctionality, DescriptionFunctionality,
        TypeFunctionality, WordTypeFunctionality, WordDescriptionFunctionality{
    private static WordQueries w_q;
    private static SynonymQueries s_q;
    private static AntonymQueries a_q;
    private static DescriptionQueries d_q;
    private static TypeQueries t_q;
    private static WordTypeQueries wt_q;
    private static WordDescriptionQueries wd_q;

    private static TOEFL_DB db;

    private TOEFL_DB(Context context) {
        w_q = new WordQueries(context);
        s_q = new SynonymQueries(context);
        a_q = new AntonymQueries(context);
        d_q = new DescriptionQueries(context);
        t_q = new TypeQueries(context);
        wt_q = new WordTypeQueries(context);
        wd_q = new WordDescriptionQueries(context);
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
    public void addSynonym(int id1, int id2) {
        s_q.open();
        s_q.addSynonym(id1, id2);
        s_q.close();
    }

    @Override
    public void deleteSynonym(int id) {
        s_q.open();
        s_q.deleteSynonym(id);
        s_q.close();
    }

    //************************

    // FOR TESTING

    public ArrayList<Integer> getSynonyms() {
        s_q.open();
        ArrayList a = s_q.getSynonyms();
        s_q.close();
        return a;
    }
    //********************
    @Override
    public void addAntonym(int id1, int id2) {
        a_q.open();
        a_q.addAntonym(id1,id2);
        a_q.close();
    }

    @Override
    public void deleteAntonym(int id) {
        a_q.open();
        a_q.deleteAntonym(id);
        a_q.close();
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
    public Type editType(Type type) {
        t_q.open();
        Type t = t_q.editType(type);
        t_q.close();
        return t;
    }

    @Override
    public void deleteType(int id) {
        t_q.open();
        t_q.deleteType(id);
        t_q.close();
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
}
