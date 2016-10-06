package ru.knize.hyperloop.entities;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Created by knize on 06.10.16.
 */
@Entity
@Table(name = "Edge")
public class EdgeEntity {
    private int id;

    @Id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private BranchEntity branch;

    @ManyToOne
    public BranchEntity getBranch() {
        return branch;
    }

    public void setBranch(BranchEntity branch) {
        this.branch = branch;
    }

    private StationEntity fromStation;

    @ManyToOne
    public StationEntity getFromStation() {
        return fromStation;
    }

    public void setFromStation(StationEntity fromStation) {
        this.fromStation = fromStation;
    }

    private StationEntity toStation;

    @ManyToOne
    public StationEntity getToStation() {
        return toStation;
    }

    public void setToStation(StationEntity toStation) {
        this.toStation = toStation;
    }
}
