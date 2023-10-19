package com.wuzhou.businesscard.controller;

import com.wuzhou.businesscard.entity.Card;
import com.wuzhou.businesscard.entity.Status;
import com.wuzhou.businesscard.service.CardService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "card")
public class CardController {
    final
    CardService cardService;

    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

    @PostMapping(value = "/create")
    @ResponseBody
    public Integer createCard(@RequestBody Card card, @RequestHeader("token") String token) {
        return cardService.createCard(card,token);
    }
    @GetMapping(value = "/delete")
    @ResponseBody
    public Status<Integer> deleteCard(@RequestParam Integer id,@RequestHeader("token") String token) {
        return cardService.deleteCard(id,token);
    }
    @PostMapping(value = "/update")
    @ResponseBody
    public Status<Integer> updateCard(@RequestBody Card card,@RequestHeader("token") String token) {
        return cardService.updateCard(card,token);
    }
    @GetMapping(value = "/getListById")
    @ResponseBody
    public Status<List<Card>> getCardListByUserId(@RequestHeader("token") String token) {
        return cardService.getCardListByUserId(token);
    }
    @GetMapping(value = "/getById")
    @ResponseBody
    public Status<Card> getCardById(@RequestParam Integer id) {
        return cardService.getCardById(id);
    }
}
