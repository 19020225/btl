package sample;

import java.io.IOException;

public  class DictionaryCommandLine{
    //DictionaryManagement D_management=new DictionaryManagement();

    public void showAllWords(Dictionary list){
        System.out.println("No  |English           |Vietnamese");
        for(int i=0;i<list.wordList.size();i++){
            System.out.printf("%d\t|%-18s|%s\n",i+1,list.wordList.get(i).word_target,
                    list.wordList.get(i).word_explain);
        }
    }
    public void dictionaryBasic(DictionaryManagement D_management,Dictionary list){
        //DictionaryCommandLine x=new DictionaryCommandLine();
        D_management.insertFromCommandline(list);
        showAllWords(list);
    }
    public void dictionaryAdvanced(Dictionary list,DictionaryManagement x) throws IOException {
        //DictionaryCommandLine advanced=new DictionaryCommandLine();
        x.insertFromfile(list);
        showAllWords(list);
    }

}