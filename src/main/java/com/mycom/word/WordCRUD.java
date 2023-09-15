package com.mycom.word;

import java.util.ArrayList;
import java.util.Scanner;

public class WordCRUD implements ICRUD {

    static ArrayList<Word> list;
    Scanner s;
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
    public void updateItem() {
        System.out.print("=> 수정할 단어 검색 : ");
        String keyword = s.next().trim();
        ArrayList<Integer> idlist = this.listAll(keyword);
        System.out.print("=> 수정할 번호 검색 : ");
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
