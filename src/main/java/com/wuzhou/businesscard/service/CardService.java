package com.wuzhou.businesscard.service;

import com.wuzhou.businesscard.entity.Card;
import com.wuzhou.businesscard.entity.Status;

import java.util.List;

public interface CardService {
    Integer createCard(Card card,String token);
    Status<Integer> deleteCard(Integer id,String token);
    Status<Integer> updateCard(Card card,String token);
    Status<List<Card>> getCardListByUserId(String token);
    Status<Card> getCardById(Integer id);
}
