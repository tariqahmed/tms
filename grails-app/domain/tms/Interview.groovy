package tms

class Interview
{
    static belongsTo =  [candidate : Candidate]

    Date interviewDate

    static constraints =
    {
        interviewDate(nullable: false)
    }
}
