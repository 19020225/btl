package sample;

import java.io.*;
import java.util.Scanner;

public class DictionaryManagement extends Word {
    public void insertFromCommandline(Dictionary list) {
        System.out.printf("Nhap so luong tu: ");
        Scanner scan = new Scanner(System.in);
        int n;
        n = scan.nextInt();
        for(int i=0;i<n;i++){
            Word w=new Word();
            w.word_target=scan.nextLine();
            w.word_explain=scan.nextLine();
            list.wordList.add(w);
        }
    }

    //Thêm từ vào file
    public void addwordFromcommandlineToFile(String target,String explain,Dictionary list){

        String FILENAME = "D:\\Dictionary\\src\\sample\\dictionaries.txt";
        BufferedWriter bw = null;
        FileWriter fw = null;
        try {
            String newword='\n'+target+'\t'+explain;
            File file = new File(FILENAME);
            fw = new FileWriter(file.getAbsoluteFile(), true);
            bw = new BufferedWriter(fw);
            bw.write(newword);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bw != null)
                    bw.close();
                if (fw != null)
                    fw.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    //lấy từ trong file
    public void insertFromfile(Dictionary list) throws FileNotFoundException {
        File fis = new File("D:\\Dictionary\\src\\sample\\dictionaries.txt");

            Scanner scanner = new Scanner(fis);

            while (scanner.hasNextLine()) {
                String a=scanner.nextLine();
                if(a.length()>=1) {
                    Word w=new Word();
                    String[] arr = a.split("\t");
                    w.word_target=arr[0];
                    w.word_explain=arr[1];
                    list.wordList.add(w);
                }
            }
        scanner.close();
    }

    //Tra từ
    public String dictionaryLookup(String wordd,Dictionary list) {
        for (int i = 0; i < list.wordList.size(); i++) {
            if (wordd.equalsIgnoreCase(list.wordList.get(i).word_target)) {
                return list.wordList.get(i).word_explain;
            }
        }
        return "Từ Điển Chưa Cập Nhật";
    }
    public void saveToFIle(Dictionary list) throws FileNotFoundException,UnsupportedEncodingException {
        PrintWriter printWriter=new PrintWriter("D:\\Dictionary\\src\\sample\\dictionaries.txt");
        printWriter.print("");
        for(int i=0;i<list.wordList.size();i++){
            printWriter.append(list.wordList.get(i).word_target+"\t"+list.wordList.get(i).word_explain+"\n");
        }
        printWriter.close();
    }
}

