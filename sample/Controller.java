package sample;

import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML
    TextField searchword;
    @FXML
    TextArea wordexplain;
    @FXML
    ListView listview;
    @FXML
    TextField addEnglish;
    @FXML
    TextField addVietNamese;
    @FXML
    TextField lateTarget,lateExplain,newTarget,newExplain;
    @FXML
    Button deleteEnglish;

    Dictionary list = new Dictionary();
    DictionaryManagement Dict=new DictionaryManagement();

    public void initialize(URL location, ResourceBundle resourceBundle){
        try {
            Dict.insertFromfile(list);
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }
    }

    public void searchWord(ActionEvent event) throws IOException{
        String input = searchword.getText();
        if(!input.equals("")) {
            wordexplain.setText(Dict.dictionaryLookup(input, list));
        }
        else{
            Parent addViewParent = FXMLLoader.load(getClass().getResource("sample.fxml"));
            Scene addViewScene = new Scene(addViewParent);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(addViewScene);
            window.show();
        }
    }

    public void AutoCompleteTextfield() throws IOException{
        listview.getItems().clear();
        String word = searchword.getText();
            for (int i = 0; i < list.wordList.size(); i++) {
                if (list.wordList.get(i).word_target.toUpperCase().startsWith(word.toUpperCase())) {
                    listview.getItems().add(list.wordList.get(i).word_target);
                }
            }
    }

    public void textFieldSearch() throws IOException{
        String input = searchword.getText();
        wordexplain.setText(Dict.dictionaryLookup(input,list));
    }

    public void onMouseClickListview(){
        String english = (String) listview.getSelectionModel().getSelectedItem();
        searchword.setText(english);
    }

    public void addWord(ActionEvent event) throws IOException {
        Parent addViewParent = FXMLLoader.load(getClass().getResource("add.fxml"));
        Scene addViewScene = new Scene(addViewParent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(addViewScene);
        window.show();
    }

    public void them(ActionEvent event) throws IOException {
        String english = addEnglish.getText();
        String vietnamese = addVietNamese.getText();
            Dict.addwordFromcommandlineToFile(english, vietnamese, list);
            Parent addViewParent = FXMLLoader.load(getClass().getResource("sample.fxml"));
            Scene addViewScene = new Scene(addViewParent);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(addViewScene);
            window.show();
    }

    public void Delete(ActionEvent event) throws IOException {
        String word = searchword.getText();
        if(!word.equals("")) {
            for (int i = 0; i < list.wordList.size(); i++) {
                if (word.equalsIgnoreCase(list.wordList.get(i).word_target)) {
                    listview.getItems().remove(word);
                    list.wordList.remove(i);
                }
            }
            Dict.saveToFIle(list);
        }
    }
    
    public void Repair(ActionEvent event) throws IOException {
        Parent addViewParent = FXMLLoader.load(getClass().getResource("repair.fxml"));
        Scene addViewScene = new Scene(addViewParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(addViewScene);
        window.show();
    }

    public void sua(ActionEvent event) throws IOException {
        String latetarget = lateTarget.getText();
        String lateexplain = lateExplain.getText();
        String newtarget = newTarget.getText();
        String newexplain = newExplain.getText();
        for(int i=0;i<list.wordList.size();i++){
            if(list.wordList.get(i).word_target.equalsIgnoreCase(latetarget)){
                list.wordList.get(i).word_target = newtarget;
            }
        }
        for(int i=0;i<list.wordList.size();i++){
            if(list.wordList.get(i).word_explain.equalsIgnoreCase(lateexplain)){
                list.wordList.get(i).word_explain = newexplain;
            }
        }
        Dict.saveToFIle(list);
        Parent addViewParent = FXMLLoader.load(getClass().getResource("sample.fxml"));
        Scene addViewScene = new Scene(addViewParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(addViewScene);
        window.show();
    }
}

