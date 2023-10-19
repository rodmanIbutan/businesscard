package com.wuzhou.businesscard.service.Impl;

import com.wuzhou.businesscard.entity.Card;
import com.wuzhou.businesscard.entity.Status;
import com.wuzhou.businesscard.mapper.CardMapper;
import com.wuzhou.businesscard.service.CardService;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.wuzhou.businesscard.utils.JwtUtil.parseJWT;
import static java.lang.Integer.parseInt;

@Service
public class CardServiceImpl implements CardService {
    final
    CardMapper cardMapper;

    public CardServiceImpl(CardMapper cardMapper) {
        this.cardMapper = cardMapper;
    }

    public Integer createCard(Card card, String token) {
        Integer id = parseInt(parseJWT(token).get("id", String.class));
        card.setUserId(id);
        return cardMapper.createCard(card);
    }

    public Status<Integer> deleteCard(Integer id, String token) {
        Integer userId = parseInt(parseJWT(token).get("id", String.class));
        Integer res = cardMapper.deleteCard(id, userId);
        if (res == 0) return new Status<>(false, "删除失败", res);
        return new Status<>(true, "删除成功", res);
    }

    public Status<Integer> updateCard(Card card, String token) {
        Integer userId = parseInt(parseJWT(token).get("id", String.class));
        card.setUserId(userId);
        Integer res = cardMapper.updateCard(card);
        if (res == 0) return new Status<>(false, "更新失败", res);
        return new Status<>(true, "更新成功", res);
    }

    public Status<List<Card>> getCardListByUserId(String token) {
        Integer id = parseInt(parseJWT(token).get("id", String.class));
        return new Status<>(true, "查询成功", cardMapper.getCardListByUserId(id));
    }

    public Status<Card> getCardById(Integer id) {
        return new Status<>(true, "查询成功", cardMapper.getCardById(id));
    }
}
