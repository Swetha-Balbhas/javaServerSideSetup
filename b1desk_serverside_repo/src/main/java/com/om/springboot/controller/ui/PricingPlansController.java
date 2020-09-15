package com.om.springboot.controller.ui;


import com.om.springboot.dto.response.ApiResponse;
import com.om.springboot.dto.response.user.CurrencyRateResponse;
import com.om.springboot.service.application.ExchangeRate;
import com.om.springboot.util.ErrorConstants;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/b1desk")
public class PricingPlansController {

    @Autowired
    ExchangeRate exchangeRate;

    @CrossOrigin
    @GetMapping("/postLogin/getCurrencyRate/{currencyRate}/{amount}")
    public ResponseEntity<?> getRate(@PathVariable String currencyRate, @PathVariable Double amount) {
        try {
            String rate = exchangeRate.getExchangeRate();
            JSONObject myResponse = new JSONObject(rate);
            //  JSONObject ratesObj = new JSONObject(myResponse.getJSONObject("rates"));
            System.out.println(myResponse);
            String exchangeRateOfEnteredValue = String.format(myResponse.getJSONObject("rates").getString(currencyRate));
            System.out.println("...........> entered current rate is " + currencyRate
                    + "Current amount is" + exchangeRateOfEnteredValue);
            double d1 = Double.parseDouble(exchangeRateOfEnteredValue);
            double value = d1 * amount;
            CurrencyRateResponse currencyRateResponse = new CurrencyRateResponse(true, "")
                    .setCurrency(value);
            return new ResponseEntity(currencyRateResponse, HttpStatus.OK);

        } catch (JSONException pe) {
            System.err.println("JSONException when get currency");
        }
        return new ResponseEntity(new ApiResponse(false, ErrorConstants.E112.name()), HttpStatus.OK);
    }


}
