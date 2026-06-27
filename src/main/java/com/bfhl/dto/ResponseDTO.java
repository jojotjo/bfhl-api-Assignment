package com.bfhl.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class ResponseDTO {

    @JsonProperty("is_success")
    private boolean success;

    @JsonProperty("user_id")
    private String userId;

    @JsonProperty("email")
    private String email;

    @JsonProperty("roll_number")
    private String rollNumber;

    @JsonProperty("odd_numbers")
    private List<String> oddNumbers;

    @JsonProperty("even_numbers")
    private List<String> evenNumbers;

    @JsonProperty("alphabets")
    private List<String> alphabets;

    @JsonProperty("special_characters")
    private List<String> specialCharacters;

    @JsonProperty("sum")
    private String sum;

    @JsonProperty("concat_string")
    private String concatString;

    // GETTERS
    public boolean isSuccess() { return success; }
    public String getUserId() { return userId; }
    public String getEmail() { return email; }
    public String getRollNumber() { return rollNumber; }
    public List<String> getOddNumbers() { return oddNumbers; }
    public List<String> getEvenNumbers() { return evenNumbers; }
    public List<String> getAlphabets() { return alphabets; }
    public List<String> getSpecialCharacters() { return specialCharacters; }
    public String getSum() { return sum; }
    public String getConcatString() { return concatString; }

    // SETTERS
    public void setSuccess(boolean success) { this.success = success; }
    public void setUserId(String userId) { this.userId = userId; }
    public void setEmail(String email) { this.email = email; }
    public void setRollNumber(String rollNumber) { this.rollNumber = rollNumber; }
    public void setOddNumbers(List<String> oddNumbers) { this.oddNumbers = oddNumbers; }
    public void setEvenNumbers(List<String> evenNumbers) { this.evenNumbers = evenNumbers; }
    public void setAlphabets(List<String> alphabets) { this.alphabets = alphabets; }
    public void setSpecialCharacters(List<String> specialCharacters) { this.specialCharacters = specialCharacters; }
    public void setSum(String sum) { this.sum = sum; }
    public void setConcatString(String concatString) { this.concatString = concatString; }
}