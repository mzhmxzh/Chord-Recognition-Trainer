package com.mzhmxzh.chordRecognitionTrainer;

import javax.sound.midi.*;

public class Chord {

	private String root;
	private Name name;

	public String getName() {
		return root + "_" + name;
	}

	public Chord(String root, Name name) {
		this.root = root;
		this.name = name;
	}

	public Chord() {
		this.root = "C";
		this.name = Name.MAJOR;
	}

	private MidiEvent[][] getSound() {
		int start = 0;
		int end = 10;
		MidiEvent[][] events = new MidiEvent[2][];
		int key = (int) root.charAt(0) - 19;
		if (root.length() > 1) {
			if (root.charAt(2) == 's') {
				key++;
			} else {
				key--;
			}
		}
		int[] intervals = name.intervals;
		events[0] = new MidiEvent[intervals.length];
		events[1] = new MidiEvent[intervals.length];
		for (int i = 0; i < intervals.length; i++) {
			events[0][i] = makeEvent(144, 1, key + intervals[i], 100, start);
			events[1][i] = makeEvent(128, 1, key + intervals[i], 100, end);
		}
		return events;
	}

	public Sequence makeSound() {
		Sequence seq = null;
		try {
			seq = new Sequence(Sequence.PPQ, 4);
			Track track = seq.createTrack();
			MidiEvent[][] events = getSound();
			for (int i = 0; i < 2; i++) {
				for (int j = 0; j < events[0].length; j++) {
					track.add(events[i][j]);
				}
			}
		} catch (Exception e) {
			System.out.println("Can't get sequence");
		}
		return seq;
	}

	private static MidiEvent makeEvent(int comd, int chan, int one, int two, int tick) {
		MidiEvent event = null;
		try {
			ShortMessage a = new ShortMessage();
			a.setMessage(comd, chan, one, two);
			event = new MidiEvent(a, tick);
		} catch (Exception e) {
			System.out.println("Oops");
		}
		return event;
	}

	enum Name {
		MAJOR(new int[]{0, 4, 7}), MINOR(new int[]{0, 3, 7});

		private int[] intervals;

		Name(int[] intervals) {
			this.intervals = intervals;
		}
	}

/*
enum Note{
c,d,e,f,g,a,b,
c1,d1,e1,f1,g1,a1,b1,
c2,d2,e2,f2,g2,a2,b2,
c_sharp,d_sharp,e_sharp,f_sharp,g_sharp,a_sharp,b_sharp,
c1_sharp,d1_sharp,e1_sharp,f1_sharp,g1_sharp,a1_sharp,b1_sharp,
c2_sharp,d2_sharp,e2_sharp,f2_sharp,g2_sharp,a2_sharp,b2_sharp,
c_flat,d_flat_e_flat_f_flat_g_flat,a_flat,b_flat,
c1_flat,d1_flat_e1_flat_f1_flat_g1_flat,a1_flat,b1_flat,
c2_flat,d2_flat,e2_flat,f2_flat,g2_flat,a2_flat,b2_flat;

}
*/
}