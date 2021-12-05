package com.sample.interfaces;
import javax.servlet.http.Part;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

public interface IOrderServletInterface {
    public String getTextFromPart(Part part) throws IOException;
    public String isValidRequest(String str[], HttpServletResponse res) throws IOException;
    public double calculateTotalAmountPaid(String requestContent[]);
    public int getCapValue(String categoryName);
}
