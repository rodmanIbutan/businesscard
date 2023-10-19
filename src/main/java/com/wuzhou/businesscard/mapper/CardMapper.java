package com.wuzhou.businesscard.mapper;

import com.wuzhou.businesscard.entity.Card;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CardMapper {
    Integer createCard(Card card);
    Integer deleteCard(@Param("id") Integer id,@Param("userid") Integer userid);
    Integer updateCard(Card card);
    List<Card> getCardListByUserId(@Param("userid") Integer userid);
    Card getCardById(@Param("id") Integer id);
}
