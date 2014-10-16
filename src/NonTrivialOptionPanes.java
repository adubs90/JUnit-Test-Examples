/*
 * Option Panes
 * Autumn 2011 TCSS 305
 */

import java.util.Random;

import javax.swing.JOptionPane;

/**
 * Displays some option panes.
 * 
 * @author Daniel M. Zimmerman
 * @version 2011-04-12
 */
public final class NonTrivialOptionPanes
{
  /**
   * The minimum value to guess.
   */
  private static final int MIN_NUMBER = 1;
  
  /**
   * The maximum value to guess.
   */
  private static final int MAX_NUMBER = 100;
  
  /**
   * The "and" separator.
   */
  private static final String AND_SEP = " and ";
  
  /**
   * Private constructor, to prevent instantiation.
   */
  private NonTrivialOptionPanes()
  {
    // do nothing
  }
  
  /**
   * Shows a hint based on whether the guess is higher or
   * lower than the number.
   * 
   * @param the_guess The guess.
   * @param the_number The number.
   */
  public static void showHint(final int the_guess, final int the_number)
  {
    if (the_guess < the_number)
    {
      JOptionPane.showMessageDialog(null, "Too low!");
    }
    else if (the_number < the_guess)
    {
      JOptionPane.showMessageDialog(null, "Too high!");
    }
  }
  
  /**
   * Plays the number guessing game.
   */
  public static void play()
  {
    final Random random = new Random();
    final int number = 
      random.nextInt(MAX_NUMBER - MIN_NUMBER + 1) + MIN_NUMBER;
    int guess = 0;
    int tries = 0;
    
    while (guess != number)
    {
      final String response = 
        JOptionPane.showInputDialog("Guess my integer between " + MIN_NUMBER +
                                    AND_SEP + MAX_NUMBER);
      tries = tries + 1;
      try
      {
        guess = Integer.parseInt(response);
        if (MIN_NUMBER <= guess && guess <= MAX_NUMBER)
        {
          showHint(guess, number);
        }
        else
        {
          JOptionPane.showMessageDialog(null, guess + " is not between " + 
                                        MIN_NUMBER + AND_SEP + MAX_NUMBER + "!");
        }
      }
      catch (final NumberFormatException e)
      {
        JOptionPane.showMessageDialog(null, response + " is not a integer!");
      }      
    }
    
    JOptionPane.showMessageDialog(null, "Congratulations, it only took you " + 
                                  tries + " tries!");
  }
  
  /**
   * Displays an option pane with each of the 5 possible icons.
   * 
   * @param the_args Command line arguments, ignored.
   */
  public static void main(final String... the_args)
  {
    final int response =
      JOptionPane.showConfirmDialog(null, "Would you like to play a game?",
                                    "Number Game", 
                                    JOptionPane.YES_NO_OPTION,
                                    JOptionPane.QUESTION_MESSAGE);
    if (response == JOptionPane.YES_OPTION)
    {
      play();
    }
    else
    {
      JOptionPane.showMessageDialog(null, "Oh well... maybe next time.",
                                    "Oh Well!", JOptionPane.WARNING_MESSAGE);
    }
  }
}
