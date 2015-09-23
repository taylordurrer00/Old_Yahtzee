// Taylor Durrer
// Period 6
// 4/29/13
// Final Game - Yahtzee

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class YahtzeeGame extends JFrame implements ActionListener {

	private static final int FRAME_WIDTH = 800;
	private static final int FRAME_HEIGHT = 600;
	private static final int FRAME_X_ORIGIN = 400;
	private static final int FRAME_Y_ORIGIN = 150;

	private static final int BUTTON_WIDTH = 120;
	private static final int BUTTON_HEIGHT = 30;

	private JButton holdButton1;
	private JButton holdButton2;
	private JButton holdButton3;
	private JButton holdButton4;
	private JButton holdButton5;
	private JButton rollButton;

	private	Container contentPane;
	private	JPanel gamePanel;
	private JPanel holdButtonsPanel;
	private	JPanel scorePanel;
	private	JPanel upperPanel;
	private	JPanel lowerPanel;

	private Element ones;
	private Element twos;
	private Element threes;
	private Element fours;
	private Element fives;
	private Element sixes;
	private Element upperBonus;
	private Element upper;
	private Element threeOfAKind;
	private Element fourOfAKind;
	private Element fullHouse;
	private Element smStraight;
	private Element lgStraight;
	private Element yahtzee;
	private Element chance;
	private Element lower;
	private Element total;

	public Dice[] dice;
	private int rolls;
	private Score score;

	public static void main (String [] args) {
		YahtzeeGame frame = new YahtzeeGame( );
		frame.setVisible(true);
	}

	public YahtzeeGame( ) {
		score = new Score( );
		rolls = 0;

		ones = new Element( );
		twos = new Element( );
		threes = new Element( );
		fours = new Element( );
		fives = new Element( );
		sixes = new Element( );
		upperBonus = new Element( );
		upper = new Element( );
		threeOfAKind = new Element( );
		fourOfAKind = new Element( );
		fullHouse = new Element( );
		smStraight = new Element( );
		lgStraight = new Element( );
		yahtzee = new Element( );
		chance = new Element( );
		lower = new Element( );
		total = new Element( );

		//set the frame properties
		setTitle("Yahtzee");
		setSize(FRAME_WIDTH, FRAME_HEIGHT);
		setLocation(FRAME_X_ORIGIN, FRAME_Y_ORIGIN);
		setResizable(false);

		contentPane = getContentPane( );
		contentPane.setLayout(new BorderLayout(10, 20));

		gamePanel = new JPanel( );
		gamePanel.setBorder(BorderFactory.createTitledBorder("Game:"));
		gamePanel.setLayout(new FlowLayout( ));

		scorePanel = new JPanel( );
		scorePanel.setBorder(BorderFactory.createTitledBorder("Score Sheet:"));
		scorePanel.setLayout(new BoxLayout(scorePanel, BoxLayout.Y_AXIS));

		//upper panel
		upperPanel = new JPanel( );
		upperPanel.setBorder(BorderFactory.createTitledBorder("UPPER"));
		upperPanel.setLayout(new GridLayout(8, 3));

		upperPanel.add(new JLabel("Ones:   "));
		upperPanel.add(ones.label);
		upperPanel.add(ones.button);
		upperPanel.add(new JLabel("Twos:   "));
		upperPanel.add(twos.label);
		upperPanel.add(twos.button);
		upperPanel.add(new JLabel("Threes:   "));
		upperPanel.add(threes.label);
		upperPanel.add(threes.button);
		upperPanel.add(new JLabel("Fours:   "));
		upperPanel.add(fours.label);
		upperPanel.add(fours.button);
		upperPanel.add(new JLabel("Fives:   "));
		upperPanel.add(fives.label);
		upperPanel.add(fives.button);
		upperPanel.add(new JLabel("Sixes:   "));
		upperPanel.add(sixes.label);
		upperPanel.add(sixes.button);
		upperPanel.add(new JLabel("Bonus:   "));
		upperPanel.add(upperBonus.label);
		upperPanel.add(new JLabel(""));
		upperPanel.add(new JLabel("Upper Total:   "));
		upperPanel.add(upper.label);


		//lower panel
		lowerPanel = new JPanel( );
		lowerPanel.setBorder(BorderFactory.createTitledBorder("LOWER"));
		lowerPanel.setLayout(new GridLayout(9, 2));
		lowerPanel.add(new JLabel("3 of a kind:   "));
		lowerPanel.add(threeOfAKind.label);
		lowerPanel.add(threeOfAKind.button);
		lowerPanel.add(new JLabel("4 of a kind:   "));
		lowerPanel.add(fourOfAKind.label);
		lowerPanel.add(fourOfAKind.button);
		lowerPanel.add(new JLabel("Full House:   "));
		lowerPanel.add(fullHouse.label);
		lowerPanel.add(fullHouse.button);
		lowerPanel.add(new JLabel("Small Straight:   "));
		lowerPanel.add(smStraight.label);
		lowerPanel.add(smStraight.button);
		lowerPanel.add(new JLabel("Large Straight:   "));
		lowerPanel.add(lgStraight.label);
		lowerPanel.add(lgStraight.button);
		lowerPanel.add(new JLabel("Yahtzee:   "));
		lowerPanel.add(yahtzee.label);
		lowerPanel.add(yahtzee.button);
		lowerPanel.add(new JLabel("Chance:   "));
		lowerPanel.add(chance.label);
		lowerPanel.add(chance.button);
		lowerPanel.add(new JLabel("Lower Total:   "));
		lowerPanel.add(lower.label);
		lowerPanel.add(new JLabel(""));
		lowerPanel.add(new JLabel("TOTAL:   "));
		lowerPanel.add(total.label);
		lowerPanel.add(new JLabel(""));

		upperPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
		lowerPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
		scorePanel.add(upperPanel);
		scorePanel.add(lowerPanel);
		contentPane.add(gamePanel, BorderLayout.CENTER);
		contentPane.add(scorePanel, BorderLayout.EAST);

		//create dice
		dice = new Dice[6];
		dice[0] = new Dice( );
		for (int i = 1; i < dice.length; i++){
			dice[i] = new Dice( );
			dice[i].initialize( );
			gamePanel.add(dice[i].getImage( ));
		}

		//hold buttons
		holdButtonsPanel = new JPanel( );
		holdButtonsPanel.setLayout(new GridLayout(1,5));
		holdButton1 = new JButton("Hold");
		holdButton1.setSize(BUTTON_WIDTH, BUTTON_HEIGHT);
		holdButton1.addActionListener(new ActionListener( )	{
			public void actionPerformed(ActionEvent e) {
				dice[1].hold( );
			}
		});
		holdButtonsPanel.add(holdButton1);
		holdButton1.setEnabled(false);

		holdButton2 = new JButton("Hold");
		holdButton2.setSize(BUTTON_WIDTH, BUTTON_HEIGHT);
		holdButton2.addActionListener(new ActionListener( ) {
			public void actionPerformed(ActionEvent e) {
				dice[2].hold( );
			}
		});
		holdButtonsPanel.add(holdButton2);
		holdButton2.setEnabled(false);

		holdButton3 = new JButton("Hold");
		holdButton3.setSize(BUTTON_WIDTH, BUTTON_HEIGHT);
		holdButton3.addActionListener(new ActionListener( ) {
			public void actionPerformed(ActionEvent e) {
				dice[3].hold( );
			}
		});
		holdButtonsPanel.add(holdButton3);
		holdButton3.setEnabled(false);

		holdButton4 = new JButton("Hold");
		holdButton4.setSize(BUTTON_WIDTH, BUTTON_HEIGHT);
		holdButton4.addActionListener(new ActionListener( ) {
			public void actionPerformed(ActionEvent e) {
				dice[4].hold( );
			}
		});
		holdButtonsPanel.add(holdButton4);
		holdButton4.setEnabled(false);

		holdButton5 = new JButton("Hold");
		holdButton5.setSize(BUTTON_WIDTH, BUTTON_HEIGHT);
		holdButton5.addActionListener(new ActionListener( ) {
			public void actionPerformed(ActionEvent e) {
				dice[5].hold( );
			}
		});
		holdButtonsPanel.add(holdButton5);
		holdButton5.setEnabled(false);
		gamePanel.add(holdButtonsPanel);

		//roll button
		rollButton = new JButton("Roll");
		rollButton.setSize(BUTTON_WIDTH, BUTTON_HEIGHT);
		rollButton.addActionListener(new ActionListener( ) {
			public void actionPerformed(ActionEvent e)	{
				rolls++;
				if (rolls < 39) {
					for (int i = 1; i < dice.length; i++) {
						dice[i].roll( );
					}
				}
				if (rolls % 3 == 0) {
					rollButton.setEnabled(false);
					score.scoreUpper(dice);
					if (!ones.isUsed){
						ones.button.setEnabled(true);
						ones.button.setText(score.getOnes( ));
					}
					if (!twos.isUsed){
						twos.button.setEnabled(true);
						twos.button.setText(score.getTwos( ));
					}
					if (!threes.isUsed){
						threes.button.setEnabled(true);
						threes.button.setText(score.getThrees( ));
					}
					if (!fours.isUsed){
						fours.button.setEnabled(true);
						fours.button.setText(score.getFours( ));
					}
					if (!fives.isUsed){
						fives.button.setEnabled(true);
						fives.button.setText(score.getFives( ));
					}
					if (!sixes.isUsed){
						sixes.button.setEnabled(true);
						sixes.button.setText(score.getSixes( ));
					}

					score.scoreLower(dice);
					if (!threeOfAKind.isUsed){
						threeOfAKind.button.setEnabled(true);
						threeOfAKind.button.setText(score.getThreeOfAKind( ));
					}
					if (!fourOfAKind.isUsed){
						fourOfAKind.button.setEnabled(true);
						fourOfAKind.button.setText(score.getFourOfAKind( ));
					}
					if (!fullHouse.isUsed){
						fullHouse.button.setEnabled(true);
						fullHouse.button.setText(score.getFullHouse( ));
					}
					if (!smStraight.isUsed){
						smStraight.button.setEnabled(true);
						smStraight.button.setText(score.getSmStraight( ));
					}
					if (!lgStraight.isUsed){
						lgStraight.button.setEnabled(true);
						lgStraight.button.setText(score.getLgStraight( ));
					}
					if (!yahtzee.isUsed){
						yahtzee.button.setEnabled(true);
						yahtzee.button.setText(score.getYahtzee( ));
					}
					if (!chance.isUsed){
						chance.button.setEnabled(true);
						chance.button.setText(score.getChance( ));
					}

					for(int i = 1; i < dice.length; i++) {
						dice[i].resetHold( );
					}
					holdButton1.setEnabled(false);
					holdButton2.setEnabled(false);
					holdButton3.setEnabled(false);
					holdButton4.setEnabled(false);
					holdButton5.setEnabled(false);

				}
				else{
					holdButton1.setEnabled(true);
					holdButton2.setEnabled(true);
					holdButton3.setEnabled(true);
					holdButton4.setEnabled(true);
					holdButton5.setEnabled(true);

					ones.button.setEnabled(false);
					twos.button.setEnabled(false);
					threes.button.setEnabled(false);
					fours.button.setEnabled(false);
					fives.button.setEnabled(false);
					sixes.button.setEnabled(false);
					threeOfAKind.button.setEnabled(false);
					fourOfAKind.button.setEnabled(false);
					fullHouse.button.setEnabled(false);
					smStraight.button.setEnabled(false);
					lgStraight.button.setEnabled(false);
					yahtzee.button.setEnabled(false);
					chance.button.setEnabled(false);
				}

			}
		});
		gamePanel.add(rollButton);

		ones.button.addActionListener(new ActionListener( ) {
			public void actionPerformed(ActionEvent e) {
				ones.label.setText(score.getOnes( ));
				ones.buttonClicked( );
				if (rolls < 39) {
					rollButton.setEnabled(true);
				}
				score.initialize( );
				upper.value += ones.value;
				upper.label.setText("" + upper.value);
				if(upper.value >= 63) {
					upperBonus.value = 35;
					upperBonus.label.setText("" + upperBonus.value);
					upper.value += upperBonus.value;
					upper.label.setText("" + upper.value);
				}

				total.value = upper.value + lower.value;
				total.label.setText("" + total.value);

				ones.button.setText("");
				twos.button.setText("");
				threes.button.setText("");
				fours.button.setText("");
				fives.button.setText("");
				sixes.button.setText("");
				threeOfAKind.button.setText("");
				fourOfAKind.button.setText("");
				fullHouse.button.setText("");
				smStraight.button.setText("");
				lgStraight.button.setText("");
				yahtzee.button.setText("");
				chance.button.setText("");

				ones.button.setEnabled(false);
				twos.button.setEnabled(false);
				threes.button.setEnabled(false);
				fours.button.setEnabled(false);
				fives.button.setEnabled(false);
				sixes.button.setEnabled(false);
				threeOfAKind.button.setEnabled(false);
				fourOfAKind.button.setEnabled(false);
				fullHouse.button.setEnabled(false);
				smStraight.button.setEnabled(false);
				lgStraight.button.setEnabled(false);
				yahtzee.button.setEnabled(false);
				chance.button.setEnabled(false);
			}
		});

		twos.button.addActionListener(new ActionListener( ) {
			public void actionPerformed(ActionEvent e) {
				twos.label.setText(score.getTwos( ));
				twos.buttonClicked( );
				if (rolls < 39) {
					rollButton.setEnabled(true);
				}
				score.initialize( );
				upper.value += twos.value;
				upper.label.setText("" + upper.value);
				if(upper.value >= 63) {
					upperBonus.value = 35;
					upperBonus.label.setText("" + upperBonus.value);
					upper.value += upperBonus.value;
					upper.label.setText("" + upper.value);
				}

				total.value = upper.value + lower.value;
				total.label.setText("" + total.value);

				ones.button.setText("");
				twos.button.setText("");
				threes.button.setText("");
				fours.button.setText("");
				fives.button.setText("");
				sixes.button.setText("");
				threeOfAKind.button.setText("");
				fourOfAKind.button.setText("");
				fullHouse.button.setText("");
				smStraight.button.setText("");
				lgStraight.button.setText("");
				yahtzee.button.setText("");
				chance.button.setText("");

				ones.button.setEnabled(false);
				twos.button.setEnabled(false);
				threes.button.setEnabled(false);
				fours.button.setEnabled(false);
				fives.button.setEnabled(false);
				sixes.button.setEnabled(false);
				threeOfAKind.button.setEnabled(false);
				fourOfAKind.button.setEnabled(false);
				fullHouse.button.setEnabled(false);
				smStraight.button.setEnabled(false);
				lgStraight.button.setEnabled(false);
				yahtzee.button.setEnabled(false);
				chance.button.setEnabled(false);
			}
		});

		threes.button.addActionListener(new ActionListener( ) {
			public void actionPerformed(ActionEvent e) {
				threes.label.setText(score.getThrees( ));
				threes.buttonClicked( );
				if (rolls < 39) {
					rollButton.setEnabled(true);
				}
				score.initialize( );
				upper.value += threes.value;
				upper.label.setText("" + upper.value);
				if(upper.value >= 63) {
					upperBonus.value = 35;
					upperBonus.label.setText("" + upperBonus.value);
					upper.value += upperBonus.value;
					upper.label.setText("" + upper.value);
				}

				total.value = upper.value + lower.value;
				total.label.setText("" + total.value);

				ones.button.setText("");
				twos.button.setText("");
				threes.button.setText("");
				fours.button.setText("");
				fives.button.setText("");
				sixes.button.setText("");
				threeOfAKind.button.setText("");
				fourOfAKind.button.setText("");
				fullHouse.button.setText("");
				smStraight.button.setText("");
				lgStraight.button.setText("");
				yahtzee.button.setText("");
				chance.button.setText("");

				ones.button.setEnabled(false);
				twos.button.setEnabled(false);
				threes.button.setEnabled(false);
				fours.button.setEnabled(false);
				fives.button.setEnabled(false);
				sixes.button.setEnabled(false);
				threeOfAKind.button.setEnabled(false);
				fourOfAKind.button.setEnabled(false);
				fullHouse.button.setEnabled(false);
				smStraight.button.setEnabled(false);
				lgStraight.button.setEnabled(false);
				yahtzee.button.setEnabled(false);
				chance.button.setEnabled(false);
			}
		});

		fours.button.addActionListener(new ActionListener( ) {
			public void actionPerformed(ActionEvent e) {
				fours.label.setText(score.getFours( ));
				fours.buttonClicked( );
				if (rolls < 39) {
					rollButton.setEnabled(true);
				}
				score.initialize( );
				upper.value += fours.value;
				upper.label.setText("" + upper.value);
				if(upper.value >= 63) {
					upperBonus.value = 35;
					upperBonus.label.setText("" + upperBonus.value);
					upper.value += upperBonus.value;
					upper.label.setText("" + upper.value);
				}

				total.value = upper.value + lower.value;
				total.label.setText("" + total.value);

				ones.button.setText("");
				twos.button.setText("");
				threes.button.setText("");
				fours.button.setText("");
				fives.button.setText("");
				sixes.button.setText("");
				threeOfAKind.button.setText("");
				fourOfAKind.button.setText("");
				fullHouse.button.setText("");
				smStraight.button.setText("");
				lgStraight.button.setText("");
				yahtzee.button.setText("");
				chance.button.setText("");

				ones.button.setEnabled(false);
				twos.button.setEnabled(false);
				threes.button.setEnabled(false);
				fours.button.setEnabled(false);
				fives.button.setEnabled(false);
				sixes.button.setEnabled(false);
				threeOfAKind.button.setEnabled(false);
				fourOfAKind.button.setEnabled(false);
				fullHouse.button.setEnabled(false);
				smStraight.button.setEnabled(false);
				lgStraight.button.setEnabled(false);
				yahtzee.button.setEnabled(false);
				chance.button.setEnabled(false);
			}
		});

		fives.button.addActionListener(new ActionListener( ) {
			public void actionPerformed(ActionEvent e) {
				fives.label.setText(score.getFives( ));
				fives.buttonClicked( );
				if (rolls < 39) {
					rollButton.setEnabled(true);
				}
				score.initialize( );
				upper.value += fives.value;
				upper.label.setText("" + upper.value);
				if(upper.value >= 63) {
					upperBonus.value = 35;
					upperBonus.label.setText("" + upperBonus.value);
					upper.value += upperBonus.value;
					upper.label.setText("" + upper.value);
				}

				total.value = upper.value + lower.value;
				total.label.setText("" + total.value);

				ones.button.setText("");
				twos.button.setText("");
				threes.button.setText("");
				fours.button.setText("");
				fives.button.setText("");
				sixes.button.setText("");
				threeOfAKind.button.setText("");
				fourOfAKind.button.setText("");
				fullHouse.button.setText("");
				smStraight.button.setText("");
				lgStraight.button.setText("");
				yahtzee.button.setText("");
				chance.button.setText("");

				ones.button.setEnabled(false);
				twos.button.setEnabled(false);
				threes.button.setEnabled(false);
				fours.button.setEnabled(false);
				fives.button.setEnabled(false);
				sixes.button.setEnabled(false);
				threeOfAKind.button.setEnabled(false);
				fourOfAKind.button.setEnabled(false);
				fullHouse.button.setEnabled(false);
				smStraight.button.setEnabled(false);
				lgStraight.button.setEnabled(false);
				yahtzee.button.setEnabled(false);
				chance.button.setEnabled(false);
			}
		});

		sixes.button.addActionListener(new ActionListener( ) {
			public void actionPerformed(ActionEvent e) {
				sixes.label.setText(score.getSixes( ));
				sixes.buttonClicked( );
				if (rolls < 39) {
					rollButton.setEnabled(true);
				}
				score.initialize( );
				upper.value += sixes.value;
				upper.label.setText("" + upper.value);
				if(upper.value >= 63) {
					upperBonus.value = 35;
					upperBonus.label.setText("" + upperBonus.value);
					upper.value += upperBonus.value;
					upper.label.setText("" + upper.value);
				}

				total.value = upper.value + lower.value;
				total.label.setText("" + total.value);

				ones.button.setText("");
				twos.button.setText("");
				threes.button.setText("");
				fours.button.setText("");
				fives.button.setText("");
				sixes.button.setText("");
				threeOfAKind.button.setText("");
				fourOfAKind.button.setText("");
				fullHouse.button.setText("");
				smStraight.button.setText("");
				lgStraight.button.setText("");
				yahtzee.button.setText("");
				chance.button.setText("");

				ones.button.setEnabled(false);
				twos.button.setEnabled(false);
				threes.button.setEnabled(false);
				fours.button.setEnabled(false);
				fives.button.setEnabled(false);
				sixes.button.setEnabled(false);
				threeOfAKind.button.setEnabled(false);
				fourOfAKind.button.setEnabled(false);
				fullHouse.button.setEnabled(false);
				smStraight.button.setEnabled(false);
				lgStraight.button.setEnabled(false);
				yahtzee.button.setEnabled(false);
				chance.button.setEnabled(false);
			}
		});

		threeOfAKind.button.addActionListener(new ActionListener( ) {
			public void actionPerformed(ActionEvent e) {
				threeOfAKind.label.setText(score.getThreeOfAKind( ));
				threeOfAKind.buttonClicked( );
				if (rolls < 39) {
					rollButton.setEnabled(true);
				}
				score.initialize( );
				lower.value += threeOfAKind.value;
				lower.label.setText("" + lower.value);

				total.value = upper.value + lower.value;
				total.label.setText("" + total.value);

				ones.button.setText("");
				twos.button.setText("");
				threes.button.setText("");
				fours.button.setText("");
				fives.button.setText("");
				sixes.button.setText("");
				threeOfAKind.button.setText("");
				fourOfAKind.button.setText("");
				fullHouse.button.setText("");
				smStraight.button.setText("");
				lgStraight.button.setText("");
				yahtzee.button.setText("");
				chance.button.setText("");

				ones.button.setEnabled(false);
				twos.button.setEnabled(false);
				threes.button.setEnabled(false);
				fours.button.setEnabled(false);
				fives.button.setEnabled(false);
				sixes.button.setEnabled(false);
				threeOfAKind.button.setEnabled(false);
				fourOfAKind.button.setEnabled(false);
				fullHouse.button.setEnabled(false);
				smStraight.button.setEnabled(false);
				lgStraight.button.setEnabled(false);
				yahtzee.button.setEnabled(false);
				chance.button.setEnabled(false);
			}
		});

		fourOfAKind.button.addActionListener(new ActionListener( ) {
			public void actionPerformed(ActionEvent e) {
				fourOfAKind.label.setText(score.getFourOfAKind( ));
				fourOfAKind.buttonClicked( );
				if (rolls < 39) {
					rollButton.setEnabled(true);
				}
				score.initialize( );
				lower.value += fourOfAKind.value;
				lower.label.setText("" + lower.value);

				total.value = upper.value + lower.value;
				total.label.setText("" + total.value);

				ones.button.setText("");
				twos.button.setText("");
				threes.button.setText("");
				fours.button.setText("");
				fives.button.setText("");
				sixes.button.setText("");
				threeOfAKind.button.setText("");
				fourOfAKind.button.setText("");
				fullHouse.button.setText("");
				smStraight.button.setText("");
				lgStraight.button.setText("");
				yahtzee.button.setText("");
				chance.button.setText("");

				ones.button.setEnabled(false);
				twos.button.setEnabled(false);
				threes.button.setEnabled(false);
				fours.button.setEnabled(false);
				fives.button.setEnabled(false);
				sixes.button.setEnabled(false);
				threeOfAKind.button.setEnabled(false);
				fourOfAKind.button.setEnabled(false);
				fullHouse.button.setEnabled(false);
				smStraight.button.setEnabled(false);
				lgStraight.button.setEnabled(false);
				yahtzee.button.setEnabled(false);
				chance.button.setEnabled(false);
			}
		});

		fullHouse.button.addActionListener(new ActionListener( ) {
			public void actionPerformed(ActionEvent e) {
				fullHouse.label.setText(score.getFullHouse( ));
				fullHouse.buttonClicked( );
				if (rolls < 39) {
					rollButton.setEnabled(true);
				}
				score.initialize( );
				lower.value += fullHouse.value;
				lower.label.setText("" + lower.value);

				total.value = upper.value + lower.value;
				total.label.setText("" + total.value);

				ones.button.setText("");
				twos.button.setText("");
				threes.button.setText("");
				fours.button.setText("");
				fives.button.setText("");
				sixes.button.setText("");
				threeOfAKind.button.setText("");
				fourOfAKind.button.setText("");
				fullHouse.button.setText("");
				smStraight.button.setText("");
				lgStraight.button.setText("");
				yahtzee.button.setText("");
				chance.button.setText("");

				ones.button.setEnabled(false);
				twos.button.setEnabled(false);
				threes.button.setEnabled(false);
				fours.button.setEnabled(false);
				fives.button.setEnabled(false);
				sixes.button.setEnabled(false);
				threeOfAKind.button.setEnabled(false);
				fourOfAKind.button.setEnabled(false);
				fullHouse.button.setEnabled(false);
				smStraight.button.setEnabled(false);
				lgStraight.button.setEnabled(false);
				yahtzee.button.setEnabled(false);
				chance.button.setEnabled(false);
			}
		});

		smStraight.button.addActionListener(new ActionListener( ) {
			public void actionPerformed(ActionEvent e) {
				smStraight.label.setText(score.getSmStraight( ));
				smStraight.buttonClicked( );
				if (rolls < 39) {
					rollButton.setEnabled(true);
				}
				score.initialize( );
				lower.value += smStraight.value;
				lower.label.setText("" + lower.value);

				total.value = upper.value + lower.value;
				total.label.setText("" + total.value);

				ones.button.setText("");
				twos.button.setText("");
				threes.button.setText("");
				fours.button.setText("");
				fives.button.setText("");
				sixes.button.setText("");
				threeOfAKind.button.setText("");
				fourOfAKind.button.setText("");
				fullHouse.button.setText("");
				smStraight.button.setText("");
				lgStraight.button.setText("");
				yahtzee.button.setText("");
				chance.button.setText("");

				ones.button.setEnabled(false);
				twos.button.setEnabled(false);
				threes.button.setEnabled(false);
				fours.button.setEnabled(false);
				fives.button.setEnabled(false);
				sixes.button.setEnabled(false);
				threeOfAKind.button.setEnabled(false);
				fourOfAKind.button.setEnabled(false);
				fullHouse.button.setEnabled(false);
				smStraight.button.setEnabled(false);
				lgStraight.button.setEnabled(false);
				yahtzee.button.setEnabled(false);
				chance.button.setEnabled(false);
			}
		});

		lgStraight.button.addActionListener(new ActionListener( ) {
			public void actionPerformed(ActionEvent e) {
				lgStraight.label.setText(score.getLgStraight( ));
				lgStraight.buttonClicked( );
				if (rolls < 39) {
					rollButton.setEnabled(true);
				}
				score.initialize( );
				lower.value += lgStraight.value;
				lower.label.setText("" + lower.value);

				total.value = upper.value + lower.value;
				total.label.setText("" + total.value);

				ones.button.setText("");
				twos.button.setText("");
				threes.button.setText("");
				fours.button.setText("");
				fives.button.setText("");
				sixes.button.setText("");
				threeOfAKind.button.setText("");
				fourOfAKind.button.setText("");
				fullHouse.button.setText("");
				smStraight.button.setText("");
				lgStraight.button.setText("");
				yahtzee.button.setText("");
				chance.button.setText("");

				ones.button.setEnabled(false);
				twos.button.setEnabled(false);
				threes.button.setEnabled(false);
				fours.button.setEnabled(false);
				fives.button.setEnabled(false);
				sixes.button.setEnabled(false);
				threeOfAKind.button.setEnabled(false);
				fourOfAKind.button.setEnabled(false);
				fullHouse.button.setEnabled(false);
				smStraight.button.setEnabled(false);
				lgStraight.button.setEnabled(false);
				yahtzee.button.setEnabled(false);
				chance.button.setEnabled(false);
			}
		});

		yahtzee.button.addActionListener(new ActionListener( ) {
			public void actionPerformed(ActionEvent e) {
				yahtzee.label.setText(score.getYahtzee( ));
				yahtzee.buttonClicked( );
				if (rolls < 39) {
					rollButton.setEnabled(true);
				}
				score.initialize( );
				lower.value += yahtzee.value;
				lower.label.setText("" + lower.value);

				total.value = upper.value + lower.value;
				total.label.setText("" + total.value);

				ones.button.setText("");
				twos.button.setText("");
				threes.button.setText("");
				fours.button.setText("");
				fives.button.setText("");
				sixes.button.setText("");
				threeOfAKind.button.setText("");
				fourOfAKind.button.setText("");
				fullHouse.button.setText("");
				smStraight.button.setText("");
				lgStraight.button.setText("");
				yahtzee.button.setText("");
				chance.button.setText("");

				ones.button.setEnabled(false);
				twos.button.setEnabled(false);
				threes.button.setEnabled(false);
				fours.button.setEnabled(false);
				fives.button.setEnabled(false);
				sixes.button.setEnabled(false);
				threeOfAKind.button.setEnabled(false);
				fourOfAKind.button.setEnabled(false);
				fullHouse.button.setEnabled(false);
				smStraight.button.setEnabled(false);
				lgStraight.button.setEnabled(false);
				yahtzee.button.setEnabled(false);
				chance.button.setEnabled(false);
			}
		});

		chance.button.addActionListener(new ActionListener( ) {
			public void actionPerformed(ActionEvent e) {
				chance.label.setText(score.getChance( ));
				chance.buttonClicked( );
				if (rolls < 39) {
					rollButton.setEnabled(true);
				}
				score.initialize( );
				lower.value += chance.value;
				lower.label.setText("" + lower.value);

				total.value = upper.value + lower.value;
				total.label.setText("" + total.value);

				ones.button.setText("");
				twos.button.setText("");
				threes.button.setText("");
				fours.button.setText("");
				fives.button.setText("");
				sixes.button.setText("");
				threeOfAKind.button.setText("");
				fourOfAKind.button.setText("");
				fullHouse.button.setText("");
				smStraight.button.setText("");
				lgStraight.button.setText("");
				yahtzee.button.setText("");
				chance.button.setText("");

				ones.button.setEnabled(false);
				twos.button.setEnabled(false);
				threes.button.setEnabled(false);
				fours.button.setEnabled(false);
				fives.button.setEnabled(false);
				sixes.button.setEnabled(false);
				threeOfAKind.button.setEnabled(false);
				fourOfAKind.button.setEnabled(false);
				fullHouse.button.setEnabled(false);
				smStraight.button.setEnabled(false);
				lgStraight.button.setEnabled(false);
				yahtzee.button.setEnabled(false);
				chance.button.setEnabled(false);
			}
		});




		setDefaultCloseOperation(EXIT_ON_CLOSE);
}

	public void actionPerformed(ActionEvent event) {

	}
}

