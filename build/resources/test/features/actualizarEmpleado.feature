Feature: Actualizar un empleado
  yo como administrador de los servicios rest de restApiExample
  quiero realizar peticiones al servicio put de actualizar empleado
  para actualizar toda la informacion acerca de un empleado

  Scenario Outline: Actualizar empleado
    Given que estoy apuntando con un endpoint a la api put de empleado de restApiExample
    When envio la peticion put con el <id> y la informacion del empleado mediante su <index> en los datos de prueba
    Then recibo el <codigo> de respuesta statuscode
    And un <mensaje> de confirmacion junto con la informacion del usuario actualizado
    Examples:
      | index | id | codigo | mensaje                                  |
      | 1     | 1  | 200    | "Successfully! Record has been updated." |
      | 2     | 2  | 200    | "Successfully! Record has been updated." |
      | 3     | 3  | 200    | "Successfully! Record has been updated." |