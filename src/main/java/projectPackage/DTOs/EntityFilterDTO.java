package projectPackage.DTOs;

public class EntityFilterDTO {
    private int year;
    private int minYear;
    private int maxYear;

    public EntityFilterDTO(int year){
        this.year = year;
        this.minYear = minYear;
        this.maxYear = maxYear;
    }

    public int getYear(){
        return year;
    }
    public int getMinYear(){
        return minYear;
    }
    public int getMaxYear(){
        return maxYear;
    }
}
