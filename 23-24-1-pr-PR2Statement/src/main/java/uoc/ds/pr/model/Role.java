package uoc.ds.pr.model;

public class Role {
    private String roleId;
    private String description;

    // Constructor
    public Role(String roleId, String description) {
        this.roleId = roleId;
        this.description = description;
    }

    // Getters
    public String getRoleId() {
        return roleId;
    }

    public String getDescription() {
        return description;
    }

    // Setters
    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }
    public void setDescription(String description) {
        this.description = description;
    }
}
