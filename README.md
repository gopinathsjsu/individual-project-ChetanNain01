# individual-project-ChetanNain01
Name: Chetan Nain.

SJSU Student ID:  015761122

Link to the live application: [Click Here](http://100.26.206.1:8080/StoreApp/)
to go to http://100.26.206.1:8080/StoreApp/

Link to the git repository: [Click Here!](https://github.com/gopinathsjsu/individual-project-ChetanNain01)

## Steps to run the project

Download the project from the git repository.

Inside the individual-project-ChetanNain01 execute the following commands on the command prompt

```bash
mvn-clean-build
```
```bash
mvn tomcat7:run
```
After executing the two commands successfully go to the browser and visit
http://localhost:8080/StoreApp/


## Application Screenshot

The homepage of the application:
![image](https://user-images.githubusercontent.com/91368366/144767981-7f5ca2ec-b8e7-4054-904f-65cb7ca40612.png)

Output on a valid input:


![image](https://user-images.githubusercontent.com/91368366/144767995-64144900-e72f-4257-b50c-5e2b2df51b25.png)


Output on an invalid input:

![image](https://user-images.githubusercontent.com/91368366/144768005-69a91f2d-958c-4383-a70f-432c679be441.png)

Screenshot of the list of Cards:

![image](https://user-images.githubusercontent.com/91368366/144787486-503e16bf-0e23-4fa8-85c9-4646ba3845e3.png)


Screenshot of the application:

![image](https://user-images.githubusercontent.com/91368366/144768064-e2729575-846c-4c15-b076-a5d02d9c3a27.png)


## Design Patterns Used

### Singleton:
I have used a singleton design pattern to make sure that only one card is used for the transaction. The following code demonstrates the implementation of the Singleton Design Pattern. 

```java
public class Cards {
    private static Cards singletonObject = null;
    List<Integer> cardList = new ArrayList<>();

    public List<Integer> getCardList() {
        return cardList;
    }

    public void setCardList(List<Integer> cardList) {
        this.cardList = cardList;
    }

    public static Cards getInstance() {
        if(singletonObject == null) {
            singletonObject = new Cards();
        }
        return singletonObject;
    }
}
```

### Factory Design Pattern

Factory Method provides us an interface for creating objects in a superclass but allows subclasses to to chnage the object. It is one of the best ways to create objects.
Here I have defined the IOrderServletInterface which helps us in defining the properties of the Servlet class that will be inheriting the interface.


```java
public interface IOrderServletInterface {
    public String getTextFromPart(Part part) throws IOException;
    public String isValidRequest(String str[], HttpServletResponse res) throws IOException;
    public double calculateTotalAmountPaid(String requestContent[]);
    public int getCapValue(String categoryName);
}
```

### Chain-of-Responsibility Pattern: 

The chain-of-responsibility design pattern is a behavioral design pattern that consists of command objects and a sequence of processing objects. Each processing object contains logic that defines the types of command objects that it can handle; the rest are passed to the next processing object in the chain. A mechanism also exists for adding new processing objects to the end of this chain. In the code I will be proceeding ahead with the orders only when the stock items of that particular type is available. This is one of the implementation of this design pattern.

## Class Diagram

![image](https://user-images.githubusercontent.com/91368366/144796629-dc000087-d0e9-4268-8e5a-7a26115d748e.png)

