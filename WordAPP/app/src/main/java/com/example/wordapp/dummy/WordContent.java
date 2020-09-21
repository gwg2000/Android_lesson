package com.example.wordapp.dummy;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class WordContent {

    public static final List<WordItem> ITEMS = new ArrayList<WordItem>();

    public static final Map<String, WordItem> ITEM_MAP = new HashMap<String, WordItem>();

    static {
        addItem("0","Apple","苹果","I want an apple.");
        addItem("1","Orange","橙子","Would you care for some orange juice?");
        addItem("2","Banana","香蕉","He slipped on a banana skin.");
        addItem("3","Lemon","柠檬","A lemon is an acid fruit.");
        addItem("4","Pear","梨子","There are lots of pear trees near the house.");
    }

    private static void addItem(WordItem item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }
    private static void addItem(String id, String word,String content, String sample) {
        WordItem wordItem=new WordItem(id,word,content,sample);
        ITEMS.add(wordItem);
        ITEM_MAP.put(wordItem.id, wordItem);
    }

    public static class WordItem implements Serializable {
        public final String id;
        public final String word;
        public final String meaning;
        public final String sample;

        public WordItem(String id, String word,String meaning, String sample) {
            this.id = id;
            this.word=word;
            this.meaning = meaning;
            this.sample = sample;
        }
        @Override
        public String toString() {
            return word+":"+meaning+","+sample;
        }

    }
}