package com.mycom.word;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        System.out.println("Hello World!!!");
        System.out.print("이름 입력 : ");
        String name = s.next();
        System.out.println("[" + name + "]님 환영합니다~!");
    }
}
