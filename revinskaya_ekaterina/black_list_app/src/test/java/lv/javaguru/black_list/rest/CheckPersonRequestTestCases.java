package lv.javaguru.black_list.rest;

import org.junit.jupiter.api.Test;

public class CheckPersonRequestTestCases extends CheckPersonBlackListControllerTest{
    @Test
    public void requestWithNullFirstNameTest() throws Exception {
        equalsJsonFiles("nullFirstNameRequest");
    }
    @Test
    public void requestWithEmptyFirstNameTest() throws Exception {
        equalsJsonFiles("emptyFirstNameRequest");
    }
    @Test
    public void requestWithNullLastNameTest() throws Exception {
        equalsJsonFiles("nullLastNameRequest");
    }
    @Test
    public void requestWithEmptyLastNameTest() throws Exception {
        equalsJsonFiles("emptyLastNameRequest");
    }
    @Test
    public void requestWithNullPersonalCodeTest() throws Exception {
        equalsJsonFiles("nullPersonalCodeRequest");
    }
    @Test
    public void requestWithEmptyPersonalCodeTest() throws Exception {
        equalsJsonFiles("emptyPersonalCodeRequest");
    }

    @Test
    public void requestPersonNotPresentInListTest() throws Exception {
        equalsJsonFiles("personNotPresentInList");
    }
    @Test
    public void requestPersonPresentInListTest() throws Exception {
        equalsJsonFiles("personPresentInList");
    }
}
