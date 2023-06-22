package haetaekey.com;

public class FFData {
    private String menu;
    private String name;
    private String price;

    FFData() {}

    public FFData(String menu, String name, String price) {
        this.menu = menu;
        this.name = name;
        this.price = price;
    }

    public String getMenu() {
        return menu;
    }

    public void setMenu(String menu) {
        this.menu = menu;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
