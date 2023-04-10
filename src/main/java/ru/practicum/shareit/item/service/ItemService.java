package ru.practicum.shareit.item.service;

import ru.practicum.shareit.item.dto.ItemDto;

import java.util.List;

public interface ItemService {

    ItemDto saveItem(int userId, ItemDto itemDto);

    ItemDto updateItem(int itemId, int userId, ItemDto itemDto);

    ItemDto getItemById(int id);

    void deleteItemById(int id);

    List<ItemDto> getAllUserItems(int userId);

    List<ItemDto> searchItemByText(String text);
}
