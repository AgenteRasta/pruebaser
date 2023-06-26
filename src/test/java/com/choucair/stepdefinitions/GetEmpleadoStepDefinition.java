package com.choucair.stepdefinitions;

import com.choucair.setup.ApiSetUp;
import com.choucair.utils.LeerTxt;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.junit.jupiter.api.Assertions;

import java.util.List;
import java.util.logging.Logger;
import static org.hamcrest.CoreMatchers.equalTo;
import static com.choucair.questions.ReturnResponse.returnResponse;
import static com.choucair.tasks.DoGet.doGet;
import static com.choucair.utils.ReqresResources.API_BASE_URL;
import static com.choucair.utils.ReqresResources.GET_EMPLEADO_RESOURCE;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.hamcrest.CoreMatchers.notNullValue;

public class GetEmpleadoStepDefinition extends ApiSetUp {
    public static Logger LOGGER = Logger.getLogger(String.valueOf(GetEmpleadoStepDefinition.class));

    int codigoRespuesta;
    int idIndex;
    JSONObject empleadoJson = null;
    JSONObject resBody = null;
    JSONObject data = null;
    Response actualResponse;
    JSONParser parser = new JSONParser();
    List<String> lines;


    @Given("que estoy apuntando con un endpoint a la api get de empleado de restApiExample")
    public void queEstoyApuntandoConUnEndpointALaApiGetDeEmpleadoDeRestApiExample() {
        try{
            waitForMilliseconds(60000);
            setUp(API_BASE_URL.getValue());
            LOGGER.info("Inicio de la automatizacion");
        }catch (Exception e){
            LOGGER.warning(e.getMessage());
        }
    }

    @When("envio la peticion get con el {int} de un empleado")
    public void envioLaPeticionGetConElDeUnEmpleado(Integer id) {
        idIndex=id;
        try{
            actor.attemptsTo(
                    doGet()
                            .withTheResource(GET_EMPLEADO_RESOURCE.getValue()+String.valueOf(id))
            );
        }catch (Exception e){
            LOGGER.info("FALLO EN AGREGANDO EL RECURSO");
            LOGGER.warning(e.getMessage());
        }
    }

    @Then("recibo un {int} de respuesta")
    public void reciboUnDeRespuesta(Integer code) {
        try{
            LOGGER.info("Inician los asserts");
            codigoRespuesta=code;
            actualResponse=returnResponse().answeredBy(actor);
            actor.should(
                    seeThatResponse("El codigo de respuesta es: "+ actualResponse.getStatusCode(),
                            codigo -> codigo.statusCode(code))
            );
        }catch (Exception e){
            LOGGER.info("Fallo comparando los codigos de respuesta");
            Assertions.fail();
        }
    }

    @Then("un {string} de confirmacion junto con la informacion")
    public void unDeConfirmacionJuntoConLaInformacion(String mensaje) {
        try{
            Thread.sleep(50000);
            lines= LeerTxt.readTextFile("DatosPruebaGet.txt");
            String jsonString=lines.get(idIndex-1);
            empleadoJson=(JSONObject) parser.parse(jsonString);
            resBody=(JSONObject) parser.parse(actualResponse.getBody().asString());
            data=(JSONObject) parser.parse(resBody.get("data").toString());
            actor.should(
                    seeThat("Retorna la informacion",
                            info-> actualResponse,notNullValue()),
                    seeThat("Retorna el mensaje",
                            mssg->resBody.get("message").toString(), equalTo(mensaje)),
                    seeThat("El nombre del empleado es",
                            nombre->data.get("employee_name").toString(),equalTo(empleadoJson.get("employee_name").toString())),
                    seeThat("El id del empleado es",
                            ide->data.get("id").toString(),equalTo(empleadoJson.get("id").toString())),
                    seeThat("El sueldo del empleado es",
                            nombre->data.get("employee_salary").toString(),equalTo(empleadoJson.get("employee_salary").toString())),
                    seeThat("La edad del empleado es",
                            nombre->data.get("employee_age").toString(),equalTo(empleadoJson.get("employee_age").toString()))
            );
        }catch (Exception e){
            Assertions.fail();
        }

    }
}
