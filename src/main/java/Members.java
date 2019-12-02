public class Members{
    private String username;
    private String firstName;
    private String lastName;
    private String bio;

    /*
    constructor to create members. 
    @Param usernameIn input for the username
    @Param firstNameIn input for first name
    @Param lastNameIn input for last name
    sets bio field to null
    */
    public Members(String usernameIn, String firstNameIn, String lastNameIn){
        username = usernameIn;
        firstName = firstNameIn;
        lastName = lastNameIn;
        bio = null;
    }

    /*
    @Param bioIn - input for new bio
    sets bio to input.
    */
    public void setBio(String bioIn){
        bio = bioIn;
    }

     /*
    @Param firstNameIn - input for new first name
    sets first name to input.
    */
   
    public void setFirstName(String firstNameIn) {
        firstName = firstNameIn;
    }

    /*
    Returns bio
    */
    public String getBio() {
        return bio;
    }

    /*
    @Param lastNameIn - input for new last name
    sets last name to input.
    */
    public void setLastName(String lastNameIn) {
        lastName = lastNameIn;
    }

    /*
    Returns first name and full name concatenated
    */
    public String getName() {
        return firstName + " " + lastName;
    }

    /*
    Returns first name
    */
    public String getFirstName() {
        return firstName;
    }

    /*
    Returns last name
    */
    public String getLastName() {
        return lastName;
    }

    /*
    Returns username
    */
    public String getUsername() {
        return username;
    }
}
