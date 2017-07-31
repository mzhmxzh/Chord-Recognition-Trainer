package com.mzhmxzh.chordRecognitionTrainer;

import java.util.Random;

public class Helper {
	private static String[] notes = new String[]{"C", "C_SHARP", "D", "D_SHARP", "E", "F", "F_SHARP", "G", "G_SHARP", "A", "A_SHARP", "B"};

	public Chord generateRandomChord() {
		Random random = new Random();
		String root = generateRandomNote(random);
		Chord.Name[] names = Chord.Name.values();
		Chord.Name name = names[random.nextInt(names.length)];
		return new Chord(root, name);
	}

	private static String generateRandomNote(Random random) {
		return notes[random.nextInt(notes.length)];
	}

}
