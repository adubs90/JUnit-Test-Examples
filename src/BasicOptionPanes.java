/*
 * Option Panes
 * Autumn 2011 TCSS 305
 */

import javax.swing.JOptionPane;

/**
 * Displays an option pane with each of the 5 possible icons.
 * 
 * @author Daniel M. Zimmerman
 * @version 2011-04-12
 */
public final class BasicOptionPanes
{
  /**
   * The option pane title.
   */
  private static final String TITLE = "Greeting";
  
  /**
   * The option pane text.
   */
  private static final String TEXT = "Hello, World!";
  
  /**
   * Private constructor, to prevent instantiation.
   */
  private BasicOptionPanes()
  {
    // do nothing
  }
  
  /**
   * Displays an option pane with each of the 5 possible icons.
   * 
   * @param the_args Command line arguments, ignored.
   */
  public static void main(final String... the_args)
  {
    JOptionPane.showMessageDialog(null, TEXT, TITLE, JOptionPane.PLAIN_MESSAGE);
    JOptionPane.showMessageDialog(null, TEXT, TITLE, JOptionPane.INFORMATION_MESSAGE);
    JOptionPane.showMessageDialog(null, TEXT, TITLE, JOptionPane.QUESTION_MESSAGE);
    JOptionPane.showMessageDialog(null, TEXT, TITLE, JOptionPane.ERROR_MESSAGE);
    JOptionPane.showMessageDialog(null, TEXT, TITLE, JOptionPane.WARNING_MESSAGE);
  }
}
