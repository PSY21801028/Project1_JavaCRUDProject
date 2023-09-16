package com.mycom.word;

import java.util.ArrayList;
import java.util.Scanner;

public class WordManager {
    Scanner s = new Scanner(System.in);
    WordCRUD wordCRUD;
    WordManager() {
        wordCRUD = new WordCRUD(s);
    }
    public int selectMenu() {
        System.out.printf("***영단어 마스터***\n"
                + "*****************\n"
                + "1. 모든 단어 보기\n"
                + "2. 수준별 단어 보기\n"
                + "3. 단어 검색\n"
                + "4. 단어 추가\n"
                + "5. 단어 수정\n"
                + "6. 단어 삭제\n"
                + "7. 파일 저장\n"
                + "0. 나가기\n"
                + "*****************\n"
                + "=> 원하는 메뉴는? ");
        return s.nextInt();
    }
    public void start() {

        wordCRUD.loadfile(); // 처음 프로그램이 시작될때, while 문으로 들어가면 계속 반복되니까 이 구간에서 불러옴.
        while(true) {
            int menu = selectMenu();
            if(menu == 0) {
                System.out.println("프로그램 종료! See you later :)");
                break;
            }
            else if(menu == 1) wordCRUD.listAll(); // view all
            if(menu == 2) wordCRUD.searchLevel(); // level category
            if(menu == 3) wordCRUD.searchWord(); // search
            if(menu == 4) wordCRUD.addWord(); // create
            if(menu == 5) {
             wordCRUD.updateItem();
            } // update
            if(menu == 6) {
                wordCRUD.deleteitem();// delete
            }
            if(menu == 7) {
                wordCRUD.saveFile();
            } // save

        }
    }
}
