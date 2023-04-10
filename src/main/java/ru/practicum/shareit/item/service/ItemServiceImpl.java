package ru.practicum.shareit.item.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.mapper.ItemMapper;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.item.storage.ItemStorage;
import ru.practicum.shareit.user.storage.UserStorage;

import java.util.ArrayList;
import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {

    private final UserStorage userStorage;
    private final ItemStorage itemStorage;

    public ItemServiceImpl(@Qualifier("InMemoryUserStorage") UserStorage userStorage,
                           @Qualifier("InMemoryItemStorage") ItemStorage itemStorage) {
        this.userStorage = userStorage;
        this.itemStorage = itemStorage;
    }

    @Override
    public ItemDto saveItem(int userId, ItemDto itemDto) {
        Item savedItem = ItemMapper.dtoToItem(itemDto);
        savedItem.setOwner(userStorage.getUserById(userId));
        return ItemMapper.itemToDto(itemStorage.saveItem(savedItem));
    }

    @Override
    public ItemDto updateItem(int itemId, int userId, ItemDto itemDto) {
        Item itemToUpdate = ItemMapper.dtoToItem(itemDto);
        itemToUpdate.setId(itemId);
        itemToUpdate.setOwner(userStorage.getUserById(userId));
        itemStorage.updateItem(itemToUpdate);
        return ItemMapper.itemToDto(itemStorage.getItemById(itemId));
    }

    @Override
    public ItemDto getItemById(int id) {
        return ItemMapper.itemToDto(itemStorage.getItemById(id));
    }

    @Override
    public void deleteItemById(int id) {
        itemStorage.deleteItemById(id);
    }

    @Override
    public List<ItemDto> getAllUserItems(int userId) {
        List<ItemDto> result = new ArrayList<>();
        for (Item item : itemStorage.getAllUserItems(userId)) {
            result.add(ItemMapper.itemToDto(item));
        }
        return result;
    }

    @Override
    public List<ItemDto> searchItemByText(String text) {
        List<ItemDto> result = new ArrayList<>();
        for (Item item : itemStorage.searchItemByText(text)) {
            result.add(ItemMapper.itemToDto(item));
        }
        return result;
    }
}
