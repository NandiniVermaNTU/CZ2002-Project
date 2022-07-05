/**
 * Interface for calculating tax
 * @author Pua Yong Hao
 * @version 1.0
 */
package restuarantapp;

public interface CalculateTax {
	public float calculateTax(float price);

	public float getTaxRate();

	public void setTaxRate(float taxRate);
}
