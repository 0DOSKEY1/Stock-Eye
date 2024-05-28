// package com.stockeye;

// import org.junit.jupiter.api.Test;
// import org.junit.jupiter.api.extension.ExtendWith;
// import org.mockito.InjectMocks;
// import org.mockito.Mock;
// import org.mockito.junit.jupiter.MockitoExtension;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;

// import com.stockeye.controller.ItemController;
// import com.stockeye.model.Item;
// import com.stockeye.service.ItemService;

// import java.util.Arrays;
// import java.util.List;
// import static org.junit.jupiter.api.Assertions.assertEquals;
// import static org.mockito.Mockito.*;

// @ExtendWith(MockitoExtension.class)
// public class ItemControllerTests {

//     @Mock
//     private ItemService itemService;

//     @InjectMocks
//     private ItemController itemController;

//     @Test
//     public void testGetAllItems() {
//         // Arrange
//         Item item1 = new Item();
//         Item item2 = new Item();
//         List<Item> items = Arrays.asList(item1, item2);
//         when(itemService.getAllItems()).thenReturn(items);

//         // Act
//         List<Item> result = itemController.getAllItems();

//         // Assert
//         assertEquals(2, result.size());
//         assertEquals(item1, result.get(0));
//         assertEquals(item2, result.get(1));
//     }

//     @Test
//     public void testGetItemById() {
//         // Arrange
//         long itemId = 1L;
//         Item item = new Item();
//         when(itemService.getItemById(itemId)).thenReturn(item);

//         // Act
//         Item result = itemController.getItemById(itemId);

//         // Assert
//         assertEquals(item, result);
//     }

//     @Test
//     public void testCreateItem() {
//         // Arrange
//         Item item = new Item();
//         when(itemService.createItem(item)).thenReturn(item);

//         // Act
//         Item result = itemController.createItem(item);

//         // Assert
//         assertEquals(item, result);
//     }

//     @Test
//     public void testUpdateItem() {
//         // Arrange
//         Long itemId = 1L;
//         Item item = new Item();
//         when(itemService.updateItem(itemId, item)).thenReturn(item);

//         // Act
//         Item result = itemController.updateItem(itemId, item);

//         // Assert
//         assertEquals(item, result);
//     }

//     @Test
//     public void testDeleteItem() {
//         // Arrange
//         Long itemId = 1L;

//         // Act
//         ResponseEntity<?> responseEntity = itemController.deleteItem(itemId);

//         // Assert
//         assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
//         verify(itemService, times(1)).deleteItem(itemId);
//     }
// }
