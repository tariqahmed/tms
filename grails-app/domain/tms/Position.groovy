package tms

class Position
{
    String positionName
    static hasMany  =  [skills : PositionSkill]


    static constraints =
    {
    }
}
