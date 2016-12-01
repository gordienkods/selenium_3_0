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
    public static final String SAVE_BUTTON_ON_ADD_NEW_CUSTOMER_PAGE = "//*[contains(text(),'Save')]";
    public static final String USER_NAME_INPUT_ON_INDEX = "//input[@name='email']";
    public static final String PASSWORD_INPUT_ON_INDEX = "//input[@name='password']";
    public static final String LOG_IN_BUTTON = "//button[text()='Login']";
}
