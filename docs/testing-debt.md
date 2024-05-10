# 🎯 Testing Debt
Este proyecto no posee tests, por lo que se considera que tiene una deuda técnica en este 
aspecto. Se debería agregar tests para asegurar que el código funciona correctamente y que no 
se rompe al realizar cambios. Además, los tests nos ayudara a documentar el comportamiento 
del código y a entender mejor su funcionamiento.

## 🤔 Propuesta
Una propuesta para reducir la deuda técnica tan grande que posee este proyecto, se recomienda 
comenzar en primer lugar por un microservicio, por ejemplo el
[RestEndpointsItemSpec](/src/main/java/org/nearbyshops/RESTEndpointsItemSpec). En un primer 
lugar se separaria este servicio del resto, luego se crearian tests haciendo uso de **JUnit** 
y **Mockito** para asegurar que el código funciona; y una vez que se tenga una buena cobertura 
de tests para las funcionalidades actuales se realizaria un refactoring del código para 
mejorar su calidad.

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
```

```java
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
```

```java
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
```

```java
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
```
[🏠](./index.html) | [🔙](./testing-debt.html) | [🔜](./static-analysis.html)
