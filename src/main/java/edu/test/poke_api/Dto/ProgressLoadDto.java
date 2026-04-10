package edu.test.poke_api.Dto;

public class ProgressLoadDto {
    int totalCount;
    int dataLoaded;
    int percentage;

    public ProgressLoadDto() {}

    public ProgressLoadDto(int count, int currentLoad) {
        totalCount = count;
        dataLoaded = currentLoad;
        percentage =  count > 0 ? currentLoad * 100 / count : 0;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public int getDataLoaded() {
        return dataLoaded;
    }

    public int getPercentage() {
        return percentage;
    }

    
}
