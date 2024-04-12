# Deuda Tecnica - Tests
Cuando se habla de deuda técnica, no solo se refiere a la calidad del código, sino también a la calidad de los tests. 
Los tests son una parte fundamental del desarrollo de software, ya que permiten asegurar que el código funciona 
correctamente y que no se rompe al realizar cambios. Por lo tanto, es importante mantener los tests actualizados y en 
buen estado.

Este proyecto no posee tests, por lo que se considera que tiene una deuda técnica en este aspecto. Se debería agregar
tests para asegurar que el código funciona correctamente y que no se rompe al realizar cambios. Además, los tests nos
ayudara a documentar el comportamiento del código y a entender mejor su funcionamiento.

## Propuesta
Como se ha mencionado con anterioridad en el [README](README.md) del proyecto, una propuesta para reducir la deuda 
técnica tan grande que posee este proyecto, se recomienda comenzar en primer lugar por un microservicio, por ejemplo el
[RestEndpointsItemSpec](/src/main/java/org/nearbyshops/RESTEndpointsItemSpec). En un primer lugar se separaria este
servicio del resto, luego se crearian tests haciendo uso de **JUnit** y **Mockito** para asegurar que el código
funciona; y una vez que se tenga una buena cobertura de tests para las funcionalidades actuales se realizaria un
refactoring del código para mejorar su calidad.

### Ejemplos
A continuación se presentan algunos ejemplos de tests que se podrían agregar al proyecto:
```java
@Test
public void testGetItemSpecs() {
    // Arrange
    List<ItemSpec> itemSpecs = Arrays.asList(
            new ItemSpec(1, "Size", "Small"),
            new ItemSpec(2, "Size", "Medium"),
            new ItemSpec(3, "Size", "Large")
    );
    when(itemSpecService.getItemSpecs()).thenReturn(itemSpecs);

    // Act
    List<ItemSpec> result = restEndpointsItemSpec.getItemSpecs();

    // Assert
    assertEquals(itemSpecs, result);
}

@Test
public void testGetItemSpec() {
    // Arrange
    ItemSpec itemSpec = new ItemSpec(1, "Size", "Small");
    when(itemSpecService.getItemSpec(1)).thenReturn(itemSpec);

    // Act
    ItemSpec result = restEndpointsItemSpec.getItemSpec(1);

    // Assert
    assertEquals(itemSpec, result);
}

@Test
public void testCreateItemSpec() {
    // Arrange
    ItemSpec itemSpec = new ItemSpec(1, "Size", "Small");
    when(itemSpecService.createItemSpec(itemSpec)).thenReturn(itemSpec);

    // Act
    ItemSpec result = restEndpointsItemSpec.createItemSpec(itemSpec);

    // Assert
    assertEquals(itemSpec, result);
}

@Test
public void testUpdateItemSpec() {
    // Arrange
    ItemSpec itemSpec = new ItemSpec(1, "Size", "Small");
    when(itemSpecService.updateItemSpec(1, itemSpec)).thenReturn(itemSpec);

    // Act
    ItemSpec result = restEndpointsItemSpec.updateItemSpec(1, itemSpec);

    // Assert
    assertEquals(itemSpec, result);
}

@Test
public void testDeleteItemSpec() {
    // Arrange
    ItemSpec itemSpec = new ItemSpec(1, "Size", "Small");
    when(itemSpecService.deleteItemSpec(1)).thenReturn(itemSpec);

    // Act
    ItemSpec result = restEndpointsItemSpec.deleteItemSpec(1);

    // Assert
    assertEquals(itemSpec, result);
}

@Test
public void testGetItemSpecsByItem() {
    // Arrange
    List<ItemSpec> itemSpecs = Arrays.asList(
            new ItemSpec(1, "Size", "Small"),
            new ItemSpec(2, "Size", "Medium"),
            new ItemSpec(3, "Size", "Large")
    );
    when(itemSpecService.getItemSpecsByItem(1)).thenReturn(itemSpecs);

    // Act
    List<ItemSpec> result = restEndpointsItemSpec.getItemSpecsByItem(1);

    // Assert
    assertEquals(itemSpecs, result);
}

@Test
public void testGetItemSpecsByItemCategory() {
    // Arrange
    List<ItemSpec> itemSpecs = Arrays.asList(
            new ItemSpec(1, "Size", "Small"),
            new ItemSpec(2, "Size", "Medium"),
            new ItemSpec(3, "Size", "Large")
    );
    when(itemSpecService.getItemSpecsByItemCategory(1)).thenReturn(itemSpecs);

    // Act
    List<ItemSpec> result = restEndpointsItemSpec.getItemSpecsByItemCategory(1);

    // Assert
    assertEquals(itemSpecs, result);
}

@Test
public void testGetItemSpecsByItemSpecName() {
    // Arrange
    List<ItemSpec> itemSpecs = Arrays.asList(
            new ItemSpec(1, "Size", "Small"),
            new ItemSpec(2, "Size", "Medium"),
            new ItemSpec(3, "Size", "Large")
    );
    when(itemSpecService.getItemSpecsByItemSpecName("Size")).thenReturn(itemSpecs);

    // Act
    List<ItemSpec> result = restEndpointsItemSpec.getItemSpecsByItemSpecName("Size");

    // Assert
    assertEquals(itemSpecs, result);
}

@Test
public void testGetItemSpecsByItemSpecNameAndValue() {
    // Arrange
    List<ItemSpec> itemSpecs = Arrays.asList(
            new ItemSpec(1, "Size", "Small"),
            new ItemSpec(2, "Size", "Medium"),
            new ItemSpec(3, "Size", "Large")
    );
    when(itemSpecService.getItemSpecsByItemSpecNameAndValue("Size", "Small")).thenReturn(itemSpecs);

    // Act
    List<ItemSpec> result = restEndpointsItemSpec.getItemSpecsByItemSpecNameAndValue("Size", "Small");

    // Assert
    assertEquals(itemSpecs, result);
}

@Test
public void testGetItemSpecsByItemSpecNameAndValueAndItemCategory() {
    // Arrange
    List<ItemSpec> itemSpecs = Arrays.asList(
            new ItemSpec(1, "Size", "Small"),
            new ItemSpec(2, "Size", "Medium"),
            new ItemSpec(3, "Size", "Large")
    );
    when(itemSpecService.getItemSpecsByItemSpecNameAndValueAndItemCategory("Size", "Small", 1)).thenReturn(itemSpecs);

    // Act
    List<ItemSpec> result = restEndpointsItemSpec.getItemSpecsByItemSpecNameAndValueAndItemCategory("Size", "Small", 1);

    // Assert
    assertEquals(itemSpecs, result);
}

@Test
public void testGetItemSpecsByItemSpecNameAndValueAndItem() {
    // Arrange
    List<ItemSpec> itemSpecs = Arrays.asList(
            new ItemSpec(1, "Size", "Small"),
            new ItemSpec(2, "Size", "Medium"),
            new ItemSpec(3, "Size", "Large")
    );
    when(itemSpecService.getItemSpecsByItemSpecNameAndValueAndItem("Size", "Small", 1)).thenReturn(itemSpecs);

    // Act
    List<ItemSpec> result = restEndpointsItemSpec.getItemSpecsByItemSpecNameAndValueAndItem("Size", "Small", 1);

    // Assert
    assertEquals(itemSpecs, result);
}
```