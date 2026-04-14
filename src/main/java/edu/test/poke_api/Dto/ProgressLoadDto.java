package edu.test.poke_api.Dto;

public class ProgressLoadDto {
    long totalCount;
    long dataLoaded;
    long percentage;

    public ProgressLoadDto() {}

    public ProgressLoadDto(long count, long currentLoad) {
        totalCount = count;
        dataLoaded = currentLoad;
        percentage =  count > 0 ? currentLoad * 100 / count : 0;
    }

    public long getTotalCount() {
        return totalCount;
    }

    public long getDataLoaded() {
        return dataLoaded;
    }

    public long getPercentage() {
        return percentage;
    }

    
}
