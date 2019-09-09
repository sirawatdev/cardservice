package com.hackathon.cardservice.service;


import com.hackathon.cardservice.constants.Response;
import com.hackathon.cardservice.exception.CardException;
import com.hackathon.cardservice.model.Cards;
import com.hackathon.cardservice.model.Refs;
import com.hackathon.cardservice.model.cardupdate.AmountCard;
import com.hackathon.cardservice.model.cardupdate.TypeDigitsCard;
import com.hackathon.cardservice.repositories.CardRepository;
import com.hackathon.cardservice.repositories.RefRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import javax.smartcardio.Card;
import javax.validation.constraints.Min;
import java.util.List;

@Service
public class CardService {
    private static final Logger log = LogManager.getLogger(CardService.class.getName());
    private CardRepository cardRepository;
    private RefRepository refRepository;

    @Autowired
    public CardService(CardRepository cardRepository,RefRepository refRepository){
        this.cardRepository = cardRepository;
        this.refRepository = refRepository;
    }

    public List<Cards> getCardByUserId(Long userId) throws CardException{
        if(cardRepository.findAllByUserId(userId).isEmpty()){
            throw new CardException(Response.CARD_NOTFOUND,HttpStatus.BAD_REQUEST);
        }
        else{
            return cardRepository.findAllByUserId(userId);
        }

    }

    public Cards createCard(Cards body){
        return cardRepository.save(body);
    }

    public Cards editCard(@PathVariable @Min(1) Cards body) throws CardException{
        if(cardRepository.findAllById(body.getId()) == null){
            throw new CardException(Response.CARD_NOTFOUND,HttpStatus.BAD_REQUEST);
        }
        else{
            return cardRepository.save(body);
        }
    }

    public Refs createRef(@Min(1) Refs body) throws CardException{
        if(true) {
            return refRepository.save(body);
        }
        else{
            throw new CardException(Response.CARD_NOTFOUND,HttpStatus.BAD_REQUEST);
        }
    }

    public Cards updateAmountCard(AmountCard body) throws CardException{
        //REF86431506191700:51848643
        int colon;
        int length;
        Long cardId;
        Long refCode;

        if(body.getRefCode().contains(":") && body.getRefCode().contains("REF")){
            colon = body.getRefCode().indexOf(":");
            length = body.getRefCode().length();
            refCode = Long.parseLong(body.getRefCode().substring(3, colon));

            cardId = Long.parseLong(body.getRefCode().substring(colon+1, length));
            System.out.println(cardId);

            Refs ref = refRepository.findAllByCardIdAndRefId(cardId,refCode);

            if(ref == null || ref.getStatus() == 1){
                throw new CardException(Response.CARD_NOTFOUND,HttpStatus.BAD_REQUEST);
            }
            else{
                ref.setStatus(1);
            }
        }
        else{
            throw new CardException(Response.CARD_NOTFOUND,HttpStatus.BAD_REQUEST);
        }



        Cards card = cardRepository.findAllById(body.getId());
        if(card==null){
            throw new CardException(Response.CARD_NOTFOUND,HttpStatus.BAD_REQUEST);
        }
        else{
            card.setAmount(body.getAmount());
            return cardRepository.save(card);
        }
    }

    public Cards updateInformationCard(@PathVariable @Min(1) TypeDigitsCard body) throws CardException{

        Cards card = cardRepository.findAllById(body.getId());
        if(card == null){
            throw new CardException(Response.CARD_NOTFOUND,HttpStatus.BAD_REQUEST);
        }
        else{
            card.setDigits(body.getDigits());
            card.setType(body.getType());
            return cardRepository.save(card);
        }
    }

    public void deleteCard(@PathVariable @Min(1) Long id) throws CardException{
        if(cardRepository.findAllById(id) == null){
            throw new CardException(Response.CARD_NOTFOUND,HttpStatus.BAD_REQUEST);
        }
        else{
            cardRepository.deleteById(id);
        }
    }


}
