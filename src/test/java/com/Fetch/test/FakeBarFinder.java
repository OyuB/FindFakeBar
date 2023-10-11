package com.Fetch.test;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;
import java.util.List;

public class FakeBarFinder {

    public static void main(String[] args) throws InterruptedException {

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.get("http://sdetchallenge.fetch.com/");


        WebElement leftBowl1 = driver.findElement(By.id("left_0"));
        WebElement rightBowl1 = driver.findElement(By.id("right_0"));

        WebElement weighButton = driver.findElement(By.id("weigh"));

        WebElement resultButton = driver.findElement(By.xpath("//div[@class='result']//button"));

        WebElement resetButton = driver.findElement(By.xpath("//button[.=\"Reset\"]"));

        List<WebElement> goldBars = driver.findElements(By.xpath("//div[@class='coins']/button"));




        for (int i = 1; i < goldBars.size(); i++) {

            rightBowl1.sendKeys(goldBars.get(i).getText());
            leftBowl1.sendKeys(goldBars.get(0).getText());

            weighButton.click();

            String fakeBarIndex = "";

            String result = resultButton.getText();


            if (result.equals("=")) {

                resetButton.click();
                Thread.sleep(2000);
                i++;
            } else if (result.equals(">")) {
                fakeBarIndex = rightBowl1.getAttribute("value");
                System.out.println(fakeBarIndex);

            } else if (result.equals("<")) {
                fakeBarIndex = leftBowl1.getAttribute("value");
            }

            WebElement fakeGoldBar = driver.findElement(By.xpath("//button[@id='coin_" + fakeBarIndex + "']"));



            fakeGoldBar.click();
        }
        List<WebElement> weighings = driver.findElements(By.tagName("li"));

        System.out.println("Number of Weighings: " + weighings.size());
        System.out.println("List of Weighings:");

        for (WebElement weighing : weighings) {
            System.out.println(weighing.getText());
        }


        driver.close();


    }
}

