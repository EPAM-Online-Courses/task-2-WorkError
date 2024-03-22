package efs.task.syntax;

import java.util.Random;
import java.util.Arrays;
import java.util.Scanner;
public class GuessNumberGame {

    private final int M;
    private final double L;
    private final int correctNumber;

    //Do not modify main method
    public static void main(String[] args) {
        try {
            GuessNumberGame game = new GuessNumberGame(args.length > 0 ? args[0] : "");
            game.play();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public GuessNumberGame(String argument) {

        try {
            this.M = Integer.parseInt(argument);
        } catch (NumberFormatException e){
            System.out.println("'" + argument + "'" + " to " + UsefulConstants.WRONG_ARGUMENT + " - to nie liczba");
            throw new IllegalArgumentException();
        }

        if( M < 1 || M > 400){
            System.out.println("'" + argument + "'" + " to " + UsefulConstants.WRONG_ARGUMENT + " - jest spoza zakresu <1," + UsefulConstants.MAX_UPPER_BOUND + ">");
            throw new IllegalArgumentException();
        }

        Random rand = new Random();
        this.correctNumber = rand.nextInt(this.M) + 1;
        this.L = Math.floor(Math.log(this.M)/Math.log(2))+1;


    }

    public void play() {
        int tryCounter = 0;
        String userTry;
        int intUserTry;
        String[] progressArr = new String[(int)this.L];
        Arrays.fill(progressArr, ".");

        Scanner scanner = new Scanner(System.in);
        System.out.println("Zagrajmy. Zgadnij liczbe z zakresu <1," + this.M +">");

        while(true){
            if(tryCounter >= this.L){
                System.out.println("NIESTETY, wyczerpałeś limit prób (" + (int)this.L + ") do odgadnięcia liczby " + this.correctNumber);
                break;
            }

            progressArr[tryCounter] = "*";
            System.out.print("Twoje próby: [");
            for (String item : progressArr){
                System.out.print(item);
            }
            System.out.println("]");

            System.out.println(UsefulConstants.GIVE_ME + " liczbę: ");
            userTry = scanner.next();

            try{
                intUserTry = Integer.parseInt(userTry);
            } catch (NumberFormatException e){
                System.out.println("Hmm, " + "'" + userTry + "' to " + UsefulConstants.NOT_A_NUMBER);
                tryCounter++;
                continue;
            }

            if(intUserTry == this.correctNumber){
                System.out.println(UsefulConstants.YES + "!");
                System.out.println(UsefulConstants.CONGRATULATIONS + ", - " + tryCounter + " tyle prób zajęło odgadnięcie liczby " + this.correctNumber);
                break;
            } else if (intUserTry > this.correctNumber) {
                System.out.println("To " + UsefulConstants.TO_MUCH);
            } else {
                System.out.println("To " + UsefulConstants.TO_LESS);
            }
            tryCounter++;
        }
    }
}
