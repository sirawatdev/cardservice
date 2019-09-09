package com.hackathon.cardservice.controller;


import com.hackathon.cardservice.constants.Response;
import com.hackathon.cardservice.exception.CardException;
import com.hackathon.cardservice.model.Cards;
import com.hackathon.cardservice.model.Refs;
import com.hackathon.cardservice.model.cardupdate.AmountCard;
import com.hackathon.cardservice.model.cardupdate.TypeDigitsCard;
import com.hackathon.cardservice.model.response.ResponseModel;
import com.hackathon.cardservice.model.response.StatusModel;
import com.hackathon.cardservice.service.CardService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

@Validated
@RestController
@RequestMapping(path="/card")
public class CardController {
    private static final Logger log = LogManager.getLogger(CardController.class.getName());
    private CardService cardService;

    @Autowired
    public CardController(CardService cardService){
        this.cardService = cardService;
    }

    @PostMapping()
    public HttpEntity<ResponseModel> createCard(@Valid @RequestBody Cards body){
        log.info("From Card Controller do : create card type is ="+body.getType());
        StatusModel status = new StatusModel(Response.SUCCESS_GET.getCode(),
                Response.SUCCESS_GET.getMessage());

        Cards resp = cardService.createCard(body);
        return ResponseEntity.ok(new ResponseModel(status, resp));
    }

    @PostMapping(path = "/ref/create")
    public HttpEntity<ResponseModel> createRef(@Valid @RequestBody Refs body) throws CardException{
        log.info("From Card Controller do : create card type is ="+body.getRefId());
        StatusModel status = new StatusModel(Response.SUCCESS_GET.getCode(),
                Response.SUCCESS_GET.getMessage());

        Refs resp = cardService.createRef(body);
        return ResponseEntity.ok(new ResponseModel(status, resp));
    }


    @GetMapping(path = "/{id}")
    public HttpEntity<ResponseModel> getCardByUserId(@PathVariable @Min(1) Long id) throws CardException {
        log.info("get available card using user id="+id);
        StatusModel status = new StatusModel(Response.SUCCESS_GET.getCode(),
                Response.SUCCESS_GET.getMessage());

        List<Cards> resp = cardService.getCardByUserId(id);

        return ResponseEntity.ok(new ResponseModel(status, resp));
    }

    @PutMapping(path = "/edit/{id}")
    public HttpEntity<ResponseModel> editCardByCardId(@Valid @RequestBody TypeDigitsCard body, @PathVariable @Min(1) Long id) throws CardException {
        log.info("From Card Controller do : create card type is ="+body.getType());
        StatusModel status = new StatusModel(Response.SUCCESS_GET.getCode(),
                Response.SUCCESS_GET.getMessage());
        body.setId(id);
        Cards resp = cardService.updateInformationCard(body);

        return ResponseEntity.ok(new ResponseModel(status, resp));
    }

    @PutMapping(path = "/amount/{id}")
    public HttpEntity<ResponseModel> updateAmountCardByCardId(@Valid @RequestBody AmountCard body, @PathVariable @Min(1) Long id) throws CardException {
        log.info("From Card Controller do : create card type is ="+body.getAmount());
        StatusModel status = new StatusModel(Response.SUCCESS_GET.getCode(),
                Response.SUCCESS_GET.getMessage());
        body.setId(id);
        Cards resp = cardService.updateAmountCard(body);

        return ResponseEntity.ok(new ResponseModel(status, resp));
    }

    @DeleteMapping(path = "/del/{id}")
    public HttpEntity<ResponseModel> deleteCardByCardId(@PathVariable @Min(1) Long id) throws CardException {
        log.info("get available card using user id="+id);
        StatusModel status = new StatusModel(Response.SUCCESS_GET.getCode(),
                Response.SUCCESS_GET.getMessage());

       cardService.deleteCard(id);

        return ResponseEntity.ok(new ResponseModel(status));
    }



}
