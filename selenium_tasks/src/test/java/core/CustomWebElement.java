package core;

/**
 * Created by Dimon on 28.11.2016.
 */
public class CustomWebElement {

    private Type type;
    private String locator;

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getLocator() {
        return locator;
    }

    public void setLocator(String locator) {
        this.locator = locator;
    }
}
