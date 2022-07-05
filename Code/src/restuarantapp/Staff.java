/**
 * Contains all the information about the staff of the restaurant who created the order.
 * 
 * @author Nandini Verma
 *
 */

package restuarantapp;

public class Staff 
{
    
    /**
    * Stores the name of the staff member
    */
    private String name;
    
    
    /**
    * Stores the gender of the staff member
    */
    private gender gender;
    
    
    /**
    * Stores the employee ID of the staff member
    */
    private int employeeID;
    
    
    /**
    * Stores the Job Title of the staff member
    */
    private jobTitle jobTitle;
    
    /**
    * Store information of staff who created the order
    * 
    * @param name        The name of the staff who created this order
    * @param employeeID  The Employee ID of the staff who created this order
    * @param jobTitle    The Job Title of the staff who created this order
    * @param gender      The gender of the staff who created this order
    */
        
    public Staff(String name, int employeeID, jobTitle jobTitle, gender gender)
    {
        this.name = name;
        this.employeeID = employeeID;
        this.jobTitle = jobTitle;
        this.gender = gender;
    }
    
    /**
    * Set Staff name
    * 
    * @param name Name of the staff of the restauarant
    */
    public void setName(String name)
    {
        this.name = name;
    }
    
    /**
    * Set Staff Gender 
    * 
    * @param gender Gender of the staff of the restauarant
    */
    public void setGender(gender gender)
    {
        this.gender = gender;
    }
    
    /**
    * Set Employee name
    *  
    * @param employeeID Employee ID of the staff of the restauarant
    */
    public void setEmployeeID(int employeeID)
    {
        this.employeeID = employeeID;
    }
    
    /**
    * Set Job Title of the Staff
    * 
    * @param jobTitle Job Title of the staff of the restauarant
    */
    public void setJobTitle(jobTitle jobTitle)
    {
        this.jobTitle = jobTitle;
    }
    
    /**
    * Return name of the staff of the restauarant
    * 
    * @return name
    */
    public String getName()
    {
        return name;
    }
   
     /**
     * Return gender of the staff of the restauarant
     * 
     * @return gender
     */
    public gender getGender()
    {
        return gender;
    }
    
     /**
     * Return Employee ID of the staff of the restauarant
     * 
     * @return employeeID
     */
    public int getEmployeeID()
    {
        return employeeID;
    }
    
     /** 
     * Return Job Title of the staff of the restauarant
     * 
     * @return jobTitle
     */
    public jobTitle getJobTitle()
    {
        return jobTitle;
    }
    
}
