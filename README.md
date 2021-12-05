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

### Abstract Factory

Since Abstract Factory is used to create related objects without specifying their concrete classes. I have used it to create the OrderServlet class.

```java
public interface IOrderServletInterface {
    public String getTextFromPart(Part part) throws IOException;
    public String isValidRequest(String str[], HttpServletResponse res) throws IOException;
    public double calculateTotalAmountPaid(String requestContent[]);
    public int getCapValue(String categoryName);
}
```

### Prototype
Prototype is a creational design pattern that lets you copy existing objects without making your code dependent on their classes.

Defining the object in the IPrototype interface: 
```java
public interface IPrototype {
    public IPrototype getClone();     
}
```
and then in the items.java file 
```java
public IPrototype getClone() {  
        return new Items(); 
    } 
```
and finally in the OrderServlet.java
```java
 public int getCapValue(String categoryName) {
        Items item =(Items) itemsObj.getClone();
        if(categoryName.equalsIgnoreCase("Essential")){
            return item.getEssentialCapValue();
        }else if(categoryName.equalsIgnoreCase("Misc")){
            return item.getMiscCapValue();
        }else if(categoryName.equalsIgnoreCase("Luxury")){
            return item.getLuxaryCapValue();
        }
        return 0;
    }

```
