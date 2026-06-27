package com.bfhl.service;

import com.bfhl.dto.RequestDTO;
import com.bfhl.dto.ResponseDTO;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class BfhlServiceImpl implements BfhlService {

    // ✏️ FILL IN YOUR DETAILS
    private static final String FULL_NAME = "prabhjot_kaur";       // lowercase
    private static final String DOB       = "15092004";            // e.g. "01012004"
    private static final String EMAIL     = "prabhjot1254.be23@chitkara.edu.in";      // your email
    private static final String ROLL      = "2310991254";    // your roll number

    @Override
    public ResponseDTO processData(RequestDTO request) {

        // Null check
        if (request == null || request.getData() == null) {
            ResponseDTO err = new ResponseDTO();
            err.setSuccess(false);
            return err;
        }

        List<String> data     = request.getData();
        List<String> evens    = new ArrayList<>();
        List<String> odds     = new ArrayList<>();
        List<String> alphas   = new ArrayList<>();
        List<String> specials = new ArrayList<>();
        long sum = 0;
        StringBuilder alphaCombined = new StringBuilder();

        for (String item : data) {
            if (isNumber(item)) {
                long num = Long.parseLong(item);
                sum += num;
                if (num % 2 == 0) evens.add(item);
                else               odds.add(item);
            } else if (isAlpha(item)) {
                alphas.add(item.toUpperCase());
                alphaCombined.append(item.toUpperCase());
            } else {
                specials.add(item);
            }
        }

        // Reverse then alternating caps
        String reversed = alphaCombined.reverse().toString();
        StringBuilder concat = new StringBuilder();
        for (int i = 0; i < reversed.length(); i++) {
            char c = reversed.charAt(i);
            concat.append(i % 2 == 0 ? Character.toUpperCase(c)
                    : Character.toLowerCase(c));
        }

        ResponseDTO resp = new ResponseDTO();
        resp.setSuccess(true);
        resp.setUserId(FULL_NAME + "_" + DOB);
        resp.setEmail(EMAIL);
        resp.setRollNumber(ROLL);
        resp.setEvenNumbers(evens);
        resp.setOddNumbers(odds);
        resp.setAlphabets(alphas);
        resp.setSpecialCharacters(specials);
        resp.setSum(String.valueOf(sum));
        resp.setConcatString(concat.toString());
        return resp;
    }

    private boolean isNumber(String s) {
        try { Long.parseLong(s); return true; }
        catch (NumberFormatException e) { return false; }
    }

    private boolean isAlpha(String s) {
        return s != null && !s.isEmpty() && s.chars().allMatch(Character::isLetter);
    }
}