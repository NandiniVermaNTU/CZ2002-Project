/**
 * Implements interface, CalculateTax to calculate price with GST
 * @author Pua Yong Hao
 * @version 1.1
 */
package restuarantapp;

public class GST implements CalculateTax {

	/**
	 * Tax rate of GST
	 */
	private float taxRate = 0.07f;

	/**
	 * Return the price after adding GST
	 * 
	 * @param price The price of ordered items
	 */
	@Override
	public float calculateTax(float price) {
		// TODO Auto-generated method stub
		return price * (1 + taxRate);
	}

	/**
	 * Return GST
	 * 
	 * @return taxRate
	 */
	@Override
	public float getTaxRate() {
		// TODO Auto-generated method stub
		return taxRate;
	}

	/**
	 * Set GST
	 * 
	 * @param taxRate Tax rate that GST should be set to
	 */
	@Override
	public void setTaxRate(float taxRate) {
		// TODO Auto-generated method stub
		this.taxRate = taxRate;
	}

}
