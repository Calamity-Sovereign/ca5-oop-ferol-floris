
package projectPackage.DTOs;

/**                                                     OOP Feb 2022
 *  Data Transfer Object (DTO)
 *
 * This POJO (Plain Old Java Object) is called the Data Transfer Object (DTO).
 * (or, alternatively, the Model Object or the Value Object).
 * It is used to transfer data between the DAO and the Business Objects.
 * Here, it represents a row of data from the User database table.
 * The DAO creates and populates a User object (DTO) with data retrieved from
 * the resultSet and passes the User object to the Business Layer.
 *
 * Collections of DTOs( e.g. ArrayList<User> ) may also be passed
 * between the Data Access Layer (DAOs) and the Business Layer objects.
 */

public class Team
{
    private int id;
    private String teamCode;
    private String teamName;
    private String region;
    private String division;
    private int foundedIn;

    public Team(int Id, String teamCode, String teamName, String region, String division, int founded_in)
    {
        this.id = Id;
        this.teamCode = teamCode;
        this.teamName = teamName;
        this.region = region;
        this.division = division;
        this.foundedIn = foundedIn;
    }

    public Team(String teamCode, String teamName, String region, String division, int founded_in)
    {
        this.id = 0;
        this.teamCode = teamCode;
        this.teamName = teamName;
        this.region = region;
        this.division = division;
        this.foundedIn = foundedIn;
    }

    public Team()
    {
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String teamCode()
    {
        return teamCode;
    }

    public void teamCode(String teamCode)
    {
        this.teamCode = teamCode;
    }

    public String teamName()
    {
        return teamName;
    }

    public void setteamName(String teamName)
    {
        this.teamName = teamName;
    }

    public String getregion()
    {
        return region;
    }

    public void setregion(String region)
    {
        this.region = region;
    }

    public String getDivision()
    {
        return division;
    }

    public void setDivision(String division)
    {
        this.division = division;
    }

    public int getfounded_in()
    {
        return foundedIn;
    }

    public void setfounded_in(String founded_in) {this.foundedIn = foundedIn;

    }


    @Override
    public String toString()
    {
        return "team{" + "id=" + id + ", teamCode=" + teamCode + ", teamName=" +
                teamName + ", region=" + region + ", division=" + division + ", foundedIn=" + foundedIn + '}';
    }

}
