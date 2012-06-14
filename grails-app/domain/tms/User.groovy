package tms

class User
{

    String firstName
    String lastName
    String email
    String password
    Date dateCreated
    Date lastUpdated

    static constraints =
    {
        firstName(blank: false, minSize: 2, maxSize: 15)
        lastName(blank: false, minSize:  2, maxSize: 15)
        email(email: true, blank: false, minSize: 5, maxSize: 20)
        password(blank: false, minSize: 1, maxSize: 15)
    }
}
