package com.fitnessClub;

import java.io.File;
import java.util.LinkedList;

public class JavaProject {

    public static void main(String[] args) {
	    String mem;

        MembershipManagement mm = new MembershipManagement();
        FileHandler fh = new FileHandler();

        LinkedList<Member> members = fh.readFile();
        int choice = mm.getChoise();

        while (choice != -1) {
            switch (choice){
                case 1: {
                    mm.addMembers(members);
                    break;
                }
                case 2: {
                    mm.removeMember(members);
                    break;
                }
                case 3: {
                    mm.printMemberInfo(members);
                    break;
                }
                default:
                    System.out.println("Выбран недопустимый вариант. Попробуйте снова: ");
            }
            choice = mm.getChoise();
        }
    }
}
