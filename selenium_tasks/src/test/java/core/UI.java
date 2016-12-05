package core;

public class UI {

    public static final String MAIN_MENU_ALL_MAIN_ELEMENTS = "//ul[@id='box-apps-menu']/li";
    public static final String FIRST_ITEM_IN_CAMPAIGNS_GOODS_LIST = "//*[text()='Campaigns']/following-sibling::*//li[1]";
    public static final String CONTAINER_WITH_ITEM_NAME = "//*[@class='name']";
    public static final String OLD_PRICE = "//*[@class='price-wrapper']/s";
    public static final String NEW_PRICE = "//*[@class='price-wrapper']/strong";
    public static final String DETEIL_PAGE_HEADER = "//h1";
    public static final String ADD_NEW_CUSTOMER_BUTTON = "//a[contains(text(), 'Add New Customer')]";
    public static final String EMAIL_ADDRESS_INPUT_ON_ADD_NEW_CUSTOMER_PAGE = "//*[contains(text(), 'Email Address')]/input";
    public static final String FIRST_NAME_INPUT_ON_ADD_NEW_CUSTOMER_PAGE = "//*[contains(text(), 'First Name')]/input";
    public static final String PASSWORD_INPUT_ON_ADD_NEW_CUSTOMER_PAGE = "//*[contains(text(), 'Password')]/input";
    public static final String LOG_OUT_BUTTON_IN_ADMIN = "//a[@title='Logout']";
    public static final String SAVE_BUTTON = "//*[contains(text(),'Save')]";
    public static final String USER_NAME_INPUT_ON_INDEX = "//input[@name='email']";
    public static final String PASSWORD_INPUT_ON_INDEX = "//input[@name='password']";
    public static final String LOG_IN_BUTTON = "//button[text()='Login']";
    public static final String CATALOG_IN_LEFT_MENU = "//*[text()='Catalog']/ancestor::a";
    public static final String ADD_NEW_PRODUCT_BUTTON = "//*[contains(text(),'Add New Product')]";
    public static final String ENABLED_STATUS_RADIO_BUTTON = "//label[contains(text(),'Enabled')]//input";
    public static final String PRODUCT_NAME = "//input[@name='name[en]']";
    public static final String CODE = "//input[@name='code']";
    public static final String RUBBER_DUCKS_CATEGORY = "//*[contains(text(),'Rubber Ducks')]/ancestor::tr[1]//input";
    public static final String PRODUCT_GROUPS_MALE = "//*[contains(text(),'Male')]/ancestor::tr[1]//input";
    public static final String UPLOAD_IMAGES_INPUT = "//input[@name='new_images[]']";
    public static final String DATE_VLID_FROM_INPUT = "[name=\"date_valid_from\"]";
    public static final String DATE_VLID_TO_INPUT = "[name=\"date_valid_to\"]";
    public static final String INFORMATION_TAB = "//a[text()='Information']";
    public static final String MANUFACTURER_SELECT = "//select[@name='manufacturer_id']";
    public static final String KEYWORDS_INPUT = "//*[text()='Keywords']/following-sibling::input";
    public static final String SHORT_DESCRIPTION_INPUT = "//*[text()='Short Description']/following-sibling::*//input";
    public static final String DESCRIPTION_FIELD = "//div[@class='trumbowyg-editor']";
    public static final String HEAD_TITLE_INPUT = "//*[text()='Head Title']/following-sibling::*//input";
    public static final String META_DESCRIPTION_INPUT = "//*[text()='Meta Description']/following-sibling::*//input";
    public static final String PRICES_TAB = "//a[text()='Prices']";
    public static final String QUANTITY = "[name=\"quantity\"]";
    public static final String PURCHASE_PRICE_INPUT = "[name=\"purchase_price\"]";
    public static final String PURCHASE_PRICE_SELECT = "//select[@name='purchase_price_currency_code']";
    public static final String PRICE_INPUT_USD = "[name=\"prices[USD]\"]";
    public static final String PRICE_INPUT_EUR = "[name=\"prices[EUR]\"]";
    public static final String PRICE_INCLU_TAX_USD_INPUT = "[name=\"gross_prices[USD]\"]";
    public static final String PRICE_INCLU_TAX_EUR_INPUT = "[name=\"gross_prices[EUR]\"]";

}
