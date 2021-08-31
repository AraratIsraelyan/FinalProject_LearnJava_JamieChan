package com.fitnessClub;

import java.util.Scanner;
import java.util.LinkedList;
import java.util.InputMismatchException;

public class MembershipManagement {

    final private Scanner input = new Scanner(System.in);

    private int getIntInput(){
        int choise = 0;

        while (choise == 0){
            try {
                choise = input.nextInt();
                if (choise == 0 || choise < 0) {
                    throw new InputMismatchException();
                }
                input.nextLine();
            }
            catch (InputMismatchException e){
                System.out.println(e.getMessage());
            }
        }
        return choise;
    };

    private void printClubOptions(){
        System.out.println("1) Club Mercury");
        System.out.println("2) Club Neptune");
        System.out.println("3) Club Jupiter");
        System.out.println("4) Multi Clubs");
    }

    public int getChoise(){
        int choise;

        System.out.println("WELCOME TO OZONE FITNESS CENTER\n" +
                "================================\n" +
                "1) Add member\n2) Remove member\n3) Display member information\n" +
                "Please select an option (or enter -1 to quit):");

        choise = getIntInput();
        return choise;
    }

    public String addMembers(LinkedList<Member> m){
        String name;
        int club;
        String mem;
        double fees;
        int memberID;
        Member mbr;
        Calculator<Integer> cal;

        // Получение имени пользователя
        System.out.println("Введите имя пользователя: ");
        name = input.nextLine();

        //  Получение информации о доступности клубов
        printClubOptions();
        System.out.println("Выберите клуб, который хотите посещать: ");
        club = getIntInput();
        while (club < 1 | club > 4){
            System.out.println("Введнно некорректное значение. Попробуйте снова\n" +
                    "Выберите клуб, который хотите посещать: ");
            club = getIntInput();
        }

        //  Вычисление идентификатора посетителя
        if (m.size() > 0){
            memberID = m.getLast().getMemberID() + 1;
        }
        else
            memberID = 1;

        // Добавление посетителя в линкед лист
        if (club != 4){
            cal = (n) -> {
                switch (n){
                    case 1: return 900;
                    case 2: return 950;
                    case 3: return 1000;
                    default:
                        return -1;
                }
            };
            fees = cal.calculateFees(club);

            mbr = new SingleClubMember('S', memberID, name, fees, club);
            m.add(mbr);
            mem = mbr.toString();
            System.out.println("\nSTATUS: Single Club Member added\n");
        }
        else {
            cal = (n) -> {
                if (n == 4)
                    return 1200;
                else
                    return -1;
            };
            fees = cal.calculateFees(club);

            mbr = new MultiClubMember('M', memberID, name, fees, 100);
            m.add(mbr);
            mem = mbr.toString();
            System.out.println("\nSTATUS: Multi Club Member added\n");
        }

        return mem;
    }

    public void removeMember(LinkedList<Member> m){
        int memberID;
        System.out.println("Введите код пользователя, которого хотите удалить: ");
        memberID = getIntInput();

        for (int i = 0; i < m.size(); i++) {
            if (m.get(i).getMemberID() == memberID) {
                m.remove(i);
                System.out.println("Пользователь с идентификатором " + memberID + " удален.");
                return;
            }
        }
        System.out.println("Пользователь с идентификатором " + memberID + " не найден");
    }

    public void printMemberInfo(LinkedList<Member> m){
        int memberID;
        System.out.println("Введите код пользователя: ");
        memberID = getIntInput();

        for (int i = 0; i < m.size(); i++) {
            if (m.get(i).getMemberID() == memberID) {
                System.out.println("Пользователь с идентификатором " + memberID + " найден:");
                String[] memberInfo = m.get(i).toString().split(", ");

                System.out.println("Member Type = " + memberInfo[0]);
                System.out.println("Member ID = " + memberInfo[1]);
                System.out.println("Member Name = " + memberInfo[2]);
                System.out.println("Membership Fees = " + memberInfo[3]);
                if (memberInfo[0].charAt(0) == 'S'){
                    System.out.println("Club ID = " + memberInfo[4]);
                }
                else {
                    System.out.println("Membership Points = " + memberInfo[4]);
                }
                System.out.println("\n\n");
                return;
            }
        }
        System.out.println("Пользователь с идентификатором " + memberID + " не найден");
    }

}
