package austinegwa.comradeshoppingmkt.Model;

/**
 * Created by gwaza on 1/18/2018.
 */

public class Category {
    private String category_imagelink;
    private String category_name;
    private String category_description;

    public Category() {
    }

    public Category(String category_imagelink, String category_name, String category_description) {
        this.category_imagelink = category_imagelink;
        this.category_name = category_name;
        this.category_description = category_description;
    }

    public String getCategory_imagelink() {
        return category_imagelink;
    }

    public void setCategory_imagelink(String category_imagelink) {
        this.category_imagelink = category_imagelink;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public String getCategory_description() {
        return category_description;
    }

    public void setCategory_description(String category_description) {
        this.category_description = category_description;
    }
}