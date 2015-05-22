package ru.sav.ws.resourcemapping;

public class Mapping {
    private String resourceID;
    private String hash;

    Mapping() {}

    Mapping(String resourceID, String hash) {
        this.resourceID = resourceID;
        this.hash = hash;
    }

    public String getResourceID() {
        return resourceID;
    }

    public void setResourceID(String resourceID) {
        this.resourceID = resourceID;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }
}
