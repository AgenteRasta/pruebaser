Feature: Ver informacion de un empleado
  yo como administrador de los servicios rest de restApiExample
  quiero realizar peticiones al servicio get de ver empleado
  para ver toda la informacion acerca de un empleado

  Scenario Outline: Ver informacion de un empleado
    Given que estoy apuntando con un endpoint a la api get de empleado de restApiExample
    When envio la peticion get con el <id> de un empleado
    Then recibo un <codigo> de respuesta
    And un <mensaje> de confirmacion junto con la informacion
    Examples:
      | id | codigo | mensaje                                  |
      | 1  | 200    | "Successfully! Record has been fetched." |
      | 2  | 200    | "Successfully! Record has been fetched." |
      | 3  | 200    | "Successfully! Record has been fetched." |
