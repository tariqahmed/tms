package tms

class Interview
{
    static belongsTo =  [candidate : Candidate]
    static hasMany  = [interviewResults : InterviewResults]

    Date interviewDate

    static constraints =
    {
        interviewDate(nullable: false)
    }
}
