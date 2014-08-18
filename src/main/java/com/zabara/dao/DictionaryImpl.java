package com.zabara.dao;

import com.zabara.beans.Word;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: Yaroslav_Zabara
 * Date: 23.01.13
 * Time: 19:22
 * To change this template use File | Settings | File Templates.
 */
public class DictionaryImpl implements Dictionary {


    private Collection<Word> dictionary;
    //упрощение
    private long maxId = 0;

    private static Dictionary instance;


    enum WordComparator implements Comparator<Word> {
        ID_SORT {
            public int compare(Word o1, Word o2) {
                return (int) (o1.getId() - o2.getId());
            }
        },
        WORD_SORT {
            public int compare(Word o1, Word o2) {
                return o1.getWord().compareTo(o2.getWord());
            }
        },
        DEFINITION_SORT {
            public int compare(Word o1, Word o2) {
                return o1.getDefinition().compareTo(o2.getDefinition());
            }
        };


        public static Comparator<Word> decending(final Comparator<Word> other) {
            return new Comparator<Word>() {
                public int compare(Word o1, Word o2) {
                    return -1 * other.compare(o1, o2);
                }
            };
        }

        public static Comparator<Word> getComparator(final WordComparator... multipleOptions) {
            return new Comparator<Word>() {
                public int compare(Word o1, Word o2) {
                    for (WordComparator option : multipleOptions) {
                        int result = option.compare(o1, o2);
                        if (result != 0) {
                            return result;
                        }
                    }
                    return 0;
                }
            };
        }
    }

    public static synchronized Dictionary getInstance() {
        if (instance == null) {
            instance = new DictionaryImpl();
        }
        return instance;
    }

    private DictionaryImpl() {
        dictionary = new HashSet<Word>();
        InitDictionary();
    }

    private void InitDictionary() {
        addWord(new Word(0l, "человек", "животное разумное"));
        addWord(new Word(1l, "нуб", "глупый игроман"));
        addWord(new Word(2l, "сессия", "учебный процесс/сессия в браузере"));
        addWord(new Word(3l, "вода", "h2o"));
        addWord(new Word(4l, "мужик", "МУЖИИИИИК"));
        addWord(new Word(5l, "бла-бла", "qwe-qwe"));
        addWord(new Word(5l, "foo", "fooobaaarr"));
    }

    @Override
    public Word getWordById(long id) {
        for (Word word : dictionary) {
            if (word.getId() == id) {
                return word;
            }
        }
        return null;
    }

    @Override
    public String getDefinitionByWord(String wordValue) {
        for (Word word : dictionary) {
            if (word.getWord().equals(wordValue)) {
                return word.getDefinition();
            }
        }
        return null;
    }

    @Override
    public void addWord(Word word) {
        if (word != null) {
            if (word.getId() <= maxId) {
                word.setId(maxId);
            }
            dictionary.add(word);
            maxId++;
        }
    }

    @Override
    public void addWord(String word, String definition) {
        addWord(new Word(maxId++, word, definition));
    }

    @Override
    public void removeWord(String word) {
        dictionary.remove(word);
    }

    @Override
    public Word getWordObj(String wordValue) {
        for (Word word : dictionary) {
            if (word.getWord().equals(wordValue)) {
                return word;
            }
        }
        return null;
    }

    @Override
    public List<Word> getWords(String wordValue, String definition, List<String> orderBy) {
        List<Word> result = new ArrayList<Word>();
        for (Word word : dictionary) {
            if (wordValue != null && !wordValue.isEmpty() && word.getWord().contains(wordValue)) {
                result.add(word);
                continue;
            }
            if (definition != null && !definition.isEmpty() && word.getDefinition().contains(definition)) {
                result.add(word);
            }
        }

        if (orderBy != null && orderBy.size() > 0) {
            WordComparator orderByArray[] = new WordComparator[orderBy.size()];
            int i = 0;
            for (String order : orderBy) {
                if (order.equals("word")) {
                    orderByArray[i] = WordComparator.WORD_SORT;
                }
                if (order.equals("id")) {
                    orderByArray[i] = WordComparator.ID_SORT;
                }
                if (order.equals("definition")) {
                    orderByArray[i] = WordComparator.DEFINITION_SORT;
                }
                i++;
            }

            Collections.sort(result, WordComparator.decending(
                    WordComparator.getComparator(orderByArray)));
        }
        return result;
    }
}
