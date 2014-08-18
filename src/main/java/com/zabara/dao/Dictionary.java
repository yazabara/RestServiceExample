package com.zabara.dao;

import com.zabara.beans.Word;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Yaroslav_Zabara
 * Date: 23.01.13
 * Time: 19:18
 * To change this template use File | Settings | File Templates.
 */
public interface Dictionary {

    Word getWordById(long id);

    String getDefinitionByWord(String word);

    void addWord(Word word);

    void addWord(String word, String definition);

    void removeWord(String word);

    Word getWordObj(String word);

    List<Word> getWords(String word, String definition, List<String> orderBy);
}
