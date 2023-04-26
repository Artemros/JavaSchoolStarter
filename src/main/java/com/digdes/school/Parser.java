package com.digdes.school;

import java.util.ArrayList;
import java.util.List;

public class Parser {
    public int getIndex(final String part, final String word) {
        String sub_str = part.toLowerCase();
        int ret = sub_str.indexOf(word);
        int word_position = sub_str.indexOf(word);
        int iterator = 0;
        int coma_count = 0;
        while (sub_str.contains(word.toLowerCase())) {


            if (sub_str.charAt(iterator) == '\'' || sub_str.charAt(iterator) == '‘' || sub_str.charAt(iterator) == '’') {
                coma_count++;
            }
            if (word_position < iterator) {
                if (coma_count % 2 == 0) {
                    return ret;
                } else {
                    sub_str = sub_str.substring(word_position + word.length());
                    word_position = sub_str.indexOf(word);
                    ret += word_position + word.length();
                    iterator = -1;
                }
            }
            iterator++;
        }

        return -1;
    }

    public String getStringAfterIndex(final String part, final int index) {
        return part.substring(index);
    }

    public String getStringBeforeIndex(final String part, final int index) {
        return part.substring(0, index);
    }

    public String getStringBetweenIndex(final String part, final int index1, final int index2) {
        return part.substring(index1, index2);
    }

    public String[] customSplit(String to_split, String regex) {
        List<String> list = new ArrayList<>();

        String last_part = to_split;
        int index = getIndex(last_part, regex);
        while (index > -1) {
            list.add(getStringBeforeIndex(last_part, index));
            last_part = getStringAfterIndex(last_part, index + regex.length());
            index = getIndex(last_part, regex);
        }
        list.add(last_part);

        return list.toArray(new String[0]);
    }
}