class Dice {

	private final JLabel DICE_1 = new JLabel(new ImageIcon("OneDice.gif"));
	private final JLabel DICE_2 = new JLabel(new ImageIcon("TwoDice.gif"));
	private final JLabel DICE_3 = new JLabel(new ImageIcon("ThreeDice.gif"));
	private final JLabel DICE_4 = new JLabel(new ImageIcon("FourDice.gif"));
	private final JLabel DICE_5 = new JLabel(new ImageIcon("FiveDice.gif"));
	private final JLabel DICE_6 = new JLabel(new ImageIcon("SixDice.gif"));
	private final JLabel DICE_1_HELD = new JLabel(new ImageIcon("OneDiceHeld.gif"));
	private final JLabel DICE_2_HELD = new JLabel(new ImageIcon("TwoDiceHeld.gif"));
	private final JLabel DICE_3_HELD = new JLabel(new ImageIcon("ThreeDiceHeld.gif"));
	private final JLabel DICE_4_HELD = new JLabel(new ImageIcon("FourDiceHeld.gif"));
	private final JLabel DICE_5_HELD = new JLabel(new ImageIcon("FiveDiceHeld.gif"));
	private final JLabel DICE_6_HELD = new JLabel(new ImageIcon("SixDiceHeld.gif"));

