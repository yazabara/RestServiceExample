package com.zabara.beans;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created with IntelliJ IDEA.
 * Word: Yaroslav_Zabara
 * Date: 23.01.13
 * Time: 19:16
 * To change this template use File | Settings | File Templates.
 */
@XmlRootElement
public class Word {

    Long id;
    String word;
    String definition;

    public Word(Long id, String word, String definition) {
        this.id = id;
        this.word = word;
        this.definition = definition;
    }

    public Word() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getDefinition() {
        return definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }
}
