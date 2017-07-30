package com.mzhmxzh.chordRecognitionTrainer;

import javax.sound.midi.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class ChordRecognitionTrainer {
	private Chord givenChord;
	private Chord guessChord;
	private JTextField userGuess;
	private JLabel tipLabel;
	private Helper helper;
	private Sequencer sequencer;

	public static void main(String[] args) {
		new ChordRecognitionTrainer().start();
	}

	private void start() {
		helper = new Helper();
		guessChord = helper.generateRandomChord();
		givenChord = new Chord();//Default is C_Major
		try {
			sequencer = MidiSystem.getSequencer();
			sequencer.open();
//            try{
//                sequencer.setSequence(new Sequence(Sequence.PPQ,4));
//            }catch (InvalidMidiDataException ex){System.out.println("Can't set the sequence");}
		} catch (MidiUnavailableException ex) {
			System.out.println("Can't get the sequencer");
		}
		sequencer.setTempoInBPM(120);

		buildGUI();
	}

	private void buildGUI() {
		JFrame frame = new JFrame("Chord Recognition Trainer");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel mainPanel = new JPanel();

		JButton playGivenChord = new JButton("play it");
		playGivenChord.addActionListener(new PlayChordListener(givenChord));
		JButton playGuessChord = new JButton("play it");
		playGuessChord.addActionListener(new PlayChordListener(guessChord));
		JButton makeGuess = new JButton("go");
		makeGuess.addActionListener(ignored -> {
			String rightAnswer = guessChord.getName();
			if (userGuess.getText().equals(rightAnswer)) {
				tipLabel.setText("You are right");
			} else {
				tipLabel.setText("No it's a " + rightAnswer);
			}
		});
		JButton nextButton = new JButton("next");
		nextButton.addActionListener(ignored -> {
			tipLabel.setText("for example, 'C_Major' or 'Bf_Augmented'");
			guessChord = helper.generateRandomChord();
			userGuess.setText("");
		});

		JLabel givenChordLabel = new JLabel();
		givenChordLabel.setText("C MAJOR Chord");
		JLabel guessChordLabel = new JLabel();
		guessChordLabel.setText("Mystery Chord");
		JLabel userGuessLabel = new JLabel();
		userGuessLabel.setText("I guess it's a");
		tipLabel = new JLabel();
		tipLabel.setText("for example, 'C_Major' or 'Bf_Augmented'");

		userGuess = new JTextField(15);

		Box buttonBox = new Box(BoxLayout.X_AXIS);
		buttonBox.add(makeGuess);
		buttonBox.add(nextButton);

		GridLayout grid = new GridLayout(3, 2);
		JPanel gridPanel = new JPanel(grid);
		gridPanel.add(givenChordLabel);
		gridPanel.add(playGivenChord);
		gridPanel.add(guessChordLabel);
		gridPanel.add(playGuessChord);
		gridPanel.add(userGuessLabel);
		gridPanel.add(userGuess);

		mainPanel.add(gridPanel);
		mainPanel.add(tipLabel);
		mainPanel.add(buttonBox);

		frame.getContentPane().add(mainPanel);
		frame.setBounds(150, 150, 500, 400);
		frame.pack();
		frame.setVisible(true);
	}

	private class PlayChordListener implements ActionListener {
		private Chord chord;

		public PlayChordListener(Chord chord) {
			this.chord = chord;
		}

		@Override
		public void actionPerformed(ActionEvent ignored) {
			Sequence seq = chord.makeSound();
			try {
				sequencer.setSequence(seq);
			} catch (InvalidMidiDataException e) {
				System.out.println("Can't set sequence");
				e.printStackTrace();
			}
			sequencer.start();
		}
	}
}