	private JLabel currentDice;
	private boolean isHeld;
	private int diceNumber;

	public Dice( ) {
		diceNumber = 0;
		isHeld = false;
		currentDice = null;
	}

	public void setImage(int number, boolean held) {
		if (!held) {
			if (number == 1)
				currentDice.setIcon(new ImageIcon("OneDice.GIF"));
			if (number == 2)
				currentDice.setIcon(new ImageIcon("TwoDice.gif"));
			if (number == 3)
				currentDice.setIcon(new ImageIcon("ThreeDice.gif"));
			if (number == 4)
				currentDice.setIcon(new ImageIcon("FourDice.gif"));
			if (number == 5)
				currentDice.setIcon(new ImageIcon("FiveDice.gif"));
			if (number == 6)
				currentDice.setIcon(new ImageIcon("SixDice.gif"));
		}
		else {
			if (number == 1)
				currentDice.setIcon(new ImageIcon("OneDiceHeld.gif"));
			if (number == 2)
				currentDice.setIcon(new ImageIcon("TwoDiceHeld.gif"));
			if (number == 3)
				currentDice.setIcon(new ImageIcon("ThreeDiceHeld.gif"));
			if (number == 4)
				currentDice.setIcon(new ImageIcon("FourDiceHeld.gif"));
			if (number == 5)
				currentDice.setIcon(new ImageIcon("FiveDiceHeld.gif"));
			if (number == 6)
				currentDice.setIcon(new ImageIcon("SixDiceHeld.gif"));
		}

	}

