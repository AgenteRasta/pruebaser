package com.choucair.stepdefinitions;

import com.choucair.setup.ApiSetUp;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.junit.jupiter.api.Assertions;
import java.util.logging.Logger;
import static com.choucair.questions.ReturnResponse.returnResponse;
import static com.choucair.tasks.DoDelete.doDelete;
import static com.choucair.utils.ReqresResources.*;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;

public class DelEmpleadoStepDefinition extends ApiSetUp {
    public static Logger LOGGER = Logger.getLogger(String.valueOf(DelEmpleadoStepDefinition.class));
    int codigoRespuesta;
    int idAux;
    JSONObject resBody = null;
    Response actualResponse;
    JSONParser parser = new JSONParser();
    @Given("que estoy apuntando con un endpoint a la api delete de empleado de restApiExample")
    public void queEstoyApuntandoConUnEndpointALaApiDeleteDeEmpleadoDeRestApiExample() {
        try{
            setUp(API_BASE_URL.getValue());
            LOGGER.info("Inicio de la automatizacion");
        }catch (Exception e){
            LOGGER.warning(e.getMessage());
        }
    }

    @When("envio la peticion delete con el {int} del empleado que deseo eliminar")
    public void envioLaPeticionDeleteConElDelEmpleadoQueDeseoEliminar(Integer id) {
        try{
            idAux=id;
            actor.attemptsTo(
                    doDelete()
                            .withTheResource(DELETE_EMPLEADO_RESOURCE.getValue()+String.valueOf(id))
            );
        }catch (Exception e){
            LOGGER.info("FALLO EN AGREGANDO EL RECURSO");
            LOGGER.warning(e.getMessage());
        }
    }

    @Then("puedo observar el {int} de respuesta")
    public void puedoObservarElDeRespuesta(Integer code) {
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

    @Then("un {string} de confirmacion junto con la respuesta del servicio")
    public void unDeConfirmacionJuntoConLaRespuestaDelServicio(String mensaje) {
        try {
            resBody=(JSONObject) parser.parse(actualResponse.getBody().asString());
            actor.should(
                    seeThat("Retorna la informacion",
                            info-> actualResponse,notNullValue()),
                    seeThat("Se borro el usuario con id",
                            del->resBody.get("data").toString(),equalTo(String.valueOf(idAux))),
                    seeThat("Se obtiene el mensaje",
                            msj->resBody.get("message").toString(),equalTo(mensaje))
            );
        }catch (Exception e){
            LOGGER.info("Fallo en los asserts");
            Assertions.fail();
        }
    }
}
