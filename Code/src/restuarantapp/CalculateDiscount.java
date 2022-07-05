/**
 * Calculates and returns the discounted price of an order item
 * 
 * @author Nandini Verma
 *
 */

package restuarantapp;

public interface CalculateDiscount  
{   
   public void setDiscountRate(float discountRate);
   
   public float getDiscountRate();
   
   public float calculateDiscount(float itemprice);
}

 