	public void initialize( ) {
		diceNumber = 1;
		isHeld = false;
		currentDice = DICE_1;
	}

	public JLabel getImage( ) {
		return currentDice;
	}

	public void roll( ) {
		if (!isHeld) {
			diceNumber = (int) (Math.floor(Math.random( ) * 6) + 1);
			setImage(diceNumber, isHeld);
		}
	}

	public void hold( ) {
		isHeld = !isHeld;
		setImage(diceNumber, isHeld);
	}

	public void resetHold( ) {
		if (isHeld) {
			isHeld = false;
		}
	}

	public int getNumber( ) {
		return diceNumber;
	}
}

class Score {
	private int ones;
	private int twos;
	private int threes;
	private int fours;
	private int fives;
	private int sixes;
	private int upperBonus;
	private int upper;
	private int threeOfAKind;
	private int fourOfAKind;
	private int fullHouse;
	private int smStraight;
	private int lgStraight;
	private int yahtzee;
	private int chance;
	private int lower;
	private int total;

	public Score( ) {
		initialize( );
	}

	public void initialize( ) {
		ones = 0;
		twos = 0;
		threes = 0;
		fours = 0;
		fives = 0;
		sixes = 0;
		upperBonus = 0;
		upper = 0;
		threeOfAKind = 0;
		fourOfAKind = 0;
		fullHouse = 0;
		smStraight = 0;
		lgStraight = 0;
		yahtzee = 0;
		chance = 0;
		lower = 0;
		total = 0;
	}

