package ru.practicum.shareit.item.storage;

import org.springframework.stereotype.Component;
import ru.practicum.shareit.exception.EntityNotFoundException;
import ru.practicum.shareit.item.model.Item;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component("InMemoryItemStorage")
public class InMemoryItemStorage implements ItemStorage {

    private int id = 0;
    private final Map<Integer, Item> items = new HashMap<>();

    @Override
    public Item saveItem(Item item) {
        id++;
        item.setId(id);
        items.put(item.getId(), item);
        return item;
    }

    @Override
    public Item updateItem(Item item) {
        if (items.containsKey(item.getId())) {
            Item updatedItem = items.get(item.getId());
            if ((updatedItem.getOwner().getId()) != (item.getOwner().getId())) {
                throw new EntityNotFoundException("Пользователь с id = " + updatedItem.getOwner().getId()
                        + " не владеет такой вещью!");
            }
            if (item.getAvailable() != null) {
                updatedItem.setAvailable(item.getAvailable());
            }
            if (item.getDescription() != null) {
                updatedItem.setDescription(item.getDescription());
            }
            if (item.getName() != null) {
                updatedItem.setName(item.getName());
            }
            items.put(updatedItem.getId(), updatedItem);
        } else {
            throw new EntityNotFoundException("Вещи с таким id нет в базе!");
        }
        return items.get(item.getId());
    }

    @Override
    public List<Item> getAllUserItems(int userId) {
        return items.values().stream()
                .filter(item -> item.getOwner().getId() == userId).collect(Collectors.toList());
    }

    @Override
    public Item getItemById(int id) {
        if (!items.containsKey(id)) {
            throw new EntityNotFoundException("Вещь не нашлась!");
        } else {
            return items.get(id);
        }
    }

    @Override
    public List<Item> searchItemByText(String text) {
        List<Item> result = new ArrayList<>();
        if (text.isEmpty()) {
            return result;
        }
        for (Item item : items.values()) {
            if ((item.getDescription().toLowerCase().contains(text.toLowerCase()) ||
                    item.getName().toLowerCase().contains(text.toLowerCase()))
                    && item.getAvailable()) {
                result.add(item);
            }
        }
        return result;
    }

    @Override
    public void deleteItemById(int id) {
        if (items.containsKey(id)) {
            items.remove(id);
        } else {
            throw new EntityNotFoundException("Вещь не нашлась, нечего удалять!");
        }
    }
}
