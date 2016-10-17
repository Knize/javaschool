package ru.knize.hyperloop.DTO;

import ru.knize.hyperloop.entities.BranchEntity;

/**
 * Created by knize on 12.10.16.
 */
public class BranchDTO {
    private int id;
    private String name;

    public BranchDTO() {
    }

    @Override
    public String toString() {
        return "BranchDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static BranchDTO fromBranchEntity(BranchEntity be) {
        BranchDTO branchDTO = new BranchDTO();
        branchDTO.setId(be.getId());
        branchDTO.setName(be.getName());
        return branchDTO;
    }

}
