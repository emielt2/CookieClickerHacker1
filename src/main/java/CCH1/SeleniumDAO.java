package CCH1;
/*WAS import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import static org.junit.Assert.fail;
*/

import com.thoughtworks.selenium.webdriven.commands.Click;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeDriver;
//import org.openga.selenium.
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.soap.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMResult;
import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.fail;

/**
 * Created by E on 29/12/2015.
 */
public class SeleniumDAO {
    public SeleniumDAO(String input){
        baseUrl = input;
    }
    static WebDriver driver;// idee voor global
    static String baseUrl; //idee voor global
    //private static WebDriver driver;
   // private static String baseUrl;
    private boolean acceptNextAlert = true;
    private static StringBuffer verificationErrors = new StringBuffer();



    public static void startSeleniumConnection() throws Exception {
        System.out.println("Check1 " + baseUrl);
        //driver = new FirefoxDriver();
        System.setProperty("webdriver.chrome.driver", "F:\\SeleniumDownloadFolder\\chromedriver_win32\\chromedriver.exe");
        ChromeOptions chromeoptions = new ChromeOptions();
        //chromeoptions.addArguments("start-maximized");
        //options.addArguments("user-data-dir=/path/to/your/custom/profile");
        chromeoptions.addArguments("user-data-dir=Y:\\Browser_profile");
        driver = new ChromeDriver(chromeoptions);
        //driver = new ChromeDriver();
        System.out.println("Check1");
        //File file = new File("C:/EmielUserDATA/H-DISK/ALL_JAVA_SELENIUM/JAR_LIB/IEDriverServer_x64_2.45.0/IEDriverServer.exe");
        //System.setProperty("webdriver.ie.driver", file.getAbsolutePath());
        //WebDriver driver = new InternetExplorerDriver();
        //driver = new InternetExplorerDriver();
        System.out.println("Check2");
       // baseUrl = "https://www.google.nl/";
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        System.out.println("Check3");
        //driver.get(baseUrl + "/?gws_rd=ssl");
        driver.get(baseUrl /*+ inputURL*/);
    }
    public static void stopSeleniumConnection() throws Exception {
        driver.findElement(By.id("prefsButton")).click();
        driver.findElement(By.linkText("Save")).click();
        Thread.sleep(1000);
        driver.close();
        Thread.sleep(500);
        driver.quit();
        String verificationErrorString = verificationErrors.toString();
        if (!"".equals(verificationErrorString)) {
            fail(verificationErrorString);
        }
    }

    public void multiClickCookie() {
        for(int i=0;i<300;i++){
            driver.findElement(By.id("bigCookie")).click();
            //if(i%1==0)System.out.println(i);
        }

    }