	public void scoreUpper(Dice[] diceArray) {
		for(int i = 1; i < diceArray.length; i++) {
			if (diceArray[i].getNumber( ) == 1) {
				ones += 1;
			}
			if (diceArray[i].getNumber( ) == 2) {
				twos += 2;
			}
			if (diceArray[i].getNumber( ) == 3) {
				threes += 3;
			}
			if (diceArray[i].getNumber( ) == 4) {
				fours += 4;
			}
			if (diceArray[i].getNumber( ) == 5) {
				fives += 5;
			}
			if (diceArray[i].getNumber( ) == 6) {
				sixes += 6;
			}
		}

	}

	public void scoreLower(Dice[] diceArray) {
		if (ones == 3 || twos == 6 || threes == 9 || fours == 12 || fives == 15 || sixes == 18) {
			for (int i = 1; i < diceArray.length; i++) {
				threeOfAKind += diceArray[i].getNumber( );
			}
		}
		if (ones == 4 || twos == 8 || threes == 12 || fours == 16 || fives == 20 || sixes == 24) {
			for (int i = 1; i < diceArray.length; i++) {
				fourOfAKind += diceArray[i].getNumber( );
			}
		}
		if ((ones == 3 || twos == 6 || threes == 9 || fours == 12 || fives == 15 || sixes == 18) &&
			(ones == 2 || twos == 4 || threes == 6 || fours == 8 || fives == 10 || sixes == 12)) {
				fullHouse = 25;
		}
		if ((ones >= 1 && twos >= 2 && threes >= 3 && fours >= 4) ||
			(twos >= 2 && threes >= 3 && fours >= 4 && fives >= 5) ||
			(threes >= 3 && fours >= 4 && fives >= 5 && sixes >= 6)) {
				smStraight = 30;
		}
		if ((ones >= 1 && twos >= 2 && threes >= 3 && fours >= 4 && fives >= 5) ||
			(twos >= 2 && threes >= 3 && fours >= 4 && fives >= 5 && sixes >= 6)) {
				lgStraight = 40;
		}
		if (ones == 5 || twos == 10 || threes == 15 || fours == 20 || fives == 25 || sixes == 30) {
			yahtzee = 50;
		}
		chance = ones + twos + threes + fours + fives + sixes;
	}

