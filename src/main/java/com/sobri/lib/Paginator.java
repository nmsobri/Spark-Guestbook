package com.sobri.lib;

import java.util.List;
import java.util.ArrayList;

public class Paginator {

    protected int totalItems;
    protected int currentPage;
    protected int prevPage;
    protected int nextPage;
    protected int totalPages;
    protected List<Integer> range;
    protected int startRange;
    protected int midRange;
    protected int endRange;
    protected String paginator;
    protected int limit;
    protected String url;

    public Paginator(String url, int totalItems, int limit, int currentPage) {
        this.url = (!url.endsWith("/")) ? url + "/" : url;
        this.totalItems = totalItems;
        this.limit = limit;
        this.currentPage = currentPage;
        this.midRange = 7;
        this.init();
        this.paginate();
    }

    protected void init() {
        this.totalPages = (int) Math.round(Math.ceil(this.totalItems / (this.limit * 1.0)));

        if (this.currentPage < 1) {
            this.currentPage = 1;
        }

        if (this.currentPage > this.totalPages && this.totalPages > 0) {
            this.currentPage = this.totalPages;
        }

        this.prevPage = this.currentPage - 1;
        this.nextPage = this.currentPage + 1;
    }

    protected void paginate() {
        if (this.totalPages > 1) {
            if (this.currentPage != 1 && this.totalItems >= 1) {
                this.paginator = "<li class'page-item'><a class='page-link' href='" + this.url + this.prevPage + "'>&laquo;Previous</a></li>";
            } else {
                this.paginator = "<li class='page-item disabled'><a class='page-link'>&laquo;Previous</a></li>";
            }

            this.range = this.range();

            for (int i = 1; i <= this.totalPages; i++) {
                if (this.range.get(0) > 2 && i == this.range.get(0)) {
                    this.paginator += "<li class='page-item disabled'><a class='page-link'>...</a></li>";
                }

                if (i == 1 || i == this.totalPages || this.range.contains(i)) {
                    if (i == this.currentPage) {
                        this.paginator += "<li class='page-item active'><a class='page-link' title='Go to page " + i + " of " + this.totalPages + "' href='" + this.url + i + "'>" + i + "</a></li>";
                    } else {
                        this.paginator += "<li class='page-item'><a class='page-link' title='Go to page " + i + " of " + this.totalPages + "' href='" + this.url + i + "'>" + i + "</a></li>";
                    }
                }

                if (this.range.get(this.midRange - 1) < this.totalPages - 1 && i == this.range.get(this.midRange - 1)) {
                    this.paginator += "<li class='page-item disabled'><a class='page-link'>...</a></li>";
                }
            }

            if (this.currentPage != this.totalPages && this.totalItems >= 1) {
                this.paginator += "<li class='page-item'><a class='page-link' href='" + this.url + this.nextPage + "'>Next&raquo;</a></li>";
            } else {
                this.paginator += "<li class='page-item disabled'><a class='page-link'>Next&raquo;</a></li>";
            }
        }
    }

    protected List<Integer> range() {
        this.startRange = this.currentPage - ((int) Math.round(Math.floor(this.midRange / 2.0)));
        this.endRange = this.currentPage + ((int) Math.round(Math.floor(this.midRange / 2.0)));

        if (this.startRange <= 0) {
            this.startRange = 1;
            this.endRange = this.midRange;
        }

        if (this.endRange > this.totalPages) {
            this.startRange -= this.endRange - this.totalPages;
            this.endRange = this.totalPages;
        }

        return this.createRange(this.startRange, this.endRange, 1);
    }

    public String paginator() {
        return this.paginator;
    }

    public int page() {
        return (this.currentPage - 1) * this.limit;
    }

    public int limit() {
        return this.limit;
    }

    protected List<Integer> createRange(int start, int end, int increment) {
        if (start < end && increment < 0) throw new IllegalArgumentException();
        if (start > end && increment > 0) throw new IllegalArgumentException();

        List<Integer> values = new ArrayList<>();
        boolean reverse = start > end;

        for (int i = start, index = 0; reverse ? (i >= end) : (i <= end); i += increment, ++index) {
            values.add(i);
        }

        return values;
    }
}
