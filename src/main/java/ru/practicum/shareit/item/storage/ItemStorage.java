package ru.practicum.shareit.item.storage;

import ru.practicum.shareit.item.model.Item;

import java.util.List;

public interface ItemStorage {

    Item saveItem(Item item);

    Item updateItem(Item item);

    List<Item> getAllUserItems(int userId);

    Item getItemById(int id);

    List<Item> searchItemByText(String text);

    void deleteItemById(int id);

}