	public String getOnes( ) {
		return "" + ones;
	}

	public String getTwos( ) {
		return "" + twos;
	}

	public String getThrees( ) {
		return "" + threes;
	}

	public String getFours( ) {
		return "" + fours;
	}

	public String getFives( ) {
		return "" + fives;
	}

	public String getSixes( ) {
		return "" + sixes;
	}

	public String getUpper( ) {
		return "" + upper;
	}

	public String getUpperBonus( ) {
		return "" + upperBonus;
	}

	public String getThreeOfAKind( ) {
		return "" + threeOfAKind;
	}

	public String getFourOfAKind( ) {
		return "" + fourOfAKind;
	}

	public String getFullHouse( ) {
		return "" + fullHouse;
	}

	public String getSmStraight( ) {
		return "" + smStraight;
	}

	public String getLgStraight( ) {
		return "" + lgStraight;
	}

	public String getYahtzee( ) {
		return "" + yahtzee;
	}

	public String getChance( ) {
		return "" + chance;
	}

	public String getLower( ) {
		return "" + lower;
	}

	public String getTotal( ) {
		return "" + total;
	}
}

class Element {
	public boolean isUsed;
	public JLabel label;
	public JButton button;
	public int value;

	public Element( ) {
		isUsed = false;
		label = new JLabel("0");
		button = new JButton("");
		value = 0;
	}

	public void buttonClicked( ) {
		isUsed = true;
		button.setText("");
		button.setEnabled(false);
		value = Integer.parseInt(label.getText( ));
	}
}
