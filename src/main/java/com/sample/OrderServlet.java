package com.sample;
import com.sample.model.Cards;
import com.sample.model.Item;
import com.sample.model.Items;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.HashMap;
import java.util.List;

import com.sample.interfaces.IOrderServletInterface;

@WebServlet(
        name = "OrderServlet",
        urlPatterns = "/order"
)
@MultipartConfig
public class OrderServlet extends HttpServlet implements IOrderServletInterface {
    private static final int BUFFER_SIZE = 2048;
    Items itemsObj = new Items();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/plain");
        resp.setHeader("content-Disposition", "attachment; filename=\"output.txt\"");
        String[] requestContent = getTextFromPart(req.getPart("file")).split("\n");
        try {
            OutputStream outputStream = resp.getOutputStream();
            String outputTxt;
            String invalidRequestStr = isValidRequest(requestContent, resp);
            if (invalidRequestStr.length() == 0) {
                Cards cards = new Cards();
                for(int i = 1; i < requestContent.length; i++ ){
                    if(!cards.getCardList().contains(getPaymentCardNumber(requestContent[i]))){
                        cards.getCardList().add(getPaymentCardNumber(requestContent[i]));
                        cards.setCardList(cards.getCardList());
                        List<Integer> cardList = cards.getCardList();
                        for(int j=0;j<cardList.size();j++){
                            System.out.println(cardList.get(j));
                        }
                    }
                }
                
                double totalAmountPaid = calculateTotalAmountPaid(requestContent);
                outputTxt = "Total amount paid: \n "+ totalAmountPaid;
            } else {
                outputTxt = "Please correct the following quantities\n"+ invalidRequestStr;
            }
            outputStream.write(outputTxt.getBytes(StandardCharsets.UTF_8));
            outputStream.flush();
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getTextFromPart(Part part) throws IOException {
        BufferedReader reader =
                new BufferedReader(new InputStreamReader(part.getInputStream(), "UTF-8"));
        StringBuilder value = new StringBuilder();
        char[] buffer = new char[BUFFER_SIZE];
        for (int length = 0; (length = reader.read(buffer)) > 0; ) {
            value.append(buffer, 0, length);
        }
        return value.toString();
    }

    @Override
    public String isValidRequest(String[] requestContent, HttpServletResponse resp) throws IOException {
        String invalidRequestString = "";
        Items items =(Items) itemsObj.getClone();  
        HashMap<String, Integer> categoryCountMap = new HashMap<>();
        for(int i = 1; i < requestContent.length; i++){
            String orderedItemName = getItemName(requestContent[i]);
            int orderedItemQuantity = getItemQuantity(requestContent[i]);
            String category = items.getCategory(orderedItemName);
            int val = categoryCountMap.getOrDefault(category, 0)  + orderedItemQuantity;
            if(val > getCapValue(category)){
                System.out.println("Cap Value =" + getCapValue(category));
                System.out.println("Category =" + category);
                invalidRequestString = "Luxuary can be a max. of 3, Essential can be a max. of 5 \n and Misc can be a max. of 6.";
            }
            categoryCountMap.put(category, categoryCountMap.getOrDefault(category, 0) + orderedItemQuantity);
            System.out.println(categoryCountMap);
            for(Map.Entry<String, Item> set: items.itemMap.entrySet()){
                if(set.getKey().equalsIgnoreCase(orderedItemName)){
                    if(set.getValue().getAvailableQuantity() < orderedItemQuantity){
                        invalidRequestString += requestContent[i]+"\n";
                    }
                }

            }
        }
        return invalidRequestString;
    }

    @Override
    public double calculateTotalAmountPaid(String requestContent[]){
        double amountPaid = 0;
        Items items =(Items) itemsObj.getClone();
        for(int i = 1; i < requestContent.length; i++ ){
            String orderedItemName = getItemName(requestContent[i]);
            int orderedItemQuantity = getItemQuantity(requestContent[i]);
            for(Map.Entry<String, Item> set: items.itemMap.entrySet()){
                if(set.getKey().equalsIgnoreCase(orderedItemName)){
                        amountPaid += set.getValue().getPrice() * orderedItemQuantity;
                }

            }
        }
        return amountPaid;
    }

    private int getItemQuantity(String orderStr) {
        return Integer.parseInt(orderStr.split(",")[1]);
    }

    private String getItemName(String orderStr) {
        return orderStr.split(",")[0];
    }

    private Integer getPaymentCardNumber(String orderStr) {
        return Integer.getInteger(orderStr.split(",")[2]);
    }

    @Override
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
}
