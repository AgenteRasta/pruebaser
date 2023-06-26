Feature: Eliminar un empleado
  yo como administrador de los servicios rest de restApiExample
  quiero realizar peticiones al servicio delete de eliminar empleado
  para borrar toda la informacion acerca de un empleado

  Scenario Outline: Eliminar empleado
    Given que estoy apuntando con un endpoint a la api delete de empleado de restApiExample
    When envio la peticion delete con el <id> del empleado que deseo eliminar
    Then puedo observar el <codigo> de respuesta
    And un <mensaje> de confirmacion junto con la respuesta del servicio
    Examples:
      | id  | codigo | mensaje                                 |
      | 1   | 200    | "Successfully! Record has been deleted" |
      | 2   | 200    | "Successfully! Record has been deleted" |
      | 100 | 200    | "Successfully! Record has been deleted" |