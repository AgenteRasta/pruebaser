Feature: Crear un empleado
  yo como administrador de los servicios rest de restApiExample
  quiero realizar peticiones al servicio post de crear empleado
  para registrar toda la informacion acerca de un empleado

  Scenario Outline: Crear empleado
    Given que estoy apuntando con un endpoint a la api post de empleado de restApiExample
    When envio la peticion post con la informacion del empleado segun su <index>
    Then recibo el <codigo> de respuesta
    And un <mensaje> de confirmacion junto con la informacion del usuario creado
    Examples:
      | index | codigo | mensaje                                |
      | 1     | 200    | "Successfully! Record has been added." |
      | 2     | 200    | "Successfully! Record has been added." |
      | 3     | 200    | "Successfully! Record has been added." |