package com.automation.petclinic.conf;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

public class Configuration {

    private static Configuration instance;

    private final String mainUrl;
    private final String apiUrl;
    private final int apiPort;
    private String browser = System.getProperty("browser", "chrome");

    private Configuration() {
        String schema = System.getProperty("schema", "http");
        String host = System.getProperty("host", "localhost");
        String port = System.getProperty("port", "4200");
        String app = System.getProperty("app", "petclinic");
        mainUrl = schema.concat("://").concat(host).concat(":").concat(port).concat("/").concat(app);

        String apihost = System.getProperty("apihost", "localhost");
        apiPort = Integer.valueOf(System.getProperty("apiport", "9966"));
        apiUrl = schema.concat("://").concat(apihost);
    }

    public static Configuration getInstance() {
        if (instance == null){
            instance = new Configuration();
        }
        return instance;
    }

    public String getMainUrl(){
        return mainUrl;
    }
    public String getApiUrl(){
        return apiUrl;
    }

    public int getApiPort() {
        return apiPort;
    }
//    private static final String MAIN_URL = "http://139.59.149.247:8000/petclinic/";

    public WebDriver getDriver() {
        WebDriver driver;

        switch (browser.toLowerCase()) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                break;
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                driver =  new FirefoxDriver();
                break;
            default:
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
        }

        driver.manage().timeouts().implicitlyWait(4000, TimeUnit.MILLISECONDS);

        return driver;

    }
}
