/*
 * 2-Dimensional Points
 * Autumn 2011 TCSS305
 */

package geometry;

/**
 * Represents a point in the 2-dimensional plane.
 * 
 * @author Autumn 2011 TCSS305B Class
 * @version 6 October 2011
 */
public class Point2D
{
  /**
   * The origin.
   */
  public static final Point2D ORIGIN =
    new Point2D(0, 0, CoordinateSystem.CARTESIAN);
  
  /**
   * The threshold for equivalence of coordinates.
   */
  public static final double EQUIVALENCE_THRESHOLD = 10E-6;
  
  /**
   * The x-coordinate.
   */
  private final double my_x;
  
  /**
   * The y-coordinate.
   */
  private final double my_y;
  
  /**
   * The rho.
   */
  private final double my_rho;
  
  /**
   * The theta.
   */
  private final double my_theta;
  
  /**
   * Construct a point with the specified coordinates.
   * 
   * @param coord_1 The first coordinate (x or rho).
   * @param coord_2 The second coordinate (y or theta)
   * @param the_system The coordinate system to use.
   * @exception NullPointerException if you pass in a null
   * coordinate system.
   * @exception IllegalArgumentException if you pass in a 
   * unknown coordinate system.
   */
  public Point2D(final double coord_1, final double coord_2,
                 final CoordinateSystem the_system)
    throws IllegalArgumentException, NullPointerException
  {
    switch (the_system)
    {
      case CARTESIAN:
        my_x = coord_1;
        my_y = coord_2;
        my_rho = Math.sqrt(my_x * my_x + my_y * my_y);
        my_theta = Math.atan2(my_y, my_x);
        break;
        
      case POLAR:
        checkRanges(coord_1, coord_2);
        my_rho = coord_1;
        my_theta = coord_2;
        my_x = my_rho * Math.cos(my_theta);
        my_y = my_rho * Math.sin(my_theta);
        break;
        
      default:
        throw new IllegalArgumentException("unknown coordinate system: " + the_system);
    }
  }
  
  /**
   * Checks to see if the specified rho and theta are in legitimate
   * ranges.
   * 
   * @param the_rho The rho.
   * @param the_theta The theta.
   * @exception IllegalArgumentException if one or both coordinates
   * are out of range.
   */
  private void checkRanges(final double the_rho, final double the_theta)
    throws IllegalArgumentException
  {
    if (the_rho < 0)
    {
      throw new IllegalArgumentException("negative rho");
    }
    if (the_theta < -Math.PI || Math.PI < the_theta)
    {
      throw new IllegalArgumentException("theta out of range");
    }
  }
  
  /**
   * Checks to see if two numbers are within a threshold range
   * of each other.
   * 
   * @param num_1 The first number.
   * @param num_2 The second number.
   * @return true if the numbers are within EQUIVALENCE_THRESHOLD of
   * each other.
   */
  private boolean withinThreshold(final double num_1, final double num_2)
  {
    return Math.abs(num_1 - num_2) < EQUIVALENCE_THRESHOLD;
  }
  
  /**
   * @return What is your x-coordinate?
   */
  public double x()
  {
    return my_x;
  }

  /**
   * @return What is your y-coordinate?
   */
  public double y()
  {
    return my_y;
  }
  
  /**
   * @return What is your rho?
   */
  public double rho()
  {
    return my_rho;
  }

  /**
   * @return What is your theta?
   */
  public double theta()
  {
    return my_theta;
  }
  
//    What is your string representation?

  /**
   * Calculates the distance to the specified point.
   * 
   * @param the_point The other point.
   * @return the distance.
   */
  public double distance(final Point2D the_point)
  {
    final double dx = x() - the_point.x();
    final double dy = y() - the_point.y();
    return Math.sqrt(dx * dx + dy * dy);
  }
  
  /**
   * Translates this point by (the_x, the_y).
   * 
   * @param the_x The x to translate by.
   * @param the_y The y to translate by.
   * @return the resulting point.
   */
  public Point2D translate(final double the_x, final double the_y)
  {
    return new Point2D(my_x + the_x, my_y + the_y, CoordinateSystem.CARTESIAN);  
  }
  
  /** 
   * Rotates this point by the_theta.
   * 
   * @param the_theta The angle to rotate by.
   * @return the resulting point.
   */
  public Point2D rotate(final double the_theta)
  {
    double result_theta = my_theta + the_theta;
    
    while (Math.PI < result_theta) 
    {
      result_theta = result_theta - 2 * Math.PI;
    }
    while (result_theta < -Math.PI)
    {
      result_theta = result_theta + 2 * Math.PI;
    }
    
    return new Point2D(my_rho, result_theta, CoordinateSystem.POLAR);
  }
  
  /**
   * Scale this point by the_factor.
   * 
   * @param the_factor The scaling factor.
   * @return the resulting point.
   */
  public Point2D scale(final double the_factor)
  {
    if (the_factor >= 0)
    {
      return new Point2D(rho() * the_factor, theta(), CoordinateSystem.POLAR);
    }
    else
    {
      throw new IllegalArgumentException("negative scaling factor");
    }
  }
  
  /**
   * {@inheritDoc}
   */
  public boolean equals(final Object the_other)
  {
    boolean result = false;
    if (this == the_other)
    {
      result = true;
    }
    else if (the_other != null && the_other.getClass() == getClass())
    {
      final Point2D point = (Point2D) the_other;
      result = withinThreshold(my_x, point.my_x) && withinThreshold(my_y, point.my_y);
    }
    
    return result;
  }
  
  /**
   * {@inheritDoc}
   */
  public int hashCode()
  {
    return toString().hashCode();
  }
  
  /**
   * {@inheritDoc}
   */
  public String toString()
  {
    final StringBuilder sb = new StringBuilder();
    sb.append('(');
    sb.append(my_x);
    sb.append(", ");
    sb.append(my_y);
    sb.append(')');
    return sb.toString();
  }
  
  // Constraints:
  //
  // The polar and Cartesian coordinates must represent the same point 
  // to within a small margin of error.
  // Theta must range from -pi to pi.
  // Rho must be non-negative.
  // The origin is always equivalent to (0, 0).
  // The x and y coordinates always range from Double.MIN_VALUE 
  // to Double.MAX_VALUE.
}
