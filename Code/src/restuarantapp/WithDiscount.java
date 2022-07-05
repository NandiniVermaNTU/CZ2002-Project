/**
 * Implements interface CalculateDiscount to calculate the price of an order item with membership discount 
 *
 * @author Nandini Verma
 * 
 */

package restuarantapp;

public class WithDiscount implements CalculateDiscount
{

   /**
    * Stores the discount rate when membership discount is offered (default value is 0.25)
    */   
    private float discountRate = 0.25f;  
    /**
    * 
    * @param discountRate  The rate of discount offered by the restaurant to a member on an order item
    *
    */   
  
  
    /**
    * Set Discount rate
    * 
    * @param discountRate Discount rate that should be offered
    */
    @Override  
   public void setDiscountRate(float discountRate)
   {
       this.discountRate = discountRate;
   }
   
   
   /**
   * Return Discount rate
   * 
   * @return discountRate
   */
   @Override
   public float getDiscountRate()
   {
       return discountRate;
   }

    /**
    * Return the price of the order item after calculating discount
    * 
    * @param itemprice The price of the ordered item
    */  
   @Override
   public float calculateDiscount(float itemprice)
   {
       return (1-discountRate)*itemprice;
   }
}


