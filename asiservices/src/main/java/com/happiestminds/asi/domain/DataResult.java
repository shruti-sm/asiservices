package com.happiestminds.asi.domain;

import java.util.List;

/**
 * 
 * @author shruti.mishra
 *
 * @param <T>
 */
public class DataResult<T> {

    private List<T> results;
    private int count;
    private int pageSize = 20;
    private int currentPage;

    public List<T> getResults() {
        return results;
    }

    public void setResults(List<T> results) {
        this.results = results;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getTotalPages() {
        return (int) Math.ceil((double) count / (double) pageSize);
    }

    public int getPageSize() {
        return pageSize;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }
}
