package com.mzhmxzh.chordRecognitionTrainer;

import java.util.Random;

public class Helper {
	private static String[] notes = new String[]{"C", "Cs", "D", "Ds", "E", "F", "Fs", "G", "Gs", "A", "As", "B"};

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
