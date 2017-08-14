package domain.models.json.ad;

import domain.models.json.JsonModel;

/**
 * Created by Someone on 15.01.2017.
 */
public class NewAdJsonModel extends JsonModel {
    private String title;
    private String rubric;
    private String description;
    private String locationCity;
    private String locationDistrict;
    private String contactPerson;
    private String condition;
    private String privateOrBusyness;
    private String price;
    private String model;

    public String getTitle() {
        return title;
    }

    public String getCategory() {
        return rubric;
    }

    public String getDescription() {
        return description;
    }

    public String getLocationCity() {
        return locationCity;
    }
    public String getLocationDistrict() {
        return locationDistrict;
    }

    public String getContactPerson() {
        return contactPerson;
    }

    public String getCondition() {
        return condition;
    }

    public String getPrivateOrBusyness() {
        return privateOrBusyness;
    }

    public String getPrice() {
        return price;
    }

    public String getModel() {
        return model;
    }
}
