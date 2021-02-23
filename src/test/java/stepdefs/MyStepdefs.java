package stepdefs;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.lang.Math;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import utils.Person;


import static java.time.temporal.ChronoUnit.SECONDS;
import static org.junit.Assert.assertEquals;
import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

public class MyStepdefs {


    String url = "https://demoqa.com/automation-practice-form";

    WebDriver driver;
    ArrayList<Person> Personas;
    WebElement firstName;
    WebElement lastName;
    WebElement userEmail;
    WebElement gender;
    WebElement userNumber;
    WebElement dob;
    WebElement subject;
    WebElement hobby;
    WebElement address;
    WebElement submit;
    WebElement state;
    WebElement city;
    WebElement closeButton;
    WebElement clearSubject;
    String[] subjectOptions = {"Maths", "Arts", "Social Studies"};
    String[] cities = {"Delhi", "Gurgaon", "Noida"};


    @Before
    public void prepareUsers() {

        System.setProperty("webdriver.gecko.driver", "/Users/alfredobazolopez/Downloads/geckodriver");
        driver = new FirefoxDriver();

        // ArrayList for users.
        Personas = new ArrayList<Person>();

        Person personOne = new Person("Jan", "Dam");
        Personas.add(personOne);
        Person personTwo = new Person("Chack", "Norris");
        Personas.add(personTwo);
        Person personThree = new Person("Klark", "Kent");
        Personas.add(personThree);
        Person personFour = new Person("John", "Daw");
        Personas.add(personFour);
        Person personFive = new Person("Bat", "Man");
        Personas.add(personFive);
        Person personSix = new Person("Tim", "Los");
        Personas.add(personSix);
        Person personSeven = new Person("Dave", "Core");
        Personas.add(personSeven);
        Person personEight = new Person("Pay", "Pal");
        Personas.add(personEight);
        Person personNine = new Person("Lazy", "Cat");
        Personas.add(personNine);
        Person personTen = new Person("Jack", "Johnes");
        Personas.add(personTen);

    }

    @When("user opens the requested webpage")
    public void open_webpage() {
        try {
            driver.get(url);
        } finally {
            System.out.println("URL " + url + " opened");
        }
    }

    @Then("^url should be \"(.*)\"$")
    public void check_url(String urlExpected) {
        try {
            assertEquals(urlExpected, driver.getCurrentUrl());
        } finally {
            System.out.println("URL expected.");
        }
    }

    @When("register a new user randomly")
    public void registerANewUserRandomly() {

        for (int i = 0; i < 5; i++) {

            int randomPerson = (int) (Math.random() * (Personas.size()));

            // Write first name of the user.
            firstName = driver.findElement(By.id("firstName"));
            firstName.clear();
            firstName.sendKeys(Personas.get(randomPerson).getFirstName());

            // Write last name of the user.
            lastName = driver.findElement(By.id("lastName"));
            lastName.clear();
            lastName.sendKeys(Personas.get(randomPerson).getLastName());

            // Write email
            userEmail = driver.findElement(By.id("userEmail"));
            userEmail.clear();
            userEmail.sendKeys("random@example.com");
            new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.textToBePresentInElementValue(userEmail, "random@example.com"));


            // Select gender
            driver.manage().timeouts().implicitlyWait(Duration.of(5, SECONDS));
            String genderSelector = "gender-radio-".concat(String.valueOf((int) (1 + Math.random() * 3)));
            gender = driver.findElement(By.id(genderSelector));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click()", gender);
            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

            // Introduce phone number
            int n = 1000000 + new Random().nextInt(99999999);
            String phoneNumber = String.valueOf(346).concat(String.valueOf(n));
            userNumber = driver.findElement(By.id("userNumber"));
            userNumber.clear();
            userNumber.sendKeys(phoneNumber);

            // Introduce DOB
            // Month could be from 1 to 12.
            String monthOfBirth = String.valueOf((int) (1 + Math.random() * 12));
            // Day could be from 1 to 28
            String dayOfBirth = String.valueOf((int) (1 + Math.random() * 28));
            Random random = new Random();
            // Year from 1940 to 2002.
            String yearOfBirth = String.valueOf(random.nextInt(2002 + 1 - 1950) + 1940);
            dob = driver.findElement(By.id("dateOfBirthInput"));
            String dateOfBirth = yearOfBirth + "/" + monthOfBirth + "/" + dayOfBirth;
            dob.sendKeys(Keys.chord(Keys.COMMAND, "a", Keys.DELETE));
            dob.sendKeys(dateOfBirth);
            dob.sendKeys(Keys.chord(Keys.ENTER));


            // Introduce subject
            subject = driver.findElement(By.id("subjectsInput"));
            String subjectText = subjectOptions[(int) (Math.random() * 2)];
            subject.sendKeys(subjectText);
            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
            subject.sendKeys(Keys.chord(Keys.COMMAND));
            subject.sendKeys(Keys.chord(Keys.TAB));


            // Introduce hobbies
            String hobbySelector = "hobbies-checkbox-".concat(String.valueOf((int) (1 + Math.random() * 3)));
            hobby = driver.findElement(By.id(hobbySelector));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click()", hobby);
            driver.manage().timeouts().implicitlyWait(Duration.of(5, SECONDS));

            // Introduce State
            state = driver.findElement(By.id("react-select-3-input"));
            state.sendKeys("NCR");
            state.sendKeys(Keys.chord(Keys.ENTER));

            // Introduce City
            city = driver.findElement(By.id("react-select-4-input"));
            city.sendKeys(cities[(int) (Math.random() * 3)]);
            city.sendKeys(Keys.chord(Keys.ENTER));

            // Introduce current address
            address = driver.findElement(By.id("currentAddress"));
            address.clear();
            address.sendKeys("Avda Emilio Lemos 19, Sevilla");

            // Click submit button
            submit = driver.findElement(By.id("submit"));
            submit.click();

            // Close button
            closeButton = driver.findElement(By.id("closeLargeModal"));
            closeButton.click();

            // Personas delete
            Personas.remove(randomPerson);

            int personasRemaining = Personas.size();
            System.out.println("\n" + "List of users without already registered ones.");
            for (int j = 0; j < personasRemaining; j++) {
                System.out.println("User: " + j + "\t" + Personas.get(j).getFirstName() + "\t" + Personas.get(j).getLastName());
            }
        }
    }

    @After
    public void deleteUsers() {

        int personasRemaining = Personas.size();
        for (int i = 0; i < personasRemaining; i++) {
            Personas.remove(0);
        }

        try {
            driver.quit();
        } finally {
            System.out.println("Finishing tests.");
        }
    }
}
