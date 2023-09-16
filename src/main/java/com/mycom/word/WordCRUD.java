package com.mycom.word;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class WordCRUD implements ICRUD {

    static ArrayList<Word> list;
    Scanner s;
    final String fname = "Dictionary.txt";

    WordCRUD(Scanner s) {
        list = new ArrayList<>();
        this.s = s;
    }
    @Override
    public Object add() {
        System.out.print("--> 난이도(1,2,3) & 새 단어 입력 : ");
        int level = s.nextInt(); // 난이도 Int 입력
        String word = s.next(); // 단어 String 입력
        s.nextLine(); // NULL 받아주는 용

        System.out.print("뜻 입력 : ");
        String meaning = s.nextLine();

        return new Word(0, level, word, meaning);
    }

    public void addWord() {
        Word one = (Word)add();
        list.add(one);
        System.out.println("새 단어를 단어장 추가 완료. ");
    }

    public void listAll() {
        System.out.println("--------------------------------");
        for(int i=0; i<list.size(); i++) {
            System.out.print((i+1) + " ");
            System.out.println(list.get(i).toString());
        }
        System.out.println("--------------------------------");
    }
    public ArrayList<Integer> listAll(String keyword) {

        ArrayList<Integer> idList = new ArrayList<>();
        int j = 0;
        System.out.println("--------------------------------");
        for(int i=0; i<list.size(); i++) {
            String word = list.get(i).getWord();
            if(!word.contains(keyword)) continue;
            System.out.print((j+1) + " ");
            System.out.println(list.get(i).toString());
            idList.add(i);
            j++;
        }
        System.out.println("--------------------------------");
        return idList;
    }

    public void listAll(int level) {
        int j = 0;
        System.out.println("--------------------------------");
        for(int i=0; i<list.size(); i++) {
            int ilevel = list.get(i).getLevel();
            if(ilevel != level) continue;
            System.out.print((j+1) + " ");
            System.out.println(list.get(i).toString());
            j++;
        }
        System.out.println("--------------------------------");
    }

    public void updateItem() {
        System.out.print("=> 수정할 단어 검색 : ");
        String keyword = s.next().trim();
        ArrayList<Integer> idlist = this.listAll(keyword);
        System.out.print("=> 수정할 번호 선택 : ");
        int id = s.nextInt();
        s.nextLine();
        System.out.print("=> 뜻 입력 : ");
        String meaning = s.nextLine();
        Word word = list.get(idlist.get(id-1));
        word.setMeaning(meaning);
        System.out.println("단어 수정 완료!");
    }

    public void deleteitem() {
        System.out.print("=> 삭제할 단어 검색 : ");
        String keyword = s.next();
        ArrayList<Integer> idlist = this.listAll(keyword);
        System.out.print("=> 삭제할 번호 선택 : ");
        int id = s.nextInt();
        s.nextLine();

        System.out.println("정말 삭제하시겠습니까? (Y/N) ");
        String ans = s.next().trim();
        if(ans.equalsIgnoreCase("y")) {
            list.remove((int)idlist.get(id-1));
            System.out.println("단어 삭제 완료!");
        } else
            System.out.println("취소!");
    }

    public void loadfile() {
        try {
            BufferedReader br = new BufferedReader(new FileReader(fname));
            String line;
            int count = 0; // word 의 갯수를 새기 위해.

            while(true) { // file의 많은 line을 계속 읽어오기 위한 반복문
                line = br.readLine(); // line by line으로 data를 받아옴
                if(line == null) break; // file의 끝을 만나게 하기 위해
                String data[] = line.split("\\|"); //  "|"(vertical bar)를 문자로 인식시켜주기 위한 작업으로 (// 추가), "|" 기준으로 data를 split함.
                int level = Integer.parseInt(data[0]); // String으로 처리 되어있는 level 숫자를 integer로 전환시켜줌
                String word = data[1];
                String meaning = data[2];
                list.add(new Word(0, level, word, meaning)); // word의 생성자를 그대로 불러옴(data가 있을때의 생성자로 매개변수 넘겨주기)
                count++;
            }
            br.close();
            System.out.println("==> " + count + "개 로딩 완료!! ");

        } catch (FileNotFoundException e) {
            e.printStackTrace();;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveFile() {
        try {
            PrintWriter pr = new PrintWriter(new FileWriter(fname)); // printwriter(Java에서 텍스트를 파일이나 다른 출력 대상에 쓰는 데 사용되는 클래스) 객체화 작업, Filewriter 생성자를 통해 "test.txt"라는 파일을 만드는 결정
            for(Word one : list) { // list에서 data를 들고 오기 위함.
                pr.write(one.toFileString() + "\n"); // file에 작성할 양식, 단어 하나 당 한 라인을 넣어주기 위한 "\n"
            }
            pr.close(); // file을 열었으면 꺼주는 것도 해줘야함!
            System.out.println("===> 데이터 저장 완료!!");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

//    public void searchLevel() {
//        System.out.print("=> 원하는 레벨은? (1~3) ");
//        int level = s.nextInt();
//        listAll(level);
//    }
//
//    public void searchWord() {
//        System.out.print("=> 원하는 단어는? ");
//        String keyword = s.next().trim();
//        listAll(keyword);
//    }

    @Override
    public int update(Object obj) {
        return 0;
    }

    @Override
    public int delete(Object obj) {
        return 0;
    }

    @Override
    public int selectOne(int id) {
        return 0;
    }
}
