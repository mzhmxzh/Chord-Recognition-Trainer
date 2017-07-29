package com.mzhmxzh.chordRecognitionTrainer;

import java.util.Random;

public class Helper{
    public Chord generateRandomChord(){
        String root = generateRandomNote();
        Random random = new Random();
        Chord.Name[] names = Chord.Name.values();
        Chord.Name name = names[random.nextInt(names.length)];
        return new Chord(root,name);
    }

    private static String generateRandomNote(){
        String note = null;
        int random = (int)(Math.random() * 12);
        switch(random){
            case 0 : {note="C";break;}
            case 1 : {note="Cs";break;}
            case 2 : {note="D";break;}
            case 3 : {note="Ds";break;}
            case 4 : {note="E";break;}
            case 5 : {note="F";break;}
            case 6 : {note="Fs";break;}
            case 7 : {note="G";break;}
            case 8 : {note="Gs";break;}
            case 9 : {note="A";break;}
            case 10 : {note="As";break;}
            case 11 : {note="B";break;}
        }
        return note;
    }

}