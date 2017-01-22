package alerts.bet.sbhacksiii.com.alerts;

/**
 * Created by bijanfazeli on 1/21/17.
 */

public class User {
    // Private fields
    private String fName, lName, email;

    /**
     * Default constructor
     * @param fName The first name of the user
     * @param lName The last name of the user
     * @param email The email of the user
     */
    public User(String fName, String lName, String email) {
        this.fName = fName;
        this.lName = lName;
        this.email = email;
    }

    /**
     * Returns the user's name
     *
     * @return User's first name.
     */
    public String getfName() {
        return fName;
    }

    /**
     * Set's the first name of the user
     *
     * @param fName A first name.
     */
    public void setfName(String fName) {
        this.fName = fName;
    }

    /**
     * Returns the last name of the user
     * @return A last name.
     */
    public String getlName() {
        return lName;
    }

    /**
     * Sets the last name of the user
     *
     * @param lName A last name to set.
     */
    public void setlName(String lName) {
        this.lName = lName;
    }

    /**
     * Returns the email of the user
     *
     * @return The user's email.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email of the user
     *
     * @param email An email to set for the user.
     */
    public void setEmail(String email) {
        this.email = email;
    }
}
