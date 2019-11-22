package com.sobri.lib;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class Paginator {

    protected int itemsPerPage;
    protected int totalItems;
    protected int currentPage;
    protected int prevPage;
    protected int nextPage;
    protected int totalPages;
    protected List<Integer> range;
    protected int start_range;
    protected int midRange;
    protected int end_range;
    protected String paginator;
    protected String queryString;
    protected int limit;
    protected String url;

    public Paginator(String url, int totalItems, int limit, int currentPage) {
        this.url = (!url.endsWith("/")) ? url + "/" : url;
        this.totalItems = totalItems;
        this.limit = limit;
        this.itemsPerPage = limit;
        this.currentPage = currentPage;
        this.midRange = 7;
        this.init();
        this.paginate();
    }

    protected void init() {
        if (this.itemsPerPage <= 0) {
            this.itemsPerPage = this.limit;
        }

        this.totalPages = (int) Math.round(Math.ceil(this.totalItems / (this.itemsPerPage * 1.0)));

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

            this.start_range = this.currentPage - (int) Math.round(Math.floor(this.midRange / 2.0));
            this.end_range = this.currentPage + (int) Math.round(Math.floor(this.midRange / 2.0));

            if (this.start_range <= 0) {
                this.start_range = 1;
                this.end_range += Math.abs(this.start_range) + 1;
            }

            if (this.end_range > this.totalPages) {
                this.start_range -= this.end_range - this.totalPages;
                this.end_range = this.totalPages;
            }

            this.range = this.range(this.start_range, this.end_range, 1);

            for (int i = 1; i <= this.totalPages; i++) {
                if (this.range.get(0) > 2 && i == this.range.get(0)) {
                    this.paginator += "<li class='page-item disabled'><a class='page-link'>...</a></li>";
                }

                if (i == 1 || i == this.totalPages || this.range.contains(i)) {
                    if (i == this.currentPage) {
                        this.paginator += "<li class='page-item active'><a class='page-link' title='Go to page " + i + " of " + this.totalPages + "' href='#'>" + i + "</a></li>";
                    } else {
                        this.paginator += "<li class='page-item'><a class='page-link' title='Go to page " + i + " of " + this.totalPages + "' href='" + this.url + i + "'>" + i + "</a></li>";
                    }
                }

                if (this.range.get(this.midRange - 2) < this.totalPages - 1 && i == this.range.get(this.midRange - 2)) {
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


    public String itemPerPage() {
        String items = "";
        List<Integer> ipp_array = new ArrayList<>(List.of(10, 25, 50, 100));

        if (!ipp_array.contains(this.limit)) {
            ipp_array.add(this.limit);
            Collections.sort(ipp_array);
        }

        if (this.totalItems > this.limit) {

            for (int ipp_opt : ipp_array) {
                if (ipp_opt == this.itemsPerPage) {
                    items += "<option selected value='" + ipp_opt + "'>" + ipp_opt + "</option>";
                } else {
                    items += "<option value='" + ipp_opt + "'>" + ipp_opt + "</option>";
                }
            }

            return "<span class='paginate'>Items per page:</span><select class='paginate' onchange='window.location=" + "'" + "?page=1&ipp='+this[this.selectedIndex].value+'" + this.queryString + "';return false" + "'>" + items + "</select>";
        }

        return null;
    }

    public String jumpMenu() {
        if (this.totalPages > 1) {
            String option = "";

            for (int i = 1; i <= this.totalPages; i++) {
                if (i == this.currentPage) {
                    option += "<option value='" + i + "' selected>" + i + "</option>";
                } else {
                    option += "<option value='" + i + "'>" + i + "</option>";
                }
            }

            return "<span class='paginate'>Page:</span><select class='paginate' onchange=\"window.location=" + "'" + this.url + "?page='+this[this.selectedIndex].value+'&ipp=" + this.itemsPerPage + this.queryString + "';return false" + "'>" + option + "</select>";
        }

        return null;
    }

    public String paginator() {
        return this.paginator;
    }

    public int page() {
        return (this.currentPage - 1) * this.itemsPerPage;
    }

    public int perPage() {
        return this.itemsPerPage;
    }

    protected List<Integer> range(int start, int end, int increment) {
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
