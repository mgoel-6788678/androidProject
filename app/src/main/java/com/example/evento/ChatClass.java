package com.example.evento;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class ChatClass implements Serializable {

    public static class Chat implements Serializable{
        public String UserName;
        public String Content;

        Chat() {
        }

        public void setContents(String name, String cont) {
            UserName = name;
            Content = cont;
        }

        public String toString() {
            return UserName + " " + Content + "\n";
        }
    }

    public ArrayList<Chat> ChatBox;

    public ChatClass() {
    }

    public void appendChat(String name, String cont) {
        if (ChatBox == null) {
            ChatBox = new ArrayList<>();
        }
        Chat tmp = new Chat();
        tmp.setContents(name, cont);
        ChatBox.add(tmp);
    }

    public String toString() {
        StringBuilder t = new StringBuilder();
        for (int i= 0; i<this.ChatBox.size(); ++i) {
            t.append(ChatBox.get(i).toString());
        }
        return t.toString();
    }
}
