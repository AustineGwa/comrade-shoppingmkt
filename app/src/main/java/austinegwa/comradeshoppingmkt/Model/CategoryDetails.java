package austinegwa.comradeshoppingmkt.Model;

/**
 * Created by gwaza on 1/21/2018.
 */

public class CategoryDetails {
    private String detailsName;
    private String detailsDescription;
    private String categoryID;
    private String detailsimage;

    public CategoryDetails() {
    }

    public CategoryDetails(String detailsName, String detailsDescription, String categoryID, String detailsimage) {
        this.detailsName = detailsName;
        this.detailsDescription = detailsDescription;
        this.categoryID = categoryID;
        this.detailsimage = detailsimage;
    }

    public String getDetailsName() {
        return detailsName;
    }

    public void setDetailsName(String detailsName) {
        this.detailsName = detailsName;
    }

    public String getDetailsDescription() {
        return detailsDescription;
    }

    public void setDetailsDescription(String detailsDescription) {
        this.detailsDescription = detailsDescription;
    }

    public String getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(String categoryID) {
        this.categoryID = categoryID;
    }

    public String getDetailsimage() {
        return detailsimage;
    }

    public void setDetailsimage(String detailsimage) {
        this.detailsimage = detailsimage;
    }
}