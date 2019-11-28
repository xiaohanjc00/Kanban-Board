public class Member{
    private String username;
    private String firstName;
    private String lastName;
    private String bio;

    public Member(String usernameIn, String firstNameIn, String lastNameIn){
        username = usernameIn;
        firstName = firstNameIn;
        lastName = lastNameIn;
        bio = null;
    }

    public void setBio(String bioIn){
        bio = bioIn;
    }

    public void setFirstName(String firstNameIn) {
        firstName = firstNameIn;
    }

    public String getBio() {
        return bio;
    }

    public void setLastName(String lastNameIn) {
        lastName = lastNameIn;
    }

    public String getName() {
        return firstName + " " + lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getUsername() {
        return username;
    }
}
