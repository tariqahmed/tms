package tms

class PositionSkill
{
    Integer positionLevel
    static belongsTo = [position:Position]
    static hasOne = [skill:Skill]

    static constraints =
    {
    }
}
