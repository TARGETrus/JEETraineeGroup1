package model;

import javax.persistence.*;

@Entity
@Table(name="filters", schema="web_app_db")
public class Filter {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="filter_id", length=12, nullable=false, unique=true)
    private int    filterID;

    @Column(name="filter_name", length=255, nullable=false)
    private String filterName;

    @Column(name="filter_data", length=255, nullable=false)
    private String filterData;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Filter() {}

    public int getFilterID() {
        return filterID;
    }

    public void setFilterID(int filterID) {
        this.filterID = filterID;
    }

    public String getFilterName() {
        return filterName;
    }

    public void setFilterName(String filterName) {
        this.filterName = filterName;
    }

    public String getFilterData() {
        return filterData;
    }

    public void setFilterData(String filterData) {
        this.filterData = filterData;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString(){

        return "\nFilter data: \n" + "ID: " + filterID + ", Title: " + filterName +
                ", Filter data: " + filterData + ", User ID: " + user.getUserID() + "\n";

    }

}
