/*
 * 2-Dimensional Points
 * Autumn 2011 TCSS305
 */

package geometry;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Test class for Point2D. This is a very rudimentary test
 * class, which only has constructor tests in it; a full
 * test class would test all of Point2D's methods.
 * 
 * @author TCSS305 Class
 * @version 2011-10-13
 */
public class Point2DTest
{
  /**
   * The margin of error for floating point comparisons.
   */
  public static final Double THRESHOLD = 10E-8;
  
  /**
   * Constructs Point2D objects using Cartesian coordinates.
   */
  @Test
  public void constructingCartesian()
  {
    final double[] x = {2, -2};
    final double[] y = {2, -2};
    final double[] rho = {2 * Math.sqrt(2), 2 * Math.sqrt(2)};
    final double[] theta = {Math.PI / 4, -3 * Math.PI / 4};
    
    for (int i = 0; i < x.length; i++) 
    {
      final Point2D p = new Point2D(x[i], y[i], 
                                    CoordinateSystem.CARTESIAN);
      assertEquals("x-coordinate", x[i], p.x(), 0);
      assertEquals("y-coordinate", y[i], p.y(), 0);
      assertEquals("rho", rho[i], p.rho(), THRESHOLD);
      assertEquals("theta", theta[i], p.theta(), THRESHOLD);
    }
  }
  
  /**
   * Constructs Point2D objects using polar coordinates.
   */
  @Test
  public void constructingPolar()
  {
    final double[] x = {2, -2};
    final double[] y = {2, -2};
    final double[] rho = {2 * Math.sqrt(2), 2 * Math.sqrt(2)};
    final double[] theta = {Math.PI / 4, -3 * Math.PI / 4};
    
    for (int i = 0; i < rho.length; i++) 
    {
      final Point2D p = new Point2D(rho[i], theta[i], 
                                    CoordinateSystem.POLAR);
      assertEquals("x-coordinate", x[i], p.x(), THRESHOLD);
      assertEquals("y-coordinate", y[i], p.y(), THRESHOLD);
      assertEquals("rho", rho[i], p.rho(), 0);
      assertEquals("theta", theta[i], p.theta(), 0);
    }
  }
  
  /**
   * Tests attempt to use a null coordinate system.
   */
  @Test(expected = NullPointerException.class)
  public void constructingNothingUseful()
  {
    new Point2D(0, 0, null);
  }
}
