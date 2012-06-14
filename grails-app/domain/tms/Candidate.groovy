package tms

class Candidate
{
    static hasMany = [interviews : Interview]

    String firstName
    String lastName
    String email
    String homePhone
    String cellPhone
    String address
    String city
    String stateCode
    String zipCode
    Date dateCreated
    Date lastUpdated

    static constraints =
    {
        firstName(blank: false)
        lastName(blank: false)
        email(email: true, blank: false)
        homePhone()
        cellPhone()
        address(blank:false)
        city(blank: false)
        stateCode(minSize: 2, blank: false)
        zipCode(minSize: 5, blank: false)
    }
}