    static int amountProds=0;
    static double startCPS[]={0.5,4,10,40,100,400,6666,98765,999999,10000000};
    public void bestBuyProd() {

        String productPriceName[] = new String[10];
        String productCodeName[] = new String[10];
        String productTitle[] = new String[10];
        //String productNNmae
        long productPrice[] = new long[10];
        double productCPS[] = new double[10];
        double productCPSperPrize[] = new double[10];
        long productROI[] = new long[10];

        List<Long> results = new ArrayList<>();
        //for(Integer i=0;i<10;i++){
        for(Integer i=0;i<amountProds;i++){
            Integer j = i+1;
            String CSSProductPrice = new String("#productPrice");
            String CSSProductName = new String("productName");
            CSSProductPrice = CSSProductPrice.concat(j.toString());
            CSSProductName = CSSProductName.concat(j.toString());
            System.out.println("CSSProductPrice="+ CSSProductPrice);

            productPriceName[i]=CSSProductPrice;
            productCodeName[i]=CSSProductName;
            System.out.println("productCodeName[i]="+ productCodeName[i]);
            Actions builder = new Actions(driver);
            try{

                productPrice[i] = Long.parseLong(driver.findElement(new By.ByCssSelector(CSSProductPrice)).getText().replace(",",""));
                builder.moveToElement(driver.findElement(By.id(productCodeName[i]))).perform();
                //builder.moveToElement(driver.findElement(By.id("productName1"))).perform();
                Thread.sleep(200);
                System.out.println(startCPS[i]);
                if(productPrice[i]!=-1 && i<amountProds) productCPS[i]= Double.parseDouble(driver.findElement(new By.ByCssSelector("#tooltip > div > div.data > b:nth-child(1)")).getText().replace(",",""));
                else productCPS[i]= startCPS[i];

                long price = productPrice[i];
                productCPSperPrize[i] = productCPS[i];
                productCPSperPrize[i] /= productPrice[i];
                productROI[i]=productPrice[i];
                productROI[i]/=productCPS[i];
                /*if(productCPS[i]==0){
                    amountProds=i;
                    System.out.println("amountProds!!! = "+amountProds);
                    break;
                }*/
                //results.add(productCPSperPrize[i]);
                results.add(productROI[i]);
            }
            catch (NumberFormatException e){
                productPrice[i]=-1;
                e.printStackTrace();
            }
            catch (org.openqa.selenium.StaleElementReferenceException e){
                System.out.println("S-t-a-l-e");
                i--;
            }
            catch (Exception e){
                e.printStackTrace();

            }

            //productPrice[i] = Long.parseLong("1234");
        }

        for(Integer i=0;i<amountProds;i++){

/*
            long price = productPrice[i];
            productCPSperPrize[i] = productCPS[i];
            productCPSperPrize[i] /= productPrice[i];
            //            results.add(productCPSperPrize[i]);
            */

            String producttitlecss = "#productName" + (i+1);
            productTitle[i]=driver.findElement(new By.ByCssSelector(producttitlecss)).getText();
            System.out.print(
                    " array["+i+"]=" +
                    " Titel=" + productTitle[i] +
                    " Name: "+ productPriceName[i] +
                    " Prize: " + productPrice[i] +
                    " CPS: " + productCPS[i] +
                    " ROItijd: " + productROI[i] );

            System.out.println(" " + "cps/price= " + productCPSperPrize[i] );



        }
        System.out.println("Quickest is " + Collections.min(results));
        //System.out.println("Loser is " + Collections.max(results));
        System.out.println("array[x] winner is " + results.indexOf(Collections.min(results)));
        System.out.println("Name of quickest is: " + productTitle[results.indexOf(Collections.min(results))]);
        driver.findElement(By.id(productCodeName[results.indexOf(Collections.min(results))])).click(); //buys best
        System.out.println("results.indexOf(Collections.min(results)="  + results.indexOf(Collections.min(results)));
        System.out.println("amountProds="  + amountProds);
        String owned = "productOwned";
        Integer ownedx = amountProds;
        owned = owned.concat(ownedx.toString());
        if(results.indexOf(
                Collections.min(results))+1==amountProds &&
                amountProds<10 &&
                driver.findElement(By.id(owned)).isDisplayed()
                ){
            System.out.println("Amount of PRODS INCREASED! New Value = " + amountProds++);


            ;
        }
        /*while(results.contains(Collections.max(results)))
        {
            int i=results.indexOf(Collections.max(results));
            //System.out.println("the index of 6:"+results.indexOf(Collections.max(results)));
            //results.set(i, -1);
            System.out.println(i);
        }
        */
        // driver.findElement(By.id("productName1")).click(); //koopt oma


/*PERFECT VOOR MOUSEHOVER DATA READ
        Actions builder = new Actions(driver);
        //builder.moveToElement(hoverElement).perform();
        try {
            builder.moveToElement(driver.findElement(By.id("productName1"))).perform();
            String y2= driver.findElement(new By.ByCssSelector("#tooltip > div > div.data > b:nth-child(1)")).getText();
            System.out.println("y2 = " + y2);
            //double x =
            //productCPS[1]=x;
        } catch (Exception e) {
            e.printStackTrace();
        }
        //By locator = By.id("clickElementID");
        //driver.click(locator);

        //String yy = driver.findElement(new By.ByCssSelector("#productPrice2")).getText();
        //String yy = driver.findElement(new By.ByCssSelector(CSSx)).getText();
        //System.out.println(yy);
*/

    }

    public void buyNextItem() {

        try {
            //todo driver.findElement(By.id("upgrade0")).getAttribute(color);
            driver.findElement(By.id("upgrade0")).click();
            Actions builder = new Actions(driver);
            builder.moveToElement(driver.findElement(By.id("upgrade0"))).perform();
        }
        catch (org.openqa.selenium.StaleElementReferenceException e){
            System.out.println("S-t-a-l-e-ItemBuy!");
        }
        catch (Exception e){

        }
    }

    public boolean trigIsSelected() {
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        try{
            if(driver.findElement(By.id("menu")).isDisplayed())return true;
            if(driver.findElement(By.id("menu")).getText()!="Statistics")return false;
        }
        catch(Exception e){
        System.out.println("#menu not found");
           // e.printStackTrace();
            return false;
        }
        //if(isElementPresent( cssSelector("Statistics")))return false;

//todo

        return true;
    }
}
/*
class ResultObj{
    String name;
    double results
}
*/