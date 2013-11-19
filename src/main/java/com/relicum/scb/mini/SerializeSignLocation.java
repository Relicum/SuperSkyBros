package com.relicum.scb.mini;

/**
 * The type SerializeSignLocation.
 */
public class SerializeSignLocation {

    /**
     * The Location the sign is at
     */
    private SerializedLocation location;

    /**
     * The Xml node representing the sign location.
     */
    private String XmlLocation;


    /**
     * Instantiates a new Serialize Sign Location Service
     *
     * @param location the location
     */
    public SerializeSignLocation(SerializedLocation location) {
        this.location = location;
    }


    /**
     * Gets location.
     *
     * @return the location
     */
    public SerializedLocation getLocation() {
        return location;
    }


    /**
     * Gets xml location.
     *
     * @return the xml location
     */
    public String getXmlLocation() {
        return XmlLocation;
    }
}
